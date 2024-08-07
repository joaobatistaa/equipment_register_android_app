package com.android.inventariocmrm;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class add_equip extends AppCompatActivity {

    public static EditText nserie;
    public TextView lbl_agr;
    ArrayAdapter<String> adapter, adapter2, adapter3, adapter4;
    ArrayList<String> listaDados=new ArrayList<>();
    ArrayList<String> listaDados2=new ArrayList<>();
    ArrayList<String> listaDados3=new ArrayList<>();
    ArrayList<String> listaDados4=new ArrayList<>();
    ImageView btn_barcode;
    Button btn_adicionar;
    String agrupamento, escola, edificio, tipo, marca, modelo, n_serie, npatrimonio, sala, estado;
    String api_key = "cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL";
    String adicionar_equip = "http://opcm.pt/cmrm_inventario/api/addequipamentos/";
    String get_equip = "http://opcm.pt/cmrm_inventario/api/getequipamentos/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL";
    String get_agrupamentos = "http://opcm.pt/cmrm_inventario/api/getagrupamentos/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL";
    String get_escolas = "http://opcm.pt/cmrm_inventario/api/getescolas/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL";
    String get_edificios = "http://opcm.pt/cmrm_inventario/api/getedificios/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL";
    String get_estados = "http://opcm.pt/cmrm_inventario/api/getestados/?api_key=cda11v2OkqSI1rhQm37PBXKnpisMtlaDzoc4w0U6uNATgZRbJL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_equipamentos);

        nserie = (EditText) findViewById(R.id.nserie);
        btn_barcode = (ImageView) findViewById(R.id.btn_barcode);

        final Spinner agr = (Spinner) findViewById(R.id.agrupamentos);
        final Spinner esc = (Spinner) findViewById(R.id.escolas);
        final Spinner edi = (Spinner) findViewById(R.id.edificios);
        final EditText edt_tipo = (EditText) findViewById(R.id.tipo);
        final EditText edt_marca = (EditText) findViewById(R.id.marca);
        final EditText edt_modelo = (EditText) findViewById(R.id.modelo);
        final EditText edt_nserie = (EditText) findViewById(R.id.nserie);
        final EditText edt_npatrimonio = (EditText) findViewById(R.id.npatrimonio);
        final EditText edt_sala = (EditText) findViewById(R.id.sala);
        final Spinner est = (Spinner) findViewById(R.id.estados);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDados);
        agr.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDados2);
        esc.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDados3);
        edi.setAdapter(adapter3);

        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaDados4);
        est.setAdapter(adapter4);

        btn_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });

        btn_adicionar = (Button) findViewById(R.id.btn_adicionar);
        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_tipo.getText().toString().equals("")) {
                    edt_tipo.setText("N/A");
                }

                if (edt_marca.getText().toString().equals("")) {
                    edt_marca.setText("N/A");
                }

                if (edt_modelo.getText().toString().equals("")) {
                    edt_modelo.setText("N/A");
                }

                if (edt_nserie.getText().toString().equals("")) {
                    edt_nserie.setText("N/A");
                }

                if (edt_npatrimonio.getText().toString().equals("")) {
                    edt_npatrimonio.setText("N/A");
                }

                if (edt_sala.getText().toString().equals("")) {
                    edt_sala.setText("N/A");
                }

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse(adicionar_equip).newBuilder();
                    urlBuilder.addQueryParameter("agrupamento", agr.getSelectedItem().toString());
                    urlBuilder.addQueryParameter("escola", esc.getSelectedItem().toString());
                    urlBuilder.addQueryParameter("edificio", edi.getSelectedItem().toString());
                    urlBuilder.addQueryParameter("tipo", edt_tipo.getText().toString());
                    urlBuilder.addQueryParameter("marca", edt_marca.getText().toString());
                    urlBuilder.addQueryParameter("modelo", edt_modelo.getText().toString());
                    urlBuilder.addQueryParameter("num_serie", edt_nserie.getText().toString());
                    urlBuilder.addQueryParameter("num_patrimonio", edt_npatrimonio.getText().toString());
                    urlBuilder.addQueryParameter("sala", edt_sala.getText().toString());
                    urlBuilder.addQueryParameter("estado", est.getSelectedItem().toString());

                    String url = urlBuilder.build().toString();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Toast.makeText(getApplicationContext(), "Erro ao adicionar equipamento, sem resposta!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Toast.makeText(getApplicationContext(), "Equipamento adicionado com Sucesso!", Toast.LENGTH_LONG).show();
                                        finish();
                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Erro ao adicionar equipamento!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Erro ao adicionar equipamento!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        BuscarAgrupamentos ba = new BuscarAgrupamentos();
        ba.execute();
        BuscarEscolas be = new BuscarEscolas();
        be.execute();
        BuscarEdificiosMunicipais bem = new BuscarEdificiosMunicipais();
        bem.execute();
        BuscarEstados best = new BuscarEstados();
        best.execute();
    }

    private class BuscarAgrupamentos extends AsyncTask<Void,Void,Void> {
        ArrayList<String> lista;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lista=new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            InputStream is=null;
            String result="";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(get_agrupamentos)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("agrupamentos");

                for(int i = 0; i < Jarray.length(); i++){
                    JSONObject agr = Jarray.getJSONObject(i);
                    lista.add(agr.getString("agrupamento_nome"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listaDados.addAll(lista);
            adapter.notifyDataSetChanged();
        }
    }

    private class BuscarEscolas extends AsyncTask<Void,Void,Void> {
        ArrayList<String> lista2;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lista2=new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            InputStream is=null;
            String result="";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(get_escolas)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("escolas");

                for(int i = 0; i < Jarray.length(); i++){
                    JSONObject agr = Jarray.getJSONObject(i);
                    lista2.add(agr.getString("escola_nome"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listaDados2.addAll(lista2);
            adapter2.notifyDataSetChanged();
        }
    }

    private class BuscarEdificiosMunicipais extends AsyncTask<Void,Void,Void> {
        ArrayList<String> lista3;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lista3=new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            InputStream is=null;
            String result="";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(get_edificios)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("edificios");

                for(int i = 0; i < Jarray.length(); i++){
                    JSONObject agr = Jarray.getJSONObject(i);
                    lista3.add(agr.getString("edificio_nome"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listaDados3.addAll(lista3);
            adapter3.notifyDataSetChanged();
        }
    }

    private class BuscarEstados extends AsyncTask<Void,Void,Void> {
        ArrayList<String> lista4;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lista4=new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            InputStream is=null;
            String result="";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(get_estados)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("estados");

                for(int i = 0; i < Jarray.length(); i++){
                    JSONObject agr = Jarray.getJSONObject(i);
                    lista4.add(agr.getString("estado_nome"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            listaDados4.addAll(lista4);
            adapter4.notifyDataSetChanged();
        }
    }
}


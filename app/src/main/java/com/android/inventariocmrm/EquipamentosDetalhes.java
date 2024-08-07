package com.android.inventariocmrm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipamentosDetalhes extends AppCompatActivity {

    public static final String EXTRA_OBJC = "key.EXTRA_OBJC";

    public static void navigate(AppCompatActivity activity, View transitionView, Equips obj) {
        Intent intent = new Intent(activity, EquipamentosDetalhes.class);
        intent.putExtra(EXTRA_OBJC, obj);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, EXTRA_OBJC);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private Equips post;
    View parent_view, lyt_parent;
    TextView txt_nserie2, txt_agrupamento2, txt_escola2, txt_tipo2, txt_marca2, txt_modelo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipamentos_detalhes);
        parent_view = findViewById(android.R.id.content);
        lyt_parent = findViewById(R.id.lyt_parent);

        txt_nserie2 = findViewById(R.id.txt_nserie2);
        txt_agrupamento2 = findViewById(R.id.txt_agrupamento2);
        txt_escola2 = findViewById(R.id.txt_escola2);
        txt_tipo2 = findViewById(R.id.txt_tipo2);
        txt_marca2 = findViewById(R.id.txt_marca2);
        txt_modelo2 = findViewById(R.id.txt_modelo2);

        ViewCompat.setTransitionName(findViewById(R.id.image), EXTRA_OBJC);
        post = (Equips) getIntent().getSerializableExtra(EXTRA_OBJC);

        displayData();

    }

    private void displayData() {
        txt_nserie2.setText(Html.fromHtml(post.nserie));
        txt_agrupamento2.setText(Html.fromHtml(post.agrupamento));
        txt_escola2.setText(Html.fromHtml(post.escola));
        txt_tipo2.setText(Html.fromHtml(post.tipo));
        txt_marca2.setText(Html.fromHtml(post.marca));
        txt_modelo2.setText(Html.fromHtml(post.modelo));

    }
}

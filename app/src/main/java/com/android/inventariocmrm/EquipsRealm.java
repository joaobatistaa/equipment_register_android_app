package com.android.inventariocmrm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EquipsRealm extends RealmObject {
    @PrimaryKey
    public long id = -1;
    public String agrupamento = "";
    public String escola = "";
    public String edificio = "";
    public String nserie = "";
    public String npatrimonio = "";
    public String tipo = "";
    public String marca = "";
    public String modelo = "";
    public String sala = "";
    public String estado = "";

    public long added_date = 0;

    public Equips getOriginal(){
        Equips p = new Equips();
        p.id = id;
        p.agrupamento = agrupamento;
        p.escola = escola;
        p.edificio = edificio;
        p.nserie = nserie;
        p.npatrimonio = npatrimonio;
        p.tipo = tipo;
        p.marca = marca;
        p.modelo = modelo;
        p.sala = sala;
        p.estado = estado;

        return p;
    }

}

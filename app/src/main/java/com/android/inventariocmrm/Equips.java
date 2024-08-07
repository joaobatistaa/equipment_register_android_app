package com.android.inventariocmrm;

import java.io.Serializable;

public class Equips implements Serializable {

    public long id = -1;
    public String agrupamento;
    public String escola;
    public String edificio;
    public String nserie;
    public String npatrimonio;
    public String tipo;
    public String marca;
    public String modelo;
    public String sala;
    public String estado;

    public EquipsRealm getObjectRealm() {

    EquipsRealm p = new EquipsRealm();
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

    public Equips(){
    }
}

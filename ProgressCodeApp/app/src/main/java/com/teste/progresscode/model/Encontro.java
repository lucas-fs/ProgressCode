package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Encontro {
    private int id;
    private String dataRealizao;
    private int idEvento;

    public Encontro(Integer id, String dataRealizao, Integer idEvento){
        this.id = id;
        this.idEvento = idEvento;
        this.dataRealizao = dataRealizao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataRealizao(String dataRealizao) {
        this.dataRealizao = dataRealizao;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getId() {
        return id;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public String getDataRealizao() {
        return dataRealizao;
    }
}

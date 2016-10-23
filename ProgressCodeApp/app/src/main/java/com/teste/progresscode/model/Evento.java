package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Evento {
    private int id;
    private String nomeEvento;
    private String descricao;

    public Evento(Integer id, String nomeEvento, String descricao){
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.descricao = descricao;
    }

    public Evento(){}

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }
}

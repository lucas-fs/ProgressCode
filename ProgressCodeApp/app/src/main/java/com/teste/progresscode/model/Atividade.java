package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Atividade {
    private int id;
    private String descricao;
    private int idEncontro;

    public Atividade(Integer id, String descricao, Integer idEncontro){
        this.id = id;
        this.idEncontro = idEncontro;
        this.descricao = descricao;
    }

    public Atividade(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdEncontro(int idEncontro) {
        this.idEncontro = idEncontro;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdEncontro() {
        return idEncontro;
    }
}

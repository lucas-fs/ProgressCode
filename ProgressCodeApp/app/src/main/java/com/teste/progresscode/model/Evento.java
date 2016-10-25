package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Evento {

    @SerializedName("id")
    private int id;

    @SerializedName("nome_evento")
    private String nomeEvento;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("resource_uri")
    private String resourceUri;


    public Evento(Integer id, String nomeEvento, String descricao, String resourceUri){
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.descricao = descricao;
        this.resourceUri = resourceUri;

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

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

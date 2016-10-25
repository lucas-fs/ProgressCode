package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Encontro {

    @SerializedName("id")
    private int id;

    @SerializedName("data_realizao")
    private String dataRealizacao;

    @SerializedName("evento_id")
    private int idEvento;

    @SerializedName("resource_uri")
    private String resourceUri;

    public Encontro(Integer id, String dataRealizacao, Integer idEvento, String resourceUri){
        this.id = id;
        this.idEvento = idEvento;
        this.dataRealizacao = dataRealizacao;
        this.resourceUri = resourceUri;
    }

    public Encontro(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setDataRealizao(String dataRealizao) {
        this.dataRealizacao = dataRealizao;
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
        return dataRealizacao;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/10/16.
 */

public class Inscrito {
    @SerializedName("data_nasc")
    private String dataNasc;

    @SerializedName("escola")
    private String escola;

    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("resource_uri")
    private String resourceUri;

    public Inscrito(String nome, String dataNasc, String escola, Integer id, String resourceUri) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.escola = escola;
        this.id = id;
        this.resourceUri = resourceUri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

}

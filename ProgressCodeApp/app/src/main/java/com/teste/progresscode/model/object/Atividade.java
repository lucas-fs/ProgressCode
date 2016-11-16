package com.teste.progresscode.model.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Atividade {

    @SerializedName("id")
    private int id;

    @SerializedName("descricao")
    private String descricao;

    @SerializedName("encontro_id")
    private int idEncontro;

    @SerializedName("resource_uri")
    private String resourceUri;

    public Atividade(Integer id, String descricao, Integer idEncontro, String resourceUri){
        this.id = id;
        this.idEncontro = idEncontro;
        this.descricao = descricao;
        this.resourceUri = resourceUri;
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

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

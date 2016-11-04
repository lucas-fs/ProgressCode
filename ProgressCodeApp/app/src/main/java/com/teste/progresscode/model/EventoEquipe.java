package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class EventoEquipe {

    @SerializedName("equipe_id")
    private int idEquipe;

    @SerializedName("evento_id")
    private int idEvento;

    @SerializedName("resource_uri")
    private String resourceUri;

    public EventoEquipe(int idEquipe, int idEvento, String resourceUri) {
        this.idEquipe = idEquipe;
        this.idEvento = idEvento;
        this.resourceUri = resourceUri;
    }

    public EventoEquipe(){}

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

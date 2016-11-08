package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class EventoInscrito {

    @SerializedName("inscrito_id")
    private int idInscrito;

    @SerializedName("evento_id")
    private int idEvento;

    @SerializedName("resource_uri")
    private String resourceUri;

    public EventoInscrito(int idInscrito, int idEvento, String resourceUri) {
        this.idInscrito = idInscrito;
        this.idEvento = idEvento;
        this.resourceUri = resourceUri;
    }

    public EventoInscrito(){}

    public int getIdInscrito() {
        return idInscrito;
    }

    public void setIdInscrito(int idInscrito) {
        this.idInscrito = idInscrito;
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

package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class EventoInscrito {

    @SerializedName("evento_id")
    private int idInscrito;

    @SerializedName("inscrito_id")
    private int idEvento;

    public EventoInscrito(Integer idInscrito, Integer idEvento){
        this.idEvento = idEvento;
        this.idInscrito = idInscrito;
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
}

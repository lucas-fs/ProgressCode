package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class EventoEquipe {
    private int idEquipe;
    private int idEvento;

    public EventoEquipe(Integer idEquipe, Integer idEvento){
        this.idEquipe = idEquipe;
        this.idEvento = idEvento;
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
}

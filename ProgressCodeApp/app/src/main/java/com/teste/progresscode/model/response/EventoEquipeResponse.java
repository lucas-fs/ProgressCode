package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.EventoEquipe;
import com.teste.progresscode.model.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EventoEquipeResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<EventoEquipe> eventoEquipes;

    public EventoEquipeResponse(Meta meta, List<EventoEquipe> eventoEquipes) {
        this.meta = meta;
        this.eventoEquipes = eventoEquipes;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<EventoEquipe> getEventoEquipes() {
        return eventoEquipes;
    }

    public void setEventoEquipes(List<EventoEquipe> eventoEquipes) {
        this.eventoEquipes = eventoEquipes;
    }
}

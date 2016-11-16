package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.object.EventoInscrito;
import com.teste.progresscode.model.object.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EventoInscritoResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<EventoInscrito> eventoInscritos;

    public EventoInscritoResponse(Meta meta, List<EventoInscrito> eventoInscritos) {
        this.meta = meta;
        this.eventoInscritos = eventoInscritos;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<EventoInscrito> getEventoInscritos() {
        return eventoInscritos;
    }

    public void setEventoInscritos(List<EventoInscrito> eventoInscritos) {
        this.eventoInscritos = eventoInscritos;
    }
}

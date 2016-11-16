package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.object.Evento;
import com.teste.progresscode.model.object.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EventoResponse {
    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Evento> eventos;

    public EventoResponse(Meta meta, List<Evento> eventos) {
        this.meta = meta;
        this.eventos = eventos;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}

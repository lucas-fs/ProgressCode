package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.Inscrito;
import com.teste.progresscode.model.Meta;

import java.util.List;

/**
 * Created by lucas on 11/10/16.
 */

public class InscritoResponse {
    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Inscrito> inscritos;

    public InscritoResponse(Meta meta, List<Inscrito> inscritos) {
        this.meta = meta;
        this.inscritos = inscritos;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Inscrito> getInscritos() {
        return inscritos;
    }

    public void setInscritos(List<Inscrito> inscritos) {
        this.inscritos = inscritos;
    }
}

package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.object.Atividade;
import com.teste.progresscode.model.object.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class AtividadeResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Atividade> atividades;

    public AtividadeResponse(Meta meta, List<Atividade> atividades) {
        this.meta = meta;
        this.atividades = atividades;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}

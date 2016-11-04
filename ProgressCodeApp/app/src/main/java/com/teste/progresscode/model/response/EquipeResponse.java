package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.Equipe;
import com.teste.progresscode.model.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EquipeResponse {
    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Equipe> equipes;

    public EquipeResponse(Meta meta, List<Equipe> equipes) {
        this.meta = meta;
        this.equipes = equipes;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }
}

package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.Encontro;
import com.teste.progresscode.model.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EncontroResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Encontro> encontros;

    public EncontroResponse(Meta meta, List<Encontro> encontros) {
        this.meta = meta;
        this.encontros = encontros;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Encontro> getEncontros() {
        return encontros;
    }

    public void setEncontros(List<Encontro> encontros) {
        this.encontros = encontros;
    }
}

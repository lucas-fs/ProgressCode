package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.object.Meta;
import com.teste.progresscode.model.object.Tutor;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class TutorResponse {
    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Tutor> tutores;

    public TutorResponse(Meta meta, List<Tutor> tutores) {
        this.meta = meta;
        this.tutores = tutores;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Tutor> getTutores() {
        return tutores;
    }

    public void setTutores(List<Tutor> tutores) {
        this.tutores = tutores;
    }
}

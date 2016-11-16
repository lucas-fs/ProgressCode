package com.teste.progresscode.model.response;

import com.google.gson.annotations.SerializedName;
import com.teste.progresscode.model.object.EquipeTutor;
import com.teste.progresscode.model.object.Meta;

import java.util.List;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class EquipeTutorResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<EquipeTutor> equipeTutores;

    public EquipeTutorResponse(Meta meta, List<EquipeTutor> equipeTutores) {
        this.meta = meta;
        this.equipeTutores = equipeTutores;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<EquipeTutor> getEquipeTutores() {
        return equipeTutores;
    }

    public void setEquipeTutores(List<EquipeTutor> equipeTutores) {
        this.equipeTutores = equipeTutores;
    }
}

package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class EquipeTutor {
    private int idEquipe;
    private int idTutor;

    public EquipeTutor(Integer idEquipe, Integer idTutor){
        this.idEquipe = idEquipe;
        this.idTutor = idTutor;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }
}

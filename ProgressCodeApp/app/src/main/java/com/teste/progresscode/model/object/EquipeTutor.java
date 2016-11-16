package com.teste.progresscode.model.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class EquipeTutor {

    @SerializedName("equipe_id")
    private int idEquipe;

    @SerializedName("tutor_id")
    private int idTutor;

    @SerializedName("resource_uri")
    private String resourceUri;

    public EquipeTutor(int idEquipe, String resourceUri, int idTutor) {
        this.idEquipe = idEquipe;
        this.resourceUri = resourceUri;
        this.idTutor = idTutor;
    }

    public EquipeTutor(){}

    public int getIdEquipe() { return idEquipe; }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

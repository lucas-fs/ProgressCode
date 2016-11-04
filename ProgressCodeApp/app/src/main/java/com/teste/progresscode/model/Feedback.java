package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Feedback {

    @SerializedName("tutor_id")
    private int idTutor;

    @SerializedName("inscrito_id")
    private int idInscrito;

    @SerializedName("atividade_id")
    private int idAtividade;

    @SerializedName("status")
    private int status;

    @SerializedName("timestamp")
    private String timeStamp;

    @SerializedName("dir_audio")
    private String dirAudio;

    @SerializedName("resource_uri")
    private String resourceUri;


    public Feedback(Integer idTutor, Integer idInscrito, Integer idAtividade, Integer status, String timeStamp, String dirAudio, String resourceUri){
        this.idTutor = idTutor;
        this.idInscrito = idInscrito;
        this.idAtividade = idAtividade;
        this.status = status;
        this.timeStamp = timeStamp;
        this.dirAudio = dirAudio;
        this.resourceUri = resourceUri;
    }

    public Feedback(){}


    public int getIdTutor() {
        return idTutor;
    }

    public int getIdInscrito() {
        return idInscrito;
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public int getStatus() {
        return status;
    }

    public String getDirAudio() {
        return dirAudio;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public void setIdInscrito(int idInscrito) {
        this.idInscrito = idInscrito;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setDirAudio(String dirAudio) {
        this.dirAudio = dirAudio;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

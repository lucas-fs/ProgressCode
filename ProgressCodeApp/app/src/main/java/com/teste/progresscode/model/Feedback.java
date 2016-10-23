package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Feedback {
    private int idTutor;
    private int idInscrito;
    private int idAtividade;
    private int status;
    private String timeStamp;
    private String dirAudio;


    public Feedback(Integer idTutor, Integer idInscrito, Integer idAtividade, Integer status, String timeStamp, String dirAudio ){
        this.idTutor = idTutor;
        this.idInscrito = idInscrito;
        this.idAtividade = idAtividade;
        this.status = status;
        this.timeStamp = timeStamp;
        this.dirAudio = dirAudio;
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
}

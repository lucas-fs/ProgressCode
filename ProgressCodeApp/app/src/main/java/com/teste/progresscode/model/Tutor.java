package com.teste.progresscode.model;

import android.app.job.JobInfo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Tutor {
    private String nome;
    private String email;
    private String senha;
    private int id;


    public Tutor(String nome, String email, String senha, Integer id){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
    }

    public Tutor(){}

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

package com.teste.progresscode.model.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icorrea on 22/10/16.
 */

public class Tutor {
    @SerializedName("nome")
    private String nome;

    @SerializedName("email")
    private String email;

    @SerializedName("senha")
    private String senha;

    @SerializedName("id")
    private int id;

    @SerializedName("resource_uri")
    private String resourceUri;


    public Tutor(String nome, String email, String senha, Integer id, String resourceUri){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.resourceUri = resourceUri;
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

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}

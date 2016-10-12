package com.teste.progresscode.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucas on 11/10/16.
 */

public class Inscrito {
    @SerializedName("nome")
    private String nome;
    @SerializedName("data_nasc")
    private String dataNasc;
    @SerializedName("escola")
    private String escola;
    @SerializedName("id")
    private Integer id;

    public Inscrito(String nome, String dataNasc, String escola, Integer id) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.escola = escola;
        this.id = id;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

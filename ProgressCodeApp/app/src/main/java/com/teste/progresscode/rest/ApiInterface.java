package com.teste.progresscode.rest;

import com.teste.progresscode.model.InscritoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lucas on 11/10/16.
 */

public interface ApiInterface {

    // Retorna inscrito com id informado na consulta
    @GET("inscrito/{id}")
    Call<InscritoResponse> getInscritoId(@Path("id") int id);

    // Retorna todos inscritos cadastrados
    @GET("inscrito/")
    Call<InscritoResponse> getAllInscritos();



}


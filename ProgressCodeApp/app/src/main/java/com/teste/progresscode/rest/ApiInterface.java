package com.teste.progresscode.rest;

import com.teste.progresscode.model.InscritoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lucas on 11/10/16.
 */

public interface ApiInterface {
    @GET("inscrito/{id}")
    Call<InscritoResponse> getInscritoId(@Path("id") int id);

    @GET("inscrito/")
    Call<InscritoResponse> getInscritos();

}


package com.teste.progresscode.rest;

import com.teste.progresscode.model.response.AtividadeResponse;
import com.teste.progresscode.model.response.EncontroResponse;
import com.teste.progresscode.model.response.EventoInscritoResponse;
import com.teste.progresscode.model.response.EventoResponse;
import com.teste.progresscode.model.response.InscritoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lucas on 11/10/16.
 */

public interface ApiInterface {

    // Retorna inscrito com id informado na consulta
   // @GET("inscrito/{id}")
    //Call<InscritoResponse> getInscritoId(@Query("id") int id);

    // Retorna todos inscritos cadastrados
    @GET("inscrito/")
    Call<InscritoResponse> getAllInscritos();

    // Retorna todos eventos cadastrados
    @GET("evento/")
    Call<EventoResponse> getAllEventos();

    // Retorna NxN evento_inscrito
    @GET("evento_inscrito/")
    Call<EventoInscritoResponse> getAllEventoInscrito();

    // Retorna todos encontros cadastrados
    @GET("encontros/")
    Call<EncontroResponse> getAllEncontros();

    // Retorna atividades relacionadas a determinado encontro
    @GET("atividades/")
    Call<AtividadeResponse> getAtividadesByEncontro(@Query("encontro_id") int id_encontro);

    // Retorna todas atividades cadastrados atividades/
    @GET("atividades/")
    Call<AtividadeResponse> getAllAtividades();

}


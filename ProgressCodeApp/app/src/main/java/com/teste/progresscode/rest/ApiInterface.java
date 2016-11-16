package com.teste.progresscode.rest;

import com.teste.progresscode.model.object.Feedback;
import com.teste.progresscode.model.response.AtividadeResponse;
import com.teste.progresscode.model.response.EncontroResponse;
import com.teste.progresscode.model.response.EquipeResponse;
import com.teste.progresscode.model.response.EquipeTutorResponse;
import com.teste.progresscode.model.response.EventoEquipeResponse;
import com.teste.progresscode.model.response.EventoInscritoResponse;
import com.teste.progresscode.model.response.EventoResponse;
import com.teste.progresscode.model.response.FeedbackResponse;
import com.teste.progresscode.model.response.InscritoResponse;
import com.teste.progresscode.model.response.TutorResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lucas on 11/10/16.
 */

public interface ApiInterface {

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

    // Retorna todas atividades cadastradas
    @GET("atividades/")
    Call<AtividadeResponse> getAllAtividades();

    // Retorna todas equipes cadastradas
    @GET("equipe/")
    Call<EquipeResponse> getAllEquipes();

    // Retorna todos tutores cadastrados
    @GET("tutores/")
    Call<TutorResponse> getAllTutores();

    // Retorna equipe_tutor
    @GET("equipe_tutor/")
    Call<EquipeTutorResponse> getAllEquipeTutor();

    // Retorna evento_equipe
    @GET("evento_equipe/")
    Call<EventoEquipeResponse> getAllEventoEquipe();

    // Retorna todos feedbacks cadastrados
    @GET("feedbacks/")
    Call<FeedbackResponse> getAllFeedbacks();

    // Envia um novo feedback para o servidor
    @POST("feedbacks/")
    Call<Feedback> postFeedback(@Body Feedback feedback);
}


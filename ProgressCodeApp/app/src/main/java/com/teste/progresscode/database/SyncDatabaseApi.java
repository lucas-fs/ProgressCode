package com.teste.progresscode.database;

import android.content.Context;
import android.util.Log;

import com.teste.progresscode.model.Atividade;
import com.teste.progresscode.model.Encontro;
import com.teste.progresscode.model.Equipe;
import com.teste.progresscode.model.EquipeTutor;
import com.teste.progresscode.model.Evento;
import com.teste.progresscode.model.EventoEquipe;
import com.teste.progresscode.model.EventoInscrito;
import com.teste.progresscode.model.Feedback;
import com.teste.progresscode.model.Inscrito;
import com.teste.progresscode.model.Tutor;
import com.teste.progresscode.model.dao.AtividadeDAO;
import com.teste.progresscode.model.dao.EncontroDAO;
import com.teste.progresscode.model.dao.EquipeDAO;
import com.teste.progresscode.model.dao.EquipeTutorDAO;
import com.teste.progresscode.model.dao.EventoDAO;
import com.teste.progresscode.model.dao.EventoEquipeDAO;
import com.teste.progresscode.model.dao.EventoInscritoDAO;
import com.teste.progresscode.model.dao.FeedbackDAO;
import com.teste.progresscode.model.dao.InscritoDAO;
import com.teste.progresscode.model.dao.TutorDAO;
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
import com.teste.progresscode.rest.ApiClient;
import com.teste.progresscode.rest.ApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class SyncDatabaseApi {

    private ApiInterface apiService;
    private static final String TAG = SyncDatabaseApi.class.getSimpleName();
    private Context context;

    public SyncDatabaseApi(Context context) {
        this.context = context;
        this.apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void syncTutor() {

        Response<TutorResponse> response = null;
        Call<TutorResponse> call = apiService.getAllTutores();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Tutor> tutores = response.body().getTutores();
                TutorDAO tutorDAO = new TutorDAO(context);
                tutorDAO.openConection();

                List<Tutor> tutoresApp = tutorDAO.getAllTutores();

                int taSize = tutoresApp.size();

                for (Tutor t : tutores) {
                    int cont = 0;
                    for (Tutor ta : tutoresApp) {
                        if (t.getId() == ta.getId()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == taSize) {
                        tutorDAO.insertTutor(t);
                    }
                }

                tutorDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncEquipe() {

        Response<EquipeResponse> response = null;
        Call<EquipeResponse> call = apiService.getAllEquipes();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Equipe> equipes = response.body().getEquipes();
                EquipeDAO equipeDAO = new EquipeDAO(context);
                equipeDAO.openConection();

                List<Equipe> equipesApp = equipeDAO.getAllEquipes();

                int eaSize = equipesApp.size();

                for (Equipe e : equipes) {
                    int cont = 0;
                    for (Equipe ea : equipesApp) {
                        if (e.getId() == ea.getId()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == eaSize) {
                        equipeDAO.insertEquipe(e);
                    }
                }

                equipeDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncEquipeTutor() {

        Response<EquipeTutorResponse> response = null;
        Call<EquipeTutorResponse> call = apiService.getAllEquipeTutor();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<EquipeTutor> equipeTutores = response.body().getEquipeTutores();
                EquipeTutorDAO equipeTutorDAO = new EquipeTutorDAO(context);
                equipeTutorDAO.openConection();

                List<EquipeTutor> equipeTutoresApp = equipeTutorDAO.getAllEquipeTutor();

                int etaSize = equipeTutoresApp.size();

                for (EquipeTutor et : equipeTutores) {
                    int cont = 0;
                    for (EquipeTutor eta : equipeTutoresApp) {
                        if (et.getIdEquipe() == eta.getIdEquipe() && et.getIdTutor() == eta.getIdTutor()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == etaSize) {
                        equipeTutorDAO.insertEquipeTutor(et);
                    }
                }

                equipeTutorDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncEvento() {

        Response<EventoResponse> response = null;
        Call<EventoResponse> call = apiService.getAllEventos();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Evento> eventos = response.body().getEventos();
                EventoDAO eventoDAO = new EventoDAO(context);
                eventoDAO.openConection();

                List<Evento> eventoApp = eventoDAO.getAllEventos();

                int eaSize = eventoApp.size();

                for (Evento e : eventos) {
                    int cont = 0;
                    for (Evento ea : eventoApp) {
                        if (e.getId() == ea.getId()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == eaSize) {
                        eventoDAO.insertEvento(e);
                    }
                }

                eventoDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncEventoEquipe() {

        Response<EventoEquipeResponse> response = null;
        Call<EventoEquipeResponse> call = apiService.getAllEventoEquipe();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<EventoEquipe> eventoEquipes = response.body().getEventoEquipes();
                EventoEquipeDAO eventoEquipeDAO = new EventoEquipeDAO(context);
                eventoEquipeDAO.openConection();

                List<EventoEquipe> eventoEquipesApp = eventoEquipeDAO.getAllEventoEquipes();

                int eeaSize = eventoEquipesApp.size();

                for (EventoEquipe ee : eventoEquipes) {
                    int cont = 0;
                    for (EventoEquipe eea : eventoEquipesApp) {
                        if (ee.getIdEquipe() == eea.getIdEquipe() && ee.getIdEvento() == eea.getIdEvento()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == eeaSize) {
                        eventoEquipeDAO.insertEventoEquipe(ee);
                    }
                }

                eventoEquipeDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncInscrito() {

        Response<InscritoResponse> response = null;
        Call<InscritoResponse> call = apiService.getAllInscritos();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Inscrito> inscritos = response.body().getInscritos();
                InscritoDAO inscritoDAO = new InscritoDAO(context);
                inscritoDAO.openConection();

                List<Inscrito> inscritosApp = inscritoDAO.getAllInscritos();

                int iaSize = inscritosApp.size();

                for (Inscrito i : inscritos) {
                    int cont = 0;
                    for (Inscrito ia : inscritosApp) {
                        if (i.getId() == ia.getId()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == iaSize) {
                        inscritoDAO.insertInscrito(i);
                    }
                }

                inscritoDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncEventoInscrito() {

        Response<EventoInscritoResponse> response = null;
        Call<EventoInscritoResponse> call = apiService.getAllEventoInscrito();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<EventoInscrito> eventoInscritos = response.body().getEventoInscritos();
                EventoInscritoDAO eventoInscritoDAO = new EventoInscritoDAO(context);
                eventoInscritoDAO.openConection();

                List<EventoInscrito> eventoInscritosApp = eventoInscritoDAO.getAllEventoInscritos();

                int eiaSize = eventoInscritosApp.size();

                for (EventoInscrito ei : eventoInscritos) {
                    int cont = 0;
                    for (EventoInscrito eia : eventoInscritosApp) {
                        if (ei.getIdEvento() == eia.getIdEvento() && ei.getIdInscrito() == eia.getIdInscrito()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == eiaSize && eiaSize != 0) {
                        eventoInscritoDAO.insertEventoInscrito(ei);
                        Log.e(TAG, "Inserindo evento_inscrito: "+ei.getResourceUri());
                    }
                }

                eventoInscritoDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncEncontro() {

        Response<EncontroResponse> response = null;
        Call<EncontroResponse> call = apiService.getAllEncontros();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Encontro> encontros = response.body().getEncontros();
                EncontroDAO encontroDAO = new EncontroDAO(context);
                encontroDAO.openConection();

                List<Encontro> encontrosApp = encontroDAO.getAllEncontros();

                int eaSize = encontrosApp.size();

                for (Encontro e : encontros) {
                    int cont = 0;
                    for (Encontro ea : encontrosApp) {
                        if (e.getId() == ea.getId()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == eaSize) {
                        encontroDAO.insertEncontro(e);
                        Log.e(TAG, "Inserindo encontro: "+e.getId());
                    }
                }

                encontroDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncAtividades() {

        Response<AtividadeResponse> response = null;
        Call<AtividadeResponse> call = apiService.getAllAtividades();

        try {
            response = call.execute();
        } catch (IOException e) {
            Log.e(TAG, "falha sync atividade");
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Atividade> atividades = response.body().getAtividades();
                AtividadeDAO atividadeDAO = new AtividadeDAO(context);
                atividadeDAO.openConection();

                List<Atividade> atividadesApp = atividadeDAO.getAllAtividades();

                int aaSize = atividadesApp.size();

                for (Atividade a : atividades) {
                    int cont = 0;
                    for (Atividade aa : atividadesApp) {
                        if (a.getId() == aa.getId()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == aaSize) {
                        atividadeDAO.insertAtividade(a);
                        Log.e(TAG, "Inserindo atividade: "+a.getDescricao());
                    }
                }

                atividadeDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }

    public void syncFeedback() {

        Response<FeedbackResponse> response = null;
        Call<FeedbackResponse> call = apiService.getAllFeedbacks();

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Feedback> feedbacks = response.body().getFeedbacks();
                FeedbackDAO feedbackDAO = new FeedbackDAO(context);
                feedbackDAO.openConection();

                List<Feedback> feedbacksApp = feedbackDAO.getAllFeedbacks();

                int faSize = feedbacksApp.size();

                for (Feedback f : feedbacks) {
                    int cont = 0;
                    for (Feedback fa : feedbacksApp) {
                        if (f.getIdInscrito() == fa.getIdInscrito() && f.getIdTutor() == fa.getIdTutor() && f.getIdAtividade() == fa.getIdAtividade()) {
                            break;
                        } else {
                            cont++;
                        }
                    }
                    if (cont == faSize) {
                        feedbackDAO.insertFeedback(f);
                        Log.e(TAG, "Inserindo Feedback: "+f.getResourceUri());
                    }
                }

                feedbackDAO.closeConection();
            }
        } else {
            Log.e(TAG, "Response = null");
        }
    }


    public void syncAllDatabase() {
        syncTutor();
        syncEquipe();
        syncEquipeTutor();
        syncEvento();
        syncEventoEquipe();
        syncInscrito();
        syncEventoInscrito();
        syncEncontro();
        syncAtividades();
        syncFeedback();
    }
}

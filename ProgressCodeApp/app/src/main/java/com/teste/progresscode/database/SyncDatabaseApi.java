package com.teste.progresscode.database;

import android.content.Context;
import android.util.Log;

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
import com.teste.progresscode.model.object.Atividade;
import com.teste.progresscode.model.object.Encontro;
import com.teste.progresscode.model.object.Equipe;
import com.teste.progresscode.model.object.EquipeTutor;
import com.teste.progresscode.model.object.Evento;
import com.teste.progresscode.model.object.EventoEquipe;
import com.teste.progresscode.model.object.EventoInscrito;
import com.teste.progresscode.model.object.Feedback;
import com.teste.progresscode.model.object.Inscrito;
import com.teste.progresscode.model.object.Tutor;
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
import java.net.SocketTimeoutException;
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

    public int syncTutor() {

        Response<TutorResponse> response = null;
        Call<TutorResponse> call = apiService.getAllTutores();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (taSize != 0) {
                        for (Tutor ta : tutoresApp) {
                            if (t.getId() == ta.getId()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == taSize) {
                        tutorDAO.insertTutor(t);
                        Log.v(TAG, "Inserindo tutor: " + t.getNome());
                    }
                }

                tutorDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncEquipe() {

        Response<EquipeResponse> response = null;
        Call<EquipeResponse> call = apiService.getAllEquipes();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (eaSize != 0) {
                        for (Equipe ea : equipesApp) {
                            if (e.getId() == ea.getId()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == eaSize) {
                        equipeDAO.insertEquipe(e);
                        Log.v(TAG, "Inserindo equipe: " + e.getDescricao());
                    }
                }

                equipeDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncEquipeTutor() {

        Response<EquipeTutorResponse> response = null;
        Call<EquipeTutorResponse> call = apiService.getAllEquipeTutor();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (etaSize != 0) {
                        for (EquipeTutor eta : equipeTutoresApp) {
                            if (et.getIdEquipe() == eta.getIdEquipe() && et.getIdTutor() == eta.getIdTutor()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == etaSize) {
                        equipeTutorDAO.insertEquipeTutor(et);
                        Log.v(TAG, "Inserindo equipe_tutor: " + et.getResourceUri());
                    }
                }

                equipeTutorDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncEvento() {

        Response<EventoResponse> response = null;
        Call<EventoResponse> call = apiService.getAllEventos();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) { // somente sera null quando nao consegue consultar o web service
            if (response.body().getMeta().getTotalCount() != 0) {
                List<Evento> eventos = response.body().getEventos();
                EventoDAO eventoDAO = new EventoDAO(context);
                eventoDAO.openConection();

                List<Evento> eventoApp = eventoDAO.getAllEventos();

                int eaSize = eventoApp.size();

                for (Evento e : eventos) {
                    int cont = 0;
                    if (eaSize != 0) {
                        for (Evento ea : eventoApp) {
                            if (e.getId() == ea.getId()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == eaSize) {
                        eventoDAO.insertEvento(e);
                        Log.v(TAG, "Inserindo evento: " + e.getNomeEvento());
                    }
                }

                eventoDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncEventoEquipe() {

        Response<EventoEquipeResponse> response = null;
        Call<EventoEquipeResponse> call = apiService.getAllEventoEquipe();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (eeaSize != 0) {
                        for (EventoEquipe eea : eventoEquipesApp) {
                            if (ee.getIdEquipe() == eea.getIdEquipe() && ee.getIdEvento() == eea.getIdEvento()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == eeaSize) {
                        eventoEquipeDAO.insertEventoEquipe(ee);
                        Log.v(TAG, "Inserindo evento_equipe: " + ee.getResourceUri());
                    }
                }

                eventoEquipeDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncInscrito() {

        Response<InscritoResponse> response = null;
        Call<InscritoResponse> call = apiService.getAllInscritos();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (iaSize != 0) {
                        for (Inscrito ia : inscritosApp) {
                            if (i.getId() == ia.getId()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == iaSize) {
                        inscritoDAO.insertInscrito(i);
                        Log.v(TAG, "Inserindo inscrito: " + i.getNome());
                    }
                }

                inscritoDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncEventoInscrito() {

        Response<EventoInscritoResponse> response = null;
        Call<EventoInscritoResponse> call = apiService.getAllEventoInscrito();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (eiaSize != 0) {
                        for (EventoInscrito eia : eventoInscritosApp) {
                            if (ei.getIdEvento() == eia.getIdEvento() && ei.getIdInscrito() == eia.getIdInscrito()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == eiaSize) {
                        eventoInscritoDAO.insertEventoInscrito(ei);
                        Log.v(TAG, "Inserindo evento_inscrito: " + ei.getResourceUri());
                    }
                }

                eventoInscritoDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncEncontro() {

        Response<EncontroResponse> response = null;
        Call<EncontroResponse> call = apiService.getAllEncontros();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (eaSize != 0) {
                        for (Encontro ea : encontrosApp) {
                            if (e.getId() == ea.getId()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == eaSize) {
                        encontroDAO.insertEncontro(e);
                        Log.v(TAG, "Inserindo encontro: " + e.getId());
                    }
                }

                encontroDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncAtividades() {

        Response<AtividadeResponse> response = null;
        Call<AtividadeResponse> call = apiService.getAllAtividades();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
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
                    if (aaSize != 0) {
                        for (Atividade aa : atividadesApp) {
                            if (a.getId() == aa.getId()) {
                                break;
                            } else {
                                cont++;
                            }
                        }
                    }
                    if (cont == aaSize) {
                        atividadeDAO.insertAtividade(a);
                        Log.v(TAG, "Inserindo atividade: " + a.getDescricao());
                    }
                }

                atividadeDAO.closeConection();
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }
    }

    public int syncFeedback() {

        Response<FeedbackResponse> response = null;
        Call<FeedbackResponse> call = apiService.getAllFeedbacks();

        try {
            response = call.execute();
        } catch (SocketTimeoutException e) {
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        FeedbackDAO feedbackDAO = new FeedbackDAO(context);

        feedbackDAO.openConection();
        List<Feedback> feedbacksApp = feedbackDAO.getAllFeedbacks();
        feedbackDAO.closeConection();

        int faSize = feedbacksApp.size();

        if (response != null) {
            if (response.body().getMeta().getTotalCount() != 0) { // request retornou dados
                List<Feedback> feedbacks = response.body().getFeedbacks();
                int fsSize = feedbacks.size();
                Log.i(TAG, "APP: " + faSize + "   SERVIDOR: " + fsSize);

                int cont;

                if (faSize > 0) { // se o app possui dados

                    for (Feedback fa : feedbacksApp) {
                        cont = 0;
                        for (Feedback fs : feedbacks) {
                            Log.v(TAG, "Teste timestamp APP: " + fa.getTimeStamp()+ " Server: "+fs.getTimeStamp());
                            if (fs.getIdInscrito() == fa.getIdInscrito() && fs.getIdTutor() == fa.getIdTutor() && fs.getIdAtividade() == fa.getIdAtividade() && fs.getTimeStamp().equals(fa.getTimeStamp())) {
                                break;
                            } else {
                                cont++;
                            }
                        }

                        if (cont == fsSize) {
                            Call<Void> callPost = apiService.postFeedback(fa);
                            try {
                                callPost.execute();
                                // Log.i(TAG, "code post: " +resp.code());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.v(TAG, "POST Feedback para o servidor: " + fa.getTimeStamp());
                        }
                    }

                    /*
                    feedbackDAO.openConection();
                    for (Feedback f : feedbacks) {
                        cont = 0;

                        for (Feedback fa : feedbacksApp) {
                            if (f.getIdInscrito() == fa.getIdInscrito() && f.getIdTutor() == fa.getIdTutor() && f.getIdAtividade() == fa.getIdAtividade() && f.getTimeStamp().equals(fa.getTimeStamp())) {
                                break;
                            } else {
                                cont++;
                            }
                        }

                        if (cont == faSize) {
                            feedbackDAO.insertFeedback(f);
                            Log.v(TAG, "Inserindo Feedback: " + f.getTimeStamp());
                        }
                    }
                    feedbackDAO.closeConection();
                    */
                } /*else {
                    for (Feedback f : feedbacks) {
                        feedbackDAO.openConection();
                        feedbackDAO.insertFeedback(f);
                        Log.v(TAG, "Inserindo Feedback: " + f.getTimeStamp());
                        feedbackDAO.closeConection();
                    }

                }*/
            } else { // o servidor nao possui dados
                if (faSize > 0) { // o app possui dados
                    for (Feedback fa: feedbacksApp) {
                        Call<Void> callPost = apiService.postFeedback(fa);
                        try {
                            callPost.execute();
                            // Log.i(TAG, "code post: " +resp.code());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.v(TAG, "POST Feedback para o servidor: " + fa.getTimeStamp());
                    }
                }
            }
            return 1;
        } else {
            Log.e(TAG, "Response = null");
            return 0;
        }

    }

    public void syncFeedbackThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncFeedback();
            }
        }).start();

    }

    public int syncAllDatabase() {

        final int errors[] = new int[10];
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    errors[0] = syncInscrito();
                    errors[1] = syncEventoInscrito();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    errors[2] = syncEncontro();
                    errors[3] = syncAtividades();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    errors[4] = syncEventoEquipe();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    errors[5] = syncEquipeTutor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    errors[6] = syncEvento();
                    thread2.start();
                    thread3.start();
                    errors[7] = syncEquipe();
                    thread4.start();
                    errors[8] = syncTutor();
                    thread5.start();
                    thread2.join();
                    thread3.join();
                    errors[9] = syncFeedback();
                    thread4.join();
                    thread5.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return countErrors(errors);
    }

    private int countErrors(int[] errors) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += errors[i];
        }
        if (sum == 10) {
            return 0;
        } else if (sum < 0) {
            return -1;
        } else {
            return 1;
        }
    }


}


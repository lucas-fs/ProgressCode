package com.teste.progresscode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.teste.progresscode.R;
import com.teste.progresscode.adapter.AtividadeAdapter;
import com.teste.progresscode.model.object.Atividade;
import com.teste.progresscode.model.object.Tutor;
import com.teste.progresscode.model.dao.AtividadeDAO;

import java.util.List;

public class AtividadesActivity extends AppCompatActivity {

    private static final String TAG = AtividadesActivity.class.getSimpleName();
    private Tutor tutor;

    List<Atividade> atividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_name);
        setSupportActionBar(myToolbar);

        //RecyclerView.Adapter adapter = new InscritoAdapter(R.layout.list_item_inscrito, getApplicationContext());

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        int id_encontro = bundle.getInt("id_encontro");
        int id_inscrito = bundle.getInt("id_inscrito");
        String nomeInscrito = bundle.getString("nome_inscrito");

        tutor = bundleToTutor(bundle);


        getSupportActionBar().setTitle(nomeInscrito);

        AtividadeDAO atividadeDAO = new AtividadeDAO(getApplicationContext());
        atividadeDAO.openConection();
        atividades = atividadeDAO.getAllAtividades();
        atividadeDAO.closeConection();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.inscritos_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new AtividadeAdapter(atividades, id_inscrito, tutor.getId(), R.layout.list_item_inscrito, getApplicationContext()));

    }

    private Tutor bundleToTutor(Bundle bundle) {
        Tutor tutor = new Tutor();
        tutor.setId(bundle.getInt("id_tutor"));
        tutor.setNome(bundle.getString("nome_tutor"));
        tutor.setEmail(bundle.getString("email_tutor"));

        return tutor;
    }
}

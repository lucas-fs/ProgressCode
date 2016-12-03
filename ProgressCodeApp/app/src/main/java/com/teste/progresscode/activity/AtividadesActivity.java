package com.teste.progresscode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.teste.progresscode.R;
import com.teste.progresscode.adapter.AtividadeAdapter;
import com.teste.progresscode.database.SyncDatabaseApi;
import com.teste.progresscode.model.dao.AtividadeDAO;
import com.teste.progresscode.model.object.Atividade;
import com.teste.progresscode.model.object.Tutor;
import com.teste.progresscode.task.UpdateFeedbackTask;

import java.util.List;

public class AtividadesActivity extends AppCompatActivity {

    private static final String TAG = AtividadesActivity.class.getSimpleName();
    private static final String LIST_STATE_KEY = "list_state";
    private Tutor tutor;
    private SyncDatabaseApi syncDatabaseApi;
    private List<Atividade> atividades;
    private Menu menuSync;
    private LinearLayoutManager layoutManager;
    private Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_name);
        setSupportActionBar(myToolbar);

        layoutManager = new LinearLayoutManager(this);

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
        atividades = atividadeDAO.getAtividadesByInscrito(id_inscrito);
        atividadeDAO.closeConection();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.inscritos_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new AtividadeAdapter(atividades, id_inscrito, tutor.getId(), R.layout.list_item_inscrito, getApplicationContext()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atividades, menu);

        menuSync = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sync) {
            // Do animation start
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ImageView iv = (ImageView)inflater.inflate(R.layout.iv_refresh, null);
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
            rotation.setRepeatCount(Animation.INFINITE);
            iv.startAnimation(rotation);
            item.setActionView(iv);

            new UpdateFeedbackTask(this).execute();

            Toast.makeText(getApplicationContext(), "Sincronizando feedbacks!", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Tutor bundleToTutor(Bundle bundle) {
        Tutor tutor = new Tutor();
        tutor.setId(bundle.getInt("id_tutor"));
        tutor.setNome(bundle.getString("nome_tutor"));
        tutor.setEmail(bundle.getString("email_tutor"));

        return tutor;
    }

    public void resetUpdating() {
        // Get our refresh item from the menu
        MenuItem m = menuSync.findItem(R.id.action_sync);
        if (m.getActionView() != null) {
            // Remove the animation.
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        listState = layoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }
}

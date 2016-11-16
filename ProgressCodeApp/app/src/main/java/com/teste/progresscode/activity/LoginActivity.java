package com.teste.progresscode.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teste.progresscode.R;
import com.teste.progresscode.model.object.Tutor;
import com.teste.progresscode.model.dao.TutorDAO;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button btLogin;
    private EditText senha;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        senha = (EditText) findViewById(R.id.senha);
        email = (EditText) findViewById(R.id.email);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                Bundle b = i.getExtras();

                int syncStatus = b.getInt("sync_status");

                Log.v(TAG, "Sync status: "+syncStatus);

                TutorDAO tutorDAO = new TutorDAO(getApplicationContext());
                tutorDAO.openConection();
                Tutor tutor = tutorDAO.getTutorByEmail(email.getText().toString());
                tutorDAO.closeConection();

                if (tutor != null) {
                    if (tutor.getSenha().equals(senha.getText().toString())) {
                        Bundle bundle = new Bundle();

                        bundle.putInt("id_tutor", tutor.getId());
                        bundle.putString("nome_tutor", tutor.getNome());
                        bundle.putString("email_tutor", tutor.getEmail());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        showDialog("Senha informada não corresponde a senha do usuário!");
                    }
                } else {
                    showDialog("Email não encontrado!");
                }
            }
        });

    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Erro no login!");
        builder.setMessage(message);

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

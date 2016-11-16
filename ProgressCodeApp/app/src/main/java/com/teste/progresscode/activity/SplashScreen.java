package com.teste.progresscode.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.teste.progresscode.R;
import com.teste.progresscode.database.SyncDatabaseApi;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SyncDatabaseApi syncDatabaseApi = new SyncDatabaseApi(getApplicationContext());


        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int status = 0;
                int stateConection;
                try  {
                    stateConection = syncDatabaseApi.syncAllDatabase();
                    status = 1;

                    switch (stateConection){
                        case -1:
                            System.out.println("Servidor inacessível, você irá operar em modo offline!");
                            //showDialog("Servidor inacessível, você irá operar em modo offline!");
                        case 1:
                            System.out.println("Ocorreram erros durante a sincronização com o servidor!");
                            //showDialog("Ocorreram erros durante a sincronização com o servidor!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    status = -1;
                } finally {
                    Bundle bundle = new Bundle();
                    bundle.putInt("sync_status",status);
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });

        thread.start();
    }
    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
        builder.setTitle("Erro na conexão!");
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

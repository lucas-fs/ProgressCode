package com.teste.progresscode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.teste.progresscode.R;
import com.teste.progresscode.database.SyncDatabaseApi;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SyncDatabaseApi syncDatabaseApi = new SyncDatabaseApi(getApplicationContext());


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int status = 0;
                try  {
                    syncDatabaseApi.syncAllDatabase();
                    status = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    status = -1;
                } finally {
                    Bundle bundle = new Bundle();
                    bundle.putInt("sync_status",status);
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });

        thread.start();
    }
}

package com.teste.progresscode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.teste.progresscode.R;
import com.teste.progresscode.model.Inscrito;
import com.teste.progresscode.model.InscritoResponse;
import com.teste.progresscode.rest.ApiClient;
import com.teste.progresscode.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {

    private static final String TAG = AboutUsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<InscritoResponse> call = apiService.getInscritos();
        call.enqueue(new Callback<InscritoResponse>() {
            @Override
            public void onResponse(Call<InscritoResponse>call, Response<InscritoResponse> response) {
                List<Inscrito> inscritos = response.body().getResults();
                Log.d(TAG, "Numero de inscritos recebidos: " + inscritos.size());
            }

            @Override
            public void onFailure(Call<InscritoResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}

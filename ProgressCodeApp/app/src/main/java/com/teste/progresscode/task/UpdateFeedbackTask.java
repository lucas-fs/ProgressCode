package com.teste.progresscode.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.teste.progresscode.activity.AtividadesActivity;
import com.teste.progresscode.database.SyncDatabaseApi;

/**
 * Created by Lucas Ferreira da Silva.
 */

public class UpdateFeedbackTask extends AsyncTask <Void, Void, Void>{

    private Context context;

    public UpdateFeedbackTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... nope) {
        try {
            // Set a time to simulate a long update process.
            //Thread.sleep(4000);
            SyncDatabaseApi syncDatabaseApi = new SyncDatabaseApi(context);
            syncDatabaseApi.syncFeedback();
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void nope) {
        // Give some feedback on the UI.
        Toast.makeText(context, "Finished complex background function!",
                Toast.LENGTH_LONG).show();

        // Change the menu back
        ((AtividadesActivity) context).resetUpdating();
    }

}

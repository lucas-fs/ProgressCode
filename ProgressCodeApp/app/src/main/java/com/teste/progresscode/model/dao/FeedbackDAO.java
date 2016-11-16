package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.object.Feedback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class FeedbackDAO {
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public FeedbackDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertFeedback(Feedback feedback){

        ContentValues values = new ContentValues();

        values.put("id_tutor", feedback.getIdTutor());
        values.put("id_inscrito", feedback.getIdInscrito());
        values.put("id_atividade", feedback.getIdAtividade());
        values.put("status", feedback.getStatus());
        values.put("timestamp", feedback.getTimeStamp());
        values.put("dir_audio", feedback.getDirAudio());

        database.insert("feedback", null, values);
    }

    private Feedback cursorToFeedback(Cursor cursor) {
        Feedback feedback = new Feedback();

        feedback.setIdTutor(cursor.getInt(0));
        feedback.setIdInscrito(cursor.getInt(1));
        feedback.setIdAtividade(cursor.getInt(2));
        feedback.setStatus(cursor.getInt(3));
        feedback.setTimeStamp(cursor.getString(4));
        feedback.setDirAudio(cursor.getString(5));

        return feedback;
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<Feedback>();

        Cursor cursor = database.query("feedback", new  String[] {"id_tutor", "id_inscrito", "id_atividade", "status", "timestamp", "dir_audio"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Feedback feedback = cursorToFeedback(cursor);
            feedbacks.add(feedback);
            cursor.moveToNext();
        }

        cursor.close();
        return feedbacks;
    }
}

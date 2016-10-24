package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.Tutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class TutorDAO {
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public TutorDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertTutor(Tutor tutor){

        ContentValues values = new ContentValues();

        values.put("id", tutor.getId());
        values.put("nome", tutor.getNome());
        values.put("email", tutor.getEmail());
        values.put("senha", tutor.getSenha());

        database.insert("tutores", null, values);
    }

    private Tutor cursorToTutor(Cursor cursor) {
        Tutor tutor = new Tutor();

        tutor.setId(cursor.getInt(0));
        tutor.setNome(cursor.getString(1));
        tutor.setEmail(cursor.getString(2));
        tutor.setSenha(cursor.getString(3));

        return tutor;
    }

    public List<Tutor> getAllTutores() {
        List<Tutor> tutores = new ArrayList<Tutor>();

        Cursor cursor = database.query("tutores", new  String[] {"id", "nome", "email", "senha"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tutor tutor = cursorToTutor(cursor);
            tutores.add(tutor);
            cursor.moveToNext();
        }

        cursor.close();
        return tutores;
    }
}

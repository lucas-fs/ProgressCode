package com.teste.progresscode.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class EquipeTutorDAO {
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public EquipeTutorDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertEquipeTutor(EquipeTutor equipeTutor){

        ContentValues values = new ContentValues();

        values.put("equipe_id", equipeTutor.getIdEquipe());
        values.put("tutor_id", equipeTutor.getIdTutor());

        database.insert("equipe_tutor", null, values);
    }

    private EquipeTutor cursorToEquipeTutor(Cursor cursor) {
        EquipeTutor equipeTutor = new EquipeTutor();

        equipeTutor.setIdEquipe(cursor.getInt(0));
        equipeTutor.setIdTutor(cursor.getInt(1));

        return equipeTutor;
    }

    public List<EquipeTutor> getAllEquipeTutor() {
        List<EquipeTutor> equipeTutores = new ArrayList<EquipeTutor>();

        Cursor cursor = database.query("equipe_tutor", new  String[] {"equipe_id", "tutor_id"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EquipeTutor equipeTutor = cursorToEquipeTutor(cursor);
            equipeTutores.add(equipeTutor);
            cursor.moveToNext();
        }
        cursor.close();
        return equipeTutores;
    }
}

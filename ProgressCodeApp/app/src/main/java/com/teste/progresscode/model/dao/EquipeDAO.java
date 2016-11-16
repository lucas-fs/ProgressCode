package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.object.Equipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class EquipeDAO {
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public EquipeDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertEquipe(Equipe equipe){

        ContentValues values = new ContentValues();

        values.put("id", equipe.getId());
        values.put("descricao", equipe.getDescricao());

        database.insert("equipes", null, values);

    }

    private Equipe cursorToEquipe(Cursor cursor) {
        Equipe equipe = new Equipe();

        equipe.setId(cursor.getInt(0));
        equipe.setDescricao(cursor.getString(1));

        return equipe;
    }

    public List<Equipe> getAllEquipes() {
        List<Equipe> equipes = new ArrayList<Equipe>();

        Cursor cursor = database.query("equipes", new  String[] {"id", "descricao"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Equipe equipe = cursorToEquipe(cursor);
            equipes.add(equipe);
            cursor.moveToNext();
        }

        cursor.close();
        return equipes;
    }
}

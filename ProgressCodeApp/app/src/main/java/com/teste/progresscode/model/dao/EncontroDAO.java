package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.Atividade;
import com.teste.progresscode.model.Encontro;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by icorrea on 23/10/16.
 */

public class EncontroDAO {

    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public EncontroDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertEncontro(Encontro encontro){

        ContentValues values = new ContentValues();

        values.put("id", encontro.getId());
        values.put("data_realizacao", encontro.getDataRealizao());
        values.put("evento_id", encontro.getIdEvento());

        database.insert("encontros", null, values);
    }

    private Atividade cursorToAtividade(Cursor cursor){
        Atividade atividade = new Atividade();

        atividade.setId(cursor.getInt(0));
        atividade.setDescricao(cursor.getString(1));
        atividade.setIdEncontro(cursor.getInt(2));

        return atividade;
    }

    public List<Atividade> getAllAtividades(){
        List<Atividade> atividades = new ArrayList<>();

        Cursor cursor = database.query("encontros", new  String[] {"id", "data_realizacao", "evento_id"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Atividade atividade = cursorToAtividade(cursor);
            atividades.add(atividade);
            cursor.moveToNext();
        }
        cursor.close();
        return atividades;
    }
}

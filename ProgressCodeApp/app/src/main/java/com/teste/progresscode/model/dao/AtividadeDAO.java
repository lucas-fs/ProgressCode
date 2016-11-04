package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.Atividade;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by icorrea on 23/10/16.
 */

public class AtividadeDAO {

    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public AtividadeDAO(Context context){
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertAtividade(Atividade atividade){
        ContentValues values = new ContentValues();

        values.put("id", atividade.getId());
        values.put("descricao", atividade.getDescricao());
        values.put("id_encontro", atividade.getIdEncontro());

        database.insert("atividades", null, values);
    }

    public List<Atividade> getAllAtividades(){
        List<Atividade> atividades = new ArrayList<>();

        Cursor cursor = database.query("atividades", new String[] {"id", "descricao", "id_encontro"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Atividade atividade = cursorToAtividade(cursor);
            atividades.add(atividade);
            cursor.moveToNext();
        }
        cursor.close();
        return atividades;
    }

    public Atividade cursorToAtividade(Cursor cursor){
        Atividade atividade = new Atividade();

        atividade.setId(cursor.getInt(0));
        atividade.setDescricao(cursor.getString(1));
        atividade.setIdEncontro(cursor.getInt(2));

        return atividade;
    }
}

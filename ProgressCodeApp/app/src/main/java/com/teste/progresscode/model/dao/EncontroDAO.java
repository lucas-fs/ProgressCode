package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
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
        values.put("id_evento", encontro.getIdEvento());

        database.insert("encontros", null, values);
    }

    private Encontro cursorToEncontro(Cursor cursor){
        Encontro encontro = new Encontro();

        encontro.setId(cursor.getInt(0));
        encontro.setDataRealizao(cursor.getString(1));
        encontro.setIdEvento(cursor.getInt(2));

        return encontro;
    }

    public List<Encontro> getAllEncontros(){
        List<Encontro> encontros = new ArrayList<>();

        Cursor cursor = database.query("encontros", new  String[] {"id", "data_realizacao", "id_evento"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Encontro encontro = cursorToEncontro(cursor);
            encontros.add(encontro);
            cursor.moveToNext();
        }
        cursor.close();
        return encontros;
    }
}

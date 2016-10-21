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
 * Created by Lucas Ferreira da Silva.
 */

public class InscritoDAO {

    // Database fields
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public InscritoDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertInscrito(Inscrito inscrito){

        ContentValues values = new ContentValues();

        values.put("id", inscrito.getId());
        values.put("nome", inscrito.getNome());
        values.put("escola", inscrito.getEscola());
        values.put("data_nasc", inscrito.getEscola());

        database.insert("inscrito", null, values);

    }


    public List<Inscrito> getAllInscritos() {
        List<Inscrito> inscritos = new ArrayList<Inscrito>();

        Cursor cursor = database.query("inscrito", new  String[] {"id", "nome", "escola", "data_nasc"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Inscrito inscrito = cursorToInscrito(cursor);
            inscritos.add(inscrito);
            cursor.moveToNext();
        }

        cursor.close();
        return inscritos;
    }



    private Inscrito cursorToInscrito(Cursor cursor) {
        Inscrito inscrito = new Inscrito();

        inscrito.setId(cursor.getInt(0));
        inscrito.setNome(cursor.getString(1));
        inscrito.setEscola(cursor.getString(2));
        inscrito.setDataNasc(cursor.getString(3));

        return inscrito;
    }
}

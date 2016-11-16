package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.object.Evento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class EventoDAO {

    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public EventoDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertEvento(Evento evento){

        ContentValues values = new ContentValues();

        values.put("id", evento.getId());
        values.put("nome_evento", evento.getNomeEvento());
        values.put("descricao", evento.getDescricao());


        database.insert("eventos", null, values);

    }

    private Evento cursorToEvento(Cursor cursor) {
        Evento evento = new Evento();

        evento.setId(cursor.getInt(0));
        evento.setNomeEvento(cursor.getString(1));
        evento.setDescricao(cursor.getString(2));

        return evento;
    }

    public List<Evento> getAllEventos() {
        List<Evento> eventos = new ArrayList<Evento>();

        Cursor cursor = database.query("eventos", new  String[] {"id", "nome_evento", "descricao"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Evento evento = cursorToEvento(cursor);
            eventos.add(evento);
            cursor.moveToNext();
        }

        cursor.close();
        return eventos;
    }
}

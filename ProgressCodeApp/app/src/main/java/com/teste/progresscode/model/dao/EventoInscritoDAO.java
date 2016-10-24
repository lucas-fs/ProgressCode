package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.EventoInscrito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class EventoInscritoDAO {
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public EventoInscritoDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertEventoInscrito(EventoInscrito eventoInscrito){

        ContentValues values = new ContentValues();

        values.put("id_evento", eventoInscrito.getIdEvento());
        values.put("id_inscrito", eventoInscrito.getIdInscrito());

        database.insert("evento_inscrito", null, values);
    }

    private EventoInscrito cursorToEventoInscrito(Cursor cursor) {
        EventoInscrito eventoInscrito = new EventoInscrito();

        eventoInscrito.setIdEvento(cursor.getInt(0));
        eventoInscrito.setIdInscrito(cursor.getInt(1));

        return eventoInscrito;
    }

    public List<EventoInscrito> getAllEventoInscritos() {
        List<EventoInscrito> eventoInscritos = new ArrayList<EventoInscrito>();

        Cursor cursor = database.query("evento_inscrito", new  String[] {"id_evento", "id_inscrito"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EventoInscrito eventoInscrito = cursorToEventoInscrito(cursor);
            eventoInscritos.add(eventoInscrito);
            cursor.moveToNext();
        }

        cursor.close();
        return eventoInscritos;
    }
}

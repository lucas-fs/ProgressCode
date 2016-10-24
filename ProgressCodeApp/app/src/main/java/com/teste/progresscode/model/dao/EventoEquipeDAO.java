package com.teste.progresscode.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.teste.progresscode.database.DbSQLiteHelper;
import com.teste.progresscode.model.EventoEquipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icorrea on 23/10/16.
 */

public class EventoEquipeDAO {
    private SQLiteDatabase database;
    private DbSQLiteHelper dbHelper;

    public EventoEquipeDAO(Context context) {
        dbHelper = new DbSQLiteHelper(context);
    }

    public void openConection() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void closeConection() {
        dbHelper.close();
    }

    public void insertEventoEquipe(EventoEquipe eventoEquipe){

        ContentValues values = new ContentValues();

        values.put("id_evento", eventoEquipe.getIdEvento());
        values.put("id_equipe", eventoEquipe.getIdEquipe());

        database.insert("evento_equipe", null, values);
    }

    private EventoEquipe cursorToEventoEquipe(Cursor cursor) {
        EventoEquipe eventoEquipe = new EventoEquipe();

        eventoEquipe.setIdEvento(cursor.getInt(0));
        eventoEquipe.setIdEquipe(cursor.getInt(1));

        return eventoEquipe;
    }

    public List<EventoEquipe> getAllEventoEquipes() {
        List<EventoEquipe> eventoEquipes = new ArrayList<EventoEquipe>();

        Cursor cursor = database.query("evento_equipe", new  String[] {"id_evento", "id_equipe"}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EventoEquipe eventoEquipe = cursorToEventoEquipe(cursor);
            eventoEquipes.add(eventoEquipe);
            cursor.moveToNext();
        }

        cursor.close();
        return eventoEquipes;
    }
}

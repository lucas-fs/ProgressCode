package com.teste.progresscode.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lucas Ferreira da Silva
 */

public class DbSQLiteHelper extends SQLiteOpenHelper {

    private Context context;

    // TAG para os logs
    private static final String TAG = DbSQLiteHelper.class.getSimpleName();

    // Informacoes do banco de dados
    private static final String DATABASE_NAME = "progresscode.db";
    private static final int DATABASE_VERSION = 1;

    // SQL de criacao das tabelas
    public static final String CREATE_TABLE_TUTOR = "CREATE TABLE " +
            "tutor (" +
            "id INTEGER PRIMARY KEY, " +
            "email TEXT NOT NULL, " +
            "nome TEXT NOT NULL, " +
            "senha TEXT NOT NULL" +
            ");";

    public static final String CREATE_TABLE_EQUIPE = "CREATE TABLE " +
            "equipe (" +
            "id INTEGER PRIMARY KEY, " +
            "descricao TEXT" +
            ");";

    public static final String CREATE_TABLE_EVENTO = "CREATE TABLE " +
            "evento (" +
            "id INTEGER PRIMARY KEY, " +
            "nome_evento TEXT NOT NULL, " +
            "descricao TEXT" +
            ");";

    public static final String CREATE_TABLE_INSCRITOS = "CREATE TABLE " +
            "inscritos (" +
            "id INTEGER PRIMARY KEY, " +
            "data_nasc TEXT, " +
            "nome TEXT NOT NULL, " +
            "escola TEXT" +
            ");";

    public static final String CREATE_TABLE_ENCONTRO = "CREATE TABLE " +
            "encontro (" +
            "id INTEGER PRIMARY KEY, " +
            "data_realizacao TEXT, " +
            "id_evento INTEGER NOT NULL, " +
            "FOREIGN KEY(id_evento) REFERENCES evento(id)" +
            ");";

    public static final String CREATE_TABLE_ATIVIDADES = "CREATE TABLE " +
            "atividades(" +
            "id INTEGER PRIMARY KEY, " +
            "descricao TEXT NOT NULL, " +
            "id_encontro INTEGER NOT NULL, " +
            "FOREIGN KEY(id_encontro) REFERENCES encontro(id)" +
            ");";

    public static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE " +
            "feedback(" +
            "id_tutor INTEGER PRIMARY KEY, " +
            "id_inscrito INTEGER PRIMARY KEY, " +
            "id_atividade INTEGER PRIMARY KEY, " +
            "status INTEGER NOT NULL, " +
            "timestamp TEXT NOT NULL, " +
            "dir_audio TEXT NOT NULL, " +
            "FOREIGN KEY(id_tutor) REFERENCES tutor(id), " +
            "FOREIGN KEY(id_inscrito) REFERENCES inscrito(id), " +
            "FOREIGN KEY(id_atividade) REFERENCES atividade(id)" +
            ");";

    public static final String CREATE_TABLE_EQUI_TUTOR = "CREATE TABLE " +
            "equipe_tutor (" +
            "id_equipe INTEGER PRIMARY KEY, " +
            "id_tutor INTEGER PRIMARY KEY, " +
            "FOREIGN KEY(id_equipe) REFERENCES equipe(id), " +
            "FOREIGN KEY(id_tutor) REFERENCES tutor(id)" +
            ");";

    public static final String CREATE_TABLE_EVEN_EQUI = "CREATE TABLE " +
            "evento_equipe (" +
            "id_evento INTEGER PRIMARY KEY, " +
            "id_equipe INTEGER PRIMARY KEY, " +
            "FOREIGN KEY(id_evento) REFERENCES evento(id), " +
            "FOREIGN KEY(id_equipe) REFERENCES equipe(id)" +
            ");";

    public static final String CREATE_TABLE_EVEN_INSC = "CREATE TABLE " +
            "evento_inscrito (" +
            "id_evento INTEGER PRIMARY KEY, " +
            "id_inscrito INTEGER PRIMARY KEY, " +
            "FOREIGN KEY(id_evento) REFERENCES evento(id), " +
            "FOREIGN KEY(id_inscrito) REFERENCES inscrito(id)" +
            ");";

    // SQL de criacao do banco de dados
    public static final String CREATE_DATABASE = "" +
            " " + CREATE_TABLE_TUTOR +
            " " + CREATE_TABLE_EQUIPE +
            " " + CREATE_TABLE_EVENTO +
            " " + CREATE_TABLE_INSCRITOS +
            " " + CREATE_TABLE_ENCONTRO +
            " " + CREATE_TABLE_ATIVIDADES +
            " " + CREATE_TABLE_FEEDBACK +
            " " + CREATE_TABLE_EQUI_TUTOR +
            " " + CREATE_TABLE_EVEN_EQUI +
            " " + CREATE_TABLE_EVEN_INSC;

    public DbSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Atualizando o banco de dados da versão: " + oldVersion + " para "
                + newVersion + ", todos dados antigos serão apagados!");

        context.deleteDatabase(DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

}

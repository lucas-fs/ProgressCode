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
    private static final int DATABASE_VERSION = 2;

    // SQL de criacao das tabelas
    public static final String CREATE_TABLE_TUTOR = "CREATE TABLE IF NOT EXISTS " +
            "tutores (" +
            "id INTEGER PRIMARY KEY, " +
            "email TEXT NOT NULL, " +
            "nome TEXT NOT NULL, " +
            "senha TEXT NOT NULL" +
            ");";

    public static final String CREATE_TABLE_EQUIPE = "CREATE TABLE IF NOT EXISTS " +
            "equipes (" +
            "id INTEGER PRIMARY KEY, " +
            "descricao TEXT" +
            ");";

    public static final String CREATE_TABLE_EVENTO = "CREATE TABLE IF NOT EXISTS " +
            "eventos (" +
            "id INTEGER PRIMARY KEY, " +
            "nome_evento TEXT NOT NULL, " +
            "descricao TEXT" +
            ");";

    public static final String CREATE_TABLE_INSCRITOS = "CREATE TABLE IF NOT EXISTS " +
            "inscritos (" +
            "id INTEGER PRIMARY KEY, " +
            "data_nasc TEXT, " +
            "nome TEXT NOT NULL, " +
            "escola TEXT" +
            ");";

    public static final String CREATE_TABLE_ENCONTRO = "CREATE TABLE IF NOT EXISTS " +
            "encontros (" +
            "id INTEGER PRIMARY KEY, " +
            "data_realizacao TEXT, " +
            "id_evento INTEGER NOT NULL, " +
            "FOREIGN KEY (id_evento) REFERENCES evento(id)" +
            ");";

    public static final String CREATE_TABLE_ATIVIDADES = "CREATE TABLE IF NOT EXISTS " +
            "atividades (" +
            "id INTEGER PRIMARY KEY, " +
            "descricao TEXT NOT NULL, " +
            "id_encontro INTEGER NOT NULL, " +
            "FOREIGN KEY (id_encontro) REFERENCES encontro(id)" +
            ");";

    public static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE IF NOT EXISTS " +
            "feedback (" +
            "id_tutor INTEGER NOT NULL, " +
            "id_inscrito INTEGER NOT NULL, " +
            "id_atividade INTEGER NOT NULL, " +
            "status INTEGER NOT NULL, " +
            "timestamp TEXT NOT NULL, " +
            "dir_audio TEXT , " +
            "FOREIGN KEY (id_tutor) REFERENCES tutor(id), " +
            "FOREIGN KEY (id_inscrito) REFERENCES inscrito(id), " +
            "FOREIGN KEY (id_atividade) REFERENCES atividade(id), " +
            "PRIMARY KEY (id_tutor, id_inscrito, id_atividade) " +
            ");";

    public static final String CREATE_TABLE_EQUI_TUTOR = "CREATE TABLE IF NOT EXISTS " +
            "equipe_tutor (" +
            "id_equipe INTEGER NOT NULL, " +
            "id_tutor INTEGER NOT NULL, " +
            "FOREIGN KEY (id_equipe) REFERENCES equipe(id), " +
            "FOREIGN KEY (id_tutor) REFERENCES tutor(id), " +
            "PRIMARY KEY (id_tutor, id_equipe) " +
            ");";

    public static final String CREATE_TABLE_EVEN_EQUI = "CREATE TABLE IF NOT EXISTS " +
            "evento_equipe (" +
            "id_evento INTEGER NOT NULL, " +
            "id_equipe INTEGER NOT NULL, " +
            "FOREIGN KEY (id_evento) REFERENCES evento(id), " +
            "FOREIGN KEY (id_equipe) REFERENCES equipe(id), " +
            "PRIMARY KEY (id_evento, id_equipe) " +
            ");";

    public static final String CREATE_TABLE_EVEN_INSC = "CREATE TABLE IF NOT EXISTS " +
            "evento_inscrito (" +
            "id_evento INTEGER NOT NULL, " +
            "id_inscrito INTEGER NOT NULL, " +
            "FOREIGN KEY (id_evento) REFERENCES evento(id), " +
            "FOREIGN KEY (id_inscrito) REFERENCES inscrito(id), " +
            "PRIMARY KEY (id_evento, id_inscrito) " +
            ");";

    /*
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

    */
    public DbSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /*
    public void recreateDatabase(Context context, SQLiteDatabase sqLiteDatabase){
        context.deleteDatabase(DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }


    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
    }
    */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(CREATE_DATABASE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TUTOR);
        sqLiteDatabase.execSQL(CREATE_TABLE_EQUIPE);
        sqLiteDatabase.execSQL(CREATE_TABLE_EQUI_TUTOR);
        sqLiteDatabase.execSQL(CREATE_TABLE_EVENTO);
        sqLiteDatabase.execSQL(CREATE_TABLE_EVEN_EQUI);
        sqLiteDatabase.execSQL(CREATE_TABLE_INSCRITOS);
        sqLiteDatabase.execSQL(CREATE_TABLE_EVEN_INSC);
        sqLiteDatabase.execSQL(CREATE_TABLE_ENCONTRO);
        sqLiteDatabase.execSQL(CREATE_TABLE_ATIVIDADES);
        sqLiteDatabase.execSQL(CREATE_TABLE_FEEDBACK);

        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");

        Log.w(TAG, "Banco criado!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.w(TAG, "Atualizando o banco de dados da versão: " + oldVersion + " para "
                + newVersion + ", todos dados antigos serão apagados!");

        if(context.deleteDatabase(DATABASE_NAME))
            Log.w(TAG, "Banco deletado!");
        else
            Log.w(TAG, "O banco não foi deletado!");
        onCreate(sqLiteDatabase);
    }

}

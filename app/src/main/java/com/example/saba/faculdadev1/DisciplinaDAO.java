package com.example.saba.faculdadev1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class DisciplinaDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "BancoDisciplinas";

    private static final int VERSAO = 1;

    public DisciplinaDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE tipo_exame (id_tipo_exame INTEGER PRIMARY KEY,"
                + " tipo TEXT UNIQUE NOT NULL);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int velha, int nova) {
        String ddl = "DROP TABLE IF EXISTS tipo_exame";
        db.execSQL(ddl);
        onCreate(db);
    }

    public void dropAll(){
        String ddl ="DROP TABLE IF EXISTS tipo_exame";
        getWritableDatabase().execSQL(ddl);
        onCreate( getWritableDatabase());

    }

    public void salvar(DisciplinaValue disciplinaValue) {
        ContentValues values = new ContentValues();
        values.put("id_tipo_exame", disciplinaValue.getIdTipoExame());
        values.put("tipo", disciplinaValue.getTipo());

        getWritableDatabase().insert("tipo_exame", null, values);

    }

    public void deletar(DisciplinaValue disciplinaValue) {
        String[] args = { disciplinaValue.getIdTipoExame().toString() };
        getWritableDatabase().delete("tipo_exame", "id_tipo_exame=?", args);
    }


    public void alterar(DisciplinaValue disciplina) {
        ContentValues values = new ContentValues();
        values.put("tipo", disciplina.getTipo());

        getWritableDatabase().update("tipo_exame", values,
                "id_tipo_exame=?", new String[]{disciplina.getIdTipoExame().toString()});
    }

    public List getLista(){

        List<DisciplinaValue> disciplinasLista = new LinkedList<DisciplinaValue>();

        String query = "SELECT * FROM tipo_exame order by tipo";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DisciplinaValue disciplinaValue = null;

        if (cursor.moveToFirst()) {
            do {
                disciplinaValue = new DisciplinaValue();
                disciplinaValue.setIdTipoExame(Long.parseLong(cursor.getString(0)));
                disciplinaValue.setTipo(cursor.getString(1));

                disciplinasLista.add(disciplinaValue);

            } while (cursor.moveToNext());
        }
        return disciplinasLista;
    }

}
package mx.edu.ittepic.u4p1_joseantoniorivasnavarrete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {
    public BD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta cuando la aplicación (ejercicio 1) se ejecuta en el CEL
        //Sirve para construir en el SQLite que está en el CEL las tablas
        //que la APP requere para funcionar.
        db.execSQL("CREATE TABLE RECETA(ID INTEGER PRIMARY KEY, NOMBRE VARCHAR(200), INGREDIENTES VARCHAR(1000),PREPARACION VARCHAR(1000),OBSERVACIONES VARCHAR(500))");
        //No funciona para select
        //db.rawQuery(  )
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
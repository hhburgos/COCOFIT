package com.example.cocofit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cocofit.recursos.Constantes;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //CONSTRUCTOR
    public AdminSQLiteOpenHelper(Context context) {
        super(context, Constantes.NOMBRE_DB, null, Constantes.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.CREA_TABLA_USUARIOS);
        db.execSQL(Constantes.CREA_TABLA_CALCULO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constantes.TABLA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS "+ Constantes.TABLA_CALCULO);

        onCreate(db);
    }
}

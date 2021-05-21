package com.example.cocofit.recursos;

import android.database.sqlite.SQLiteDatabase;

import com.example.cocofit.AdminSQLiteOpenHelper;
import com.example.cocofit.Calculo;

public class AtacaBD {

    public static boolean actualizaDataCalculo (AdminSQLiteOpenHelper admin, String table, String campo, int data, String nick) {
        boolean dev = false;
        try{
            SQLiteDatabase db = admin.getWritableDatabase();
            db.execSQL("update " + table + " set " + campo + " = " + data + " where " + Constantes.CAMPO_NICKNAME + " = '" + nick + "'' ;");
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dev;
    }
}

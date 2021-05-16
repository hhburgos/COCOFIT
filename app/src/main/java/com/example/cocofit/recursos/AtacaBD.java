package com.example.cocofit.recursos;

import android.database.sqlite.SQLiteDatabase;

import com.example.cocofit.AdminSQLiteOpenHelper;

public class AtacaBD {

    public static boolean actualizaDataCalculo (AdminSQLiteOpenHelper admin, String table, String campo, int data) {
        boolean dev = false;
        try{
            SQLiteDatabase db = admin.getWritableDatabase();
            db.execSQL("update " + table + " set " + campo + " = " + data + " ;");
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dev;
    }
}

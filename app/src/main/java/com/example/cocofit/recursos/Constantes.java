package com.example.cocofit.recursos;

import android.content.Context;
import android.widget.Toast;

public class Constantes {

    public static final int VERSION_DB = 1;
    public static final String NOMBRE_DB = "cocofit.db";

    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_CALCULO = "calculo";

    public static final String CAMPO_NICKNAME = "nickname";

    public static final String CREA_TABLA_USUARIOS = "create table usuarios (nickname text primary key, fecha_registro date)";
    public static final String CREA_TABLA_CALCULO = "create table calculo (nickname text primary key, level int, code int)";

    public static void alert (String mensaje, Context c ) {
        Toast.makeText(c, mensaje, Toast.LENGTH_LONG).show();
    }
}

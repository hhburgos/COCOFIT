package com.example.cocofit.recursos;

import android.content.Context;
import android.widget.Toast;

public class Constantes {

    //SECCION NOTICIAS
    public static final String URL_NOTICIAS_FISICA = "https://www.robotitus.com/category/fisica";
    public static final String URL_NOTICIAS_ESPACIO = "https://www.robotitus.com/category/espacio";
    public static final String URL_NOTICIAS_NATURALEZA = "https://www.robotitus.com/category/naturaleza";
    public static final String URL_NOTICIAS_MEDIOAMBIENTE = "https://www.robotitus.com/category/la-tierra";
    public static final String URL_NOTICIAS_MEDICINA = "https://www.robotitus.com/category/medicina";
    public static final String URL_NOTICIAS_CURIOSIDADES = "https://www.robotitus.com/category/sapiencias";

    //SECCION CAPITALES
    public static final String CONTINENTE_EUROPA = "Europa";

    //SECCION CALCULO
    public static final String SIGNO_SUMA = "+";
    public static final String SIGNO_RESTA = "-";
    public static final String SIGNO_MULTIPLICA = "x";
    public static final String SIGNO_DIVIDE = "/";

    public static final int CONF_CALCULO_DEFAULT = 1;

    public static final int NIVEL_DEFAULT = 1;

    //BASE DE DATOS
    public static final int VERSION_DB = 2;
    public static final String NOMBRE_DB = "cocofit.db";

    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_CALCULO = "calculo";
    public static final String TABLA_CAPITALES = "capitales";

    public static final String CAMPO_NICKNAME = "nickname";
    public static final String CAMPO_FECHA_REGISTRO = "fecha_registro";
    public static final String CAMPO_CONTINENTE = "continente";

    public static final String CAMPO_NIVEL = "level";
    public static final String CAMPO_CONF = "conf";

    public static final String CREA_TABLA_USUARIOS = "create table usuarios (nickname text primary key, fecha_registro date)";
    public static final String CREA_TABLA_CALCULO = "create table calculo (nickname text primary key, level int, conf int)";
    public static final String CREA_TABLA_CAPITALES = "create table capitales (id int primary key, pais text, capital text, continente text, acierto int)";

    public static void alert (String mensaje, Context c ) {
        Toast.makeText(c, mensaje, Toast.LENGTH_LONG).show();
    }
}

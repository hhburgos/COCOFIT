package com.example.cocofit.secciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cocofit.R;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Memoria2 extends AppCompatActivity implements OnClickListener {

    private ImageButton imb00,imb01,imb02,imb03,imb04,imb05,imb06,imb07,imb08,imb09,imb10,imb11,imb12,imb13,imb14,imb15;
    private ImageButton [] tablero = new ImageButton[16];
    private Button btReiniciar, btSalir;
    private TextView tvPuntuacion;
    private int puntuacion, aciertos;

    //imagenes
    int [] imagenes;
    int fondo;

    //variables de juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numero_primero, numero_segundo;
    boolean bloqueo = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria2);

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton00: break;
            case R.id.boton01: break;
            case R.id.boton02: break;
            case R.id.boton03: break;
            case R.id.boton04: break;
            case R.id.boton05: break;
            case R.id.boton06: break;
            case R.id.boton07: break;
            case R.id.boton08: break;
            case R.id.boton09: break;
            case R.id.boton10: break;
            case R.id.boton11: break;
            case R.id.boton12: break;
            case R.id.boton13: break;
            case R.id.boton14: break;
            case R.id.boton15: break;
            case R.id.btReiniciar: init(); break;
            case R.id.btSalir: finish(); break;
            default:break;
        }
    }

    public void init () {
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for (int i = 0; i < tablero.length; i++) {
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
        }
    }

    public ArrayList<Integer> barajar (int longitud) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < longitud*2; i++) {
            result.add(i%longitud);
        }
        Collections.shuffle(result);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public void cargarImagenes () {
        imagenes = new int[] {
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4,
                R.drawable.la5,
                R.drawable.la6,
                R.drawable.la7,
        };
        fondo = R.drawable.fondo;
    }

    public void cargarBotones () {
        btReiniciar = findViewById(R.id.btReiniciar);
        btSalir = findViewById(R.id.btSalir);
    }

    private void cargarTexto () {
        tvPuntuacion = findViewById(R.id.tvPuntuacion);
        puntuacion = 0;
        aciertos = 0;
        tvPuntuacion.setText("Puntuación");
    }

    public void cargarTablero () {
        imb00 = findViewById(R.id.boton00);
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);
        imb12 = findViewById(R.id.boton12);
        imb13 = findViewById(R.id.boton13);
        imb14 = findViewById(R.id.boton14);
        imb15 = findViewById(R.id.boton15);

        btSalir = findViewById(R.id.btSalir);
        btReiniciar = findViewById(R.id.btReiniciar);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;

        btReiniciar.setOnClickListener(this);
        btSalir.setOnClickListener(this);
        
    //https://www.youtube.com/watch?v=JsP9MuexS88&list=PL4bT56Uw3S4y_7KT-1E3RFqASzo_RNsVW&index=4
        //13:00
    }
}
package com.example.cocofit.subsecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.cocofit.R;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;
import com.example.cocofit.secciones.Capitales;

public class EligeContinentesCapitales extends AppCompatActivity implements OnClickListener {

    private Button btContinuar, btAfrica, btAmerica, btAsia, btEuropa, btOceania, btAtrasWey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elige_continentes_capitales);

        inicializa();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btEuropa: gestionaCheckBox("europa"); break;
            case R.id.btAsia:
                gestionaCheckBox("asia"); break;
            case R.id.btAmerica:
                gestionaCheckBox("america"); break;
            case R.id.btAfrica:
                gestionaCheckBox("africa"); break;
            case R.id.btOceania:
                gestionaCheckBox("oceania"); break;
            case R.id.btContinuar: Intent i = new Intent(this, Capitales.class);
                startActivity(i); break;
            case R.id.btAtrasWey: this.finish(); break;
            default: break;
        }
    }

    public void gestionaCheckBox (String continente) {
        if (continente.equals("europa")) {
            if (Variables.europa) {
                Variables.europa = false;
                btEuropa.setBackgroundColor(Color.parseColor("#4A4848"));
                //Constantes.alert("Europa Desactivado",this);
            } else {
                Variables.europa = true;
                btEuropa.setBackgroundColor(Color.parseColor("#74ad10"));
                //Constantes.alert("Europa Activado", this);
            }
        }
        else if (continente.equals("asia")) {
            if (Variables.asia) {
                Variables.asia = false;
                btAsia.setBackgroundColor(Color.parseColor("#4A4848"));
                //Constantes.alert("asia Desactivado",this);
            } else {
                Variables.asia = true;
                btAsia.setBackgroundColor(Color.parseColor("#74ad10"));
                //Constantes.alert("asia Activado", this);
            }
        }
        else if (continente.equals("africa")) {
            if (Variables.africa) {
                Variables.africa = false;
                btAfrica.setBackgroundColor(Color.parseColor("#4A4848"));
                //Constantes.alert("africa Desactivado",this);
            } else {
                Variables.africa  = true;
                btAfrica.setBackgroundColor(Color.parseColor("#74ad10"));
                //Constantes.alert("africa Activado", this);
            }
        }
        else if (continente.equals("america")) {
            if (Variables.america) {
                Variables.america = false;
                btAmerica.setBackgroundColor(Color.parseColor("#4A4848"));
                //Constantes.alert("america Desactivado",this);
            } else {
                Variables.america = true;
                btAmerica.setBackgroundColor(Color.parseColor("#74ad10"));
                //Constantes.alert("america Activado", this);
            }
        }
        else if (continente.equals("oceania")) {
            if (Variables.oceania) {
                Variables.oceania = false;
                btOceania.setBackgroundColor(Color.parseColor("#4A4848"));
                //Constantes.alert("oceania Desactivado",this);
            } else {
                Variables.oceania = true;
                btOceania.setBackgroundColor(Color.parseColor("#74ad10"));
                //Constantes.alert("oceania Activado", this);
            }
        }
        else {
            Constantes.alert("sELECCION RARA",this);
        }
    }

    public void inicializa () {
        Variables.africa = false;
        Variables.america = false;
        Variables.asia = false;
        Variables.europa = false;
        Variables.oceania = false;

        btContinuar = findViewById(R.id.btContinuar);
        btAfrica = findViewById(R.id.btAfrica);
        btAmerica  = findViewById(R.id.btAmerica);
        btAsia  = findViewById(R.id.btAsia);
        btEuropa  = findViewById(R.id.btEuropa);
        btOceania = findViewById(R.id.btOceania);
        btAtrasWey = findViewById(R.id.btAtrasWey);

        btContinuar.setOnClickListener(this);
        btAfrica.setOnClickListener(this);
        btAmerica.setOnClickListener(this);
        btAsia.setOnClickListener(this);
        btEuropa.setOnClickListener(this);
        btOceania.setOnClickListener(this);
        btAtrasWey.setOnClickListener(this);
    }
}
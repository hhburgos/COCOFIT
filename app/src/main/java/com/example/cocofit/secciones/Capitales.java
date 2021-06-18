package com.example.cocofit.secciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cocofit.R;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

import java.util.ArrayList;
import android.view.View.OnClickListener;

public class Capitales extends AppCompatActivity implements OnClickListener{

    Button btComprobar, btMostrar, btAtrasCapitales;
    EditText etRespuesta;
    TextView tvPregunta, tvContinente, tvErrores, tvAciertos;

    ArrayList<String> paises = new ArrayList<String>();
    ArrayList<String> capitales = new ArrayList<String>();
    ArrayList<String> continentes = new ArrayList<String>();

    int aciertos = 0;
    int fallos = 0;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitales);

        inicializar();
        cargaAfricaAsiaAmerica();
        muestraPregunta();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btComprobar.getId()) {
            comprueba();
        }
        else if (v.getId() == btMostrar.getId()) {
            etRespuesta.setText(capitales.get(n));
            aciertos --;
        }
        else if (v.getId() == btAtrasCapitales.getId()) {
            this.finish();
        }
    }

    public void comprueba () {
        String respuesta = etRespuesta.getText().toString();
        if (respuesta.equals(capitales.get(n))) {
            aciertos++;
            n++;
            tvAciertos.setText(""+aciertos);
            muestraPregunta();
        } else {
            fallos ++;
            tvErrores.setText(""+fallos);
            etRespuesta.setText("");
        }
    }

    public void inicializar () {
        btComprobar = findViewById(R.id.btCompruebaCapital);
        btMostrar = findViewById(R.id.btMostrar);
        btAtrasCapitales = findViewById(R.id.btAtrasCapitales);
        etRespuesta = findViewById(R.id.etRespuesta);
        tvPregunta = findViewById(R.id.tvPregunta);
        tvContinente = findViewById(R.id.tvContinente);
        tvAciertos = findViewById(R.id.tvAciertos);
        tvErrores = findViewById(R.id.tvFallos);

        btComprobar.setOnClickListener(this);
        btAtrasCapitales.setOnClickListener(this);
        btMostrar.setOnClickListener(this);
    }

    public void muestraPregunta() {
        tvContinente.setText(continentes.get(n));
        tvPregunta.setText(paises.get(n));
        etRespuesta.setText("");
    }

    public void cargaAfricaAsiaAmerica () {
        if (Variables.africa && Variables.asia) {
            continentes.add("ASIA");
            paises.add("Arabia Saudita");
            capitales.add("Riad");

            continentes.add("ÁFRICA");
            paises.add("Kenia");
            capitales.add("Nairobi");

            continentes.add("ÁFRICA");
            paises.add("Libia");
            capitales.add("Trípoli");

            continentes.add("ASIA");
            paises.add("Mongolia");
            capitales.add("Ulan Bator");

            continentes.add("ÁFRICA");
            paises.add("Togo");
            capitales.add("Lomé");

            continentes.add("ASIA");
            paises.add("Corea del sur");
            capitales.add("Seúl");
        } else if (Variables.europa) {
            continentes.add("EUROPA");
            paises.add("Portugal");
            capitales.add("Lisboa");

            continentes.add("EUROPA");
            paises.add("Alemania");
            capitales.add("Berlín");

            continentes.add("EUROPA");
            paises.add("Rusia");
            capitales.add("Moscú");

            continentes.add("EUROPA");
            paises.add("Portugal");
            capitales.add("Lisboa");
        }
        else{
            continentes.add("AMÉRICA");
            paises.add("Perú");
            capitales.add("Lima");

            continentes.add("ÁFRICA");
            paises.add("Angola");
            capitales.add("Luanda");

            continentes.add("ASIA");
            paises.add("Japón");
            capitales.add("Tokio");

            continentes.add("AMÉRICA");
            paises.add("Chile");
            capitales.add("Santiago de Chile");
        }

    }


}
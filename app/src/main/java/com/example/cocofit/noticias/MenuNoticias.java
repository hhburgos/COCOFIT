package com.example.cocofit.noticias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.cocofit.R;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

public class MenuNoticias extends AppCompatActivity implements OnClickListener {

    private Button btFisica, btEspacio, btMedicina, btCuriosidades, btMedioAmbiente, btNaturaleza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_noticias);

        inicializa();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btFisica: Variables.LINK_NOTICIAS_ACTIVO = Constantes.URL_NOTICIAS_FISICA; break;
            case R.id.btEspacio: Variables.LINK_NOTICIAS_ACTIVO = Constantes.URL_NOTICIAS_ESPACIO; break;
            case R.id.btMedicina: Variables.LINK_NOTICIAS_ACTIVO = Constantes.URL_NOTICIAS_MEDICINA; break;
            case R.id.btMedioAmbiente: Variables.LINK_NOTICIAS_ACTIVO = Constantes.URL_NOTICIAS_MEDIOAMBIENTE; break;
            case R.id.btNaturaleza: Variables.LINK_NOTICIAS_ACTIVO = Constantes.URL_NOTICIAS_NATURALEZA; break;
            case R.id.btCuriosidades: Variables.LINK_NOTICIAS_ACTIVO = Constantes.URL_NOTICIAS_CURIOSIDADES; break;
            default: break;
        }
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    public void inicializa () {
        btFisica = findViewById(R.id.btFisica);
        btEspacio = findViewById(R.id.btEspacio);
        btMedicina = findViewById(R.id.btMedicina);
        btCuriosidades = findViewById(R.id.btCuriosidades);
        btMedioAmbiente = findViewById(R.id.btMedioAmbiente);
        btNaturaleza = findViewById(R.id.btNaturaleza);

        btFisica.setOnClickListener(this);
        btEspacio.setOnClickListener(this);
        btMedicina.setOnClickListener(this);
        btCuriosidades.setOnClickListener(this);
        btMedioAmbiente.setOnClickListener(this);
        btNaturaleza.setOnClickListener(this);
    }
}
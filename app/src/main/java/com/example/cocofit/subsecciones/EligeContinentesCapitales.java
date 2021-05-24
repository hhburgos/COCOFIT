package com.example.cocofit.subsecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.example.cocofit.R;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

public class EligeContinentesCapitales extends AppCompatActivity implements OnClickListener {

    private CheckBox cbEuropa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elige_continentes_capitales);

        inicializa();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cbEuropa: gestionaCheckBox("europa"); break;
            default: break;
        }
    }

    public void gestionaCheckBox (String continente) {
        if (continente.equals("europa")) {
            if (Variables.europa) {
                Variables.europa = false;
                Constantes.alert("Europa Desactivado",this);
            } else {
                Variables.europa = true;
                Constantes.alert("Europa Activado", this);
            }
        } else {
            Constantes.alert("sELECCION RARA",this);
        }
    }

    public void inicializa () {
        cbEuropa = findViewById(R.id.cbEuropa);

        cbEuropa.setOnClickListener(this);
    }
}
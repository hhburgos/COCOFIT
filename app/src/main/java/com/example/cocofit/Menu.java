package com.example.cocofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.cocofit.noticias.MenuNoticias;
import com.example.cocofit.secciones.Calculo;
import com.example.cocofit.secciones.Memoria;
import com.example.cocofit.secciones.Memoria2;
import com.example.cocofit.subsecciones.EligeContinentesCapitales;

public class Menu extends AppCompatActivity implements OnClickListener {
    private Button btMemoria, btCalculo, btCapitales, btNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        inicializarVariables();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMemoria: Intent i = new Intent(this, Memoria2.class);
                startActivity(i);
                break;
            case R.id.btCalculo: Intent i2 = new Intent(this, Calculo.class);
                startActivity(i2);
                break;
            case R.id.btCapitales: Intent i3 = new Intent(this, EligeContinentesCapitales.class);
                startActivity(i3);
                break;
            case R.id.btNoticias: Intent i4 = new Intent(this, MenuNoticias.class);
                startActivity(i4);
                break;
            default:
                Toast.makeText(this,"Error. Acción desconocida",Toast.LENGTH_LONG).show();
        }
    }

    public void inicializarVariables() {
        btMemoria = findViewById(R.id.btMemoria);
        btCalculo = findViewById(R.id.btCalculo);
        btCapitales = findViewById(R.id.btCapitales);
        btNoticias = findViewById(R.id.btNoticias);

        btNoticias.setOnClickListener(this);
        btMemoria.setOnClickListener(this);
        btCalculo.setOnClickListener(this);
        btCapitales.setOnClickListener(this);
    }
}
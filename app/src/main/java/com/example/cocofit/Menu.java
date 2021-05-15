package com.example.cocofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements OnClickListener {
    private Button btMemoria, btCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        inicializarVariables();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btMemoria: Intent i = new Intent(this, Memoria.class);
                startActivity(i);
                break;
            case R.id.btCalculo: Intent i2 = new Intent(this, Calculo.class);
                startActivity(i2);
                break;
            default:
                Toast.makeText(this,"Error. Acción desconocida",Toast.LENGTH_LONG).show();
        }
    }

    public void inicializarVariables() {
        btMemoria = findViewById(R.id.btMemoria);
        btCalculo = findViewById(R.id.btCalculo);

        btMemoria.setOnClickListener(this);
        btCalculo.setOnClickListener(this);
    }
}
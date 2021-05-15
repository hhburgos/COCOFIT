package com.example.cocofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

public class Calculo extends AppCompatActivity implements OnClickListener {

    private TextView tvOperacion, tvRacha, tvNivel;
    private EditText etResultado;
    private Button btGenera;

    private int nivel, num1, num2, result, intentos, aciertos, fallos, racha;
    private String signo;

    public void corrige () {
        if(compruebaResultadoUsuario()) {
            Constantes.alert("Bien, correcto",this);
            generateOperation();
        }
        else {
            Constantes.alert("mal, era " + result,this);
        }
        etResultado.setText("");
        actualizaDatos();
    }

    public void generateOperation () {
        num1 = ((int) (Math.random() * 10 * nivel) + 1);
        num2 = ((int) (Math.random() * 10 * nivel) + 1);
        result = dameResultadoReal();

        String operacion = num1 + " " + signo + " " + num2;
        showOperation(operacion);

        //String resultado = String.valueOf(result);
        //etResultado.setText(resultado);
    }

    public int dameResultadoReal () {
        int dev = 0;
        if (signo.equals("+")) {
            dev = num1 + num2;
        }
        else {
            Constantes.alert("Error en el programa dameResultadoReal()",this);
        }

        return dev;
    }

    public boolean compruebaResultadoUsuario () {
        boolean dev = false;
        try {
            int resultado_user = Integer.valueOf(etResultado.getText().toString());
            if (resultado_user == result) {
                dev = true;
            }
        } catch (Exception e) {
            Constantes.alert("Asegúrate de introducir números enteros",this);
        }
        actualizaRacha(dev);
        return dev;
    }

    public void actualizaRacha (boolean acierto) {
        if (acierto) {
            racha += 1;
        } else {
            racha = 0;
        }
    }

    public void showOperation (String operation) {
        tvOperacion.setText(operation);
    }

    public void actualizaDatos () {
        tvRacha.setText("Racha: " + racha);
        tvNivel.setText("Nivel: " + nivel);
    }

    @Override //CONSTRUCTOR
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        instancia();
        generateOperation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGenera: corrige(); break;
            default:
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
    }

    public void instancia () {
        nivel = 1;
        signo = "+";
        racha = 0;
        nivel = Variables.nivel_calculo;

        tvOperacion = findViewById(R.id.tvOperacion);
        tvNivel = findViewById(R.id.tvNivel);
        tvRacha = findViewById(R.id.tvRacha);
        etResultado = findViewById(R.id.etRespuesta);
        btGenera = findViewById(R.id.btnGenera);

        btGenera.setOnClickListener(this);

        actualizaDatos();
    }
}
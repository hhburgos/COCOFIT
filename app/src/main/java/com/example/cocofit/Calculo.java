package com.example.cocofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.cocofit.recursos.AtacaBD;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

public class Calculo extends AppCompatActivity implements OnClickListener {

    private TextView tvOperacion, tvRacha, tvNivel;
    private EditText etResultado;
    private Button btGenera;
    private AdminSQLiteOpenHelper admin;

    private int num1, num2, result, intentos, aciertos, fallos, racha, conf;
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
        trackeaNivel();
        actualizaDatos();
    }

    public void generateOperation () {
        num1 = ((int) (Math.random() * 10 * Variables.nivel_calculo) + (Variables.nivel_calculo * Variables.nivel_calculo));
        num2 = ((int) (Math.random() * 10 * Variables.nivel_calculo) + (Variables.nivel_calculo * Variables.nivel_calculo));
        result = dameResultadoReal();

        String operacion = num1 + " " + signo + " " + num2;
        showOperation(operacion);

        //String resultado = String.valueOf(result);
        //etResultado.setText(resultado);
    }

    public void trackeaNivel () {
        int barrera_next_level = (10 * Variables.nivel_calculo) - (Variables.nivel_calculo * Variables.nivel_calculo);
        if (racha > barrera_next_level) {
            Variables.nivel_calculo += 1;
            AtacaBD.actualizaDataCalculo(admin,Constantes.TABLA_CALCULO,Constantes.CAMPO_NIVEL,Variables.nivel_calculo);
        }
    }

    public void gestionaSignos () {
        int pos;

    }

    public void actualizaNivelEnBD() {
        //mediante static en clase constantes puede ser
    }

    public int dameResultadoReal () {
        int dev = 0;
        if (signo.equals(Constantes.SIGNO_SUMA)) {
            dev = num1 + num2;
        }
        else if (signo.equals(Constantes.SIGNO_RESTA)) {
            dev = num1 - num2;
        }
        else if (signo.equals(Constantes.SIGNO_MULTIPLICA)) {
            dev = num1 * num2;
        }
        else if (signo.equals(Constantes.SIGNO_DIVIDE)) {
            // Debo asegurarme de que el resultado es un número entero, lo haré con una función
            dev = num1 / num2;
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
        tvNivel.setText("Nivel: " + Variables.nivel_calculo);
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
        admin = new AdminSQLiteOpenHelper(this);
        signo = "+";
        racha = 0;
        conf = Variables.conf_calculo;

        tvOperacion = findViewById(R.id.tvOperacion);
        tvNivel = findViewById(R.id.tvNivel);
        tvRacha = findViewById(R.id.tvRacha);
        etResultado = findViewById(R.id.etRespuesta);
        btGenera = findViewById(R.id.btnGenera);

        btGenera.setOnClickListener(this);

        actualizaDatos();
    }

}
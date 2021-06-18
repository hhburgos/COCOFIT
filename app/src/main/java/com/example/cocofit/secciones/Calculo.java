package com.example.cocofit.secciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.cocofit.AdminSQLiteOpenHelper;
import com.example.cocofit.R;
import com.example.cocofit.recursos.AtacaBD;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

import java.util.ArrayList;

public class Calculo extends AppCompatActivity implements OnClickListener {

    private TextView tvOperacion, tvRacha, tvNivel, tvControl;
    private EditText etResultado;
    private Button btGenera;
    private AdminSQLiteOpenHelper admin;

    private int num1, num2, result, intentos, aciertos, fallos, racha, conf;
    private String signo;

    public void corrige () {
        if(compruebaResultadoUsuario()) {
            gestionaSignos();
            //Constantes.alert("Bien, correcto",this);
            generateOperation();
        }
        else {
            //Constantes.alert("mal, era " + result,this);
        }
        etResultado.setText("");
        test();
        trackeaNivel();
        actualizaDatos();
    }

    public void generateOperation () {
        num1 = ((int) (Math.random() * 10 * Variables.nivel_calculo) + (Variables.nivel_calculo * Variables.nivel_calculo));
        num2 = ((int) (Math.random() * 10 * Variables.nivel_calculo) + (Variables.nivel_calculo));
        gestionaSignos();
        result = dameResultadoReal();

        String operacion = num1 + " " + signo + " " + num2;
        showOperation(operacion);


        //String resultado = String.valueOf(result);
        //etResultado.setText(resultado);
    }

    public void trackeaNivel () {
        int barrera_next_level = (10 * Variables.nivel_calculo) - (Variables.nivel_calculo );
        if (racha > barrera_next_level) {
            Variables.nivel_calculo += 1;
            actualizaDataCalculo(admin,Constantes.TABLA_CALCULO,Constantes.CAMPO_NIVEL,Variables.nivel_calculo,Variables.nicnkanme);
            //Constantes.alert("trackeo completoado",this);
        }
    }

    public boolean actualizaDataCalculo(AdminSQLiteOpenHelper admin, String table, String campo, int data, String nick) {
        boolean dev = false;
        try{
            SQLiteDatabase db = admin.getWritableDatabase();
            db.execSQL("update " + table + " set " + campo + " = " + data + " where " + Constantes.CAMPO_NICKNAME + " = '" + nick + "' ;");
            db.close();
            //Constantes.alert("actualizado nivel",this);
        }
        catch (Exception e) {
            //Constantes.alert("fallo en actualiza nivel",this);
            e.printStackTrace();
        }
        return dev;
    }

    public void test () {
        etResultado.setText(String.valueOf(result));
    }

    public void gestionaSignos () {
        //Constantes.alert();

        switch (Variables.conf_calculo) {
            case 1: config1(); break;
            default: break;
        }
    }

    public void config1 () {
        ArrayList<String> posibilidades = new ArrayList<String>();
        if (Variables.nivel_calculo > 8) {
            posibilidades.add("-"); posibilidades.add("+"); posibilidades.add("x");
            signo = dameRandomSigno(posibilidades);
        }
        else if (Variables.nivel_calculo > 4) {
            posibilidades.add("-"); posibilidades.add("+");
            signo = dameRandomSigno(posibilidades);
        }
        else {
            signo = "+";
        }
    }

    public String dameRandomSigno (ArrayList<String> signos) {
        String dev = "-";
        int n = signos.size();
        int random = ((int) (Math.random() * n));
        //tvControl.setText("random:" + random + " n="+n);
        dev = signos.get(random);
        return dev;
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
        signo = "/";
        racha = 0;
        conf = Variables.conf_calculo;

        tvOperacion = findViewById(R.id.tvOperacion);
        tvNivel = findViewById(R.id.tvNivel);
        tvRacha = findViewById(R.id.tvRacha);
        tvControl = findViewById(R.id.tvControl);
        etResultado = findViewById(R.id.etRespuesta);
        btGenera = findViewById(R.id.btnGenera);

        btGenera.setOnClickListener(this);

        actualizaDatos();
    }

}
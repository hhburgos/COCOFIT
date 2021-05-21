package com.example.cocofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btInicio;
    private EditText etNickname;
    private AdminSQLiteOpenHelper adminDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);*/
        //esto es lo que funciona
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_main);

        inicializar();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btInicio) {
            trataNickname();
        }
    }

    public void trataNickname () {
        Variables.nicnkanme = etNickname.getText().toString();

        if (existeElUsuario(Variables.nicnkanme)) {
            estableceDataCalculo(Variables.nicnkanme);
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        } else {
            if (insertarUsuario(Variables.nicnkanme) && creaDataSecciones(Variables.nicnkanme)) {
                Variables.nicnkanme = Variables.nicnkanme;
                Variables.nivel_calculo = Constantes.NIVEL_DEFAULT;
                Intent i = new Intent(this, Menu.class);
                startActivity(i);
            }
        }
    }

    public boolean insertarUsuario (String nickname) {
        boolean dev = false;

        if (nickname.isEmpty()) {
            Constantes.alert("No puedes dejar el campo vació",this);
        } else {
            try {
                //AdminSQLiteOpenHelper adminDB = new AdminSQLiteOpenHelper(this);
                SQLiteDatabase db = adminDB.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put(Constantes.CAMPO_NICKNAME, nickname);
                db.insert(Constantes.TABLA_USUARIOS, null, registro);
                db.close();
                Constantes.alert("Nuevo usuarios registrado",this);
                dev = true;
            }
            catch (Exception e) {
                Constantes.alert("Fallo en el registro",this);
            }
        }
        return dev;
    }

    public boolean existeElUsuario (String nickname) {
        boolean dev = false;
        try {
            SQLiteDatabase db = adminDB.getWritableDatabase();
            Cursor fila = db.rawQuery("select * from " + Constantes.TABLA_USUARIOS +
                    " where " + Constantes.CAMPO_NICKNAME + " like '" + nickname + "' ;", null);
            if (fila.moveToFirst()) {
                dev = true;
            }
            db.close();
        } catch (Exception e) {
            Constantes.alert("petó ",this);
        }
        return dev;
    }

    public boolean estableceDataCalculo (String nickname) {
        boolean dev = false;
        try {
            SQLiteDatabase db = adminDB.getWritableDatabase();
            Cursor fila = db.rawQuery("select " + Constantes.CAMPO_NIVEL + ", " + Constantes.CAMPO_CONF + " " +
                    " from " + Constantes.TABLA_CALCULO + " where " + Constantes.CAMPO_NICKNAME + " = '" + nickname + "' ;", null);
            if (fila.moveToFirst()) {
                Variables.nivel_calculo = fila.getInt(0);
                Variables.conf_calculo = fila.getInt(1);
                Constantes.alert("lvl:"+fila.getInt(0) + " nick:"+ nickname,this);
                dev = true;
            }
        }
        catch (Exception e) {
            Constantes.alert("fallo en estableceDataCalculo",this);
        }
        return dev;
    }

    public boolean creaDataSecciones (String nickname) {
        boolean dev = false;
        try {
            SQLiteDatabase db = adminDB.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put(Constantes.CAMPO_NICKNAME, nickname);
            registro.put(Constantes.CAMPO_NIVEL, Constantes.NIVEL_DEFAULT);
            registro.put(Constantes.CAMPO_CONF, Constantes.CONF_CALCULO_DEFAULT);
            db.insert(Constantes.TABLA_CALCULO, null, registro);
            db.close();
            dev = true;
        }
        catch (Exception e) {
            //poner algo aqui
        }
        return dev;
    }

    public void inicializar () {
        adminDB = new AdminSQLiteOpenHelper(this);

        btInicio = findViewById(R.id.btInicio);
        etNickname = findViewById(R.id.etNickname);

        btInicio.setOnClickListener(this);
    }



}
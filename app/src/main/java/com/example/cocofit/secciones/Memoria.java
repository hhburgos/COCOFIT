package com.example.cocofit.secciones;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.cocofit.R;

public class Memoria extends AppCompatActivity implements OnClickListener {
    private AnimationDrawable miAnimacion;
    private ImageView cara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        cara = new ImageView(this);
        cara.setOnClickListener(this);
        cara.setBackgroundResource(R.drawable.carta1);
        miAnimacion = (AnimationDrawable) cara.getBackground();
        miAnimacion.start();
        setContentView(cara);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(cara)) {
            Toast.makeText(this,"click imagen",Toast.LENGTH_LONG).show();
            miAnimacion.start();
        }
    }
}
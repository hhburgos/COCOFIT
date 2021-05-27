package com.example.cocofit.subsecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cocofit.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FisicaTitulares extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica_titulares);


    }

    public static void pruebaScrapy () {
        try {
            Document doc = Jsoup.connect("https://www.robotitus.com/category/fisica").get();

            String title = doc.title();
            //System.out.println("Tiutlo: "+ title);
            Log.d("activity_main",title);

//			Elements links = doc.select("a[href]");
            Elements titulares = doc.select("div[class]");
//			for (Element link : links) {
//				System.out.println("Link; "+ link.attr("href"));
//				System.out.println("Texto: "+ link.text());
//			}

            //System.out.println("---------------------------------\n");

            String clases;
            String titulo_noticia;
            int tam;
            Elements links;

            for (Element titular : titulares) {
                clases = titular.attr("class");
                if (clases.equals("post-content")) {
                    links = titular.getAllElements();
                    titulo_noticia = titular.text();
                    //String titular1 = dameCadenaPrevia(titulo_noticia,"Victor","\\s+");
                    //System.out.println("Titular1: " + titular1);
                    tam = titulo_noticia.length();
                    //titulo_noticia = titulo_noticia.substring(0, (tam/2));
                    //System.out.println("Titulo: " + titulo_noticia);
                    //System.out.println("Link; "+ links.attr("href"));
                    Log.d("activity_main","link: " + links.attr("href"));
                    String link = links.attr("href");
                    String link2 = link.replace("-", " ");
                    String linkPro = link2.replace("https://www.robotitus.com/", "");
                    //System.out.println(upperCaseFirst(linkPro));
                    Log.d("activity_main","linkPro: " + linkPro);
                }
            }



        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String  palabraEliminar(String oracion,String palabra) {
        if(oracion.contains(palabra))
            return oracion.replaceAll(palabra, "");
        return oracion;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public static String dameCadenaPosterior (String cadena, String buscar, String split) {
        String dev = "";
        String cadenaDondeBuscar = cadena;
        String loQueQuieroBuscar = buscar;
        String[] palabras = loQueQuieroBuscar.split(split);
        for (String palabra : palabras) {
            int index = cadenaDondeBuscar.indexOf(loQueQuieroBuscar);
            int tam = cadenaDondeBuscar.length();
            if (cadenaDondeBuscar.contains(palabra)) {
                //System.out.println("Encontrado" + index + " :" + cadenaDondeBuscar.substring(0,index));
                dev = cadenaDondeBuscar.substring(index,tam);
                //aquí tu lógica en caso que se haya encontrado...
            }
        }
        return dev;
    }

    public static String dameCadenaPrevia (String cadena, String buscar, String split) {
        String dev = "";
        String cadenaDondeBuscar = cadena;
        String loQueQuieroBuscar = buscar;
        String[] palabras = loQueQuieroBuscar.split(split);
        for (String palabra : palabras) {
            int index = cadenaDondeBuscar.indexOf(loQueQuieroBuscar);
            if (cadenaDondeBuscar.contains(palabra)) {
                //System.out.println("Encontrado" + index + " :" + cadenaDondeBuscar.substring(0,index));
                dev = cadenaDondeBuscar.substring(0,index);
                //aquí tu lógica en caso que se haya encontrado...
            }
        }
        return dev;
    }
}
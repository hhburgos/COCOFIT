package com.example.cocofit.noticias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.example.cocofit.R;
import com.example.cocofit.recursos.Constantes;
import com.example.cocofit.recursos.Variables;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;
    private String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapter(parseItems, this);
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();

        Constantes.alert("title: " + mensaje,this);

    }

    private class Content extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity2.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity2.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(Variables.LINK_NOTICIAS_ACTIVO).get();
                Elements titulares = doc.select("div[class]");

                String clases;
                String titulo_noticia;
                Elements links;
                for (Element titular : titulares) {
                    clases = titular.attr("class");
                    if (clases.equals("news-post article-post")) {
                        links = titular.getAllElements();

                        System.out.println("Link; "+ links.attr("href"));
                        String link = links.attr("href");
                        String link2 = link.replace("-", " ");
                        String linkPro = link2.replace("https://www.robotitus.com/", "");
                        String title = upperCaseFirst(linkPro);
                        String imgUrl = titular.select("img").eq(0).attr("src");
                        String linkNoticiaCompleta = titular.select("a").eq(0).attr("href");
                        //String detailUrl = getDetails(linkNoticiaCompleta);
                        parseItems.add(new ParseItem(imgUrl, title, linkNoticiaCompleta));
                        //System.out.println(upperCaseFirst(linkPro));
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static String getDetails (String url){

        String dev = "";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements titulares = doc.select("div[class]");

            String clases;
            String titulo_noticia;
            int tam;
            Elements links;

            for (Element titular : titulares) {
                clases = titular.attr("class");
                if (clases.equals("entry-content post-content")) {
                    dev += upperCaseFirst(titular.select("p").text())  + "\n";
                }
            }
        }
        catch (IOException e) {

        }

        return dev;
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

}
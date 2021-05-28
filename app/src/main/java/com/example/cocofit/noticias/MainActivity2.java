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
                Document doc = Jsoup.connect("https://www.robotitus.com/category/tecnologia").get();


//			Elements links = doc.select("a[href]");
                Elements titulares = doc.select("div[class]");
//			for (Element link : links) {
//				System.out.println("Link; "+ link.attr("href"));
//				System.out.println("Texto: "+ link.text());
//			}

                System.out.println("---------------------------------\n");

                String clases;
                String titulo_noticia;
                Elements links;
                int cont = 0;
                for (Element titular : titulares) {
                    clases = titular.attr("class");
                    if (clases.equals("news-post article-post")) {
                        //if (cont > 8) break;
                        //cont += 1;
                        links = titular.getAllElements();
                        titulo_noticia = titular.text();
                        //String titular1 = dameCadenaPrevia(titulo_noticia,"Victor","\\s+");
                        //System.out.println("Titular1: " + titular1);
                        //  tam = titulo_noticia.length();
                        //titulo_noticia = titulo_noticia.substring(0, (tam/2));
                        //System.out.println("Titulo: " + titulo_noticia);
                        System.out.println("Link; "+ links.attr("href"));
                        String link = links.attr("href");
                        String link2 = link.replace("-", " ");
                        String linkPro = link2.replace("https://www.robotitus.com/", "");
                        String title = linkPro;
                        String imgUrl = titular.select("img").eq(0).attr("src");
                        parseItems.add(new ParseItem(imgUrl, title));
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

}
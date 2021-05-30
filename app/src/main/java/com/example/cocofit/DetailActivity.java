package com.example.cocofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cocofit.noticias.MainActivity2;
import com.example.cocofit.noticias.ParseItem;
import com.example.cocofit.recursos.Constantes;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView, detailTextView;
    ArrayList<String> detailString = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.textView);
        detailTextView = findViewById(R.id.detailTextView);
        detailTextView.setMovementMethod( new ScrollingMovementMethod());

        titleTextView.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);
        Content content = new Content();
        content.execute();

    }


    private class Content extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String p = "";
            for (int i = 0; i < detailString.size(); i++) {
                p = detailString.get(i) + "\n\n";
                detailTextView.append(p);
            }
            detailTextView.append("\n\n\n\n\n");
            detailTextView.append("\n\n\n\n\n\n\n");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String detailUrl = getIntent().getStringExtra("detailUrl");
                Document doc = Jsoup.connect(detailUrl).get();

                Elements titulares = doc.select("div[class]");

                String clases;
                String titulo_noticia;
                int tam = 0;
                Elements links;
                for (Element titular : titulares) {
                    clases = titular.attr("class");
                    if (clases.equals("entry-content post-content")) {
                        //detailString.add(tam,upperCaseFirst(titular.select("p").text()));
                        Elements p = titular.select("p");
                        tam = p.size();
                        for (int i = 0; i < tam; i++) {
                            detailString.add(upperCaseFirst(titular.select("p").eq(i).text()));
                        }

                    }
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

}
package com.example.cocofit.scrapy;

import android.content.Context;

import com.example.cocofit.recursos.Constantes;

import java.net.*;
import java.io.*;

public class URLDemo {
    public static void urldemo (Context c) {
		/*if(args.length != 1) {
			System.out.println("Utilizar : java URLDemo <URL>");
			System.exit(0);
		}*/

        String [] a = {"https://www.mclibre.org/consultar/xml/"};

        try {
            URL u = new URL(a[0]);
            Constantes.alert("ordenador = " + u.getHost(),c);
            System.out.println("protocolo = " + u.getProtocol());
            System.out.println("ordenador = " + u.getHost());
            System.out.println("fichero = " + u.getFile());
            System.out.println("puerto = " + u.getPort());
            System.out.println("ref = " + u.getRef());
        }
        catch (MalformedURLException e) {
            System.out.println("URL errónea: ");
        }
        catch (Exception e1) {
            System.out.println("Error: " + e1.getMessage());
        }
    }
}

package com.example.hilos_persistencia_sonido;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Main_tema extends Activity {

    private ConstraintLayout layout;
    private Button button;

    private int[] arrayFondo={R.drawable.fondo_supermario, R.drawable.paisaje_1};

   // private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temas);

        layout = findViewById(R.id.layout);
        button = findViewById(R.id.button);


        //colores/imagenes aleatorios en cada onclick
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Imagenes aleatorias
                /*Random numeros = new Random();
                layout= findViewById(R.id.layout);
                int posicion = numeros.nextInt(2);
                layout.setBackgroundResource(arrayFondo[posicion]);*/

                //colores aleatorios
                Random numeros = new Random();
                int red = numeros.nextInt(255);
                int green = numeros.nextInt(255);
                int blue = numeros.nextInt(255);

                int color = Color.argb(255,numeros.nextInt(256),numeros.nextInt(256),numeros.nextInt(256));
                layout.setBackgroundColor(color);


            }
        });



       /* crea un navegador--- webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.es/");*/
    }

    public void FondoAleatorio(){


       /* layout= findViewById(R.id.cl1);


        int posicion = numeros.nextInt(2);

        layout.setBackgroundResource(arrayFondo[posicion]);
        */



    }


/*
    public void CambiarFondo(View view){
    layout.setBackgroundResource(R.drawable.fondo_supermario) ;
    }
*/



}

package com.example.hilos_persistencia_sonido;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class Gestion extends Activity {

    private Partida partida;

    private int dificultad;

    private int FPS=30;

    private Handler temporizador;

    private int botes;

    MediaPlayer golpeo;

    MediaPlayer fin;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        botes=0;

        Bundle extras=getIntent().getExtras();

        dificultad=extras.getInt("DIFICULTAD");

        partida=new Partida(getApplicationContext(),dificultad);

        setContentView(partida);

        temporizador=new Handler();

        temporizador.postDelayed(elhilo,2000);

        golpeo=MediaPlayer.create(this,R.raw.golpeobalon);
        fin=MediaPlayer.create(this,R.raw.finaljuego);
    }

    private Runnable elhilo=new Runnable() {
        @Override
        public void run() {

            if(partida.movimientoBola()) fin();

            else{

                partida.invalidate(); //elimina el contenido de ImageView y llama de nuevo a OnDraw

                temporizador.postDelayed(elhilo,1000/FPS);
            }

        }
    };

    public boolean onTouchEvent(MotionEvent evento){

        int x=(int) evento.getX();

        int y=(int) evento.getY();

        golpeo.start();

        if(partida.toque(x,y))botes++;

        return false;
    }

    public void fin(){

        temporizador.removeCallbacks(elhilo);

        fin.start();

        Intent in=new Intent();

        in.putExtra("PUNTUACION",botes*dificultad);

        setResult(RESULT_OK,in);

        finish(); //destruye la actividad actual

    }
}

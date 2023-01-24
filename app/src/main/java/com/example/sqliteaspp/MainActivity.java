package com.example.sqliteaspp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Clase principal del restaurante. Permite ver la carta del restaurante, pedir
 * comandas, calcular la cuenta de una mesa, cobrar mesas y gestionar el alta y
 * baja de reservas de clientes.
 *
 * @author Laura Lopez
 */
public class MainActivity extends Activity {

    private Button boton;
    public GestorBDRestaurante gbdRest = new GestorBDRestaurante(this);

    MediaPlayer mp;
    SoundPool soundPool;
    int idEfecteBoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        //Reproduïm la música de fons
        mp = MediaPlayer.create(this, R.raw.sylvia_communis);
        mp.start();
        //Instanciem un efecte de so per a un botó
        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        idEfecteBoto = soundPool.load(this, R.raw.efecte_boto, 0);

        gbdRest.cargarDatos();

        boton = (Button) findViewById(R.id.Button01);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {    //Apliquem l'efecte de so al primer botó
                soundPool.play(idEfecteBoto, 1, 1, 1, 0, 1);lanzarPlatos(null);
            }
        });

        boton = (Button) findViewById(R.id.Button02);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarHacerPedido(null);
            }
        });

        boton = (Button) findViewById(R.id.Button03);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarCalcularPedido(null);
            }
        });

        boton = (Button) findViewById(R.id.Button04);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarReserva(null);
            }
        });

        boton = (Button) findViewById(R.id.Button05);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarEliminarReserva(null);
            }
        });

        boton = (Button) findViewById(R.id.Button06);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                lanzarsalir();
            }
        });

    }

    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onResume() {
        super.onResume();
        mp.start();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        mp.pause();
        super.onPause();
    }

    @Override protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


    //IMPORTANT!!! Prémer el botó 'Back' en una activitat és equivalent al mètode onDestroy() i no permetrà la recuperació de les dades
    //Mètode per emmagatzemar la posició del MediaPlayer
    @Override protected void onSaveInstanceState(Bundle estadoGuardado){
        super.onSaveInstanceState(estadoGuardado);
        if (mp != null) {
            int pos = mp.getCurrentPosition();
            estadoGuardado.putInt("posicion", pos);
        }
    }

    //Mètode per recuperar la posició del MediaPlayer
    @Override protected void onRestoreInstanceState(Bundle estadoGuardado){
        super.onRestoreInstanceState(estadoGuardado);
        if (estadoGuardado != null && mp != null) {
            int pos = estadoGuardado.getInt("posicion");
            mp.seekTo(pos);
        }
    }


    public void lanzarPlatos(View view) {
        Intent i = new Intent(this, ListarPlatos.class);
        startActivity(i);
    }

    public void lanzarHacerPedido(View view) {

        Intent i = new Intent(this, PedirPlatos.class);
        startActivity(i);
    }

    public void lanzarCalcularPedido(View view) {
        Intent i = new Intent(this, CalcularPedido.class);
        startActivity(i);
    }

    public void lanzarReserva(View view) {
        Intent i = new Intent(this, Reservar.class);
        startActivity(i);
    }

    public void lanzarEliminarReserva(View view) {
        Intent i = new Intent(this, EliminarReserva.class);
        startActivity(i);
    }

    public void lanzarsalir() {
        finish();
    }


}


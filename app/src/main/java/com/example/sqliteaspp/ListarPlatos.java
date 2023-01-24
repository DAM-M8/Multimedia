package com.example.sqliteaspp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Clase para mostrar la carta a los clientes del restaurante. Se lanza al
 * pulsar el boton 'Ver carta' de la actividad principal.
 * 
 * @author Laura Lopez
 */
public class ListarPlatos extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plato_lista);
	}
}

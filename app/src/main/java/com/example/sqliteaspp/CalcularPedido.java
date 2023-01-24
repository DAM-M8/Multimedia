package com.example.sqliteaspp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase para calcular la cuenta de una mesa y cobrar la mesa. Actividad lanzada
 * al pulsar el boton 'Calcular pedido' de la actividad principal.
 * 
 * @author Laura Lopez
 */
public class CalcularPedido extends Activity {
	GestorBDRestaurante gbdRest = new GestorBDRestaurante(this);
	int id_mesa = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calcular_pedido);
	}

	/**
	 * Metodo para calcular la cuenta de una mesa. Actividad lanzada al pulsar
	 * el boton 'Calcular total' de la actividad CalcularPedido.
	 * 
	 * @author Laura Lopez
	 * @throws RuntimeException
	 *             si el usuario no introduce ningun numero de mesa.
	 */
	public void calcularTotalMesa(View view) throws RuntimeException {
		try {
			EditText ent = (EditText) findViewById(R.id.entrada);
			TextView sal = (TextView) findViewById(R.id.salida);

			String s = ent.getText().toString();
			id_mesa = Integer.parseInt(s);
			float precioTotal = gbdRest.calcularCuenta(id_mesa);

			sal.setText("Precio total: " + precioTotal + " EUR");

		} catch (RuntimeException e) {
			Toast.makeText(this, "Introduzca un valor", Toast.LENGTH_SHORT)
					.show();
		}

	}

	/**
	 * Metodo para cobrar la cuenta de una mesa. Actividad lanzada al pulsar el
	 * boton 'Cobrar mesa' de la actividad CalcularPedido.
	 * 
	 * @author Laura Lopez
	 * @throws RuntimeException
	 *             si el usuario no introduce ningun numero de mesa
	 */
	public void cobrarMesa(View view) throws RuntimeException {
		try {
			EditText ent = (EditText) findViewById(R.id.entrada);

			String s = ent.getText().toString();
			id_mesa = Integer.parseInt(s);
			gbdRest.cobrarMesa(id_mesa);

			Toast.makeText(this, "Mesa " + id_mesa + " cobrada",
					Toast.LENGTH_SHORT).show();

		} catch (RuntimeException e) {
			Toast.makeText(this, "Introduzca un valor", Toast.LENGTH_SHORT)
					.show();
		}

	}
}

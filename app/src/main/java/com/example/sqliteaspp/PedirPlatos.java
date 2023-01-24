package com.example.sqliteaspp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Clase para introducir las comandas de los clientes de una mesa. Se lanza al
 * pulsar el boton 'Hacer pedido' de la actividad principal.
 * 
 * @author Laura Lopez
 */

public class PedirPlatos extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hacer_pedido);
	}

	/**
	 * Metodo para introducir un pedido. Actividad lanzada al pulsar el boton
	 * 'Hacer pedido' de la actividad PedirPlatos.
	 * 
	 * @author Laura Lopez
	 * @throws RuntimeException
	 *             si el usuario no introduce ningun numero de mesa.
	 */
	public void crearPedido(View view) throws RuntimeException {
		GestorBDRestaurante gbdRest = new GestorBDRestaurante(this);
		final int MAX_PLATOS = 30;
		final int NUM_MESAS = 12;

		// Guardamos en una tabla todos los identificadores de platos de
		// calcular_pedido.xml
		int[] entradas = new int[] { R.id.entrada1, R.id.entrada2,
				R.id.entrada3, R.id.entrada4, R.id.entrada5, R.id.entrada6,
				R.id.entrada7, R.id.entrada8, R.id.entrada9, R.id.entrada10,
				R.id.entrada11, R.id.entrada12, R.id.entrada13, R.id.entrada14,
				R.id.entrada15, R.id.entrada16, R.id.entrada17, R.id.entrada18,
				R.id.entrada19, R.id.entrada20, R.id.entrada21, R.id.entrada22,
				R.id.entrada23, R.id.entrada24, R.id.entrada25, R.id.entrada26,
				R.id.entrada27, R.id.entrada28, R.id.entrada29, R.id.entrada30 };
		try {
			EditText ent = (EditText) findViewById(R.id.id_mesa);
			String s = ent.getText().toString();
			int id_mesa = Integer.parseInt(s);

			// Comprobar que la mesa exista
			if (0 < id_mesa && id_mesa <= NUM_MESAS) {
				int id_cuenta = (int) gbdRest.crearCuenta(id_mesa);

				// Recorremos toda la lista de platos y bebidas
				for (int i = 0; i < MAX_PLATOS; i++) {
					ent = (EditText) findViewById(entradas[i]);
					s = ent.getText().toString();
					if (!s.equals("")) { // Se ha indicado alguna cantidad
						int cantidad = Integer.parseInt(s);
						int id_plato = i + 1;
						gbdRest.hacerPedido(id_plato, cantidad, id_cuenta,
								id_mesa);
					}
				}

				Toast.makeText(this, "Pedido realizado", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(this,
						"Introduzca un identificador de la mesa valido",
						Toast.LENGTH_SHORT).show();
			}

		} catch (RuntimeException e) {
			Toast.makeText(this, "Introduzca el identificador de la mesa",
					Toast.LENGTH_SHORT).show();
		}

	}
}
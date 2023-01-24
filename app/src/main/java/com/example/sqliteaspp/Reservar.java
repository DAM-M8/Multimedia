package com.example.sqliteaspp;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase para crear las reservas de los clientes del restaurante. Se lanza al
 * pulsar el boton 'Hacer reserva' de la actividad principal.
 * 
 * @author Laura Lopez
 */
public class Reservar extends Activity {

	private TextView dateDisplay;
	private Button pickDate;
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hacer_reserva);
		// This declares our GUI elements on the screen
		dateDisplay = (TextView) findViewById(R.id.text_fecha);
		pickDate = (Button) findViewById(R.id.Fijar_fecha);
		// This will show the date picker for each click on the button
		pickDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
		// This will show us the current date final
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		updateDate();
	}

	/**
	 * Metodo para mostrar la fecha de la reserva a eliminar introducida por el
	 * usuario.
	 * 
	 * @author 
	 *         http://gdroid.com.mx/blog/2010/04/13/creacion-de-un-selector-de-fecha
	 *         -en-android/
	 */
	private void updateDate() {
		dateDisplay.setText(new StringBuilder()
				// Constant Month is 0 based so we have to add 1
				.append(month + 1).append("-").append(day).append("-")
				.append(year).append(" "));
	}

	/**
	 * Metodo para actualizar la fecha de la reserva a eliminar.
	 * 
	 * @author 
	 *         http://gdroid.com.mx/blog/2010/04/13/creacion-de-un-selector-de-fecha
	 *         -en-android/
	 */
	// Actions called everytime button "Set" is clicked
	private final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int yearOf, int monthOfYear,
				int dayOfMonth) {
			year = yearOf;
			month = monthOfYear;
			day = dayOfMonth;
			updateDate();// Show the date on the TextView
		}
	};

	/**
	 * Metodo para capturar la fecha de la reserva a eliminar introducida por el
	 * usuario.
	 * 
	 * @author 
	 *         http://gdroid.com.mx/blog/2010/04/13/creacion-de-un-selector-de-fecha
	 *         -en-android/
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, year, month,
					day);
		}
		return null;
	}

	/**
	 * Metodo para hacer una reserva de un cliente en la base de datos.
	 * Actividad lanzada al pulsar el bot�n 'Reservar' de la actividad Reservar.
	 * 
	 * @author Laura Lopez
	 * @throws RuntimeException
	 *             si el usuario no introduce un nombre de cliente.
	 */
	public void crearReserva(View view) throws RuntimeException {
		GestorBDRestaurante gbdRest = new GestorBDRestaurante(this);
		boolean reservado = false;
		try {
			EditText ent = (EditText) findViewById(R.id.edit_nombre_cliente);
			String nombre = ent.getText().toString();
			ent = (EditText) findViewById(R.id.edit_num_clientes);
			String s = ent.getText().toString();
			int num_clientes = Integer.parseInt(s);
			TextView tv = (TextView) findViewById(R.id.text_fecha);
			String fecha = (String) tv.getText();

			// Comprobar que el numero de comensales esta dentro del rango
			// disponible
			if (0 < num_clientes && num_clientes <= 6) {
				reservado = gbdRest.hacerReserva(nombre, num_clientes, fecha);
			} else {
				Toast.makeText(
						this,
						"No hay mesas para m�s de 6 personas. Reserve m�s de una mesa",
						Toast.LENGTH_SHORT).show();
			}

			if (reservado) {
				Toast.makeText(this, "Reserva realizada para: " + fecha,
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this,
						"Reserva no realizada. No hay mesas disponibles",
						Toast.LENGTH_SHORT).show();
			}

		}

		catch (RuntimeException e) {
			Toast.makeText(this, "Introduzca todos los campos",
					Toast.LENGTH_SHORT).show();
		}
	}

}

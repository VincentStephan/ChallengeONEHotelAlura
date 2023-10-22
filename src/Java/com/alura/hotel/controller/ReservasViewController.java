package com.alura.hotel.controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.alura.hotel.Dao.reservaDAO;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;
import com.alura.hotel.views.RegistroHuesped;
import com.alura.hotel.views.ReservasView;
import com.toedter.calendar.JDateChooser;

public class ReservasViewController  {
	
	private EntityManager em = JPAUtils.getEntetyManager();
	private reservaDAO reservaDao = new reservaDAO(this.em);
	
	public void guardarReservas(JDateChooser txtFechaEntrada, JDateChooser txtFechaSalida, JTextField txtValor, JComboBox<String> txtFormaPago) {
		
		if (txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {

			// Obtén los valores de los componentes de la interfaz de usuario
			Date fechaEntradaUtilDate = txtFechaEntrada.getDate();
			Date fechaSalidaUtilDate = txtFechaSalida.getDate();

			java.sql.Date fechaEntrada = new java.sql.Date(fechaEntradaUtilDate.getTime());
			java.sql.Date fechaSalida = new java.sql.Date(fechaSalidaUtilDate.getTime());

			String valor = txtValor.getText();
			String formaPago = txtFormaPago.getSelectedItem().toString();

			RegistroHuesped registro = new RegistroHuesped();

			try {

				Reserva reservaDatos = new Reserva(fechaEntrada, fechaSalida, valor, formaPago);
				reservaDao.guardar(reservaDatos);
				registro.setReserva(reservaDatos);
				registro.setNreserva();
				registro.setVisible(true);
				reservaDao.cerrar();
			

			} catch (Exception e1) {

				e1.printStackTrace();
				registro.setVisible(false);
				JOptionPane.showMessageDialog(null, "Error en guardar la informacion");
				ReservasView frame = new ReservasView();
				frame.setVisible(true);
				reservaDao.cerrar();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
		}
	}
	
	public int calcularPrecioReserva(JDateChooser txtFechaEntrada, JDateChooser txtFechaSalida) {
		int valorBaseReserva = 10;

		Date fechaEntrada = (Date) txtFechaEntrada.getDate();
		Date fechaSalida = (Date) txtFechaSalida.getDate();

		if (fechaEntrada != null && fechaSalida != null) {
			int diferenciaEnMillis = (int) (fechaSalida.getTime() - fechaEntrada.getTime());
			int diferenciaEnDias = (diferenciaEnMillis / 86400000);

			if (diferenciaEnDias > 0) {
				return valorBaseReserva * diferenciaEnDias;

			} else {
				txtFechaSalida.setCalendar(null);
				JOptionPane.showMessageDialog(null, "Seleccione una fecha correcta");

			}
		}
		return 0;

	}


}

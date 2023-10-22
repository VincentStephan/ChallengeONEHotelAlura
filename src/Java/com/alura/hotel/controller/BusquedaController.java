package com.alura.hotel.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.Dao.huespedDAO;
import com.alura.hotel.Dao.reservaDAO;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;

public class BusquedaController {

	private EntityManager em = JPAUtils.getEntetyManager();

	private huespedDAO huespedDao = new huespedDAO(this.em);
	private reservaDAO reservaDao = new reservaDAO(this.em);

	public void cargarTabla(DefaultTableModel modelo, DefaultTableModel modeloHuesped) {

		modelo.setRowCount(0);
		List<Reserva> reservaList = reservaDao.consultarTodo();

		for (Reserva reserva : reservaList) {
			Object[] fila = { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(),
					reserva.getFormaPago() };
			modelo.addRow(fila);
		}

		modeloHuesped.setRowCount(0);
		List<Huesped> huespedList = huespedDao.consultarTodo();

		for (Huesped huesped : huespedList) {
			Object[] fila = { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaDeNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
					huesped.getReservas().getId() };
			modeloHuesped.addRow(fila);
		}
	}

	public void BuscarNumeroReserva(DefaultTableModel modelo, DefaultTableModel modeloHuesped, JTextField txtBuscar) {
		try {
			modelo.setRowCount(0);
			Long NumeroReserva = null;

			try {
				NumeroReserva = Long.parseLong(txtBuscar.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Busqueda solo compatible con Numero de reserva");
				cargarTabla(modelo, modeloHuesped);
			}

			List<Reserva> ListNreserva = reservaDao.consultarPorNumeroReserva(NumeroReserva);
			em.clear();

			for (Reserva reserva : ListNreserva) {
				Object[] fila = { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
						reserva.getValor(), reserva.getFormaPago() };
				modelo.addRow(fila);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error inesperado, reinicie la aplicación");
			em.close();
		}

	}

	public void BuscarHuespedApellido(DefaultTableModel modeloHuesped, JTextField txtBuscar) {
		try {
			modeloHuesped.setRowCount(0);
			String apellido = txtBuscar.getText();
			List<Huesped> ListApellidoH = huespedDao.consultarPorApellido(apellido);
			em.clear();

			for (Huesped huesped : ListApellidoH) {
				Object[] fila = { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
						huesped.getFechaDeNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
						huesped.getReservas().getId() };
				modeloHuesped.addRow(fila);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error inesperado, reinicie la aplicación");
			em.close();
		}

	}

	public void editarReservas(DefaultTableModel modelo, DefaultTableModel modeloHuesped, JTable tbReservas) {

		int RegistroSeleccinado = tbReservas.getSelectedRow();

		if (RegistroSeleccinado >= 0) {

			Long Id = (Long) tbReservas.getValueAt(RegistroSeleccinado, 0);
			java.sql.Date FechaEntrada = (java.sql.Date) tbReservas.getValueAt(RegistroSeleccinado, 1);
			java.sql.Date FechaSalida = (java.sql.Date) tbReservas.getValueAt(RegistroSeleccinado, 2);
			String Valor = (String) tbReservas.getValueAt(RegistroSeleccinado, 3);
			String FormaPago = (String) tbReservas.getValueAt(RegistroSeleccinado, 4);

			Reserva ReservaSeleccionada = reservaDao.buscarPorId(Id);
			System.out.println(ReservaSeleccionada);
			if (ReservaSeleccionada != null) {
				ReservaSeleccionada.setFechaEntrada(FechaEntrada);
				ReservaSeleccionada.setFechaSalida(FechaSalida);
				ReservaSeleccionada.setValor(Valor);
				ReservaSeleccionada.setFormaPago(FormaPago);

				reservaDao.actualizar(ReservaSeleccionada);
				JOptionPane.showMessageDialog(null, "Se edito correctamente");
				cargarTabla(modelo, modeloHuesped);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecciona una casilla");
			cargarTabla(modelo, modeloHuesped);
		}

	}

	public void editarHuespedes(DefaultTableModel modelo, DefaultTableModel modeloHuesped, JTable tbHuespedes) {
		int RegistroSeleccionado = tbHuespedes.getSelectedRow();

		if (RegistroSeleccionado >= 0) {
			Long Id = (Long) tbHuespedes.getValueAt(RegistroSeleccionado, 0);
			String nombre = (String) tbHuespedes.getValueAt(RegistroSeleccionado, 1);
			String apellido = (String) tbHuespedes.getValueAt(RegistroSeleccionado, 2);
			java.sql.Date FechaDeNacimiento = (java.sql.Date) tbHuespedes.getValueAt(RegistroSeleccionado, 3);
			String Nacionalidad = (String) tbHuespedes.getValueAt(RegistroSeleccionado, 4);
			String telefonoStr = (String) tbHuespedes.getValueAt(RegistroSeleccionado, 5);
			Long Telefono = Long.parseLong(telefonoStr);

			Huesped HuespedSeleccionado = huespedDao.buscarPorId(Id);
			System.out.println(HuespedSeleccionado);

			if (HuespedSeleccionado != null) {
				HuespedSeleccionado.setNombre(nombre);
				HuespedSeleccionado.setApellido(apellido);
				HuespedSeleccionado.setFechaDeNacimiento(FechaDeNacimiento);
				HuespedSeleccionado.setNacionalidad(Nacionalidad);
				HuespedSeleccionado.setTelefono(Telefono);

				huespedDao.actualizar(HuespedSeleccionado);
				JOptionPane.showMessageDialog(null, "Se edito correctamente");
				cargarTabla(modelo, modeloHuesped);
				
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecciona una casilla");
			cargarTabla(modelo, modeloHuesped);
		}
	}

}

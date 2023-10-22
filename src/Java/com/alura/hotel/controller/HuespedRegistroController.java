package com.alura.hotel.controller;

import java.text.Format;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.alura.hotel.Dao.huespedDAO;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;
import com.alura.hotel.views.MenuUsuario;

import com.toedter.calendar.JDateChooser;


public class HuespedRegistroController  {
	private EntityManager em = JPAUtils.getEntetyManager();

	private huespedDAO huespedDao = new huespedDAO(this.em);

	public void guardarHuesped(JTextField txtNombre, JTextField txtApellido, JDateChooser txtFechaN,
			JComboBox<Format> txtNacionalidad, JTextField txtTelefono, Reserva reservaDatos) {
		try {
			String Nombre = txtNombre.getText();
			String Apellido = txtApellido.getText();
			Date UtilDate = txtFechaN.getDate();
			java.sql.Date FechaDeNacimiento = new java.sql.Date(UtilDate.getTime());
			String Nacionalidad = txtNacionalidad.getSelectedItem().toString();
			Long Telefono = Long.parseLong(txtTelefono.getText());
			Huesped huesped = new Huesped(Nombre, Apellido, FechaDeNacimiento, Nacionalidad, Telefono);

			huesped.setReservas(reservaDatos);
			huespedDao.guardar(huesped);
			huespedDao.cerrar();
			JOptionPane.showMessageDialog(null, "Se guardo correctamente la información");
			MenuUsuario usuario = new MenuUsuario();
			usuario.setVisible(true);
			

		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en guardar la información");
			huespedDao.cerrar();
			MenuUsuario frame = new MenuUsuario();
			frame.setVisible(true);
		}
	}



}

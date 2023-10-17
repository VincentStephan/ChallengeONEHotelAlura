package com.alura.hotel.modelo;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "huespedes")
public class Huesped {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	public Huesped(String nombre, String apellido, java.sql.Date fechaDeNacimiento, String nacionalidad, Long telefono
			 ) {
		
		Nombre = nombre;
		Apellido = apellido;
		FechaDeNacimiento = fechaDeNacimiento;
		Nacionalidad = nacionalidad;
		Telefono = telefono;
		
	}

	private String Nombre;
	private String Apellido;
	private java.sql.Date FechaDeNacimiento;
	private String Nacionalidad;
	private Long Telefono;

	
	@ManyToOne
	@JoinColumn(name = "reserva_id")
	private Reserva reservas;
	
	   @Override
	    public String toString() {
	        return String.valueOf(reservas);
	    }
}

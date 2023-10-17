package com.alura.hotel.modelo;


import java.sql.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "reservas")
public class Reserva {
	
	public Reserva(java.sql.Date fechaEntrada, java.sql.Date fechaSalida, String valor, String formaPago) {
		this.FechaEntrada = fechaEntrada;
		this.FechaSalida = fechaSalida;
		this.Valor = valor;
		this.FormaPago = formaPago;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	

	private java.sql.Date FechaEntrada;
	private java.sql.Date FechaSalida;
	private String Valor;
	
	private String FormaPago;

	

}
	
	
	
 



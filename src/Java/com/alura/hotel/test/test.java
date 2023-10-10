package com.alura.hotel.test;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;

public class test {

	

	public static void main(String[] args) {
		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotel");

	     EntityManager em = emf.createEntityManager();
	        
	     
	     Reserva reserva = new Reserva();
	     reserva.setFechaEntrada(new Date(123123));
	     reserva.setValor(200);                
	     reserva.setFormaPago("Tarjeta de crédito");

	     em.getTransaction().begin();
	     em.persist(reserva);
	     em.getTransaction().commit();
         em.clear();
         
	     
	     Huesped huesped = new Huesped();
	     huesped.setNombre("Juan");        
	     huesped.setFechaDeNacimiento(new Date(223213)); 
	     huesped.setNacionalidad("Argentino");      
	     huesped.setTelefono(555123456);      
	     huesped.setReservas(reserva);       

	 

	     em.getTransaction().begin();
	     em.persist(huesped);
	     em.getTransaction().commit();
	     em.close();
	}

}

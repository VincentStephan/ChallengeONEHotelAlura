package com.alura.hotel.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.modelo.Huesped;

public class huespedDAO {

	private EntityManager em;
	private EntityTransaction tx;

	public huespedDAO(EntityManager em) {
	this.em = em;
	}
	
	public void guardar(Huesped huesped) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(huesped);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	public void actualizar(Huesped huesped) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(huesped);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	public void remover(Huesped huesped) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			huesped = em.merge(huesped);
			em.remove(huesped);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		}

	}

	public void cerrar() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}
	  public void rollback() {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	    }
	  
		
		public List<Huesped> consultarTodo() {
		    String jpql = "SELECT h FROM huespedes h";
		    TypedQuery<Huesped> query = em.createQuery(jpql, Huesped.class);
		    return query.getResultList();
		}
}

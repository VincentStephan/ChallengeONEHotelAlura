package com.alura.hotel.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.alura.hotel.modelo.Reserva;

public class reservaDAO {

	private EntityManager em;
	private EntityTransaction tx;

	public reservaDAO(EntityManager em) {
		this.em = em;

	}

	public void guardar(Reserva reserva) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(reserva);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en guardar la informacion");
		}
	}

	public void actualizar(Reserva reserva) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(reserva);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en guardar la informacion");
		}
	}

	public void remover(Reserva reserva) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			reserva = em.merge(reserva);
			em.remove(reserva);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en guardar la informacion");
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
	
	public List<Reserva> consultarTodo() {
	    String jpql = "SELECT r FROM reservas r";
	    TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
	    return query.getResultList();
	}
}

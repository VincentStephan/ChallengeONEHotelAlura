package com.alura.hotel.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

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
		
		public List<Huesped> consultarPorApellido(String apellido) {
		    String jpql = "SELECT h FROM huespedes h WHERE h.Apellido = :Apellido";
		    TypedQuery<Huesped> query = em.createQuery(jpql, Huesped.class);
		    query.setParameter("Apellido", apellido);
		    return query.getResultList();
		}

		public Huesped buscarPorId(Long id) {
			 try {
		          return em.find(Huesped.class, id);
		        } finally {
		            em.clear();
		        }
		
		}
		
		public void eliminarObjetoEnBaseDeDatos(Long id) {
		    EntityTransaction tx = em.getTransaction();

		    try {
		        tx.begin();

		      
		        Huesped huesped = em.find(Huesped.class, id);

		        if (huesped != null) {
		            // Si se encuentra el objeto, procede a eliminarlo.
		            em.remove(huesped);
		        }

		        tx.commit();
		        em.clear();
		    } catch (Exception e) {
		        if (tx.isActive()) {
		            tx.rollback();
		        }
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error al eliminar la información");
		    }
		}
	    
}

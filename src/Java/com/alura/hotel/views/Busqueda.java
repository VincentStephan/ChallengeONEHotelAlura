package com.alura.hotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.Dao.huespedDAO;
import com.alura.hotel.Dao.reservaDAO;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.utils.JPAUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private EntityManager em = JPAUtils.getEntetyManager();

	private huespedDAO huespedDao = new huespedDAO(this.em);
	private reservaDAO reservaDao = new reservaDAO(this.em);
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private JTable tablaSeleccionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Busqueda.class.getResource("/com/alura/hotel/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (panel.getSelectedIndex() == 0) {
					tablaSeleccionada = tbReservas;
					cargarTabla();
				}

				if (panel.getSelectedIndex() == 1) {
					tablaSeleccionada = tbHuespedes;
					cargarTabla();

				}

			}
		});
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		JScrollPane scroll_table = new JScrollPane(tbReservas);
		JScrollBar horizontalScrollBar = scroll_table.getHorizontalScrollBar();
		horizontalScrollBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/imagenes/reservado.png")),
				scroll_table, null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		scroll_tableHuespedes.setVisible(false);
		scroll_tableHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

		});
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tablaSeleccionada == tbReservas) {
					BuscarNumeroReserva();

				} else if (tablaSeleccionada == tbHuespedes) {
					BuscarHuespedApellido();
				} else {
					BuscarNumeroReserva();
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
             
				try {
					if (tablaSeleccionada == tbReservas) {
						editarReservas();
					

					} else if (tablaSeleccionada == tbHuespedes) {
						editarHuespedes();
						
						
					} else {
						tablaSeleccionada = tbReservas;
						editarReservas();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Hubo un error en actualizar los datos");
				}
				

			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (tablaSeleccionada == tbReservas) {
						
						int RegistroSeleccinado = tbReservas.getSelectedRow();
						Long Id = (Long) tbReservas.getValueAt(RegistroSeleccinado, 0);
						
						reservaDao.eliminarObjetoEnBaseDeDatos(Id);
						JOptionPane.showMessageDialog(null, "Se elimino");
						cargarTabla();
					

					} else if (tablaSeleccionada == tbHuespedes) {
						
						int RegistroSeleccinado = tbHuespedes.getSelectedRow();
						Long Id = (Long) tbHuespedes.getValueAt(RegistroSeleccinado, 0);
						
						huespedDao.eliminarObjetoEnBaseDeDatos(Id);
						JOptionPane.showMessageDialog(null, "Se elimino");
						cargarTabla();
						
						
					} else {
						tablaSeleccionada = tbReservas;
						int RegistroSeleccinado = tbReservas.getSelectedRow();
						Long Id = (Long) tbReservas.getValueAt(RegistroSeleccinado, 0);
						reservaDao.eliminarObjetoEnBaseDeDatos(Id);
						JOptionPane.showMessageDialog(null, "Se elimino");
						cargarTabla();
						
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Hubo un error en eliminar los datos");
				}
				
				
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);

		cargarTabla();
	}

	//meteodos para mover a un contrroller
	
	
	
	private void cargarTabla() {

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

	private void BuscarNumeroReserva() {
		try {
			modelo.setRowCount(0);
			Long NumeroReserva = null;

			try {
				NumeroReserva = Long.parseLong(txtBuscar.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Busqueda solo compatible con Numero de reserva");
				cargarTabla();
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

	private void BuscarHuespedApellido() {
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
	
	
	private void editarReservas() {
		
		int RegistroSeleccinado = tbReservas.getSelectedRow();

		if (RegistroSeleccinado >= 0) {

			Long Id = (Long) tbReservas.getValueAt(RegistroSeleccinado, 0);
			java.sql.Date FechaEntrada = (java.sql.Date) tbReservas.getValueAt(RegistroSeleccinado , 1);
			java.sql.Date FechaSalida = (java.sql.Date) tbReservas.getValueAt(RegistroSeleccinado , 2);
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
                 cargarTabla();
             }
         }  else {
			JOptionPane.showMessageDialog(null, "Selecciona una casilla");
			cargarTabla();
		}
		
	}
	
	private void editarHuespedes() {
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
	            cargarTabla();
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Selecciona una casilla");
	        cargarTabla();
	    }
	}
	

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}

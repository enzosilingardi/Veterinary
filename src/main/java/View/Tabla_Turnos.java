package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Control.Consulta_Turno;
import Model.ControlFiles;
import View.Instrumento_Quirofano.ComboItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Turnos extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){      // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        Consulta_Turno.tabla(modelo, table);
	        
	    }

	public int existeRel(int historial, int proced) {      //Este procedimiento no se utiliza en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Medical_H_Medical_P WHERE id_Medical_History = ? AND id_Procedure = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, historial);
			pst.setInt(2, proced);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
			}
			return 1;
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e);
			return 1;
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
		
		
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Turnos frame = new Tabla_Turnos();
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
	public Tabla_Turnos(String perfil) {                         //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));      //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(40, 11, 516, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");          //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(467, 309, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {         //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
		
			JButton btnVeterinario = new JButton("Asociar veterinario");        //Abre la ventana Procedimiento_Veterinario
			btnVeterinario.setForeground(new Color(255, 255, 255));
			btnVeterinario.setFont(new Font("Roboto", Font.BOLD, 14));
			btnVeterinario.setBackground(new Color(86, 211, 243));
			btnVeterinario.setBorder(null);
			btnVeterinario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Procedimiento_Veterinario pv = new Procedimiento_Veterinario();
					pv.setVisible(true);
				}
			});
			btnVeterinario.setBounds(202, 309, 150, 23);
			contentPane.add(btnVeterinario);
			
			JButton btnSucursal = new JButton("Asociar sucursal");      //Abre la ventana Procedimiento_Sucursal
			btnSucursal.setForeground(new Color(255, 255, 255));
			btnSucursal.setFont(new Font("Roboto", Font.BOLD, 14));
			btnSucursal.setBackground(new Color(86, 211, 243));
			btnSucursal.setBorder(null);
			btnSucursal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Procedimiento_Sucursal ps = new Procedimiento_Sucursal();
					ps.setVisible(true);
				}
			});
			btnSucursal.setBounds(40, 309, 150, 23);
			contentPane.add(btnSucursal);
			
			JButton btnModificar = new JButton("Modificar");          //Abre la ventana Modificar_Turno
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setBorder(null);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Turno mt = new Modificar_Turno(table.getValueAt(fila,0).toString());      //Envía como parámetro el id de la fila seleccionada
					mt.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(146, 260, 100, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");        //Este botón permite eliminar la fila seleccionada
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setBorder(null);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String fecha = table.getValueAt(fila,3).toString();
					String hora = table.getValueAt(fila,4).toString();
					
					Consulta_Turno.eliminar(id, fecha, hora);
					mostrarTabla();
				}
			});
			btnEliminar.setBounds(252, 260, 100, 23);
			contentPane.add(btnEliminar);
			
			JButton btnAgregar = new JButton("Agregar turno");     //Abre la ventana Procedimiento_Medico
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnAgregar.setBackground(new Color(86, 211, 243));
			btnAgregar.setBorder(null);
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Procedimiento_Medico pm = new Procedimiento_Medico();
					pm.setVisible(true);
					dispose();
				}
			});
			btnAgregar.setBounds(40, 260, 100, 23);
			contentPane.add(btnAgregar);
		
		}
		
		mostrarTabla();
	}

	public Tabla_Turnos() {                        //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(40, 11, 516, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");         //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(467, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnVeterinario = new JButton("Asociar veterinario");      //Abre la ventana Procedimiento_Veterinario
		btnVeterinario.setForeground(new Color(255, 255, 255)); 
		btnVeterinario.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVeterinario.setBackground(new Color(86, 211, 243));
		btnVeterinario.setBorder(null);
		btnVeterinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Veterinario pv = new Procedimiento_Veterinario();
				pv.setVisible(true);
			}
		});
		btnVeterinario.setBounds(202, 309, 150, 23);
		contentPane.add(btnVeterinario);
		
		JButton btnSucursal = new JButton("Asociar sucursal");      //Abre la ventana Procedimiento_Sucursal
		btnSucursal.setForeground(new Color(255, 255, 255));
		btnSucursal.setFont(new Font("Roboto", Font.BOLD, 14));
		btnSucursal.setBackground(new Color(86, 211, 243));
		btnSucursal.setBorder(null);
		btnSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Sucursal ps = new Procedimiento_Sucursal();
				ps.setVisible(true);
			}
		});
		btnSucursal.setBounds(40, 309, 150, 23);
		contentPane.add(btnSucursal);
		
		JButton btnModificar = new JButton("Modificar");         //Abre la ventana Modificar_Turno
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setBorder(null);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Turno mt = new Modificar_Turno(table.getValueAt(fila,0).toString());    //Envía como parámetro el id de la fila seleccionada
				mt.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(146, 260, 100, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");             //Este botón permite eliminar la fila seleccionada
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setBorder(null);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String fecha = table.getValueAt(fila,3).toString();
				String hora = table.getValueAt(fila,4).toString();
				
				Consulta_Turno.eliminar(id, fecha, hora);
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(252, 260, 100, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar turno");      //Abre la ventana Procedimiento_Medico
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Medico pm = new Procedimiento_Medico();
				pm.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 260, 100, 23);
		contentPane.add(btnAgregar);
		
		mostrarTabla();
	}
}

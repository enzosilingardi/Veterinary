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
import Control.Control_Mascota;
import Model.ControlFiles;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Tabla_Mascota extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnModificar;
	private JButton btnAgregar;
	private JButton btnEliminar;

	void mostrarTabla(){            // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        Control_Mascota.tabla(modelo, table);
	        
	    }
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Mascota frame = new Tabla_Mascota();
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
	public Tabla_Mascota(final String perfil) {                        //Crea la ventana recibiento el perfil como parámetro
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(40, 11, 516, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(467, 309, 89, 23);
		contentPane.add(btnVolver);
		
		btnAgregar = new JButton("Agregar");     //Abre la ventana Mascota
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mascota mascota = new Mascota(perfil);
				mascota.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 277, 91, 23);
		contentPane.add(btnAgregar);

		if (perfil.equals("Admin") || perfil.equals("Manager")) {         //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
			btnModificar = new JButton("Modificar");       //Abre la ventana Modificar_Mascota
			btnModificar.setBorder(null);
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Mascota mm = new Modificar_Mascota(table.getValueAt(fila,0).toString(),perfil);       //Envía como parámetro el id de la fila seleccionada
					mm.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(139, 277, 91, 23);
			contentPane.add(btnModificar);
			
			
			
			btnEliminar = new JButton("Eliminar");        //Este botón elimina la fila seleccionada
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String nombre = table.getValueAt(fila,1).toString();
					
					Control_Mascota.eliminar(id, nombre);
					
					mostrarTabla();
				}
			});
			btnEliminar.setBounds(238, 277, 91, 23);
			contentPane.add(btnEliminar);
		}
		mostrarTabla();
	}


	public Tabla_Mascota() {                           //Crea la ventana
		
	}

}

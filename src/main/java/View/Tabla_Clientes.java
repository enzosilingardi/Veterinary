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
import Control.Control_Cliente;
import Control.Control_Mascota;
import Model.ControlFiles;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Clientes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	void mostrarTabla(){                 // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Cliente.tabla(modelo, table);
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Clientes frame = new Tabla_Clientes();
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
	public Tabla_Clientes(final String perfil) {                     //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(28, 11, 679, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");               //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(618, 351, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");     //Abre la ventana Cliente
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente(perfil);
				cliente.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(28, 330, 91, 23);
		contentPane.add(btnAgregar);
		
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {       //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		

			JButton btnModificar = new JButton("Modificar");              //Abre la ventana Modificar_Cliente
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setBorder(null);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();            
					
					Modificar_Cliente mc = new Modificar_Cliente(table.getValueAt(fila,0).toString(),perfil);    //Envía como parámetro el id de la fila seleccionada
					mc.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(124, 330, 91, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");               //Este botón elimina la fila seleccionada
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setBorder(null);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String nombre = table.getValueAt(fila,1).toString();
					
					Control_Cliente.eliminar(id, nombre);
					
					mostrarTabla();
				}
			});
			btnEliminar.setBounds(220, 330, 91, 23);
			contentPane.add(btnEliminar);
			
			
		
		}
		
		mostrarTabla();
	}

	public Tabla_Clientes() {         //Crea la ventana
		
	}

}

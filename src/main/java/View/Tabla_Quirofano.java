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
import Control.Consulta_Quirofano;
import Model.ControlFiles;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Quirofano extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){             // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        Consulta_Quirofano.tabla(modelo, table);
	        
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Quirofano frame = new Tabla_Quirofano();
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
	public Tabla_Quirofano(final String perfil) {               //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));      //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 311, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {      //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
		JButton btnRel = new JButton("Añadir a sucursal");      //Abre la ventana Quirófano_Sucursal
		btnRel.setForeground(new Color(255, 255, 255));
		btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
		btnRel.setBorder(null);
		btnRel.setBackground(new Color(86, 211, 243));
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quirofano_Sucursal qs = new Quirofano_Sucursal(perfil);      //Envía el perfil como parámetro
				qs.setVisible(true);
				dispose();
			}
		});
		btnRel.setBounds(40, 309, 161, 23);
		contentPane.add(btnRel);
		
		}
	
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {        //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
		JButton btnModificar = new JButton("Modificar");         //Abre la ventana Modificar_Quirófano
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Quirofano mq = new Modificar_Quirofano(table.getValueAt(fila,0).toString(),perfil);   //Envía como parámetro el id de la fila seleccionada
				mq.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(211, 309, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");           //Este botón elimina la fila seleccionada
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String numero = table.getValueAt(fila,1).toString();
				
				Consulta_Quirofano.eliminar(id, numero);
				
				mostrarTabla();
				
			}
		});
		btnEliminar.setBounds(312, 277, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");          //Abre la ventana quirófano
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quirofano quirofano = new Quirofano(perfil);     //Envía como parámetro el perfil
				quirofano.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(211, 277, 91, 23);
		contentPane.add(btnAgregar);
		
		}
		
		mostrarTabla();
	}

	public Tabla_Quirofano() {
		// TODO Auto-generated constructor stub
		
		
	}

}

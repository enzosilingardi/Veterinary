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
import Control.Consulta_Pedido;
import Model.ControlFiles;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Tabla_Pedido extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){                  // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        Consulta_Pedido.tabla(modelo, table);
	        
	    }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Pedido frame = new Tabla_Pedido();
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
	public Tabla_Pedido(String perfil) {                          //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");          //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {          //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
			JButton btnRel = new JButton("Realizar pedido");          //Abre la ventana Pedidos
			btnRel.setForeground(new Color(255, 255, 255));
			btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
			btnRel.setBorder(null);
			btnRel.setBackground(new Color(86, 211, 243));
			btnRel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedidos pedido = new Pedidos();
					pedido.setVisible(true);
					dispose();
				}
			});
			btnRel.setBounds(40, 309, 109, 23);
			contentPane.add(btnRel);
			
			JButton btnModificar = new JButton("Modificar");           //Abre la ventana Modificar_Pedido
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.setBorder(null);
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Pedido mp = new Modificar_Pedido(table.getValueAt(fila,0).toString());       //Envía como parámetro el id de la fila seleccionada
					mp.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(40, 260, 91, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");             //Este botón elimina la fila seleccionada
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String producto = table.getValueAt(fila,1).toString();
					String sucursal = table.getValueAt(fila,3).toString();
					
					Consulta_Pedido.eliminar(id, producto, sucursal);
					mostrarTabla();
				}
			});
			btnEliminar.setBounds(139, 260, 91, 23);
			contentPane.add(btnEliminar);
		}
		mostrarTabla();
	}


	public Tabla_Pedido() {                           //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");                //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnRel = new JButton("Realizar pedido");      //Abre la ventana Pedidos
		btnRel.setForeground(new Color(255, 255, 255));
		btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
		btnRel.setBorder(null);
		btnRel.setBackground(new Color(86, 211, 243));
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pedidos pedido = new Pedidos();
				pedido.setVisible(true);
				dispose();
			}
		});
		btnRel.setBounds(40, 309, 109, 23);
		contentPane.add(btnRel);
		
		JButton btnModificar = new JButton("Modificar");           //Abre la ventana Modificar_Pedido
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Pedido mp = new Modificar_Pedido(table.getValueAt(fila,0).toString());    //Envía como parámetro el id de la fila seleccionada
				mp.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(40, 260, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");              //Este botón elimina la fila seleccionada
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String producto = table.getValueAt(fila,1).toString();
				String sucursal = table.getValueAt(fila,3).toString();
				
				Consulta_Pedido.eliminar(id, producto, sucursal);
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(139, 260, 91, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}

}

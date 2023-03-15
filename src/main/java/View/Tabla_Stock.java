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
import Control.Consulta_Stock;
import Model.ControlFiles;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Stock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public static boolean contieneSoloNumerosRegex(String cadena) {        //Revisa que la cadena recibida como parámetro contenga solo números
	    return cadena.matches("[0-9]+");
	}

	void mostrarTabla(){                 // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel(); 
	        
	        Consulta_Stock.tabla(modelo, table);
	        
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Stock frame = new Tabla_Stock();
					frame.setVisible(true);
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tabla_Stock(String perfil) {          //Crea la ventana recibiendo como parámetro el perfil del usuario
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");                //Cierra la ventana
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		
		
			
			JButton btnModificar = new JButton("Modificar cantidad");      //Este botón permite modificar la cantidad del producto
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.setBorder(null);
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					boolean flagError = false;
					String cantidadAux = table.getValueAt(fila,2).toString();
					
					for(int i=0; i < cantidadAux.length(); i++ ) {        //Verifica que no se inserten letras
						
						if (Character.isLetter(cantidadAux.charAt(i))){
							
							flagError = true;
							break;
						}
						
						
					}
					
					if (flagError) {
						
						JOptionPane.showMessageDialog(null, "Solo se permiten números",null,JOptionPane.ERROR_MESSAGE);
						
					}else {
					
					
					int cantidad = Integer.parseInt(table.getValueAt(fila,2).toString());
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String producto = table.getValueAt(fila,1).toString();
					String sucursal = table.getValueAt(fila,3).toString();
					
					Consulta_Stock.modificar(cantidad, id, producto, sucursal);
					
					mostrarTabla();
					
					}
				}
			});
			btnModificar.setBounds(40, 309, 143, 23);
			contentPane.add(btnModificar);
			
			JButton btnAgregar = new JButton("Agregar a stock");       //Abre la ventana Sucursal_Producto
			btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnAgregar.setBorder(null);
			btnAgregar.setBackground(new Color(86, 211, 243));
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Sucursal_Producto sp = new Sucursal_Producto();
					sp.setVisible(true);
					dispose();
				}
			});
			btnAgregar.setBounds(40, 275, 143, 23);
			contentPane.add(btnAgregar);
			
			JButton btnEliminar = new JButton("Eliminar de stock");        //Este botón elimina la fila seleccionada
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String producto = table.getValueAt(fila,1).toString();
					String sucursal = table.getValueAt(fila,3).toString();
					
					Consulta_Stock.eliminar(id, producto, sucursal);
					
					mostrarTabla();
				}
			});
			btnEliminar.setBounds(193, 275, 143, 23);
			contentPane.add(btnEliminar);
		
		mostrarTabla();
		
	}

	public Tabla_Stock() {       //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png"))); //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");      //Cierra la ventana
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar cantidad");      //Este botón permite modificar la cantidad del producto   
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				boolean flagError = false;
				String cantidadAux = table.getValueAt(fila,2).toString();
				
				for(int i=0; i < cantidadAux.length(); i++ ) {        //Verifica que no se inserten letras
					
					if (Character.isLetter(cantidadAux.charAt(i))){
						
						flagError = true;
						break;
					}
					
					
				}
				
				if (flagError) {
					
					JOptionPane.showMessageDialog(null, "Solo se permiten números",null,JOptionPane.ERROR_MESSAGE);
					
				}else {
				
				
				int cantidad = Integer.parseInt(table.getValueAt(fila,2).toString());
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String producto = table.getValueAt(fila,1).toString();
				String sucursal = table.getValueAt(fila,3).toString();
				
				Consulta_Stock.modificar(cantidad, id, producto, sucursal);
				
				mostrarTabla();
				
				}
			}
		});
		btnModificar.setBounds(40, 309, 143, 23);
		contentPane.add(btnModificar);
		
		JButton btnAgregar = new JButton("Agregar a stock");       //Abre la ventana Sucursal_Producto
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sucursal_Producto sp = new Sucursal_Producto();
				sp.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 275, 143, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar de stock");      //Este botón permite eliminar la fila seleccionada
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String producto = table.getValueAt(fila,1).toString();
				String sucursal = table.getValueAt(fila,3).toString();
				
				Consulta_Stock.eliminar(id, producto, sucursal);
				
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(193, 275, 143, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}
}

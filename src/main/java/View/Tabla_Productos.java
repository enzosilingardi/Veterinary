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
import Model.ControlFiles;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Productos extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){                     // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Tipo","Descripción","Precio de Venta","Precio proveedor","Proveedor"});   //Nombre de las columnas
	       
	        table.setModel(modelo);    //Setea el modelo
	        
	        
	        String datos[] = new String[7];      //Declara que va a haber 7 columnas
	       
	        try {
	        	Connection con = Connect.getConexion();      //Realiza la conexion
	        	//Sentencia sql
	        	PreparedStatement ps = con.prepareStatement("SELECT id_Product,product_Name, type_Name, description, cost_Price, sale_Price, provider_Name\r\n"
	        			+ "FROM Product\r\n"
	        			+ "INNER JOIN Product_Type ON Product.id_Product_Type = Product_Type.id_Product_Type\r\n"
	        			+ "INNER JOIN Provider ON Product.id_Provider = Provider.id_Provider;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                datos[4] = rs.getString(5);
	                datos[5] = rs.getString(6);
	                datos[6] = rs.getString(7);
	                
	                modelo.addRow(datos);

	            }
	            table.setModel(modelo);      //Setea el modelo

	            table.getColumnModel().getColumn(0).setMaxWidth(0);             // los 4 siguientes hacen que la columna del id sea invisible para el usuario
	    		table.getColumnModel().getColumn(0).setMinWidth(0);
	    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	    		table.getColumnModel().getColumn(0).setResizable(false);
	        } catch(SQLException E) {
				JOptionPane.showMessageDialog(null,E);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	    }
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Productos frame = new Tabla_Productos();
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
	public Tabla_Productos(String perfil) {                   //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(28, 11, 679, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");          //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(618, 351, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {             //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
			JButton btnAgregar = new JButton("Añadir");                  //Abre la ventana Producto
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setBackground(new Color(86, 211, 243));
			btnAgregar.setBorder(null);
			btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Producto producto = new Producto();
					producto.setVisible(true);
					dispose();
				}
			});
			btnAgregar.setBounds(28, 333, 91, 23);
			contentPane.add(btnAgregar);
			
			JButton btnModificar = new JButton("Modificar");         //Abre la ventana Modificar_Producto
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setBorder(null);
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Producto mp = new Modificar_Producto(table.getValueAt(fila,0).toString());       //Envía como parámetro el id de la fila seleccionada
					mp.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(127, 333, 91, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");                //Este botón elimina la fila seleccionada
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setBorder(null);
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					try {
						Connection con = Connect.getConexion();      //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE id_Product = ?" );
						
							ps.setInt(1, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Producto eliminado");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha eliminado el producto "+table.getValueAt(fila,1).toString());
			               mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al eliminar producto");       //En caso de fallar, lo avisa en pantalla
			                
			            }
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
						JOptionPane.showMessageDialog(null, "Producto está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnEliminar.setBounds(226, 333, 91, 23);
			contentPane.add(btnEliminar);
			
		}
		mostrarTabla();
	}


	public Tabla_Productos() {                               //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(28, 11, 679, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");              //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(618, 351, 89, 23);
		contentPane.add(btnVolver);
		
		
		JButton btnAgregar = new JButton("Añadir");                //Abre la ventana Producto
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setBorder(null);
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = new Producto();
				producto.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(28, 333, 91, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");         //Abre la ventana Modificar_Producto
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setBorder(null);
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Producto mp = new Modificar_Producto(table.getValueAt(fila,0).toString());     //Envía como parámetro el id de la fila seleccionada
				mp.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(127, 333, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");          //Este botón elimina la fila seleccionada
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setBorder(null);
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();         //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE id_Product = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto eliminado");           //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el producto "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar producto");    //En caso de fallar, lo avisa en pantalla
		                
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Producto está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(226, 333, 91, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}
}

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
import View.Historial_Medico.ComboItem;

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
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Cantidad","Dirección Sucursal"});    //Nombre de las columnas
	       
	        table.setModel(modelo);      //Setea el modelo
	        
	        
	        
	        String datos[] = new String[4];       //Declara que va a haber 4 columnas
	       
	        try {
	        	Connection con = Connect.getConexion();     //Realiza la conexión
	        	//Sentencia sql
	        	PreparedStatement ps = con.prepareStatement("SELECT Rel_Branch_Product.id_BP, Product.product_Name, Rel_Branch_Product.amount, Branch.address\r\n"
	        			+ "FROM Rel_Branch_Product\r\n"
	        			+ "INNER JOIN Product ON Product.id_Product = Rel_Branch_Product.id_Product\r\n"
	        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Branch_Product.id_Branch;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                
	                modelo.addRow(datos);

	            }
	            
	            table.setModel(modelo);       //Setea el modelo
	            
	            table.getColumnModel().getColumn(0).setMaxWidth(0);      // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					
					for(int i=0; i < cantidadAux.length(); i++ ) {         //Verifica que no se inserten letras
						
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
					
					int result = 0;
					
					try {
						Connection con = Connect.getConexion();      //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("UPDATE Rel_Branch_Product SET amount = ? WHERE id_BP = ?");
						
							
							if(cantidad >250000) {       //Verifica si el número excede el límite
								
								JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
								mostrarTabla();
								
							}else if(cantidad<0){       //Verifica si el número es negativo
								
								JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
								mostrarTabla();
						
							}else {
								
								ps.setInt(1,cantidad);
						
							}
							
						
						
						
						ps.setInt(2, id);
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Stock modificado");            //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha modificado el stock de "+table.getValueAt(fila,1).toString()+" en la sucursal "+table.getValueAt(fila,3).toString());
			                mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al modificar stock");       //En caso de fallar, lo avisa en pantalla
			                mostrarTabla();
			            }
					
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
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
					int result = 0;
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					try {
						Connection con = Connect.getConexion();        //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Product WHERE id_BP = ?" );
						
							ps.setInt(1, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Producto eliminado de stock");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha eliminado el stock de "+table.getValueAt(fila,1).toString()+" en la sucursal "+table.getValueAt(fila,3).toString());
			               mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al eliminar producto");           //En caso de fallar, lo avisa en pantalla
			                
			            }
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
						JOptionPane.showMessageDialog(null, "Stock está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar, lo avisa en pantalla
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Rel_Branch_Product SET amount = ? WHERE id_BP = ?");
					
						
						if(cantidad >250000) {          //Revisa si el número excede el límite
							
							JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
							mostrarTabla();
							
						}else if(cantidad<0){             //Revisa si el número es negativo
							
							JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
							mostrarTabla();
					
						}else {
							
							ps.setInt(1,cantidad);
					
						}
						
					
					
					
					ps.setInt(2, id);
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Stock modificado");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha modificado el stock de "+table.getValueAt(fila,1).toString()+" en la sucursal "+table.getValueAt(fila,3).toString());
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar stock");      //En caso de fallar, lo avisa en pantalla
		                mostrarTabla();
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
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
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Product WHERE id_BP = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto eliminado de stock");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el stock de "+table.getValueAt(fila,1).toString()+" en la sucursal "+table.getValueAt(fila,3).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar producto");        //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Stock está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(193, 275, 143, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}
}

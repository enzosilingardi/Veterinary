package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Model.ControlFiles;
import View.Sucursal.ComboItem;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Sucursales extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnModificar;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField txtDireccion;

	
	
	void mostrarTabla(){         // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Dirección"});  //Nombre de las columnas
       
        table.setModel(modelo);      //Setea el modelo
        
        
        String datos[] = new String[2];  //Declara que va a haber 2 columnas
       
        try {
        	Connection con = Connect.getConexion();     //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT Branch.id_Branch, address\r\n"
        			+ "FROM Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                      //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);        //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);    // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Tabla_Sucursales frame = new Tabla_Sucursales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public int existeSucursal(String direccion) {        //Este procedimiento revisa si existe la sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			String SSQL = "SELECT count(*) FROM Branch WHERE address = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, direccion);

			result = pst.executeQuery();
			
			if (result.next()) {               
				return result.getInt(1);       //Si ya existe, la variable se pone en 1
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
	
	 
	private void limpiar() {           //Este procedimiento limpia los campos
		txtDireccion.setText("");
		
	}
	
	/**
	 * Create the frame.
	 */
	public Tabla_Sucursales(String perfil) {           //Crea la ventana recibiendo como parámetro el perfil del usuario
		setTitle("Sucursales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 364);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));        //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(40, 11, 302, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");      //Cierra la ventana
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(484, 288, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {         //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		

			btnModificar = new JButton("Modificar");              //Este botón permite modificar la sucursal seleccionada
			btnModificar.setBorder(null);
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					String direccion = table.getValueAt(fila,1).toString();
					
					int result = 0;
					
					try {
						Connection con = Connect.getConexion();        //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("UPDATE Branch SET address = ? WHERE id_Branch = ?" );
						
						
						ps.setString(1, direccion);
						
						ps.setInt(2, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Sucursal modificada");          //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                ControlFiles.addContent("Se ha modificado la sucursal "+direccion);
			                limpiar();
			                mostrarTabla();
			            } else { 
			                JOptionPane.showMessageDialog(null, "Error al modificar sucursal");     //En caso de fallar, lo avisa en pantalla
			                limpiar();
			            }
					
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnModificar.setBounds(484, 83, 89, 23);
			contentPane.add(btnModificar);
			
			btnAgregar = new JButton("Agregar");                 //Este botón permite agregar una sucursal
			btnAgregar.setBorder(null);
			btnAgregar.setBackground(new Color(86, 211, 243));
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String direccion = txtDireccion.getText();
					
					int result = 0;
					
					try {
						Connection con = Connect.getConexion();     //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("INSERT INTO Branch (address) VALUES (?)" );
						
						
						
						if(existeSucursal(direccion)!=0) {     //Revisa si la sucursal ya existe
							
							JOptionPane.showMessageDialog(null, "Sucursal ya existe");
						}else {
							ps.setString(1, direccion);
						}
							
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Sucursal guardada");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                ControlFiles.addContent("Se ha añadido la sucursal "+direccion);
			                limpiar();
			                mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al guardar sucursal");    //En caso de fallar, lo avisa en pantalla
			                limpiar();
			            }
					
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnAgregar.setBounds(385, 83, 89, 23);
			contentPane.add(btnAgregar);
			
			btnEliminar = new JButton("Eliminar");         //Este botón permite eliminar la fila seleccionada
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					try {
						Connection con = Connect.getConexion();      //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("DELETE FROM Branch WHERE id_Branch = ?" );
						
							ps.setInt(1, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Sucursal eliminada");            //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha eliminado la sucursal "+table.getValueAt(fila,1).toString());
			               mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al eliminar sucursal");       //En caso de fallar, lo avisa en pantalla
			                
			            }
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
						JOptionPane.showMessageDialog(null, "Sucursal está en uso, por favor elimine todos los registros relacionados");       //En caso de fallar, lo avisa en pantalla
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnEliminar.setBounds(435, 122, 91, 23);
			contentPane.add(btnEliminar);
		}
		

		txtDireccion = new JTextField();
		txtDireccion.setBounds(446, 44, 127, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblDireccion.setBounds(385, 47, 59, 14);
		contentPane.add(lblDireccion);
		
		mostrarTabla();
	}
	
	public Tabla_Sucursales() {                    //Crea la ventana
		// TODO Auto-generated constructor stub
		setTitle("Sucursales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 364);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(40, 11, 302, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");     //Cierra la ventana
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(484, 288, 89, 23);
		contentPane.add(btnVolver);
		
		
		btnModificar = new JButton("Modificar");       //Este botón permite modificar la sucursal seleccionada
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				String direccion = table.getValueAt(fila,1).toString();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();     //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Branch SET address = ? WHERE id_Branch = ?" );
					
					
					ps.setString(1, direccion);
					
					ps.setInt(2, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Sucursal modificada");             //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha modificado la sucursal "+direccion);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar sucursal");      //En caso de fallar, lo avisa en pantalla
		                limpiar();
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnModificar.setBounds(484, 83, 89, 23);
		contentPane.add(btnModificar);
		
		btnAgregar = new JButton("Agregar");           //Este botón permite agregar una sucursal
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String direccion = txtDireccion.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();        //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Branch (address) VALUES (?)" );
					
					
					
					if(existeSucursal(direccion)!=0) {       //Revisa si ya existe la sucursal
						
						JOptionPane.showMessageDialog(null, "Sucursal ya existe");
					}else {
						ps.setString(1, direccion);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Sucursal guardada");             //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha añadido la sucursal "+direccion);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar sucursal");      //En caso de fallar, lo avisa en pantalla
		                limpiar();
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(385, 83, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");       //Este botón permite eliminar la fila seleccionada
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Branch WHERE id_Branch = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Sucursal eliminada");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado la sucursal "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar sucursal");      //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Sucursal está en uso, por favor elimine todos los registros relacionados");       //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(435, 122, 91, 23);
		contentPane.add(btnEliminar);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(446, 44, 127, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblDireccion.setBounds(385, 47, 59, 14);
		contentPane.add(lblDireccion);
		
		mostrarTabla();
	}
}

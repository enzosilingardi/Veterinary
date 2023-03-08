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
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Tabla_Instrumento extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){                // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Instrumento","Descripción"});   //Nombre de las columnas
	       
	        table.setModel(modelo);          //Setea el modelo
	         
	        
	        String datos[] = new String[3];     //Declara que va a haber 3 columnas
	       
	        try {
	        	Connection con = Connect.getConexion();    //Realiza la conexión
	        	//Sentencia sql
	        	PreparedStatement ps = con.prepareStatement("SELECT * From Medical_Instrument;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){                   //Carga las columnas de la base de datos en la tabla
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                
	                modelo.addRow(datos);

	            }
	            table.setModel(modelo);   //Setea el modelo
	            
	            table.getColumnModel().getColumn(0).setMaxWidth(0);            // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Tabla_Instrumento frame = new Tabla_Instrumento();
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
	public Tabla_Instrumento(String perfil) {                    //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 240, 240));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");             //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {           //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		
			JButton btnRel = new JButton("Añadir a quirófano");        //Abre la ventana Instumento_Quirófano
			btnRel.setForeground(new Color(255, 255, 255));
			btnRel.setBorder(null);
			btnRel.setBackground(new Color(86, 211, 243));
			btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
			btnRel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Instrumento_Quirofano iq = new Instrumento_Quirofano();
					iq.setVisible(true);
				}
			});
			btnRel.setBounds(40, 309, 146, 23);
			contentPane.add(btnRel);
			
			JButton btnModificar = new JButton("Modificar");           //Este botón permite modificar el instrumento seleccionado
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setBorder(null);
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String nombre = table.getValueAt(fila,1).toString();
					String descripcion = table.getValueAt(fila,2).toString();
					int result = 0;
					
					try {
						Connection con = Connect.getConexion();    //Realiza la conexión 
						
						PreparedStatement ps = con.prepareStatement("UPDATE Medical_Instrument SET instrument_Name = ?, instrument_Description = ? WHERE id_Medical_Instrument = ?");
						ps.setString(1, nombre);
						ps.setString(2, descripcion);
						ps.setInt(3, id);
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Instrumento modificado");          //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha modificado el instrumento "+table.getValueAt(fila,1).toString());
			                mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al modificar instrumento");     //En caso de fallar, lo avisa en pantalla
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
			});
			btnModificar.setBounds(139, 270, 91, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");              //Este botón elimina la fila seleccionada
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					try {
						Connection con = Connect.getConexion();    //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Instrument WHERE id_Medical_Instrument = ?" );
						
							ps.setInt(1, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Instrumento eliminado");     //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha eliminado el instrumento "+table.getValueAt(fila,1).toString());
			               mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al eliminar instrumento");     //En caso de fallar, lo avisa en pantalla
			                
			            }
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
						JOptionPane.showMessageDialog(null, "Instrumento está en uso, por favor elimine todos los registros relacionados");    //En caso de fallar, lo avisa en pantalla
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnEliminar.setBounds(237, 270, 91, 23);
			contentPane.add(btnEliminar);
			
			JButton btnAgregar = new JButton("Agregar");             //Abre la ventana Instrumento
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setBorder(null);
			btnAgregar.setBackground(new Color(86, 211, 243));
			btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Instrumento instrumento = new Instrumento();
					instrumento.setVisible(true);
					dispose();
				}
			});
			btnAgregar.setBounds(40, 270, 91, 23);
			contentPane.add(btnAgregar);
		
		}
		
		mostrarTabla();
	}

	public Tabla_Instrumento() {                   //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 240, 240));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");               //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnRel = new JButton("Añadir a quirófano");     //Abre la ventana Instrumento_Quirofano
		btnRel.setForeground(new Color(255, 255, 255));
		btnRel.setBorder(null);
		btnRel.setBackground(new Color(86, 211, 243));
		btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instrumento_Quirofano iq = new Instrumento_Quirofano();
				iq.setVisible(true);
			}
		});
		btnRel.setBounds(40, 309, 146, 23);
		contentPane.add(btnRel);
		
		JButton btnModificar = new JButton("Modificar");          //Este botón permite modificar el instrumento seleccionado
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombre = table.getValueAt(fila,1).toString();
				String descripcion = table.getValueAt(fila,2).toString();
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();    //Realiza la conexión
					PreparedStatement ps = con.prepareStatement("UPDATE Medical_Instrument SET instrument_Name = ?, instrument_Description = ? WHERE id_Medical_Instrument = ?");
					ps.setString(1, nombre);
					ps.setString(2, descripcion);
					ps.setInt(3, id);
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento modificado");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha modificado el instrumento "+table.getValueAt(fila,1).toString());
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar instrumento");     //En caso de fallar, lo avisa en pantalla
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
		});
		btnModificar.setBounds(139, 270, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");             //Este botón elimina la fila seleccionada
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();         //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Instrument WHERE id_Medical_Instrument = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento eliminado");          //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el instrumento "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar instrumento");       //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Instrumento está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(237, 270, 91, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");         //Abre la ventana Instrumento
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instrumento instrumento = new Instrumento();
				instrumento.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 270, 91, 23);
		contentPane.add(btnAgregar);
		
		mostrarTabla();
	}
}

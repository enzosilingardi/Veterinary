package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
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

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class Tabla_Usuarios extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbPerfil;

	void mostrarTabla(){               // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Usuario","Nombre","Apellido","Perfil","E-Mail"});      //Nombre de las columnas
       
        table.setModel(modelo);    //Setea el modelo
        
        
        String datos[] = new String[6];       //Declara que va a haber 6 columnas
       
        try {
        	Connection con = Connect.getConexion();        //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_User, username, name, surname, profile, email\r\n"
        												+ "FROM Users;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                   //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);     //Setea el modelo
            
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
	
	void mostrarTablaPerfil(String perfil){          // Muestra la tabla según el perfil ingresado
        
        String per = perfil;
		
		DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Usuario","Nombre","Apellido","Perfil","E-Mail"});    //Nombre de las columnas
       
        table.setModel(modelo);    //Setea el modelo
        
        
        String datos[] = new String[6];     //Declara que va a haber 6 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_User, username, name, surname, profile, email\r\n"
        												+ "FROM Users WHERE profile = ?;" );
        	ps.setString(1, per);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                      //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);   //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);           // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Tabla_Usuarios frame = new Tabla_Usuarios();
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
	public Tabla_Usuarios(String perfil) {                         //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 583, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");              //Cierra la ventana
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(534, 327, 89, 23);
		contentPane.add(btnVolver);

		if (perfil.equals("Admin") || perfil.equals("Manager")) {         //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
			
			JButton btnRel = new JButton("Añadir a sucursal");      //Abre la ventana Usuario_Sucursal
			btnRel.setForeground(new Color(255, 255, 255));
			btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
			btnRel.setBackground(new Color(86, 211, 243));
			btnRel.setBorder(null);
			btnRel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario_Sucursal us = new Usuario_Sucursal();
					us.setVisible(true);
				}
			});
			btnRel.setBounds(40, 327, 150, 23);
			contentPane.add(btnRel);
		}
		

		if (perfil.equals("Admin")) {      //Muestra los siguientes botones solo si el usuario es "Admin"
		
			JButton btnAgregar = new JButton("Agregar");           //Abre la ventana Usuario
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnAgregar.setBackground(new Color(86, 211, 243));
			btnAgregar.setBorder(null);
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario usuario = new Usuario();
					usuario.setVisible(true);
					dispose();
				}
			});
			btnAgregar.setBounds(40, 272, 91, 23);
			contentPane.add(btnAgregar);
			
			JButton btnModificar = new JButton("Modificar");         //Abre la ventana Modificar_Usuario
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setBorder(null);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Usuario mu = new Modificar_Usuario(table.getValueAt(fila,0).toString());     //Envía como parámetro el id de la fila seleccionada
					mu.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(139, 272, 91, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");        //Este botón elimina la fila seleccionada
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setBorder(null);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					try {
						Connection con = Connect.getConexion();     //Realiza la conexión
						
						PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE id_User = ?" );
						
							ps.setInt(1, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Usuario eliminado");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
			                
			                ControlFiles.addContent("Se ha eliminado el usuario "+table.getValueAt(fila,1).toString());
			               mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al eliminar usuario");      //En caso de fallar, lo avisa en pantalla
			                
			            }
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
						JOptionPane.showMessageDialog(null, "Usuario está en uso, por favor elimine todos los registros relacionados");       //En caso de fallar, lo avisa en pantalla
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnEliminar.setBounds(238, 272, 91, 23);
			contentPane.add(btnEliminar);
		}
		

		cbPerfil = new JComboBox();
		cbPerfil.setForeground(new Color(255, 255, 255));
		cbPerfil.setFont(new Font("Roboto", Font.BOLD, 14));
		cbPerfil.setBackground(new Color(86, 211, 243));
		cbPerfil.setBorder(null);
		cbPerfil.setBounds(380, 272, 116, 22);
		contentPane.add(cbPerfil);
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "Manager", "Regular"}));     //Crea un ComboBox con los tipos de perfiles
		
		JButton btnMostrar = new JButton("Mostrar");           //Muestra la tabla según el perfil seleccionado
		btnMostrar.setForeground(new Color(255, 255, 255));
		btnMostrar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnMostrar.setBackground(new Color(86, 211, 243));
		btnMostrar.setBorder(null);
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String per = cbPerfil.getSelectedItem().toString();
				
				if (per.equals("")) {      //Revisa si el ComboBox está en blanco
					mostrarTabla();
				} else {
					mostrarTablaPerfil(per);
				}
				
				
			}
		});
		btnMostrar.setBounds(506, 272, 117, 23);
		contentPane.add(btnMostrar);
		
		mostrarTabla();
	}

	public Tabla_Usuarios() {                       //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el ícono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 583, 238);
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
		btnVolver.setBounds(534, 327, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnRel = new JButton("Añadir a sucursal");         //Abre la ventana Usuario_Sucursal
		btnRel.setForeground(new Color(255, 255, 255));
		btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
		btnRel.setBackground(new Color(86, 211, 243));
		btnRel.setBorder(null);
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario_Sucursal us = new Usuario_Sucursal();
				us.setVisible(true);
			}
		});
		btnRel.setBounds(40, 327, 150, 23);
		contentPane.add(btnRel);
		
		JButton btnAgregar = new JButton("Agregar");          //Abre la ventana Usuario
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnAgregar.setBackground(new Color(86, 211, 243));
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 272, 91, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");       //Abre la ventana Modificar_Usuario
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setBorder(null);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Usuario mu = new Modificar_Usuario(table.getValueAt(fila,0).toString());        //Envía como parámetro el id de la fila seleccionada
				mu.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(139, 272, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");        //Este botón elimina la fila seleccionada
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setBorder(null);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE id_User = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario eliminado");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el usuario "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar usuario");     //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Usuario está en uso, por favor elimine todos los registros relacionados");      //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(238, 272, 91, 23);
		contentPane.add(btnEliminar);
		
		cbPerfil = new JComboBox();
		cbPerfil.setForeground(new Color(255, 255, 255));
		cbPerfil.setFont(new Font("Roboto", Font.BOLD, 14));
		cbPerfil.setBackground(new Color(86, 211, 243));
		cbPerfil.setBorder(null);
		cbPerfil.setBounds(380, 272, 116, 22);
		contentPane.add(cbPerfil);
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "Manager", "Regular"}));     //Crea un ComboBox con los tipos de perfiles
		
		JButton btnMostrar = new JButton("Mostrar");            //Muestra la tabla según el perfil seleccionado 
		btnMostrar.setForeground(new Color(255, 255, 255));
		btnMostrar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnMostrar.setBackground(new Color(86, 211, 243));
		btnMostrar.setBorder(null);
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String per = cbPerfil.getSelectedItem().toString();
				
				if (per.equals("")) {       //Revisa si el ComboBox está en blanco
					mostrarTabla();
				} else {
					mostrarTablaPerfil(per);
				}
				
				
			}
		});
		btnMostrar.setBounds(506, 272, 117, 23);
		contentPane.add(btnMostrar);
		
		mostrarTabla();
	}
}

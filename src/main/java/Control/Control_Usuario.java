package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;
import View.Modificar_Usuario;
import View.Tabla_Usuarios;
import View.Usuario;

public class Control_Usuario {
	public static int existe(String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			
			String SSQL = "SELECT count(username) FROM Users WHERE username = ?;";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);      //Si ya existe, la variable se pone en 1
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
	
	public static Connection agregar(String perfil, String nombre, String apellido, String nombreU, String contrasenia, String email) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Users (profile,name,surname,username,password,email) VALUES (?,?,?,?,?,?)" );
			
			
			if (perfil == "") {      //Revisa si el ComboBox está en blanco
				
				JOptionPane.showMessageDialog(null, "Seleccione un perfil");
			}else {
				//Revisa si el usuario ya existe
				if(existe(nombreU)!=0) {
				JOptionPane.showMessageDialog(null, "Usuario ya existe");
			}else {
				ps.setString(1, perfil);
				ps.setString(2, nombre);
				ps.setString(3, apellido);
				ps.setString(4, nombreU);
				
				if(contrasenia.length()<8) {             //Revisa que la contraseña tenga por lo menos 8 caracteres
					JOptionPane.showMessageDialog(null, "La contraseña debe tener por lo menos 8 caracteres");
				}else {
					ps.setString(5, contrasenia);
				}
				
				
				if(Usuario.validaEmail(email)) {        //Revisa si el E-Mail es válido
					ps.setString(6,email);
				} else {
					JOptionPane.showMessageDialog(null, "E-Mail no válido");
				}
				
				
				
			}
				
			}
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Usuario guardado");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha agregado el usuario "+nombreU);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar usuario");  //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		return null;
		
	}
	
	public static Connection modificar(String perfil, String nombre, String apellido, String nombreU, String contrasenia, String email, int id) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();           //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Users SET profile = ?, name = ?, surname = ?, username = ?, password = ?, email = ? WHERE id_User = ?" );
			
			
			if (perfil == "") {              //Revisa si el ComboBox está en blanco
				
				JOptionPane.showMessageDialog(null, "Seleccione un perfil");
			}else {
			
				ps.setString(1, perfil);
				ps.setString(2, nombre);
				ps.setString(3, apellido);
				ps.setString(4, nombreU);
				
				if(contrasenia.length()<8) {          //Verifica si le contraseña tiene por lo menos 8 caracteres
					
					JOptionPane.showMessageDialog(null, "La contraseña debe tener por lo menos 8 caracteres");
				}else {
					ps.setString(5, contrasenia);
				}
				
				
				if(Usuario.validaEmail(email)) {             //Verifica que el E-Mail sea valido
					ps.setString(6,email);
				} else {
					JOptionPane.showMessageDialog(null, "E-Mail no válido");
				}
				
				ps.setInt(7, id);
				
			}
				
			
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Usuario modificado");         //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, despues regresa a la ventana Tabla_Usuarios
                ControlFiles.addContent("Se ha modificado el usuario "+nombreU);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar usuario");      //En caso de fallar, lo avisa en pantalla
               
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection cargar(String usuario) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(usuario);
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			String SSQL = "SELECT profile, name, surname, username, password, email\r\n"		//Sentencia sql
					+ "FROM Users WHERE id_User = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                           //Carga los campos según los resultados de la base de datos
			Modificar_Usuario.cbPerfil.setSelectedItem(result.getString(1));	
			Modificar_Usuario.txtNombre.setText(result.getString(2));
			Modificar_Usuario.txtApellido.setText(result.getString(3));
			Modificar_Usuario.txtNombreUsuario.setText(result.getString(4));
			Modificar_Usuario.txtContrasenia.setText(result.getString(5));
			Modificar_Usuario.txtEmail.setText(result.getString(6));
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return null;
		
	}
	
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
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
		return null;
		
	}
	
	public static Connection tablaPer(DefaultTableModel modelo, JTable table, String per) {
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
		return null;
		
	}
	
	public static Connection eliminar(int id, String nombre) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();     //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE id_User = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Usuario eliminado");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el usuario "+nombre);
               
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
		
		return null;
		
	}
	
}

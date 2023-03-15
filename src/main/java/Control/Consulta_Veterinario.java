package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;
import View.Modificar_Veterinario;
import View.Tabla_Veterinario;

public class Consulta_Veterinario {

	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		
		modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Apellido","Matrícula","Dirección"});     //Nombre de las columnas
	       
        table.setModel(modelo);      //Setea el modelo
        
        
        String datos[] = new String[5];      //Declara que va a haber 5 columnas
       
        try {
        	Connection con = Connect.getConexion();     //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_Veterinarian, name, surname, medical_License, address\r\n"
        			+ "FROM Veterinarian;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                
                datos[0] = rs.getString(1);   //Carga las columnas de la base de datos en la tabla
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);     //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);       // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existe(String nombre, String apellido) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Veterinarian WHERE name = ? AND surname = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,nombre);
			pst.setString(2,apellido);

			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);     //Si ya existe, la variable se pone en 1
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
	
	public static Connection agregar(String direccion, String nombre, String apellido, String matricula) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();       //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Veterinarian (address,name,surname,medical_License) VALUES (?,?,?,?)" );
			
			
			
				if(existe(nombre,apellido)!=0) {    //Revisa si ya existe el veterinario
				JOptionPane.showMessageDialog(null, "Veterinario ya existe");
			}else {
				ps.setString(1, direccion);
				ps.setString(2,nombre);
				ps.setString(3,apellido);
				ps.setString(4,matricula);
			}
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Veterinario guardado");        //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha agregado el veterinario "+nombre+" "+apellido);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar veterinario");    //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection cargar(String veterinario) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(veterinario);
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT name, surname, medical_License\r\n"		//Sentencia sql
					+ "FROM Veterinarian WHERE id_Veterinarian = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                    //Carga los campos según los resultados de la base de datos
			Modificar_Veterinario.txtNombre.setText(result.getString(1));
			Modificar_Veterinario.txtApellido.setText(result.getString(2));
			Modificar_Veterinario.txtMatricula.setText(result.getString(3));
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
	
	public static Connection modificar(String direccion, String nombre, String apellido, String matricula,int id) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Veterinarian SET address = ?, name = ? ,surname = ? ,medical_License = ?  WHERE id_Veterinarian = ?" );
			
			
				ps.setString(1, direccion);
				ps.setString(2,nombre);
				ps.setString(3,apellido);
				ps.setString(4,matricula);
				ps.setInt(5, id);
			
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Veterinario guardado");         //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, después regresa a la ventna Tabla_Veterinario
                
                ControlFiles.addContent("Se ha modificado el veterinario "+nombre+" "+apellido);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar veterinario");      //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection eliminar(int id, String nombre) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();   //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Veterinarian WHERE id_Veterinarian = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Veterinario eliminado");        //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el veterinario "+nombre);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar veterinario");      //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace(); 
			JOptionPane.showMessageDialog(null, "Veterinario está en uso, por favor elimine todos los registros relacionados");    //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
}

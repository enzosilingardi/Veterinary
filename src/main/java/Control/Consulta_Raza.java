package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;

public class Consulta_Raza {

	public static int existe(String raza) {	//Verifica si ya existe el animal en la base de datos
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(type) FROM Breed WHERE type = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, raza);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);          //Si ya existe, la variable se pone en 1
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
	
	public static Connect agregar(String tipo) {
		int result = 0;
		
		
		try {
			Connection con = Connect.getConexion();
			PreparedStatement ps = con.prepareStatement("INSERT INTO Breed (type) VALUES (?)" );  //Crea el statement
			if(existe(tipo) != 0) {
				JOptionPane.showMessageDialog(null, "Raza ya existe");            //Revisa si ya existe el registro
			}else {
				ps.setString(1, tipo);
			}
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Raza guardada");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha agregado la raza "+tipo);

            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar raza");     //En caso de fallar, lo avisa en pantalla

            }
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
	}

		
		return null;
		
	}
	
	
	public static Connection modificar(String tipo, int id, String nombre) {
		
		int result = 0;
		
		
		try {
			Connection con = Connect.getConexion();  //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Breed SET type = ? WHERE id_Breed = ?" );  
			
			ps.setString(1, tipo);
			ps.setInt(2, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Raza modificada");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha modificado la raza "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar raza");    //En caso de fallar, lo avisa en pantalla
                
            }
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection eliminar(int id, String tipo) {
		int result = 0;

		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Breed WHERE id_Breed = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Raza eliminada");             //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado la raza "+tipo);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar raza");    //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Raza está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection tabla(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Raza"});        //Nombre de las columnas
	       
        table.setModel(modelo);           //Setea el modelo
        
        
        
        String datos[] = new String[2];      //Declara que va a haber 2 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	
        	PreparedStatement ps = con.prepareStatement("SELECT * FROM Breed;" );   //Sentencia sql
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                 //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);     //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);        // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
}

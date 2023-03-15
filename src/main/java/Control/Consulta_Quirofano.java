package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;
import View.Tabla_Quirofano;

public class Consulta_Quirofano {
	
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Quirófano"});      //Nombre de las columnas
	       
        table.setModel(modelo);     //Setea el modelo
        
        
        String datos[] = new String[2];      //Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();    //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT Operating_Room.id_Operating_Room, Operating_Room.room_Number\r\n"
        			+ "FROM Operating_Room;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
            	datos[0] = rs.getString(1);
            	datos[1] = rs.getString(2);
                
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);     //Setea el modelo

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
		return null;
	}
	
	public static int existe(int numero) {	//Revisa si ya existe el quirófano
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();        //Realiza la conexión
			
			String SSQL = "SELECT count(room_Number) FROM Operating_Room WHERE room_Number = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, numero);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);        //Si ya existe, la variable se pone en 1
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
	
	public static Connection agregar(int numero) {

		int result = 0;
		
		try {
			Connection con = Connect.getConexion();        //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Operating_Room (room_Number) VALUES (?)" );
			if(existe(numero) != 0) {
				JOptionPane.showMessageDialog(null, "Quirofano ya existe");
			}else {
				ps.setInt(1, numero);
			}
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Quirófano agrgado");                      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha agregado el quirófano "+numero);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar quirófano");        //En caso de fallar, lo avisa en pantalla
                 
            }
			
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection modificar(int numero, int id) {

		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Operating_Room SET room_Number = ? WHERE id_Operating_Room = ?" );
			
			ps.setInt(1, numero);
			ps.setInt(2, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Quirófano modificado");            //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo agrega al log, después regresa a la ventana Table_Quirofano
                ControlFiles.addContent("Se ha modificado el quirófano "+numero);
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar quirófano");     //En caso de fallar, lo avisa en pantalla
                
            }
			
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection eliminar(int id, String numero) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();        //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Operating_Room WHERE id_Operating_Room = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Quirofano eliminado");        //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el quirofano "+numero);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar quirofano");     //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Quirofano está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}

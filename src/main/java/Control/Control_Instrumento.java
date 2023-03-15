package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;

public class Control_Instrumento {
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
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
		return null;
	}
	
	public static int existe(String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();          //Realiza la conexión
			
			String SSQL = "SELECT count(instrument_Name) FROM Medical_Instrument WHERE instrument_Name = ?;";	//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);           //Si la relación ya existe, la variable se pone en 1
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
	
	public static Connection agregar(String nombre, String descripcion) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();        //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_Instrument (instrument_Name, instrument_Description) VALUES (?,?)" );
			
			if(existe(nombre) != 0) {                                   //Revisa si el instrumento ya existe
				JOptionPane.showMessageDialog(null, "Instrumento ya existe");
			}else {
				ps.setString(1, nombre);
				ps.setString(2, descripcion);
			}
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Instrumento guardado");                    //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha añadido un instrumento de nombre "+nombre);
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar instrumento");             //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection modificar(String nombre, String descripcion, int id) {
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
                
                ControlFiles.addContent("Se ha modificado el instrumento "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar instrumento");     //En caso de fallar, lo avisa en pantalla
               
            }
		
			con.close();
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
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Instrument WHERE id_Medical_Instrument = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Instrumento eliminado");     //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el instrumento "+nombre);
              
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
		return null;
	}
	
}

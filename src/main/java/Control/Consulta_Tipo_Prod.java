package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;

public class Consulta_Tipo_Prod {

	public static int existe(String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT count(type_Name) FROM Product_Type WHERE type_Name = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);         //Si ya existe el tipo, la variable se pone en 1
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
	
	public static Connection tabla(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Tipo de producto"});      //Nombre de las columnas
	       
        table.setModel(modelo);      //Setea el modelo
        
        
        
        String datos[] = new String[2];     //Declara que va a haber dos columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT * FROM Product_Type" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                   //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);     // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static Connection agregar(String nombre) {

		int result = 0;
		
		try {
			Connection con = Connect.getConexion();     //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Product_Type (type_Name) VALUES (?)" );
			
			
			
			if(existe(nombre)!=0) {       //Revisa si ya existe el tipo
				
				JOptionPane.showMessageDialog(null, "Tipo ya existe");
			}else {
				ps.setString(1, nombre);
			}
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Tipo guardado");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha añadido el tipo de producto "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar tipo");       //En caso de fallar, lo avisa en pantalla
               
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
			Connection con = Connect.getConexion();   //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Product_Type SET type_Name = ? WHERE id_Product_Type = ?" ); 					
			
			ps.setString(1, tipo);
			ps.setInt(2, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Tipo modificado");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha modificado el tipo de producto "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar tipo");    //En caso de fallar, lo avisa en pantalla
                
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
			Connection con = Connect.getConexion();       //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Product_Type WHERE id_Provider_Type = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Tipo eliminado");          //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el tipo de producto "+nombre);
             
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar tipo");       //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Tipo está en uso, por favor elimine todos los registros relacionados");    //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}

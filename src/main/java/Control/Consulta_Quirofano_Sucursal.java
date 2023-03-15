package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ComboItem;
import Model.ControlFiles;

public class Consulta_Quirofano_Sucursal {
	public static Connection tabla(DefaultTableModel modelo, JTable table) {

        modelo.setColumnIdentifiers(new Object[] {"ID","Sucursal","Quirófano"});        //Nombre de las columnas
        
        table.setModel(modelo);       //Setea el modelo
        
        
        
        String datos[] = new String[3];     //Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_BOR, address, room_Number\r\n"
        			+ "FROM Rel_Branch_Operating_R\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Branch_Operating_R.id_Branch\r\n"
        			+ "INNER JOIN Operating_Room ON Operating_Room.id_Operating_Room = Rel_Branch_Operating_R.id_Operating_Room;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);     //Setea el modelo
            
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
	
	public static int existe(Object quirofano, Object sucursal) {	//revisa si ya existe la relación
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Branch_Operating_R WHERE id_Operating_Room = ? AND id_Branch = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) quirofano);
			pst.setString(2, (String) sucursal);
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
	
	public static Connection agregar(Object quirofano, Object sucursal, String nombreQ, String nombreS) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Branch_Operating_R (id_Branch,id_Operating_Room) VALUES (?,?)" );
			

						ps.setString(1, (String) sucursal);
						ps.setString(2, (String) quirofano);
					
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Quirófano añadido a sucursal");                            //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha asociado el quirófano "+nombreQ+" a la sucursal "+nombreS);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al añadir quirófano");        //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection eliminar(int id, String nombreQ, String nombreS) {
		int result = 0;
		
		
		try {
			Connection con = Connect.getConexion();     //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Operating_R WHERE id_BOR = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Quirofano eliminado de sucursal"); //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el quirofano "+nombreQ+" de la sucursal "+nombreS);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar quirofano");     //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");    //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}

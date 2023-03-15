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

public class Control_Usuario_Sucursal {

	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Usuario","Sucursal"});    //Nombre de las columnas
	       
        table.setModel(modelo);         //Setea el modelo
        
        
        
        String datos[] = new String[3];       //Decalara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_UB, username, address\r\n"
        			+ "FROM Rel_Users_Branch\r\n"
        			+ "INNER JOIN Users ON Users.id_User = Rel_Users_Branch.id_User\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Users_Branch.id_Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);         // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existe(Object usuario, Object sucursal) {	//determina si ya existe la relación
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Users_Branch WHERE id_User = ? AND id_Branch = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) usuario);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);           // si la relacion ya existe, entonces la variable se pone en 1
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
	
	public static Connection agregar(Object usuario, Object sucursal, String nombreU, String nombreS) {
		

		int result = 0;
		
		try {
			Connection con = Connect.getConexion();   //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Users_Branch (id_User,id_Branch) VALUES (?,?)" );
			
			
						ps.setString(1, (String) usuario);
						ps.setString(2, (String) sucursal);
						
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Usuario colocado");           //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha colocado el usuario "+nombreU+" en la sucursal "+nombreS);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al colocar usuario");       //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public static Connection eliminar(int id, String usuario, String sucursal) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();        //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Users_Branch WHERE id_UB = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Usuario eliminado de sucursal");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el usuario "+usuario+" de la sucursal "+sucursal);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar usuario");     //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");      //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}

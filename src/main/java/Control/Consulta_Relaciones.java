package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;


public class Consulta_Relaciones {

	public static Connection tablaAR(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Animal","Raza"});       // Nombre de las columnas
	       
        table.setModel(modelo);                    //Setea el modelo
        
        
        
        String datos[] = new String[3];         // Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT id_AB, Animal.type, Breed.type\r\n"     // sentencia sql
        			+ "FROM Rel_Animal_Breed\r\n"
        			+ "INNER JOIN Animal ON Animal.id_Animal = Rel_Animal_Breed.id_Animal\r\n"
        			+ "INNER JOIN Breed ON Breed.id_Breed = Rel_Animal_Breed.id_Breed;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                                // cargan las columnas de la base de datos a la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);                       					//Se setea el modelo
            table.getColumnModel().getColumn(0).setMaxWidth(0);				// los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existeAR(Object animal, Object raza) {	//revisa si ya existe la relación entre animal y raza
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();              //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Animal_Breed WHERE id_Animal = ? AND id_Breed = ?;"; 			//sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) animal);
			pst.setString(2, (String) raza);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);  				// si la relacion ya existe, entonces la variable se pone en 1
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
	
	public static Connection agregarAr(Object animal, Object raza) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();
			PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Animal_Breed (id_Animal ,id_Breed) VALUES (?,?)" );
			

					
			ps.setString(1, (String) animal);
			ps.setString(2, (String) raza);
					
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Raza asociada");				// en caso de ser exitoso, muestra un cartel en pantalla indicando que fue un exito
              
                ControlFiles.addContent("Se ha asociado la raza "+raza+" al animal "+animal);		// añade al log la accion realizada	
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al asociar raza");				// en caso de fallar, lo muestra en un cartel en pantalla
                
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
	
	public static Connect eliminarAR(int id) {
		int result = 0;

		
		try {
			Connection con = Connect.getConexion();
			PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Animal_Breed WHERE id_AB = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){																						// en caso de ser exitoso lo muestra en pantalla y lo añade al log
                JOptionPane.showMessageDialog(null, "Raza eliminada de animal");
                ControlFiles.addContent("Se ha eliminado una relación entre una raza y un tipo de animal");
            
            } else {																						// en caso de fallar lo muestra por pantalla
                JOptionPane.showMessageDialog(null, "Error al eliminar raza");
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");			// en caso de fallar lo muestra por pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
}

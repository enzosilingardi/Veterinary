package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;

public class Consulta_Animal {

	public static int existe(String animal) {	//Verifica si ya existe el animal en la base de datos
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(type) FROM Animal WHERE type = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, animal);
			result = pst.executeQuery();
			
			if (result.next()) {				// si ya existe, la variable la coloca como 1
				return result.getInt(1);
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
	
	public static Connection agregar(String tipo) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();
			PreparedStatement ps = con.prepareStatement("INSERT INTO Animal (type) VALUES (?)" );  //Crea la sentencia sql
			if(existe(tipo) != 0) {
				JOptionPane.showMessageDialog(null, "Animal ya existe");            //Revisa si ya existe el registro
			}else {
				ps.setString(1, tipo);
			}
			
			result = ps.executeUpdate();
			
			if(result > 0){																//Si es exitoso lo muestra en pantalla y lo añade al log
                JOptionPane.showMessageDialog(null, "Animal guardado");
                ControlFiles.addContent("Se ha añadido el tipo de animal "+tipo);
                
            } else {																	//Si falla lo muestra en pantalla
                JOptionPane.showMessageDialog(null, "Error al guardar animal");
                
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
	
	public static Connection modificar(String tipo, int id) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();
			PreparedStatement ps = con.prepareStatement("UPDATE Animal SET type = ? WHERE id_Animal = ?" );  //Crea la sentencia sql
			
			ps.setString(1, tipo);
			ps.setInt(2, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){																		//Si es exitoso lo muestra por pantalla y lo añade al log
                JOptionPane.showMessageDialog(null, "Animal modificado");
                ControlFiles.addContent("Se ha modificado un tipo de animal a "+tipo);
                
            } else {																			//Si falla lo muestra por pantalla
                JOptionPane.showMessageDialog(null, "Error al modificar animal");
                
            }
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection eliminar(int id) {
		int result = 0;
		
		
		try {
			Connection con = Connect.getConexion();
			PreparedStatement ps = con.prepareStatement("DELETE FROM Animal WHERE id_Animal = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Animal eliminado");			//Si es exitoso lo muestra en pantalla y lo añade al log
                ControlFiles.addContent("Se ha eliminado un tipo de animal");		
              
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar animal");	// Si falla, lo muestra en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Animal está en uso, por favor elimine todos los registros relacionados");			// Si falla, lo muestra en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection tabla(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Animal"});			// nombre de las columnas
	       
        table.setModel(modelo);
        
        
        
        String datos[] = new String[2]; 							//declara que va a haber 2 columnas
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT * FROM Animal;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){								// carga las columnas de la base de datos a la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);								// se setea el modelo
            
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
	
}

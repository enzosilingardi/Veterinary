package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;
import View.Modificar_Historial;
import View.Tabla_Historial;

public class Consulta_Historial {

	public static Connection tabla(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Mascota","Dueño","Descripción","Fecha","IDMas"});   //Nombre de las columnas
	       
        table.setModel(modelo);    //Setea el modelo
        
        
        
        String datos[] = new String[6];    //Declara que va a haber 6 columnas
       
        try {
        	Connection con = Connect.getConexion();    //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_Medical_History, Pet.name,  Client.name, Client.surname, description, CONVERT(varchar(10),date,103),Pet.id_Pet\r\n"
        			+ "FROM Medical_History\r\n"
        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_History.id_Pet\r\n"
        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client\r\n"
        			+ "ORDER BY Pet.name;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3)+" "+rs.getString(4);
                datos[3] = rs.getString(5);
                datos[4] = rs.getString(6);
                datos[5] = rs.getString(7);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);      //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);           // los 4 siguientes hacen que la columna del id sea invisible para el usuario
    		table.getColumnModel().getColumn(0).setMinWidth(0);
    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
    		table.getColumnModel().getColumn(0).setResizable(false);
    		table.getColumnModel().getColumn(5).setMaxWidth(0);          // los 4 siguientes hacen que la columna del idMas sea invisible para el usuario
    		table.getColumnModel().getColumn(5).setMinWidth(0);
    		table.getColumnModel().getColumn(5).setPreferredWidth(0);
    		table.getColumnModel().getColumn(5).setResizable(false);
        } catch(SQLException E) {
			JOptionPane.showMessageDialog(null,E);
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public static Connection tablaId(DefaultTableModel modelo, JTable table, String id ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Mascota","Dueño","Descripción","Fecha","IDMas"});   //Nombre de las columnas
	       
        table.setModel(modelo);       //Setea el modelo
        
        int idM = Integer.parseInt(id);
        
        String datos[] = new String[6];     //Declara que va a haber 6 columnas
       
        try {
        	Connection con = Connect.getConexion();    //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_Medical_History, Pet.name,  Client.name, Client.surname, description, CONVERT(varchar(10),date,103),Pet.id_Pet\r\n"
        			+ "FROM Medical_History\r\n"
        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_History.id_Pet\r\n"
        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client WHERE Pet.id_Pet ='"+idM+"';" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                        //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3)+" "+rs.getString(4);
                datos[3] = rs.getString(5);
                datos[4] = rs.getString(6);
                datos[5] = rs.getString(7);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);        //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);             // los 4 siguientes hacen que la columna del id sea invisible para el usuario
    		table.getColumnModel().getColumn(0).setMinWidth(0);
    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
    		table.getColumnModel().getColumn(0).setResizable(false);
    		table.getColumnModel().getColumn(5).setMaxWidth(0);               // los 4 siguientes hacen que la columna del idMas sea invisible para el usuario
    		table.getColumnModel().getColumn(5).setMinWidth(0);
    		table.getColumnModel().getColumn(5).setPreferredWidth(0);
    		table.getColumnModel().getColumn(5).setResizable(false);
        } catch(SQLException E) {
			JOptionPane.showMessageDialog(null,E);
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public static Connection agregar(int idM, String descripcion, Date date, String mascota) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();   //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_History (id_Pet,description,date) VALUES (?,?,?)" );
			
			
		
			
				ps.setInt(1, idM);
				ps.setString(2, descripcion);
				ps.setDate(3, date);
			
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Historial guardado");                            //Si fue existoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha añadido un historial para la mascota "+mascota);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar historial");                   //En caso de fallar, lo avisa en pantalla
                
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
	
	public static Connection cargar(String historial) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(historial);
		
		try {
			cn = (Connection) Connect.getConexion();           //Realiza la conexión
			
			String SSQL = "SELECT description, date\r\n"		//Sentencia sql
					+ "FROM Medical_History\r\n"
					+ "WHERE id_Medical_History = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                              //Carga los campos segun el resultado en la base de datos
			Modificar_Historial.txtDescripcion.setText(result.getString(1));
			Modificar_Historial.txtFecha.setDate(result.getDate(2));
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
	
	public static Connection modificar(int idM, String descripcion, Date date, int id, String mascota) {

		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Medical_History SET id_Pet = ?, description = ?, date = ? WHERE id_Medical_History = ?" );
			
			
			
			
				ps.setInt(1, idM);
				ps.setString(2, descripcion);
				ps.setDate(3, date);
				ps.setInt(4, id);
			
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Historial guardado");                         //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo agrega al log, despues regresa a la ventana Tabla_Historial
                ControlFiles.addContent("Se ha modificado un historial de la mascota "+mascota);

            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar historial");       //En caso de fallar, lo avisa en pantalla
                
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
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_History WHERE id_Medical_History = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate(); 
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Eliminado del historial");     //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado un historial de la mascota"+nombre);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar del historial");   //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Historial está en uso, por favor elimine todos los registros relacionados");      //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
}

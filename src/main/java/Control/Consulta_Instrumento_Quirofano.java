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

public class Consulta_Instrumento_Quirofano {

	public static Connection tabla(DefaultTableModel modelo, JTable table) {

        modelo.setColumnIdentifiers(new Object[] {"ID","Quirófano","Instrumento","Cantidad"});      //Nombre de las columnas
       
        table.setModel(modelo);            //Setea el modelo
        
        
        
        String datos[] = new String[4];           //Declara que va a haber 4 columnas
       
        try {
        	Connection con = Connect.getConexion();         //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_ORMI, room_Number, instrument_Name, quantity\r\n"
        			+ "FROM Rel_Operating_R_Medical_I\r\n"
        			+ "INNER JOIN Operating_Room ON Operating_Room.id_Operating_Room = Rel_Operating_R_Medical_I.id_Operating_Room\r\n"
        			+ "INNER JOIN Medical_Instrument ON Medical_Instrument.id_Medical_Instrument = Rel_Operating_R_Medical_I.id_Medical_Instrument;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                   // cargan las columnas de la base de datos a la tabla           
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);           // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existe(Object quirofano, Object instrumento) {	//revisa si ya existe la relación
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();    //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Operating_R_Medical_I WHERE id_Operating_Room = ? AND id_Medical_Instrument = ?;";   //Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) quirofano);
			pst.setString(2, (String) instrumento);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);                          //Si la relación ya existe, la variable se pone en 1
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
	
	public static Connection eliminar(int id, String nombreI, String nombreQ) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();               //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Operating_R_Medical_I WHERE id_ORMI = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Instrumento eliminado de quirofano");                  //Si fue existoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha removido el instrumento "+nombreI +" del quirófano "+nombreQ);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar instrumento");              //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");            //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection agregar(Object quirofano, Object instrumento, int cantidad,String nombreQ, String nombreI) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Operating_R_Medical_I (id_Operating_Room,id_Medical_Instrument,quantity) VALUES (?,?,?)" );
			
			
						ps.setString(1, (String) quirofano);
						ps.setString(2, (String) instrumento);
						ps.setInt(3, cantidad);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Instrumento colocado");             //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha asociado el instrumento "+nombreI+" al quirofano "+nombreQ);

            } else {
                JOptionPane.showMessageDialog(null, "Error al colocar instrumento");       //En caso de fallar, lo avisa en pantalla

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
	
	public static Connection modificar(int cantidad, int id, String nombreI, String nombreQ) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();        //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Rel_Operating_R_Medical_I SET quantity = ? WHERE id_ORMI = ?");
			
				
				if(cantidad<0){
					JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);     //En caso de ser un número negativo, lo avisa en pantalla
					
			
				}else {
					
					ps.setInt(1,cantidad);
			
				}
				
			
			
			
			ps.setInt(2, id);
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Cantidad modificada");        //Si fue existoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha modificado la cantidad del instrumento "+nombreI+" en el quirófano "+nombreQ);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar cantidad");        //En caso de fallar, lo avisa en pantalla
                
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
	
	public static Connection todos(Object instrumento, int cantidad) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();       //Realiza la conexión
			
			PreparedStatement pre = con.prepareStatement("SELECT id_Operating_Room FROM Operating_Room" );
			
			
			
			
			if((String) instrumento == ""){        //Revisa que el ComboBox no esté vacío
				
				JOptionPane.showMessageDialog(null, "Seleccione un instrumento");
				
			}else {
				
				
					
				ResultSet rs = pre.executeQuery();
				
				while (rs.next()){
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Operating_R_Medical_I (id_Operating_Room,id_Medical_Instrument,quantity) VALUES (?,?,?)" );
					
					if(existe(rs.getString(1),(String) instrumento)!=0) {      //Revisa que el instrumento no se encuentre en el quirófano
						
						
					}else {
					
					ps.setString(1, rs.getString(1));
					
					ps.setString(2, (String) instrumento);
					
					ps.setInt(3, cantidad);
					
					result = ps.executeUpdate();
					
					}
	            }
				
				
				
			}
				
		
				
				
			
			
			
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Instrumento colocado en todos los quirófanos");        //Si fue existoso, lo avisa mediante un mensaje en pantalla 

            } else {
                JOptionPane.showMessageDialog(null, "Error al colocar instrumento");               //En caso de fallar, lo avisa en pantalla

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
	
}

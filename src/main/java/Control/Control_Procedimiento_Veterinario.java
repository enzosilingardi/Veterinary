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

public class Control_Procedimiento_Veterinario {
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Veterinario","Procedimiento"});     //Nombre de las columnas
	       
        table.setModel(modelo);            //Setea el modelo
        
        
        
        String datos[] = new String[3];      //Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();   //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_VMP ,name, surname, proced_Name,CONVERT(varchar(10),proced_Date,103) AS pd ,CONVERT(varchar(10),proced_Time,8) as pt\r\n"
        			+ "FROM Rel_Veterinarian_Medical_P\r\n"
        			+ "INNER JOIN Medical_Procedure ON Medical_Procedure.id_Procedure = Rel_Veterinarian_Medical_P.id_Procedure\r\n"
        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
        			+ "INNER JOIN Veterinarian ON Veterinarian.id_Veterinarian = Rel_Veterinarian_Medical_P.id_Veterinarian;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                   //Carga las columnas de la base de datos en la tabla
            	datos[0] = rs.getString(1);
                datos[1] = rs.getString(2)+" "+rs.getString(3);
                datos[2] = rs.getString(4)+" - "+rs.getString(5)+" "+rs.getString(6);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo

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
	
	public static int existe(Object procedimiento, Object veterinario){
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();    //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Veterinarian_Medical_P WHERE id_Procedure = ? AND id_Veterinarian = ?;";	//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) procedimiento);
			pst.setString(2, (String) veterinario);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);    // si la relacion ya existe, entonces la variable se pone en 1
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
	
	public static Connection agregar(Object proced, Object veterinario,String nombreP, String nombreV){
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();       //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Veterinarian_Medical_P (id_Veterinarian,id_Procedure) VALUES (?,?)" );
			
			
						ps.setString(2, (String) proced);
						ps.setString(1, (String) veterinario);
						
					
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Veterinario colocado");    //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log

                ControlFiles.addContent("Se ha asociado el veterinario "+nombreV+" al procedimiento "+nombreP);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al colocar veterinario");  //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection eliminar(int id, String nombreV, String nombreP) {
		int result = 0;

		
		try {
			Connection con = Connect.getConexion();     //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Veterinarian_Medical_P WHERE id_VMP = ?" );
			
			ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Veterinario eliminado de Procedimiento");     //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el veterinario "+nombreV+" del procedimiento "+nombreP);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar veterinario");        //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}

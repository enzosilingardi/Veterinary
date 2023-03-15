package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ComboItem;
import Model.ControlFiles;
import View.Modificar_Turno;
import View.Tabla_Turnos;

public class Control_Turno {
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Mascota","Procedimiento","Fecha","Hora"});       //Nombre de las columnas
	       
        table.setModel(modelo);     //Setea el modelo
        
        
        String datos[] = new String[5];     //Declara que va a haber 5 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_Procedure, name, proced_Name, CONVERT(varchar(10),proced_Date,103),CONVERT(varchar(10),proced_Time,8)\r\n"
        			+ "FROM Medical_Procedure\r\n"
        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_Procedure.id_Pet\r\n"
        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                  //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                modelo.addRow(datos);
                

            }
            table.setModel(modelo);    //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);          // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existe(Date date, Time time) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Medical_Procedure WHERE proced_Date = ? And proced_Time = ?;";  // Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setDate(1,date);
			pst.setTime(2, time);
			
			result = pst.executeQuery();
			
			if (result.next()) {
				
				return result.getInt(1);    //Si ya existe el turno, la variable se pone en 1
				
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
	
	public static Connection agregar(Object tipo, int idM, Date date, Time start) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_Procedure (id_Procedure_Type, id_Pet, proced_Date,proced_Time) VALUES (?,?,?,?)" );
			
			
			
				ps.setString(1, (String) tipo);
				ps.setInt(2, idM);
				ps.setDate(3, date);
				
				if(existe(date,start) != 0) {                           //Revisa si ya existe el turno
					JOptionPane.showMessageDialog(null, "Turno ya existe");
				}else {
					ps.setTime(4, start);
				}
				
				
					
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Turno guardado");    //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha añadido un turno para la fecha "+date+" y hora "+start);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar turno");    //En caso de fallar, lo avisa en pantalla
                
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
	
	public static Connection cargar(String turno) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(turno);
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT proced_Date, proced_Time FROM Medical_Procedure WHERE id_Procedure = ?";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                   //Carga los campos según los resultados de la base de datos
			Modificar_Turno.txtFecha.setDate(result.getDate(1));
			Modificar_Turno.txtHora.setDate(result.getTime(2));
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
	
	public static Connection modificar(Object tipo, int idM, Date date, Time start, int id) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();        //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Medical_Procedure SET id_Procedure_Type = ?, id_Pet = ?, proced_Date = ?,proced_Time = ? WHERE id_Procedure = ?" );
			
			
			
				ps.setString(1, (String) tipo);
				ps.setInt(2, idM);
				ps.setDate(3, date);
				ps.setTime(4, start);
				ps.setInt(5, id);
				
				
					
		
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Turno modificado");         //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, despues vuelve a la ventana Tabla_Turnos
                
                ControlFiles.addContent("Se ha modificado el turno para la fecha "+date+" y hora "+start);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar turno");   //En caso de fallar, lo avisa en pantalla
                
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
	
	public static Connection eliminar(int id,String fecha,String hora) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Procedure WHERE id_Procedure = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Turno eliminado");   //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el turno para la fecha "+fecha+" y hora "+hora);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar turno");      //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Turno está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
}

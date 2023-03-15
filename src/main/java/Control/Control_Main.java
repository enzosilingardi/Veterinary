package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Control_Main {
	public static Connection tabla(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Mascota","Procedimiento","Fecha","Hora"});      //Nombres de las columnas
	       
        table.setModel(modelo);                //Setea el modelo
        
        
        String datos[] = new String[5];         //Declara que va a haber 5 columnas
       
        try {
        	Connection con = Connect.getConexion();             //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT TOP 10 id_Procedure, name, proced_Name, CONVERT(varchar(10),proced_Date,103),CONVERT(varchar(10),proced_Time,8)\r\n"
        			+ "FROM Medical_Procedure\r\n"
        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_Procedure.id_Pet\r\n"
        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
        			+ "ORDER BY proced_Date DESC" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                modelo.addRow(datos);
                

            }
            table.setModel(modelo);          //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);                // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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

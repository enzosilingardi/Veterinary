package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import View.Presupuesto;

public class Control_Presupuesto {
	public static Connection cargar() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			String SSQL = "SELECT * FROM Emitter";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			
			
			while (result.next()) {                            //Carga los campos según los resultados de la base de datos
				Presupuesto.txtEmisor.setText(result.getString("name"));
				Presupuesto.txtCuit.setText(result.getString("cuit"));
				Presupuesto.txtDir.setText(result.getString("address"));
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return null;
	}
}

package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import View.Factura;



public class Control_Factura {

	public static Connection cargar() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		
		try {
			cn = (Connection) Connect.getConexion();           //Realiza la conexión
			String SSQL = "SELECT * FROM Emitter";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			
			
			while (result.next()) {                       //Carga los campos con los datos en el registro
				Factura.txtEmisor.setText(result.getString("name"));
				Factura.txtCuit.setText(result.getString("cuit"));
				Factura.txtDir.setText(result.getString("address"));
				
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

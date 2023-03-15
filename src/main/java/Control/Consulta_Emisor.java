package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import View.Emisor;
import View.Emisor_Pre;
import View.Factura;

public class Consulta_Emisor {
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
				Emisor.txtEmisor.setText(result.getString("name"));
				Emisor.txtCuit.setText(result.getString("cuit"));
				Emisor.txtEmpresa.setText(result.getString("address"));
				
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
	
	public static Connection cargarPre() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		
		try {
			cn = (Connection) Connect.getConexion();           //Realiza la conexión
			String SSQL = "SELECT * FROM Emitter";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			
			
			while (result.next()) {                       //Carga los campos con los datos en el registro
				Emisor_Pre.txtEmisor.setText(result.getString("name"));
				Emisor_Pre.txtCuit.setText(result.getString("cuit"));
				Emisor_Pre.txtEmpresa.setText(result.getString("address"));
				
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
	
	public static Connection editar(String nombre, String empresa, String cuit) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();       //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Emitter SET name = ?, cuit = ?, address = ? WHERE id_Emitter = 1" );
			
			
		
				
				ps.setString(1, nombre);
				ps.setString(3, empresa);
				ps.setString(2,cuit);
			
				
			
			
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Emisor modificado");       //En caso de ser exitoso, lo muestra en pantalla y vuelve a la ventana Factura
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar emisor");      //En caso de fallar, lo muestra en pantalla
               
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}

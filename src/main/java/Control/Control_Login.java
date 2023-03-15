package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Control_Login {

	public static int existeUsuario(String usuario, String contrasenia) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Users WHERE username = ? AND password = ?   ;";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, usuario);
			pst.setString(2, contrasenia);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);               //Si existe, la variable se pone en 1
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
	
	public static String perfilUsuario(String usuario, String contrasenia) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();          //Realiza la contraseña
			
			String SSQL = "SELECT profile FROM Users WHERE username = ? AND password = ?   ;";	//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, usuario);
			pst.setString(2, contrasenia);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getString(1);        //Toma el contenido de el campo profile
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}

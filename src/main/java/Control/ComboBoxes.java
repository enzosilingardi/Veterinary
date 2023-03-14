package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Model.ComboItem;






public class ComboBoxes {
		public static  DefaultComboBoxModel CBAnimal(DefaultComboBoxModel modelo){
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			
			try {
				cn = (Connection) Connect.getConexion();        //Realiza la conexión
				
				String SSQL = "SELECT * FROM Animal ORDER BY id_Animal";	//Sentencia sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("Seleccionar animal",""));                //El primer elemento es "Seleccionar animal"
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));    //El elemento del ComboBox recibe el tipo de animal como label y el id como valor
					
				}
				cn.close();
			}catch(SQLException e) {
				e.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return modelo;
		}
		
		public static DefaultComboBoxModel CBRaza(DefaultComboBoxModel modelo, Object animal) {          //Carga el ComboBox raza recibiendo como parámetro el animal
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			String an = (String) animal;
			
			
			try {
				cn = (Connection) Connect.getConexion();         //Realiza la conexión
				String SSQL = "SELECT *\r\n"
						+ "FROM Breed\r\n"
						+ "INNER JOIN Rel_Animal_Breed ON Rel_Animal_Breed.id_Breed = Breed.id_Breed\r\n"   //Toma solo las razas que están relacionadas al animal elegido
						+ "WHERE Rel_Animal_Breed.id_Animal = "+an;
				pst = cn.prepareStatement(SSQL);
				
				result = pst.executeQuery();
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Breed")));       //El elemento del ComboBox recibe el tipo de raza como label y el id como valor  
					
				}
				cn.close();
			}catch(SQLException e) {
				e.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return modelo;
	    }
		
		public static DefaultComboBoxModel CBRazaS(DefaultComboBoxModel modelo) {          //Carga el ComboBox raza recibiendo como parámetro el animal
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			
			try {
				cn = (Connection) Connect.getConexion();
				String SSQL = "SELECT * FROM Breed ORDER BY id_Breed";      // realiza una sentencia sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Breed")));           //El elemento del ComboBox recibe el tipo de raza como label y el id como valor
					
				}
				cn.close();
			}catch(SQLException e) {
					JOptionPane.showMessageDialog(null,e);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return modelo;
	    }
		
		public static DefaultComboBoxModel CBTipoProv(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			try {
				cn = (Connection) Connect.getConexion();    //Realiza la conexión
				
				String SSQL = "SELECT * FROM Provider_Type ORDER BY id_Provider_Type";		//Sentencia Sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));         //El primer elemento es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("type_Name"),result.getString("id_Provider_Type")));    //El elemento del ComboBox recibe el tipo de proveedor como label y el id del tipo como valor
					
				}
				cn.close();
			}catch(SQLException e) {
					JOptionPane.showMessageDialog(null,e);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			return modelo;
			
		}
}

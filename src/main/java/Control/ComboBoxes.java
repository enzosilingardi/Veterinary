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
		
		public static DefaultComboBoxModel CBTipoProd(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			try {
				cn = (Connection) Connect.getConexion();    //Realiza la conexión
				
				String SSQL = "SELECT * FROM Product_Type ORDER BY id_Product_Type";	//Sentencia Sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));     //El primer elemento es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("type_Name"),result.getString("id_Product_Type")));     //El elemento del ComboBox recibe el tipo de producto como label y el id del tipo como valor
					
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
		
		public static DefaultComboBoxModel CBMascota(DefaultComboBoxModel modelo) {
			
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			   
			
			
			try {
				cn = (Connection) Connect.getConexion();         //Realiza la conexión
				
				String SSQL = "SELECT id_Pet,Pet.name as petN, Client.name as clientN, Client.surname as ClientS\r\n"       //Realiza una sentencia sql
						+ "FROM Pet\r\n"
						+ "INNER JOIN Client ON Pet.id_Client = Client.id_Client\r\n"
						+ "ORDER BY id_Pet";
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("petN")+" - Dueño: "+result.getString("clientN")+" "+result.getString("clientS"),result.getString("id_Pet")));   //El elemento del ComboBox recibe el nombre de la mascota, el nombre y apellido de su dueño como label y como valor el id de la mascota
					
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
		
		public static DefaultComboBoxModel CBTipoProc(DefaultComboBoxModel modelo) {
			
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			
			try {
				cn = (Connection) Connect.getConexion();      //Realiza la conexión
				
				String SSQL = "SELECT * FROM Procedure_Type ORDER BY id_Procedure_Type";  //Sentencia Sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));         //El primer elemento del ComboBox es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("proced_Name"),result.getString("id_Procedure_Type")));    //El elemento del ComboBox recibe el nombre del procedimiento como label y el id del procedimiento como valor
					
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
		
		public static DefaultComboBoxModel CBProducto(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			
			try {
				cn = (Connection) Connect.getConexion();      //Realiza la conexión
				
				String SSQL = "SELECT * FROM Product ORDER BY id_Product";		//Sentencia Sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));    //El primer elemento es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("product_Name"),result.getString("id_Product")));     //El elemento del ComboBox recibe el nombre del producto como label y el id como valor
					
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
		
		public static DefaultComboBoxModel CBSucursal(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			try {
				cn = (Connection) Connect.getConexion();    //Realiza la conexión
				String SSQL = "Select *\r\n"		//Sentencia Sql
						+ "FROM Branch\r\n"
						+ "ORDER BY Branch.address";
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));       //El primer elemento es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));      //El elemento del ComboBox recibe la dirección de la sucursal como label y el id como valor
					
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
		
		public static DefaultComboBoxModel CBUsuario(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			
			try { 
				cn = (Connection) Connect.getConexion();      //Realiza la conexión
				
				String SSQL = "SELECT * FROM Users ORDER BY id_User";		//Sentencia Sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));     //El primer elemento está en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("username"),result.getString("id_User")));      //El elemento del ComboBox recibe el nombre de usuario como label y el id como valor
					
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
		
		public static DefaultComboBoxModel CBVeterinario(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			
			
			try {
				cn = (Connection) Connect.getConexion();   //Realiza la conexión
				String SSQL = "Select *"		//Sentencia Sql
						+ "FROM Veterinarian";
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));     //El primer elemento está en blanco
				
				while (result.next()) {
					 //El elemento del ComboBox recibe el nombre y apellido del veterinario como label y el id del veterinario como valor
					modelo.addElement(new ComboItem(result.getString("name")+" "+result.getString("surname"),result.getString("id_Veterinarian")));
					
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
		
		public static DefaultComboBoxModel CBQuirofano(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			try {
				cn = (Connection) Connect.getConexion();         //Realiza la conexión
				
				String SSQL = "SELECT * FROM Operating_Room ORDER BY id_Operating_Room";
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("room_Number"),result.getString("id_Operating_Room")));       //El elemento del ComboBox recibe el número del quirófano como label y el id como valor
					
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
		
		public static DefaultComboBoxModel CBInstrumento(DefaultComboBoxModel modelo) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;

			
			try {
				cn = (Connection) Connect.getConexion();                    //Realiza la conexión
				
				String SSQL = "SELECT * FROM Medical_Instrument ORDER BY id_Medical_Instrument";		//Sentencia sql
				pst = cn.prepareStatement(SSQL);
				result = pst.executeQuery();
				modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
				
				while (result.next()) {
					modelo.addElement(new ComboItem(result.getString("instrument_Name"),result.getString("id_Medical_Instrument")));         //El elemento del ComboBox recibe el nombre del instrumento como label y el id del instrumento como valor
					
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

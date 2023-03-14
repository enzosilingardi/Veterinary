package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;
import View.Cliente;
import View.Modificar_Cliente;

public class Consulta_Cliente {

	public static int existe(String nombre, String dni) {	//Verifica si el cliente existe en la base de datos
		
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Client WHERE name = ? AND dni = ?   ;";   //Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,nombre);
			pst.setString(2,dni);
			
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);        // si ya existe, la variable la coloca como 1
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
	
	public static Connection agregar(String direccion, String dni, String nombre, String apellido, String telefono, Date date, String genero, String email) {
		

		
		int result = 0;
		
		try {
			PreparedStatement ps = null;
			Connection con = Connect.getConexion();         //Realiza la conexión
			
			ps = con.prepareStatement("INSERT INTO Client (address, dni, name,surname,  phone_Number , birthdate, gender, email) VALUES (?,?,?,?,?,?,?,?)" );
			
			
			
				if(Consulta_Cliente.existe(nombre,dni)!=0) {                  //Si ya existe el cliente no lo agrega y muestra el error por pantalla
				JOptionPane.showMessageDialog(null, "Cliente ya existe");
			}else {
				ps.setString(1, direccion);
				ps.setString(2, dni);
				ps.setString(3, nombre);
				ps.setString(4, apellido);
				
				ps.setString(5,telefono);
				
				
				
				ps.setDate(6, date);
				

				ps.setString(7, genero);
				
				if(Cliente.validaEmail(email)) {
					ps.setString(8,email);
				} else {
					JOptionPane.showMessageDialog(null, "E-Mail no válido");        //Si el E-Mail no es válido, no lo agrega y muestra por pantalla el error
				}
				
				
				
			}
				
			
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Cliente guardado");                   //En caso de ser exitoso, lo muestra en pantalla y lo agrega al log
                ControlFiles.addContent("Se ha añadido un cliente de nombre "+nombre+" "+apellido);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar cliente");        //En caso de fallar, muestra el error por pantalla
                
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
	
	public static Connection agregarOp(String direccion, String dni, String nombre, String apellido, String telefono, Date date, String genero, String email, String telefonoOp) {

		int result = 0;
		
		try {
			PreparedStatement ps = null;
			Connection con = Connect.getConexion();         //Realiza la conexión
			
			ps = con.prepareStatement("INSERT INTO Client (address, dni, name,surname,  phone_Number , birthdate, gender, email, phone_Optional) VALUES (?,?,?,?,?,?,?,?,?)" );
			
			
			
				if(Consulta_Cliente.existe(nombre,dni)!=0) {                  //Si ya existe el cliente no lo agrega y muestra el error por pantalla
				JOptionPane.showMessageDialog(null, "Cliente ya existe");
			}else {
				ps.setString(1, direccion);
				ps.setString(2, dni);
				ps.setString(3, nombre);
				ps.setString(4, apellido);
				
				ps.setString(5,telefono);
				
				
				
				ps.setDate(6, date);
				

				ps.setString(7, genero);
				
				if(Cliente.validaEmail(email)) {
					ps.setString(8,email);
				} else {
					JOptionPane.showMessageDialog(null, "E-Mail no válido");        //Si el E-Mail no es válido, no lo agrega y muestra por pantalla el error
				}
				
				ps.setString(9,telefonoOp);
				
			}
				
			
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Cliente guardado");                   //En caso de ser exitoso, lo muestra en pantalla y lo agrega al log
                ControlFiles.addContent("Se ha añadido un cliente de nombre "+nombre+" "+apellido);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar cliente");        //En caso de fallar, muestra el error por pantalla
                
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
	
	public static Connection cargar(String cliente) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(cliente);
		
		try {
			cn = (Connection) Connect.getConexion();   //Realiza la conexión
			
			String SSQL = "SELECT dni, name, surname, phone_Number, email, birthdate FROM Client WHERE id_Client = ?";	//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                       //Carga los campos según los resultados en la base de datos
			Modificar_Cliente.txtDni.setText(result.getString(1));
			Modificar_Cliente.txtNombre.setText(result.getString(2));
			Modificar_Cliente.txtApellido.setText(result.getString(3));
			Modificar_Cliente.txtTelefono.setText(result.getString(4));
			Modificar_Cliente.txtEmail.setText(result.getString(5));
			Modificar_Cliente.txtFechaNacimiento.setDate(result.getDate(6));
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
	
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		//Nombre de las columnas
        modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Apellido","Teléfono","Telefono secundario","Género","DNI","Fecha de nacimiento","E-Mail","Dirección"});
       
        table.setModel(modelo);     //Setea el modelo
        
        
        String datos[] = new String[10];    //Declara que va a haber 10 columnas
       
        try {
        	Connection con = Connect.getConexion();   //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("Select id_Client, name, surname, phone_Number, phone_Optional, gender, dni, CONVERT(varchar(10),birthdate,103), email, address\r\n"
        			+ "FROM Client;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                
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
	
	public static Connection eliminar(int id, String nombre) {
		int result = 0;
		

		try {
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Client WHERE id_Client = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Cliente eliminado");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el cliente "+nombre);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar país");     //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cliente está en uso, por favor elimine todos los registros relacionados");     //En cso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return null;
		
	}
	
}

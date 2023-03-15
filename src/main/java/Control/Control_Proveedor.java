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
import View.Modificar_Proveedor;
import View.Proveedor;
import View.Tabla_Proveedor;

public class Control_Proveedor {

	public static int existe(String nombre) {	//Verifica si ya existe el proveedor en la base de datos
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();   //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Provider WHERE provider_Name = ? ;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,nombre);

			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);     //Si ya existe, la variable se pone en 1
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
	
	public static Connection agregar(Object tipo, String direccion, String nombrePro, String nombre, String apellido, String telefono, String email, String cuit) {
		
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Provider (id_Provider_Type, address, provider_Name, name, surname, phone_Number, email, cuit) VALUES (?,?,?,?,?,?,?,?)" );
			
			
		
				
					if(Control_Proveedor.existe(nombrePro)!=0) {                         //Revisa si el proveedor ya existe
						JOptionPane.showMessageDialog(null, "Proveedor ya existe");
					}else {
						ps.setString(1, (String) tipo);
						ps.setString(2, direccion);
						ps.setString(3, nombrePro);
						ps.setString(4, nombre);
						ps.setString(5, apellido);
						
						ps.setString(6, telefono); 
						
						
						
						if(Proveedor.validaEmail(email)) {        //Revisa si el E-Mail es válido
							ps.setString(7,email);
						} else {
							JOptionPane.showMessageDialog(null, "E-Mail no válido");
						}
						
						ps.setString(8, cuit);
					}
				
				
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Proveedor guardado");                   //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se ha añadido un proveedor de nombre "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar proveedor");      //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection cargar(String proveedor) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(proveedor);
		
		try {
			cn = (Connection) Connect.getConexion();           //Realiza la conexión
			
			String SSQL = "SELECT provider_Name, name, surname, phone_Number, email, cuit FROM Provider WHERE id_Provider = ?";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                        //Carga los campos de acuerdo a los resultados de la base de datos
			Modificar_Proveedor.txtNombrePro.setText(result.getString(1));
			Modificar_Proveedor.txtNombre.setText(result.getString(2));
			Modificar_Proveedor.txtApellido.setText(result.getString(3));
			Modificar_Proveedor.txtTelefono.setText(result.getString(4));
			Modificar_Proveedor.txtEmail.setText(result.getString(5));
			Modificar_Proveedor.txtCuit.setText(result.getString(6));
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
	
	public static Connection modificar(Object tipo, String direccion, String nombrePro, String nombre, String apellido, String telefono, String email, String cuit, int id) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();            //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Provider SET id_Provider_Type = ?, address = ?, provider_Name = ?, name = ?, surname = ?, phone_Number = ?, email = ?, cuit = ? WHERE id_Provider = ?" );
			
			
					
						ps.setString(1, (String) tipo);
						ps.setString(2, direccion);
						ps.setString(3, nombrePro);
						ps.setString(4, nombre);
						ps.setString(5, apellido);
						
						ps.setString(6, telefono); 
						
						
						
						if(Proveedor.validaEmail(email)) {         //Revisa si el E-Mail es válido
							ps.setString(7,email);
						} else {
							JOptionPane.showMessageDialog(null, "E-Mail no válido");
						}
						
						ps.setString(8, cuit);
						
						ps.setInt(9, id);
					
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Proveedor modificado");            //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, después vuelve a la ventana Tabla_Proveedor
                ControlFiles.addContent("Se ha modificado el proveedor "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar proveedor");      //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
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
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Provider WHERE id_Provider = ?" );
			
			ps.setInt(1, id);
			
		
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Proveedor eliminado");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el proveedor "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar proveedor");      //En caso de fallar, lo avisa en pantalla
               
            }
			
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Proveedor está en uso, por favor elimine todos los registros relacionados");       //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) { 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Connection tabla(DefaultTableModel modelo, JTable table ) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Tipo","Dirección","Titular","Teléfono","E-Mail","CUIT"});   //Nombre de las columnas
	       
        table.setModel(modelo);            //Setea el modelo
        
        
        String datos[] = new String[8];    //Declara que va a haber 8 columnas   
       
        try {
        	Connection con = Connect.getConexion();       //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT Provider.id_Provider, provider_Name, Provider_Type.type_Name ,address, name, surname, phone_Number, email, cuit\r\n"
        			+ "FROM Provider\r\n"
        			+ "INNER JOIN Provider_Type ON Provider_Type.id_Provider_Type = Provider.id_Provider_Type;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                      //Carga las columnas de la base de datos en la tabla
            	datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5)+" "+rs.getString(6);
                datos[5] = rs.getString(7);
                datos[6] = rs.getString(8);
                datos[7] = rs.getString(9);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);     //Setea el modelo

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
	
	public static Connection tablaBus(DefaultTableModel modelo, JTable table ) {
		
		modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","CUIT","Dirección"});     //Nombre de las columnas
	       
        table.setModel(modelo);     //Setea el modelo
        
        
        String datos[] = new String[4];       //Declara que va a haber 4 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("Select id_Provider, provider_Name, cuit, address\r\n"
        			+ "FROM Provider;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                   //Llena las columnas de la tabla con las columnas de la base de datos
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);      //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);     //Las siguientes 4 vuelven invisible la columna id, para el usuario
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
	
	public static Connection tablaBusPar(DefaultTableModel modelo, JTable table,String url) {
		
		modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","CUIT","Dirección"});       //Nombre de las columnas
	       
        table.setModel(modelo);      //Setea el modelo
        
        PreparedStatement ps = null;
        
        String datos[] = new String[4];      //Declara que va a haber 4 columnas
       
        try {
        	Connection con = Connect.getConexion();       //Realiza la conexión
		
        	ps = con.prepareStatement(url);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){              //Llena las columnas de la tabla con las columnas de la base de datos 
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);    //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);     //Las siguientes 4 vuelven invisible la columna id, para el usuario
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

package Control;

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ControlFiles;
import View.Modificar_Mascota;
import View.Tabla_Mascota;


public class Consulta_Mascota {
		
		public static int existe(int duenio, String nombre) {	//Verifica si la mascota ya existe en la base de datos
			
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			try {
				cn = (Connection) Connect.getConexion();        //Realiza la conexión
				
				String SSQL = "SELECT count(*) FROM Pet WHERE name = ? AND id_Client = ? ;";	//Sentencia sql
				pst = cn.prepareStatement(SSQL);
				pst.setString(1, nombre);
				pst.setInt(2,duenio);

				result = pst.executeQuery();
				
				if (result.next()) {
					return result.getInt(1);          //Si la mascota existe, la variable se pone en 1
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
	
	
		public static Connection agregar(int idDue, String nombre, String animal,int edad,String genero, Object raza,Date date) {
			
			int result = 0;
			
			try {
				Connection con = Connect.getConexion();          //Realiza la conexión
				
					PreparedStatement ps = null;
					ps = con.prepareStatement("INSERT INTO Pet (id_Client,name,id_Animal,age,gender,id_Breed,birthdate ) VALUES (?,?,?,?,?,?,?)" );
					

					if(Consulta_Mascota.existe(idDue,nombre)!=0) {                          //Revisa si ya existe la mascota
						JOptionPane.showMessageDialog(null, "Mascota ya existe");
					}else {
				
					
					ps.setInt(1, idDue);
					ps.setString(2, nombre);
					ps.setString(3, (String) animal);
					ps.setInt(4, edad);
					ps.setString(5, genero);
					ps.setString(6, (String) raza);
					ps.setDate(7, date);
				
					
					}
				
				result = ps.executeUpdate();
				
				if(result > 0){                   
	                JOptionPane.showMessageDialog(null, "Mascota guardada");                    //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
	                ControlFiles.addContent("Se ha añadido una mascota de nombre "+ nombre);
	                
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al guardar mascota");          //En caso de fallar, LO avisa en pantalla
	                
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
		
		public static Connection modificar(int idDue, String nombre, String animal,int edad,String genero, Object raza,Date date,int id) {
			int result = 0;
			PreparedStatement ps = null;
			try {
				Connection con = Connect.getConexion();		//Realiza la conexión
				
				
					ps = con.prepareStatement("UPDATE Pet SET id_Client = ?, name = ?, id_Animal = ?, age = ?, gender = ?, id_Breed = ?, birthdate = ? WHERE id_Pet = ?" );
					

				
				
					
					ps.setInt(1, idDue);
					ps.setString(2, nombre);
					ps.setString(3, (String) animal);
					ps.setInt(4, edad);
					ps.setString(5, genero);
					ps.setString(6, (String) raza);
					ps.setDate(7, date);
				
					ps.setInt(8, id);
				
					
				
				
				result = ps.executeUpdate();
				
				if(result > 0){
	                JOptionPane.showMessageDialog(null, "Mascota modificada");             //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, después regresa a la ventana Tabla_Mascota
	                ControlFiles.addContent("Se ha modificado la mascota "+nombre);
	                
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al modificar mascota");       //En caso de fallar, lo avisa en pantalla
	                 
	            }
			
				con.close();
			}catch(SQLException E) {
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
				Connection con = Connect.getConexion();        //Realiza la conexión
				
				PreparedStatement ps = con.prepareStatement("DELETE FROM Pet WHERE id_Pet = ?" );
				
					ps.setInt(1, id);
				
				
				result = ps.executeUpdate();
				
				if(result > 0){
	                JOptionPane.showMessageDialog(null, "Mascota eliminada");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
	                
	                ControlFiles.addContent("Se ha eliminado la mascota "+nombre);
	               
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al eliminar mascota");      //En caso de fallar, lo avisa en pantalla
	                
	            }
				con.close();
			}catch(SQLException E) {
				E.printStackTrace();
				JOptionPane.showMessageDialog(null, "Mascota está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar, lo avisa en pantalla
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
		
		public static Connection tabla(DefaultTableModel modelo, JTable table) {
			modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Fecha de nacimiento","Edad","Género","Raza","Dueño"});   //Nombre de las columnas
		       
	        table.setModel(modelo);    //Setea el modelo
	        
	        
	        String datos[] = new String[8];    //Declara que va a haber 8 columnas
	       
	        try {
	        	Connection con = Connect.getConexion();     //Realiza la conexión
	        	//Sentencia Sql
	        	PreparedStatement ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type,Pet.birthdate, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
	        			+ "FROM Pet\r\n"
	        			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
	        			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
	        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                datos[4] = rs.getString(5);
	                datos[5] = rs.getString(6);
	                datos[6] = rs.getString(7);
	                datos[7] = rs.getString(8)+" "+rs.getString(9);
	                
	                modelo.addRow(datos);

	            }
	            table.setModel(modelo);     //Setea el modelo
	            
	            table.getColumnModel().getColumn(0).setMaxWidth(0);             // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
		
		public static Connection tablaBus(DefaultTableModel modelo, JTable table) {
			  modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Edad","Género","Raza","Dueño"});        //Nombre de las columnas
		       
		        table.setModel(modelo);       //Setea el modelo
		        
		        
		        String datos[] = new String[7];      //Declara que va a haber 7 columnas
		       
		        try {
		        	Connection con = Connect.getConexion();      //Realiza la conexión
		        	//Sentencia sql
		        	PreparedStatement ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
		        			+ "FROM Pet\r\n"
		        			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
		        			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
		        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client;" );
		            ResultSet rs = ps.executeQuery();
		            while (rs.next()){               //Llena las columnas de la tabla con las columnas de la base de datos
		                datos[0] = rs.getString(1);
		                datos[1] = rs.getString(2);
		                datos[2] = rs.getString(3);
		                datos[3] = rs.getString(4);
		                datos[4] = rs.getString(5);
		                datos[5] = rs.getString(6);
		                datos[6] = rs.getString(7)+" "+rs.getString(8);
		                
		                modelo.addRow(datos);

		            }
		            table.setModel(modelo);     //Setea el modelo
		            
		            table.getColumnModel().getColumn(0).setMaxWidth(0);       //Las siguientes 4 vuelven invisible la columna id, para el usuario
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
			modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Edad","Género","Raza","Dueño"});     //Nombre de las columnas
		       
	        table.setModel(modelo);       //Setea el modelo
	        
	        PreparedStatement ps = null;
	        
	        
	        String datos[] = new String[7];        //Declara que va a haber 7 columnas
	        
	        try {
	        	Connection con = Connect.getConexion();        //Realiza la conexión
	        	
	        	ps = con.prepareStatement(url);
	        	
	        	
	        	
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){                //Llena las columnas de la tabla con las columnas de la base de datos     
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                datos[4] = rs.getString(5);
	                datos[5] = rs.getString(6);
	                datos[6] = rs.getString(7)+" "+rs.getString(8);
	                
	                modelo.addRow(datos);

	            }
	            table.setModel(modelo);     //Setea el modelo
	             
	            table.getColumnModel().getColumn(0).setMaxWidth(0);      //Las siguientes 4 vuelven invisible la columna id, para el usuario
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

		public static Connection cargar(String mascota) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			int id = Integer.parseInt(mascota);
			
			try {
				cn = (Connection) Connect.getConexion();                          //Realiza la conexión
				String SSQL = "SELECT name, age FROM Pet WHERE id_Pet = ?";		//Sentencia sql
				pst = cn.prepareStatement(SSQL);
				pst.setInt(1, id);
				
				
				result = pst.executeQuery();
				while (result.next()){                        //Carga los campos de acuerdo a los resultados de la base de datos
				Modificar_Mascota.txtNombre.setText(result.getString(1));
				Modificar_Mascota.txtEdad.setText(result.getString(2));
				
				
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
}

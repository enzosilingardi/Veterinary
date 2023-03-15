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
import View.Modificar_Producto;
import View.Tabla_Productos;

public class Control_Producto {
	
	public static int existe(String nombre) {	//Verifica si ya existe el producto en la base de datos
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Product WHERE product_Name = ?;";	//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,nombre);

			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);         //Si ya existe, la variable se pone en 1
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
	
	public static Connection agregar(int idPro, String nombre, Object tipo, String descripcion, Float costo, Float precio) {
		
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();    //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Product (id_Provider, product_Name, id_Product_Type, description, cost_Price, sale_Price) VALUES (?,?,?,?,?,?)" );
			
			if(existe(nombre) != 0) {
				JOptionPane.showMessageDialog(null, "Animal ya existe");            //Revisa si ya existe el registro
			}else {
			
				ps.setInt(1, idPro);
				ps.setString(2, nombre);
				ps.setString(3, (String) tipo);
				ps.setString(4, descripcion);
				if (costo < 0) {                //Revisa que en el costo no hayan números negativos
					
					JOptionPane.showMessageDialog(null, "No se permiten números negativos");
				} else {
					ps.setFloat(5,costo);
				}
				
				if (precio < 0) {           //Revisa que en el precio no hayan números negativos
					
					JOptionPane.showMessageDialog(null, "No se permiten números negativos");
				} else {
					ps.setFloat(6,precio);
				}
				
			}
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Producto guardado");                   //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                ControlFiles.addContent("Se a añadido un producto de nombre "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar producto");    //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
		
	}
	
	public static Connect cargar(String producto) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(producto); 
		
		try {
			cn = (Connection) Connect.getConexion();         //Realiza la conexión
			
			String SSQL = "SELECT id_product_Type, product_Name, description, cost_Price, sale_Price\r\n"		//Sentencia sql
					+ "FROM Product WHERE id_Product = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                           //Carga los campos según los resultados de la base de datos
			Modificar_Producto.cbTipo.setSelectedItem(result.getString(1));	
			Modificar_Producto.txtNombre.setText(result.getString(2));
			Modificar_Producto.txtDescripcion.setText(result.getString(3));
			Modificar_Producto.txtCosto.setText(result.getString(4));
			Modificar_Producto.txtPrecio.setText(result.getString(5));
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
	
	
	public static Connect modificar(int idP, String nombre, Object tipo, String descripcion, Float costo, Float precio, int id) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();          //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Product SET id_Provider = ?, product_Name = ?, id_Product_Type = ?, description = ?, cost_Price = ?, sale_Price = ? WHERE id_Product = ?" );
			
			
			
				ps.setInt(1, idP);
				ps.setString(2, nombre);
				ps.setString(3, (String) tipo);
				ps.setString(4, descripcion);
				
				if (costo < 0) {       //Revisa que no hayan números negativos en el costo
					
					JOptionPane.showMessageDialog(null, "No se permiten números negativos");
				} else {
					ps.setFloat(5,costo);
				}
				
				if (precio < 0) {       //Revisa que no hayan números negativos en el precio
					
					JOptionPane.showMessageDialog(null, "No se permiten números negativos");
				} else {
					ps.setFloat(6,precio);
				}
				
				ps.setInt(7, id);
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Producto modificado");       //Si fue exitoso, lo avisa por pantalla mediante un mensaje y lo añade al log, después regresa a la ventana Tabla_Productos
                ControlFiles.addContent("Se ha modificado el producto "+nombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar producto");   //En caso de fallar, lo avisa pro pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Tipo","Descripción","Precio de Venta","Precio proveedor","Proveedor"});   //Nombre de las columnas
	       
        table.setModel(modelo);    //Setea el modelo
        
        
        String datos[] = new String[7];      //Declara que va a haber 7 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexion
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_Product,product_Name, type_Name, description, cost_Price, sale_Price, provider_Name\r\n"
        			+ "FROM Product\r\n"
        			+ "INNER JOIN Product_Type ON Product.id_Product_Type = Product_Type.id_Product_Type\r\n"
        			+ "INNER JOIN Provider ON Product.id_Provider = Provider.id_Provider;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);      //Setea el modelo

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
	
	public static Connection eliminar(int id, String nombre) {
		int result = 0;

		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE id_Product = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Producto eliminado");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el producto "+nombre);

            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar producto");       //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Producto está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
}

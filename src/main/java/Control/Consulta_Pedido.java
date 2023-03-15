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
import View.Modificar_Pedido;
import View.Tabla_Pedido;

public class Consulta_Pedido {
	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Proveedor","Sucursal","Cantidad"});       //Nombre de las columnas
	       
        table.setModel(modelo);     //Setea el modelo
        
        
        String datos[] = new String[5];  //Declara que va a haber 5 columnas
       
        try {
        	Connection con = Connect.getConexion();     //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT Orders.id_Order, Product.product_Name, Provider.provider_Name, Branch.address, Orders.quantity\r\n"
        			+ "FROM Orders\r\n"
        			+ "INNER JOIN Product ON Product.id_Product = Orders.id_Product\r\n"
        			+ "INNER JOIN Provider ON Provider.id_Provider = Product.id_Provider\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Orders.id_Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);       //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);           // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existe(Object producto, Object sucursal) {	//Determina si ya existe el pedido
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();         //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Orders WHERE id_Product = ? AND id_Branch = ?;";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) producto);
			pst.setString(2,(String) sucursal);

			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);          //Si ya existe el pedido, la variable se pone en 1
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
	
	public static Connection agregar(Object producto, Object sucursal, int cantidad, String nombreProducto, String nombreSucursal) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();     //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Orders (id_Product,id_Branch,quantity) VALUES (?,?,?)" );
			
			
				
						ps.setString(1, (String) producto);
						ps.setString(2, (String) sucursal);
						ps.setInt(3, cantidad);
				
			
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Pedido guardado");      //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha creado un pedido de "+nombreProducto+" para la sucursal "+nombreSucursal);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar pedido");      //En caso de fallar, lo avisa en pantalla 
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection cargar(String pedido) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(pedido);
		
		try {
			cn = (Connection) Connect.getConexion();                            //Realiza la conexión
			String SSQL = "SELECT quantity FROM Orders WHERE id_Order = ?";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                            //Carga los campos según los resultados en la base de datos
			Modificar_Pedido.txtCantidad.setText(result.getString(1));
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
	
	public static Connection modificar(Object producto, Object sucursal, int cantidad, int id, String nombreProducto) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Orders SET id_Product = ? , id_Branch = ? , quantity = ? WHERE id_Order = ?" );
			
			
					
				
						ps.setString(1, (String) producto);
						ps.setString(2, (String) sucursal);
						ps.setInt(3, cantidad);
						ps.setInt(4, id);
				
			
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Pedido modificado");                //Si fue exitoso, lo avisa por pantalla mediante un mensaje y lo añade al log, después regresa a la ventana Tabla_Pedido 
                ControlFiles.addContent("Se ha modificado un pedido de "+nombreProducto);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar pedido");        //En caso de fallar, lo avisa por pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection eliminar(int id, String producto, String sucursal) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();     //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Orders WHERE id_Order = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Pedido eliminado");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado un pedido de "+producto+" para la sucursal "+sucursal);
              
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar pedido");      //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Pedido está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
}

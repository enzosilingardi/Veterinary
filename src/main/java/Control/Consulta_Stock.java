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

public class Consulta_Stock {

	public static Connection tabla(DefaultTableModel modelo, JTable table) {
		modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Cantidad","Dirección Sucursal"});    //Nombre de las columnas
	       
        table.setModel(modelo);      //Setea el modelo
        
        
        
        String datos[] = new String[4];       //Declara que va a haber 4 columnas
       
        try {
        	Connection con = Connect.getConexion();     //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT Rel_Branch_Product.id_BP, Product.product_Name, Rel_Branch_Product.amount, Branch.address\r\n"
        			+ "FROM Rel_Branch_Product\r\n"
        			+ "INNER JOIN Product ON Product.id_Product = Rel_Branch_Product.id_Product\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Branch_Product.id_Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);      // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public static int existe(Object producto, Object sucursal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Branch_Product WHERE id_Product = ? AND id_Branch = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) producto);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);      //Si ya existe, la variable se pone en 1
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
	
	public static Connection agregar(Object sucursal, Object producto, int cantidad, String productoNombre, String sucursalNombre) {
		int result = 0;
		
		
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Branch_Product (id_Branch,id_Product,amount) VALUES (?,?,?)" );
			
			
						ps.setString(1, (String) sucursal);
						ps.setString(2, (String) producto);
						
						
						
						if(cantidad >250000) {      //Revisa si la cantidad exede el límite
							JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
							
							
						}else if(cantidad<0){        //Revisa si en la cantidad hay números negativos
							JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
							
					
						}else {
							
							ps.setInt(3, cantidad);
					
						}
						
					
				
				
				
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Producto colocado");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log

                ControlFiles.addContent("Se ha asociado el producto "+productoNombre+" con la sucursal "+sucursalNombre);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al colocar producto");    //En caso de fallar, lo avisa en pantalla
                
            }
		
			
		}catch(SQLException E) {
			E.printStackTrace();
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Connection modificar(int cantidad, int id,String producto, String sucursal) {
		int result = 0;
		
		try {
			Connection con = Connect.getConexion();      //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("UPDATE Rel_Branch_Product SET amount = ? WHERE id_BP = ?");
			
				
				if(cantidad >250000) {          //Revisa si el número excede el límite
					
					JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
					
					
				}else if(cantidad<0){             //Revisa si el número es negativo
					
					JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
					
			
				}else {
					
					ps.setInt(1,cantidad);
			
				}
				
			
			
			
			ps.setInt(2, id);
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Stock modificado");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha modificado el stock de "+producto+" en la sucursal "+sucursal);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar stock");      //En caso de fallar, lo avisa en pantalla
                
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
	
	public static Connection eliminar(int id, String producto, String sucursal) {
		int result = 0;
		try {
			Connection con = Connect.getConexion();       //Realiza la conexión
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Product WHERE id_BP = ?" );
			
				ps.setInt(1, id);
			
			
			result = ps.executeUpdate();
			
			if(result > 0){
                JOptionPane.showMessageDialog(null, "Producto eliminado de stock");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
                
                ControlFiles.addContent("Se ha eliminado el stock de "+producto+" en la sucursal "+sucursal);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar producto");        //En caso de fallar, lo avisa en pantalla
                
            }
			con.close();
		}catch(SQLException E) {
			E.printStackTrace();
			JOptionPane.showMessageDialog(null, "Stock está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}

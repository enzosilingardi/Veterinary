package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import View.Historial_Medico.ComboItem;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tabla_Stock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public static boolean contieneSoloNumerosRegex(String cadena) {
	    return cadena.matches("[0-9]+");
	}

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Cantidad","Dirección Sucursal"});
	       
	        table.setModel(modelo);
	        
	        
	        
	        String datos[] = new String[4];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT Rel_Branch_Product.id_BP, Product.product_Name, Rel_Branch_Product.amount, Address.address_Name, Address.address_Number\r\n"
	        			+ "FROM Rel_Branch_Product\r\n"
	        			+ "INNER JOIN Product ON Product.id_Product = Rel_Branch_Product.id_Product\r\n"
	        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Branch_Product.id_Branch\r\n"
	        			+ "INNER JOIN Address ON Address.id_Address = Branch.id_Address;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4)+" "+rs.getString(5);
	                
	                modelo.addRow(datos);

	            }
	            
	            table.setModel(modelo);
	            
	            table.getColumnModel().getColumn(0).setMaxWidth(0);
	    		table.getColumnModel().getColumn(0).setMinWidth(0);
	    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	    		table.getColumnModel().getColumn(0).setResizable(false);
	    		
	    		
	    		
	        } catch(SQLException E) {
				JOptionPane.showMessageDialog(null,E);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Stock frame = new Tabla_Stock();
					frame.setVisible(true);
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tabla_Stock(String perfil) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(467, 309, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
		JButton btnModificar = new JButton("Modificar cantidad");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				boolean flagError = false;
				String cantidadAux = table.getValueAt(fila,2).toString();
				
				for(int i=0; i < cantidadAux.length(); i++ ) {
					
					if (Character.isLetter(cantidadAux.charAt(i))){
						
						flagError = true;
						break;
					}
					
					
				}
				
				if (flagError) {
					
					JOptionPane.showMessageDialog(null, "Solo se permiten números",null,JOptionPane.ERROR_MESSAGE);
					
				}else {
				
				
				int cantidad = Integer.parseInt(table.getValueAt(fila,2).toString());
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Rel_Branch_Product SET amount = ? WHERE id_BP = ?");
					
						
						if(cantidad >250000) {
							JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
							mostrarTabla();
							
						}else if(cantidad<0){
							JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
							mostrarTabla();
					
						}else {
							
							ps.setInt(1,cantidad);
					
						}
						
					
					
					
					ps.setInt(2, id);
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Stock modificado");
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar stock");
		                mostrarTabla();
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				}
			}
		});
		btnModificar.setBounds(40, 309, 143, 23);
		contentPane.add(btnModificar);
		
		JButton btnAgregar = new JButton("Agregar a stock");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sucursal_Producto sp = new Sucursal_Producto();
				sp.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 275, 143, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar de stock");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Product WHERE id_BP = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto eliminado de stock");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar producto");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Stock está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(193, 275, 143, 23);
		contentPane.add(btnEliminar);
		}
		mostrarTabla();
		
	}

	public Tabla_Stock() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(467, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar cantidad");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				boolean flagError = false;
				String cantidadAux = table.getValueAt(fila,2).toString();
				
				for(int i=0; i < cantidadAux.length(); i++ ) {
					
					if (Character.isLetter(cantidadAux.charAt(i))){
						
						flagError = true;
						break;
					}
					
					
				}
				
				if (flagError) {
					
					JOptionPane.showMessageDialog(null, "Solo se permiten números",null,JOptionPane.ERROR_MESSAGE);
					
				}else {
				
				
				int cantidad = Integer.parseInt(table.getValueAt(fila,2).toString());
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Rel_Branch_Product SET amount = ? WHERE id_BP = ?");
					
						
						if(cantidad >250000) {
							JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
							mostrarTabla();
							
						}else if(cantidad<0){
							JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
							mostrarTabla();
					
						}else {
							
							ps.setInt(1,cantidad);
					
						}
						
					
					
					
					ps.setInt(2, id);
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Stock modificado");
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar stock");
		                mostrarTabla();
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				}
			}
		});
		btnModificar.setBounds(40, 309, 143, 23);
		contentPane.add(btnModificar);
		
		JButton btnAgregar = new JButton("Agregar a stock");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sucursal_Producto sp = new Sucursal_Producto();
				sp.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 275, 143, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar de stock");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Product WHERE id_BP = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto eliminado de stock");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar producto");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Stock está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(193, 275, 143, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}
}

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
import Model.ControlFiles;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Tabla_Pedido extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Producto","Proveedor","Sucursal","Cantidad"});
	       
	        table.setModel(modelo);
	        
	        
	        String datos[] = new String[5];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT Orders.id_Order, Product.product_Name, Provider.provider_Name, Branch.address, Orders.quantity\r\n"
	        			+ "FROM Orders\r\n"
	        			+ "INNER JOIN Product ON Product.id_Product = Orders.id_Product\r\n"
	        			+ "INNER JOIN Provider ON Provider.id_Provider = Product.id_Provider\r\n"
	        			+ "INNER JOIN Branch ON Branch.id_Branch = Orders.id_Branch;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                datos[4] = rs.getString(5);
	                
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
					Tabla_Pedido frame = new Tabla_Pedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tabla_Pedido(String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
			JButton btnRel = new JButton("Realizar pedido");
			btnRel.setForeground(new Color(255, 255, 255));
			btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
			btnRel.setBorder(null);
			btnRel.setBackground(new Color(86, 211, 243));
			btnRel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedidos pedido = new Pedidos();
					pedido.setVisible(true);
					dispose();
				}
			});
			btnRel.setBounds(40, 309, 109, 23);
			contentPane.add(btnRel);
			
			JButton btnModificar = new JButton("Modificar");
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.setBorder(null);
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Pedido mp = new Modificar_Pedido(table.getValueAt(fila,0).toString());
					mp.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(40, 260, 91, 23);
			contentPane.add(btnModificar);
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = 0;
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					
					try {
						Connection con = Connect.getConexion();
						PreparedStatement ps = con.prepareStatement("DELETE FROM Orders WHERE id_Order = ?" );
						
							ps.setInt(1, id);
						
						
						result = ps.executeUpdate();
						
						if(result > 0){
			                JOptionPane.showMessageDialog(null, "Pedido eliminado");
			                ControlFiles.addContent("Se ha eliminado un pedido de "+table.getValueAt(fila,1).toString()+" para la sucursal "+table.getValueAt(fila,3).toString());
			               mostrarTabla();
			            } else {
			                JOptionPane.showMessageDialog(null, "Error al eliminar pedido");
			                
			            }
						con.close();
					}catch(SQLException E) {
						E.printStackTrace();
						JOptionPane.showMessageDialog(null, "Pedido está en uso, por favor elimine todos los registros relacionados");
					}catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnEliminar.setBounds(139, 260, 91, 23);
			contentPane.add(btnEliminar);
		}
		mostrarTabla();
	}


	public Tabla_Pedido() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.setBorder(null);
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(452, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnRel = new JButton("Realizar pedido");
		btnRel.setForeground(new Color(255, 255, 255));
		btnRel.setFont(new Font("Roboto", Font.BOLD, 14));
		btnRel.setBorder(null);
		btnRel.setBackground(new Color(86, 211, 243));
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pedidos pedido = new Pedidos();
				pedido.setVisible(true);
				dispose();
			}
		});
		btnRel.setBounds(40, 309, 109, 23);
		contentPane.add(btnRel);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Pedido mp = new Modificar_Pedido(table.getValueAt(fila,0).toString());
				mp.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(40, 260, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Orders WHERE id_Order = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Pedido eliminado");
		                ControlFiles.addContent("Se ha eliminado un pedido de "+table.getValueAt(fila,1).toString()+" para la sucursal "+table.getValueAt(fila,3).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar pedido");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Pedido está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(139, 260, 91, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}

}

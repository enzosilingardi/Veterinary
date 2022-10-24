package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Sucursal_Producto extends JFrame {

	private JPanel contentPane;
	private JComboBox cbProducto;
	private JComboBox cbSucursal;
	private JTextField txtCantidad;

	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)      //Genera el label que se verá en el combobox y el valor del objeto seleccionado
	    {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public String toString()
	    {
	        return key;
	    }

	    public String getKey()
	    {
	        return key;
	    }

	    public String getValue()
	    {
	        return value;
	    }
	}
	
	public DefaultComboBoxModel cargarProducto() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Product ORDER BY id_Product";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("product_Name"),result.getString("id_Product")));
				
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
	
	public DefaultComboBoxModel cargarSucursal() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "Select *\r\n"
					+ "FROM Branch\r\n"
					+ "INNER JOIN Address ON Branch.id_Address = Address.id_Address\r\n"
					+ "ORDER BY Branch.id_Address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number"),result.getString("id_Branch")));
				
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sucursal_Producto frame = new Sucursal_Producto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public int existeRel(Object producto, Object sucursal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Branch_Product WHERE id_Product = ? AND id_Branch = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) producto);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
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
	
	private void limpiar() {
		cbProducto.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Sucursal_Producto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar producto con sucursal");
		lblTitulo.setBounds(113, 11, 209, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(51, 53, 60, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(127, 49, 190, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(51, 119, 60, 14);
		contentPane.add(lblProducto);
		
		cbProducto = new JComboBox();
		cbProducto.setBounds(127, 115, 190, 22);
		contentPane.add(cbProducto);
		cbProducto.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Object producto = cbProducto.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				
				int result = 0;
				
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Branch_Product (id_Branch,id_Product,amount) VALUES (?,?,?)" );
					
					
					if (((ComboItem) producto).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un Producto");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							if(existeRel(((ComboItem) cbProducto.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Producto ya se encuentra en la sucursal");
							}else {
								ps.setString(1, ((ComboItem) sucursal).getValue());
								ps.setString(2, ((ComboItem) producto).getValue());
								
								
								
								if(cantidad >250000) {
									JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
									
									
								}else if(cantidad<0){
									JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
									
							
								}else {
									
									ps.setInt(3, cantidad);
							
								}
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto colocado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar producto");
		                limpiar();
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAgregar.setBounds(68, 243, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(270, 296, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object producto = cbProducto.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Product WHERE id_Branch = ? AND id_Product = ?;" );
					ps.setString(1, ((ComboItem) sucursal).getValue());
					ps.setString(2, ((ComboItem) producto).getValue());
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto removido de la sucursal");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al remover producto");
		                limpiar();
		            }
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setBounds(209, 243, 89, 23);
		contentPane.add(btnEliminar);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(51, 179, 60, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(127, 176, 190, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
	}
}

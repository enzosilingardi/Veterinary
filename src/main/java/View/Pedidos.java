package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Pedidos extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JComboBox cbProducto;
	private JComboBox cbSucursal;
	
	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)
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
					Pedidos frame = new Pedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existePedido(Object producto, Object sucursal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Orders WHERE id_Product = ? AND id_Branch = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) producto);
			pst.setString(2,(String) sucursal);

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
		txtCantidad.setText("");
		
	}
	/**
	 * Create the frame.
	 */
	public Pedidos() {
		setTitle("Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Pedidos");
		lblTitulo.setBounds(154, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(57, 60, 59, 14);
		contentPane.add(lblProducto);
		
		cbProducto = new JComboBox();
		cbProducto.setBounds(147, 56, 157, 22);
		contentPane.add(cbProducto);
		cbProducto.setModel(cargarProducto());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(57, 109, 59, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(147, 105, 157, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(57, 163, 59, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(147, 160, 157, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Pedido tp = new Tabla_Pedido();
				tp.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(270, 268, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int cantidad = Integer.parseInt(txtCantidad.getText());
				Object producto = cbProducto.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Orders (id_Product,id_Branch,quantity) VALUES (?,?,?)" );
					
					
					if (((ComboItem) producto).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un producto");
					}else {
						if (((ComboItem) sucursal).getValue() == "") {
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							if(existePedido(((ComboItem) cbProducto.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
						
								JOptionPane.showMessageDialog(null, "Pedido ya existe");
					
							}else {
						
								ps.setString(1, ((ComboItem) producto).getValue());
								ps.setString(2, ((ComboItem) sucursal).getValue());
								ps.setInt(3, cantidad);
						
					}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Pedido guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar pedido");
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
		btnAgregar.setBounds(135, 223, 89, 23);
		contentPane.add(btnAgregar);
	}
}

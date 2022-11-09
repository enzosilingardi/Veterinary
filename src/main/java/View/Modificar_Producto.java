package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Producto.ComboItem;

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

public class Modificar_Producto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtCosto;
	private JTextField txtPrecio;
	private JTextField txtId;
	private JComboBox cbTipo;
	private JComboBox cbProveedor;
	
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
	
	public DefaultComboBoxModel cargarProveedor() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Provider ORDER BY id_Provider";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("provider_Name"),result.getString("id_Provider")));
				
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
					Modificar_Producto frame = new Modificar_Producto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargarCampos(String producto) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(producto);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT product_Type, product_Name, description, cost_Price, sale_Price\r\n"
					+ "FROM Product WHERE id_Product = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			cbTipo.setSelectedItem(result.getString(1));	
			txtNombre.setText(result.getString(2));
			txtDescripcion.setText(result.getString(3));
			txtCosto.setText(result.getString(4));
			txtPrecio.setText(result.getString(5));
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	/**
	 * Create the frame.
	 */
	public Modificar_Producto(String producto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(59, 37, 68, 14);
		contentPane.add(lblProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(172, 33, 187, 22);
		contentPane.add(cbProveedor);
		cbProveedor.setModel(cargarProveedor());
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(59, 84, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(172, 81, 187, 20);
		contentPane.add(txtNombre);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(59, 130, 46, 14);
		contentPane.add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Alimento", "Medico", "Accesorio"}));
		cbTipo.setBounds(172, 126, 187, 22);
		contentPane.add(cbTipo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(59, 177, 68, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(172, 174, 187, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(59, 221, 46, 14);
		contentPane.add(lblCosto);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(172, 218, 187, 20);
		contentPane.add(txtCosto);
		
		JLabel lblPrecio = new JLabel("Precio de venta");
		lblPrecio.setBounds(59, 271, 103, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(172, 268, 187, 20);
		contentPane.add(txtPrecio);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Productos tp = new Tabla_Productos();
				tp.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(276, 316, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				Object proveedor = cbProveedor.getSelectedItem();
				String tipo = cbTipo.getSelectedItem().toString();
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				float costo = Float.parseFloat(txtCosto.getText());
				float precio = Float.parseFloat(txtPrecio.getText());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Product SET id_Provider = ?, product_Name = ?, product_Type = ?, description = ?, cost_Price = ?, sale_Price = ? WHERE id_Product = ?" );
					
					
					if (((ComboItem) proveedor).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un proveedor");
					}else {
						ps.setString(1, ((ComboItem) proveedor).getValue());
						ps.setString(2, nombre);
						ps.setString(3, tipo);
						ps.setString(4, descripcion);
						
						if (costo < 0) {
							JOptionPane.showMessageDialog(null, "No se permiten números negativos");
						} else {
							ps.setFloat(5,costo);
						}
						
						if (precio < 0) {
							JOptionPane.showMessageDialog(null, "No se permiten números negativos");
						} else {
							ps.setFloat(6,precio);
						}
						
						ps.setInt(7, id);
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto modificado");
		                ControlFiles.addContent("Se ha modificado el producto "+nombre);
		                Tabla_Productos tp = new Tabla_Productos();
						tp.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar producto");
		                
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(59, 316, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 29, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(producto);
		txtId.setText(producto);
		
	}

	public Modificar_Producto() {
		// TODO Auto-generated constructor stub
	}
}

package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Proveedor.ComboItem;

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

public class Producto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtCosto;
	private JTextField txtPrecio;
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
					Producto frame = new Producto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int existeProducto(String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Product WHERE product_Name = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,nombre);

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
	
	public int productoEnUso(String producto) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(Stock.id_Product)\r\n"
					+ "FROM Product\r\n"
					+ "JOIN Stock ON Stock.id_Product = Product.id_Product\r\n"
					+ "WHERE Product.product_Name LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, producto);
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
		cbProveedor.setSelectedIndex(0);
		cbTipo.setSelectedIndex(0);
		txtNombre.setText("");
		txtCosto.setText("");
		txtPrecio.setText("");
		txtDescripcion.setText("");
		
	}

	/**
	 * Create the frame.
	 */
	public Producto() {
		setTitle("Productos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Productos");
		lblTitulo.setBounds(183, 11, 74, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(47, 49, 68, 14);
		contentPane.add(lblProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(160, 45, 187, 22);
		contentPane.add(cbProveedor);
		cbProveedor.setModel(cargarProveedor());
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(47, 96, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(160, 93, 187, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(47, 142, 46, 14);
		contentPane.add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Alimento", "Medico", "Accesorio"}));
		cbTipo.setBounds(160, 138, 187, 22);
		contentPane.add(cbTipo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(47, 189, 68, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(160, 186, 187, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(47, 233, 46, 14);
		contentPane.add(lblCosto);
		
		txtCosto = new JTextField();
		txtCosto.setBounds(160, 230, 187, 20);
		contentPane.add(txtCosto);
		txtCosto.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio de venta");
		lblPrecio.setBounds(47, 283, 103, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(160, 280, 187, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object proveedor = cbProveedor.getSelectedItem();
				String tipo = cbTipo.getSelectedItem().toString();
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				float costo = Float.parseFloat(txtCosto.getText());
				float precio = Float.parseFloat(txtPrecio.getText());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Product (id_Provider, product_Name, product_Type, description, cost_Price, sale_Price) VALUES (?,?,?,?,?,?)" );
					
					
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
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto guardado");
		                ControlFiles.addContent("Se a añadido un producto de nombre "+nombre);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar producto");
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
		btnAgregar.setBounds(156, 336, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Tabla_Productos tp = new Tabla_Productos();
				tp.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(315, 389, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Producto tp = new Tipo_Producto();
				tp.setVisible(true);
				dispose();
			}
		});
		btnNuevo.setBounds(357, 138, 89, 23);
		contentPane.add(btnNuevo);
	}
}

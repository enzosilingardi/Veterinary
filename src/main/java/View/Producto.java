package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Producto;
import Model.ComboItem;
import Model.ControlFiles;

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
	private JTextField txtPro;
	private JTextField txtIdPro;
	

	
	public DefaultComboBoxModel cargarProveedor() {          //Este ComboBox no es utilizado en la versión actual
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
	

	public DefaultComboBoxModel cargarTipo() {           //Carga el ComboBox tipo
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBTipoProd(modelo);
		
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
	

	
	public int productoEnUso(String producto) {       //Este procedimiento no es utilizado en la versión actual
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
	
	private void limpiar() {          //Este procedimiento limpia los campos
		txtIdPro.setText("");
		txtPro.setText("");
		cbTipo.setSelectedIndex(0);
		txtNombre.setText("");
		txtCosto.setText("");
		txtPrecio.setText("");
		txtDescripcion.setText("");
		
	}

	/**
	 * Create the frame.
	 */
	public Producto(String id, String nom) {        //Crea la tabla recibiendo como parámetros el id y el nombre del proveedor
		setTitle("Productos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Productos");
		lblTitulo.setBounds(183, 11, 74, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(47, 49, 68, 14);
		contentPane.add(lblProveedor);
		
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
		cbTipo.setBounds(160, 138, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
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
		
		JButton btnAgregar = new JButton("Agregar");            //Este botón permite agregar un producto
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idPro = Integer.parseInt(txtIdPro.getText());
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				float costo = Float.parseFloat(txtCosto.getText());
				float precio = Float.parseFloat(txtPrecio.getText());
				
				Control_Producto.agregar(idPro, nombre, ((ComboItem) cbTipo.getSelectedItem()).getValue(), descripcion, costo, precio);
				
				limpiar();
			}
		});
		btnAgregar.setBounds(156, 336, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");             //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Tabla_Productos tp = new Tabla_Productos();
				tp.setVisible(true);		//Abre la ventana Tabla_Productos
				dispose();
			}
		});
		btnVolver.setBounds(315, 389, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnNuevo = new JButton("Nuevo");               //Abre la ventana Tipo_Producto
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Producto tp = new Tipo_Producto();
				tp.setVisible(true);		
				dispose();
			}
		});
		btnNuevo.setBounds(357, 138, 117, 23);
		contentPane.add(btnNuevo);
		
		JButton btnSelec = new JButton("Seleccionar");         //Este botón permite seleccionar un proveedor
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Proveedor bp = new Buscar_Proveedor();
				bp.setVisible(true);		//Abre la ventana Buscar_Proveedor
				dispose();
			}
		});
		btnSelec.setBounds(357, 45, 117, 23);
		contentPane.add(btnSelec);
		
		txtPro = new JTextField();
		txtPro.setEditable(false);
		txtPro.setBounds(160, 46, 187, 20);
		contentPane.add(txtPro);
		txtPro.setColumns(10);
		txtPro.setText(nom);
		
		txtIdPro = new JTextField();
		txtIdPro.setEditable(false);
		txtIdPro.setBounds(357, 14, 53, 20);
		contentPane.add(txtIdPro);
		txtIdPro.setColumns(10);
		txtIdPro.setVisible(false);
		txtIdPro.setText(id);
	}

	public Producto() {                 //Crea la ventana
		setTitle("Productos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Productos");
		lblTitulo.setBounds(183, 11, 74, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(47, 49, 68, 14);
		contentPane.add(lblProveedor);
		
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
		cbTipo.setBounds(160, 138, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
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
		
		JButton btnAgregar = new JButton("Agregar");            //Este botón permite agregar un producto
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idPro = Integer.parseInt(txtIdPro.getText());
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				float costo = Float.parseFloat(txtCosto.getText());
				float precio = Float.parseFloat(txtPrecio.getText());
				
				Control_Producto.agregar(idPro, nombre, ((ComboItem) cbTipo.getSelectedItem()).getValue(), descripcion, costo, precio);
				
				limpiar();
				
			}
		});
		btnAgregar.setBounds(156, 336, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");              //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
				Tabla_Productos tp = new Tabla_Productos();
				tp.setVisible(true);		//Abre la ventana Tabla_Productos
				dispose();
			}
		});
		btnVolver.setBounds(315, 389, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnNuevo = new JButton("Nuevo");              //Abre la ventana Tipo_Producto
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Producto tp = new Tipo_Producto();
				tp.setVisible(true);
				dispose();
			}
		});
		btnNuevo.setBounds(357, 138, 117, 23);
		contentPane.add(btnNuevo);
		
		JButton btnSelec = new JButton("Seleccionar");         //Este botón permite buscar un proveedor
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Proveedor bp = new Buscar_Proveedor();
				bp.setVisible(true);		//Abre la ventana Buscar_Proveedor
				dispose();
			}
		});
		btnSelec.setBounds(357, 45, 117, 23);
		contentPane.add(btnSelec);
		
		txtPro = new JTextField();
		txtPro.setEditable(false);
		txtPro.setBounds(160, 46, 187, 20);
		contentPane.add(txtPro);
		txtPro.setColumns(10);
		
		txtIdPro = new JTextField();
		txtIdPro.setEditable(false);
		txtIdPro.setBounds(357, 14, 53, 20);
		contentPane.add(txtIdPro);
		txtIdPro.setColumns(10);
		txtIdPro.setVisible(false);
	}
}

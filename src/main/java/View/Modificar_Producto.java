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

public class Modificar_Producto extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNombre;
	public static JTextField txtDescripcion;
	public static JTextField txtCosto;
	public static JTextField txtPrecio;
	private JTextField txtId;
	public static JComboBox cbTipo;
	private JButton btnSelec;
	private JTextField txtIdPro;
	private JTextField txtPro;
	

	
	public DefaultComboBoxModel cargarProveedor() {            //Este ComboBox no es utilizado en la versión actual
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

	public DefaultComboBoxModel cargarTipo() {             //Carga el combobox con los tipos de producto
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
					Modificar_Producto frame = new Modificar_Producto();
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
	public Modificar_Producto(final String producto) {              //Crea la ventana recibiendo como parámetro el id del producto
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));            //Setea el logo de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(59, 37, 68, 14);
		contentPane.add(lblProveedor);
		
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
		cbTipo.setBounds(172, 126, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
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
		
		JButton btnVolver = new JButton("Volver");                //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Productos tp = new Tabla_Productos();
				tp.setVisible(true);		//Abre la ventana Tabla_Productos
				dispose();
			}
		});
		btnVolver.setBounds(276, 316, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");        //Modifica el producto según los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				int idP = Integer.parseInt(txtIdPro.getText());
				Object tipo = cbTipo.getSelectedItem();
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				float costo = Float.parseFloat(txtCosto.getText());
				float precio = Float.parseFloat(txtPrecio.getText());
				
				Control_Producto.modificar(idP, nombre, ((ComboItem) cbTipo.getSelectedItem()).getValue(), descripcion, costo, precio, id);
				
		                Tabla_Productos tp = new Tabla_Productos();
						tp.setVisible(true);
						dispose();
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
		
		Control_Producto.cargar(producto);
		txtId.setText(producto);
		
		btnSelec = new JButton("Seleccionar");               //Este botón permite seleccionar un proveedor
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Proveedor_ModPro bpm = new Buscar_Proveedor_ModPro(producto);
				bpm.setVisible(true);		//Abre la ventana Buscar_Proveedor_ModPro recibiendo como parámetro el id del producto
				dispose();
			}
		});
		btnSelec.setBounds(369, 33, 116, 23);
		contentPane.add(btnSelec);
		
		txtIdPro = new JTextField();
		txtIdPro.setEditable(false);
		txtIdPro.setBounds(369, 11, 57, 20);
		contentPane.add(txtIdPro);
		txtIdPro.setColumns(10);
		
		txtPro = new JTextField();
		txtPro.setEditable(false);
		txtPro.setBounds(172, 34, 187, 20);
		contentPane.add(txtPro);
		txtPro.setColumns(10);
		
	}

	public Modificar_Producto(final String producto, String id, String nom) {          //Crea la ventana recibiendo como parámetros el id del producto, y el id y nombre del proveedor
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(59, 37, 68, 14);
		contentPane.add(lblProveedor);
		
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
		cbTipo.setBounds(172, 126, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
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
		
		JButton btnVolver = new JButton("Volver");              //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Productos tp = new Tabla_Productos();
				tp.setVisible(true);		//Abre la ventana Tabla_Productos
				dispose();
			}
		});
		btnVolver.setBounds(276, 316, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");                //Modifica el producto segun los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				int idP = Integer.parseInt(txtIdPro.getText());
				Object tipo = cbTipo.getSelectedItem();
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				float costo = Float.parseFloat(txtCosto.getText());
				float precio = Float.parseFloat(txtPrecio.getText());
				
				Control_Producto.modificar(idP, nombre, ((ComboItem) cbTipo.getSelectedItem()).getValue(), descripcion, costo, precio, id);
				
		                Tabla_Productos tp = new Tabla_Productos();
						tp.setVisible(true);
						dispose();
		          
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
		
		Control_Producto.cargar(producto);
		txtId.setText(producto);
		
		btnSelec = new JButton("Seleccionar");                  //Este botón permite seleccionar un proveedor
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Proveedor_ModPro bpm = new Buscar_Proveedor_ModPro(producto);
				bpm.setVisible(true);		//Abre la ventana Buscar_Proveedor_ModPro recibiencdo como parámetro el id del producto
				dispose();
			}
		});
		btnSelec.setBounds(369, 33, 116, 23);
		contentPane.add(btnSelec);
		
		txtIdPro = new JTextField();
		txtIdPro.setEditable(false);
		txtIdPro.setBounds(369, 11, 57, 20);
		contentPane.add(txtIdPro);
		txtIdPro.setColumns(10);
		txtIdPro.setText(id);
		
		txtPro = new JTextField();
		txtPro.setEditable(false);
		txtPro.setBounds(172, 34, 187, 20);
		contentPane.add(txtPro);
		txtPro.setColumns(10);
		txtPro.setText(nom);
	}

	public Modificar_Producto() {
		// TODO Auto-generated constructor stub
	}
}

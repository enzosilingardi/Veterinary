package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Proveedor;
import Model.ComboItem;
import Model.ControlFiles;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombrePro;
	private JTextField txtApellido;
	private JTextField txtCuit;
	private JComboBox cbTipo;
	private JTextField txtDireccion;


	
	public DefaultComboBoxModel cargarDireccion() {           //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT *\r\n"
					+ "FROM Address\r\n"
					+ "INNER JOIN City ON Address.id_City = City.id_City";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number")+" - "+result.getString("name"),result.getString("id_Address")));
				
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
		
		Control_ComboBoxes.CBTipoProv(modelo);
		
		return modelo;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proveedor frame = new Proveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public static Boolean validaEmail (String email) {      //Valida el formato del E-Mail
		
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public int proveedorEnUso(String proveedor) {      //Este procedimiento no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();    
			
			String SSQL = "SELECT count(Product.id_Provider)\r\n"
					+ "FROM Provider\r\n"
					+ "JOIN Product ON Provider.id_Provider = Product.id_Provider\r\n"
					+ "WHERE Provider.provider_Name LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, proveedor);
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
	
	private void limpiar() {           //Este procedimiento limpia los campos
		txtDireccion.setText("");
		cbTipo.setSelectedIndex(0);
		txtNombre.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtNombrePro.setText("");
		txtApellido.setText("");
		txtCuit.setText("");
		
	}
	/**
	 * Create the frame.
	 */
	public Proveedor(final String perfil) {           //Crea la ventana
		setTitle("Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 496, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Proveedores");
		lblTitulo.setBounds(185, 11, 84, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre Titular");
		lblNombre.setBounds(62, 124, 84, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(62, 213, 66, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(62, 257, 66, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(62, 81, 46, 14);
		contentPane.add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(185, 121, 182, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(185, 254, 182, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");             //Este botón permite agregar un proveedor
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String direccion = txtDireccion.getText();
				Object tipo = cbTipo.getSelectedItem();
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String email = txtEmail.getText();
				String nombrePro = txtNombrePro.getText();
				String apellido = txtApellido.getText();
				String cuit = txtCuit.getText();
				
				
				
						if (((ComboItem) tipo).getValue() == "") {                      //Revisa si el ComboBox está en blanco
							JOptionPane.showMessageDialog(null, "Seleccione un tipo");
						}else {
							Control_Proveedor.agregar(((ComboItem) cbTipo.getSelectedItem()).getValue(), direccion, nombrePro, nombre, apellido, telefono, email, cuit);
						}
						
					limpiar();
				
			}
		});
		btnAgregar.setBounds(180, 380, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");                 //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Proveedor tp = new Tabla_Proveedor(perfil);
				tp.setVisible(true);		//Abre la ventana Tabla_Proveedor
				dispose();
			}
		});
		btnVolver.setBounds(291, 429, 89, 23);
		contentPane.add(btnVolver);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(185, 77, 182, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(62, 296, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(185, 293, 182, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNombrePro = new JLabel("Nombre Proveedor");
		lblNombrePro.setBounds(62, 49, 107, 14);
		contentPane.add(lblNombrePro);
		
		txtNombrePro = new JTextField();
		txtNombrePro.setBounds(185, 46, 182, 20);
		contentPane.add(txtNombrePro);
		txtNombrePro.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido Titular");
		lblApellido.setBounds(62, 167, 84, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(185, 164, 182, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(62, 340, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(185, 337, 182, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(185, 210, 182, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JButton btnNuevo = new JButton("Nuevo");                //Abre la ventana Tipo_Proveedor
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Proveedor tp = new Tipo_Proveedor(perfil);
				tp.setVisible(true);
				dispose();
			}
		});
		btnNuevo.setBounds(377, 77, 89, 23);
		contentPane.add(btnNuevo);
	}

	public Proveedor() {
		// TODO Auto-generated constructor stub
	}
}

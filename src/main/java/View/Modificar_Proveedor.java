package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ComboBoxes;
import Control.Connect;
import Control.Consulta_Proveedor;
import Model.ComboItem;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Proveedor extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNombre;
	public static JTextField txtTelefono;
	public static JTextField txtEmail;
	public static JTextField txtNombrePro;
	public static JTextField txtApellido;
	public static JTextField txtCuit;
	private JTextField txtId;
	private JComboBox cbTipo;
	private JTextField txtDireccion;


	
	public DefaultComboBoxModel cargarDireccion() {               //Este ComboBox no se Utiliza en la versión Actual
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
	
	public DefaultComboBoxModel cargarTipo() {                 //Carga el el ComboBox con los tipos de proveedores
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBTipoProv(modelo);
		
		return modelo;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Proveedor frame = new Modificar_Proveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public static Boolean validaEmail (String email) {        //Verifica el formato del E-Mail
		
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	/**
	 * Create the frame.
	 */
	public Modificar_Proveedor(String proveedor, final String perfil) {			//Crea la ventana recibiendo como parámetro el id del proveedor
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));	//Sentencia sql
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre Titular");
		lblNombre.setBounds(54, 113, 84, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(54, 202, 66, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(54, 246, 66, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(54, 70, 46, 14);
		contentPane.add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(177, 110, 182, 20);
		contentPane.add(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(177, 243, 182, 20);
		contentPane.add(txtTelefono);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(177, 66, 182, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(54, 285, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(177, 282, 182, 20);
		contentPane.add(txtEmail);
		
		JLabel lblNombrePro = new JLabel("Nombre Proveedor");
		lblNombrePro.setBounds(54, 38, 107, 14);
		contentPane.add(lblNombrePro);
		
		txtNombrePro = new JTextField();
		txtNombrePro.setColumns(10);
		txtNombrePro.setBounds(177, 35, 182, 20);
		contentPane.add(txtNombrePro);
		
		JLabel lblApellido = new JLabel("Apellido Titular");
		lblApellido.setBounds(54, 156, 84, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(177, 153, 182, 20);
		contentPane.add(txtApellido);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(54, 329, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(177, 326, 182, 20);
		contentPane.add(txtCuit);
		
		JButton btnVolver = new JButton("Volver");              //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Proveedor tp = new Tabla_Proveedor(perfil);
				tp.setVisible(true);		//Abre la ventana Tabla_Proveedor
				dispose();
			}
		});
		btnVolver.setBounds(265, 387, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");               //Este botón modifica el proveedor según los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String direccion = txtDireccion.getText();
				Object tipo = cbTipo.getSelectedItem();
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String email = txtEmail.getText();
				String nombrePro = txtNombrePro.getText();
				String apellido = txtApellido.getText();
				String cuit = txtCuit.getText();
				int id = Integer.parseInt(txtId.getText());
				
				
					
						if (((ComboItem) tipo).getValue() == "") {                         //Revisa si el ComboBox está en blanco
							JOptionPane.showMessageDialog(null, "Seleccione un tipo");
						}else {
							
							Consulta_Proveedor.modificar(((ComboItem) cbTipo.getSelectedItem()).getValue(), direccion, nombrePro, nombre, apellido, telefono, email, cuit, id);
							
						}
		                Tabla_Proveedor tp = new Tabla_Proveedor(perfil);
						tp.setVisible(true);
						dispose();
		            
			}
		});
		btnModificar.setBounds(67, 387, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(54, 11, 27, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		Consulta_Proveedor.cargar(proveedor);
		txtId.setText(proveedor);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(177, 199, 182, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
	}

	public Modificar_Proveedor() {
		// TODO Auto-generated constructor stub
	}

}

package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Control.Control_Cliente;
import Model.ComboItem;
import Model.ControlFiles;

public class Modificar_Cliente extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNombre;
	public static JTextField txtDni;
	public static JDateChooser txtFechaNacimiento;
	public static JTextField txtTelefono;
	private JComboBox cbGenero;
	public static JTextField txtEmail;
	public static JTextField txtApellido;
	private JTextField txtId;
	private JLabel lblTelefonoOp;
	private JTextField txtTelefonoOp;
	private JTextField txtDir;
	

	
	public DefaultComboBoxModel cargarDireccion() {           //Este ComboBox no se utiliza en la versión actual
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Cliente frame = new Modificar_Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public static Boolean validaEmail (String email) {       //Valida el formato del E-Mail
		 
			Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
	 

	/**
	 * Create the frame.
	 */
	public Modificar_Cliente(String cliente, final String perfil) {                //Crea la ventana reccibiendo por parámetro el id del cliente
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));        //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 41, 153, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(39, 118, 153, 14);
		contentPane.add(lblDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(226, 38, 171, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(39, 162, 153, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(226, 156, 171, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(39, 210, 153, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFechaNacimiento.setBounds(226, 204, 171, 20);
		contentPane.add(txtFechaNacimiento);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(39, 259, 153, 14);
		contentPane.add(lblGenero);
		
		cbGenero = new JComboBox();
		cbGenero.setBounds(226, 252, 171, 22);
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));         //Crea un ComboBox con los generos que puede tener el cliente
		contentPane.add(cbGenero);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(39, 313, 153, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(226, 307, 171, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");        //Cierra la ventana
		btnVolver.setBounds(254, 447, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Clientes tc = new Tabla_Clientes(perfil);
				tc.setVisible(true);	//Abre la ventana Tabla_Clientes
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(39, 396, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(226, 393, 171, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(39, 76, 46, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(226, 73, 171, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");              //Este boton modifica el cliente según los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String direccion = txtDir.getText();
				String dni = txtDni.getText();
				String telefono = txtTelefono.getText();
				String genero = cbGenero.getSelectedItem().toString();
				String email = txtEmail.getText();
				String fecha = ((JTextField) txtFechaNacimiento.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				String telefonoOp = txtTelefonoOp.getText();
				

					
					
					if (txtTelefonoOp.getText().isBlank()) {        //Realiza la consulta sql dependiendo si el campo telefono opcional está vacío
						
						Control_Cliente.modificar(direccion, dni, nombre, apellido, telefono, date, genero, email, id);
					} else {
						Control_Cliente.modificarOp(direccion, dni, nombre, apellido, telefono, date, genero, email, telefonoOp, id);
					}
					
					Tabla_Clientes tc = new Tabla_Clientes(perfil);
					tc.setVisible(true);
					dispose();
			}
		});
		btnModificar.setBounds(76, 447, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(37, 11, 40, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		Control_Cliente.cargar(cliente);
		txtId.setText(cliente);
		
		lblTelefonoOp = new JLabel("Telefono Secundario (Opcional)");
		lblTelefonoOp.setBounds(39, 356, 177, 14);
		contentPane.add(lblTelefonoOp);
		
		txtTelefonoOp = new JTextField();
		txtTelefonoOp.setBounds(226, 353, 171, 20);
		contentPane.add(txtTelefonoOp);
		txtTelefonoOp.setColumns(10);
		
		txtDir = new JTextField();
		txtDir.setBounds(226, 115, 171, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);
	}

	public Modificar_Cliente() {
		// TODO Auto-generated constructor stub
	}
}

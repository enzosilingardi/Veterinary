package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Modificar_Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombrePro;
	private JTextField txtApellido;
	private JTextField txtCuit;
	private JTextField txtId;

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

	/**
	 * Create the frame.
	 */
	public Modificar_Proveedor() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(177, 66, 182, 22);
		contentPane.add(cbTipo);
		
		JComboBox cbDireccion = new JComboBox();
		cbDireccion.setBounds(177, 198, 182, 22);
		contentPane.add(cbDireccion);
		
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
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(265, 387, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(67, 387, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(54, 11, 27, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
	}

}

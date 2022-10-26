package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Modificar_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtContrasenia;
	private JTextField txtNombreUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Usuario frame = new Modificar_Usuario();
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
	public Modificar_Usuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(66, 32, 110, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(66, 73, 74, 14);
		contentPane.add(lblContrasenia);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(185, 70, 163, 20);
		contentPane.add(txtContrasenia);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(185, 29, 163, 20);
		contentPane.add(txtNombreUsuario);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(66, 121, 46, 14);
		contentPane.add(lblPerfil);
		
		JComboBox cbPerfil = new JComboBox();
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "Manager", "Regular"}));
		cbPerfil.setBounds(185, 117, 163, 22);
		contentPane.add(cbPerfil);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(66, 174, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(185, 171, 163, 20);
		contentPane.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(66, 225, 59, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(185, 222, 163, 20);
		contentPane.add(txtApellido);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(66, 278, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(185, 275, 164, 20);
		contentPane.add(txtEmail);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(87, 336, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(254, 336, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 26, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
	}

}

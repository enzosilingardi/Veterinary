package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtContrasenia;
	private JTextField txtNombreUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
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
	public Usuario() {
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Usuarios");
		lblTitulo.setBounds(189, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(70, 56, 110, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(70, 97, 74, 14);
		contentPane.add(lblContrasenia);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(74, 362, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(264, 362, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(309, 410, 89, 23);
		contentPane.add(btnVolver);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setBounds(189, 94, 163, 20);
		contentPane.add(txtContrasenia);
		txtContrasenia.setColumns(10);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(189, 53, 163, 20);
		contentPane.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(70, 145, 46, 14);
		contentPane.add(lblPerfil);
		
		JComboBox cbPerfil = new JComboBox();
		cbPerfil.setBounds(189, 141, 163, 22);
		contentPane.add(cbPerfil);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(70, 198, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(189, 195, 163, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(70, 249, 59, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(189, 246, 163, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(70, 302, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(189, 299, 164, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
	}

}
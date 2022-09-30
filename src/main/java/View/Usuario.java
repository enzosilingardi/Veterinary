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
	private JTextField txtFechaNacimiento;
	private JTextField txtDni;
	private JTextField txtContraseña;
	private JTextField txtNombreUsuario;
	private JTextField txtDireccion;
	private JTextField txtNivelPermiso;

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
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setBounds(70, 97, 74, 14);
		contentPane.add(lblContraseña);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(70, 139, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(70, 181, 110, 14);
		contentPane.add(lblFechaNacimiento);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(70, 223, 46, 14);
		contentPane.add(lblGenero);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setBounds(70, 265, 46, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblNivelPermiso = new JLabel("Nivel de permiso");
		lblNivelPermiso.setBounds(70, 308, 94, 14);
		contentPane.add(lblNivelPermiso);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(40, 362, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(157, 362, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(278, 362, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(309, 410, 89, 23);
		contentPane.add(btnVolver);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(189, 178, 163, 20);
		contentPane.add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(189, 136, 163, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtContraseña = new JTextField();
		txtContraseña.setBounds(189, 94, 163, 20);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(189, 53, 163, 20);
		contentPane.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		JComboBox cbGenero = new JComboBox();
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));
		cbGenero.setBounds(189, 219, 163, 22);
		contentPane.add(cbGenero);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(189, 262, 163, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtNivelPermiso = new JTextField();
		txtNivelPermiso.setBounds(189, 305, 163, 20);
		contentPane.add(txtNivelPermiso);
		txtNivelPermiso.setColumns(10);
	}

}
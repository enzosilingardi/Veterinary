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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtFechaNacimiento;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(185, 11, 46, 14);
		contentPane.add(lblCliente);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 60, 153, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(37, 100, 153, 14);
		contentPane.add(lblDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(224, 54, 171, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(37, 144, 153, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(224, 138, 171, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(37, 192, 153, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(224, 186, 171, 20);
		contentPane.add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(37, 241, 153, 14);
		contentPane.add(lblGenero);
		
		JComboBox cbGenero = new JComboBox();
		cbGenero.setBounds(224, 234, 171, 22);
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));
		contentPane.add(cbGenero);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(37, 295, 153, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(224, 289, 171, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(48, 418, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(165, 418, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(286, 418, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(317, 466, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		JComboBox cbDireccion = new JComboBox();
		cbDireccion.setBounds(224, 96, 171, 22);
		contentPane.add(cbDireccion);
	}
}
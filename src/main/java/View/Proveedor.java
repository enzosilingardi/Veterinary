package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField textField;

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

	/**
	 * Create the frame.
	 */
	public Proveedor() {
		setTitle("Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Proveedores");
		lblTitulo.setBounds(185, 11, 84, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(62, 55, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(62, 107, 66, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(62, 157, 66, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(59, 277, 46, 14);
		contentPane.add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(154, 52, 184, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(154, 154, 184, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(37, 320, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(275, 320, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(306, 368, 89, 23);
		contentPane.add(btnVolver);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(151, 273, 184, 22);
		contentPane.add(cbTipo);
		
		JComboBox cbDireccion = new JComboBox();
		cbDireccion.setBounds(154, 103, 184, 22);
		contentPane.add(cbDireccion);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(62, 208, 46, 14);
		contentPane.add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(154, 205, 184, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}

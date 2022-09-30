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

public class Producto_Medico extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtFechaCaducidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Producto_Medico frame = new Producto_Medico();
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
	public Producto_Medico() {
		setTitle("Producto Medico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Productos medicos");
		lblTitulo.setBounds(167, 11, 117, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(70, 56, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(201, 53, 161, 20);
		contentPane.add(txtNombre);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(201, 94, 161, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(70, 97, 46, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblFechaCaducidad = new JLabel("Fecha de caducidad");
		lblFechaCaducidad.setBounds(70, 146, 121, 14);
		contentPane.add(lblFechaCaducidad);
		
		txtFechaCaducidad = new JTextField();
		txtFechaCaducidad.setColumns(10);
		txtFechaCaducidad.setBounds(201, 143, 161, 20);
		contentPane.add(txtFechaCaducidad);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(48, 197, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(165, 197, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(286, 197, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(317, 245, 89, 23);
		contentPane.add(btnVolver);
	}

}
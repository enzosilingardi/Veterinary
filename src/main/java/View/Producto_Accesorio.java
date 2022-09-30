package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Producto_Accesorio extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Producto_Accesorio frame = new Producto_Accesorio();
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
	public Producto_Accesorio() {
		setTitle("Producto Accesorio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Productos Accesorios");
		lblTitulo.setBounds(162, 11, 141, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 62, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(50, 103, 46, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(50, 151, 76, 14);
		contentPane.add(lblDescripcion);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(37, 205, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(154, 205, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(275, 205, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(306, 253, 89, 23);
		contentPane.add(btnVolver);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(142, 59, 161, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(142, 100, 161, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(142, 148, 161, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
	}
}

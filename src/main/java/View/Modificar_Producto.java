package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Modificar_Producto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtCosto;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Producto frame = new Modificar_Producto();
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
	public Modificar_Producto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(59, 37, 68, 14);
		contentPane.add(lblProveedor);
		
		JComboBox cbProveedor = new JComboBox();
		cbProveedor.setBounds(172, 33, 187, 22);
		contentPane.add(cbProveedor);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(59, 84, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(172, 81, 187, 20);
		contentPane.add(txtNombre);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(59, 130, 46, 14);
		contentPane.add(lblTipo);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(172, 126, 187, 22);
		contentPane.add(cbTipo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(59, 177, 68, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(172, 174, 187, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(59, 221, 46, 14);
		contentPane.add(lblCosto);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		txtCosto.setBounds(172, 218, 187, 20);
		contentPane.add(txtCosto);
		
		JLabel lblPrecio = new JLabel("Precio de venta");
		lblPrecio.setBounds(59, 271, 103, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(172, 268, 187, 20);
		contentPane.add(txtPrecio);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(276, 316, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(59, 316, 89, 23);
		contentPane.add(btnModificar);
	}
}

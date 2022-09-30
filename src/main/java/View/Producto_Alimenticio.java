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

public class Producto_Alimenticio extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrecio;
	private JTextField txtNombre;
	private JTextField txtPeso;
	private JTextField txtFechaCaducidad;
	private JTextField txtPrecioKilo;
	private JTextField txtPrecioBolsa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Producto_Alimenticio frame = new Producto_Alimenticio();
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
	public Producto_Alimenticio() {
		setTitle("Producto Alimenticio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Productos Alimenticios");
		lblTitulo.setBounds(161, 11, 147, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(55, 66, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(55, 107, 46, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(186, 104, 161, 20);
		contentPane.add(txtPrecio);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(186, 63, 161, 20);
		contentPane.add(txtNombre);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(55, 149, 46, 14);
		contentPane.add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(186, 146, 161, 20);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		JLabel lblFechaCaducidad = new JLabel("Fecha de caducidad");
		lblFechaCaducidad.setBounds(55, 190, 121, 14);
		contentPane.add(lblFechaCaducidad);
		
		txtFechaCaducidad = new JTextField();
		txtFechaCaducidad.setBounds(186, 187, 161, 20);
		contentPane.add(txtFechaCaducidad);
		txtFechaCaducidad.setColumns(10);
		
		JLabel lblPrecioKilo = new JLabel("Precio por kilo");
		lblPrecioKilo.setBounds(55, 234, 96, 14);
		contentPane.add(lblPrecioKilo);
		
		txtPrecioKilo = new JTextField();
		txtPrecioKilo.setBounds(186, 231, 161, 20);
		contentPane.add(txtPrecioKilo);
		txtPrecioKilo.setColumns(10);
		
		JLabel lblPrecioBolsa = new JLabel("Precio por bolsa");
		lblPrecioBolsa.setBounds(55, 276, 96, 14);
		contentPane.add(lblPrecioBolsa);
		
		txtPrecioBolsa = new JTextField();
		txtPrecioBolsa.setBounds(186, 273, 161, 20);
		contentPane.add(txtPrecioBolsa);
		txtPrecioBolsa.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(41, 322, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(158, 322, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(279, 322, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(310, 370, 89, 23);
		contentPane.add(btnVolver);
	}

}

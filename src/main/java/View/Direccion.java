package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Direccion extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNumero;
	private JTextField txtPiso;
	private JTextField txtDepto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Direccion frame = new Direccion();
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
	public Direccion() {
		setTitle("Dirección");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 482, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Dirección");
		lblTitulo.setBounds(207, 11, 43, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 100, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(46, 152, 46, 14);
		contentPane.add(lblNumero);
		
		JLabel lblPiso = new JLabel("Piso (Opcional)");
		lblPiso.setBounds(46, 203, 89, 14);
		contentPane.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Departamento (Opcional)");
		lblDepto.setBounds(46, 252, 139, 14);
		contentPane.add(lblDepto);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(46, 53, 46, 14);
		contentPane.add(lblCiudad);
		
		JComboBox cbCiudad = new JComboBox();
		cbCiudad.setBounds(228, 49, 162, 22);
		contentPane.add(cbCiudad);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(228, 97, 162, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(228, 149, 162, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(228, 200, 162, 20);
		contentPane.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepto = new JTextField();
		txtDepto.setBounds(228, 249, 162, 20);
		contentPane.add(txtDepto);
		txtDepto.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(88, 303, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(262, 303, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(330, 351, 89, 23);
		contentPane.add(btnVolver);
	}
}

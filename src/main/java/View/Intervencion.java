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

public class Intervencion extends JFrame {

	private JPanel contentPane;
	private JTextField txtMascota;
	private JTextField txtDescripcion;
	private JTextField txtQuirofano;
	private JTextField txtVeterinario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intervencion frame = new Intervencion();
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
	public Intervencion() {
		setTitle("Intervencion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Intervenciones");
		lblTitulo.setBounds(179, 11, 89, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(66, 50, 46, 14);
		contentPane.add(lblMascota);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(66, 95, 76, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblQuirofano = new JLabel("Quirofano");
		lblQuirofano.setBounds(66, 148, 63, 14);
		contentPane.add(lblQuirofano);
		
		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setBounds(66, 198, 63, 14);
		contentPane.add(lblVeterinario);
		
		txtMascota = new JTextField();
		txtMascota.setBounds(169, 47, 156, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(169, 92, 156, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtQuirofano = new JTextField();
		txtQuirofano.setBounds(169, 145, 156, 20);
		contentPane.add(txtQuirofano);
		txtQuirofano.setColumns(10);
		
		txtVeterinario = new JTextField();
		txtVeterinario.setBounds(169, 195, 156, 20);
		contentPane.add(txtVeterinario);
		txtVeterinario.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(43, 248, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(160, 248, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(281, 248, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(312, 296, 89, 23);
		contentPane.add(btnVolver);
	}

}

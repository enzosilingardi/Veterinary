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

public class Visita extends JFrame {

	private JPanel contentPane;
	private JTextField txtVeterinario;
	private JTextField txtMascota;
	private JTextField txtDomicilio;
	private JTextField txtTipoVisita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visita frame = new Visita();
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
	public Visita() {
		setTitle("Visita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Visitas médicas");
		lblTitulo.setBounds(179, 11, 100, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(82, 62, 46, 14);
		contentPane.add(lblMascota);
		
		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setBounds(82, 105, 70, 14);
		contentPane.add(lblVeterinario);
		
		txtVeterinario = new JTextField();
		txtVeterinario.setColumns(10);
		txtVeterinario.setBounds(179, 102, 160, 20);
		contentPane.add(txtVeterinario);
		
		txtMascota = new JTextField();
		txtMascota.setColumns(10);
		txtMascota.setBounds(179, 59, 160, 20);
		contentPane.add(txtMascota);
		
		JLabel lblDomicilio = new JLabel("Domicilio visita");
		lblDomicilio.setBounds(82, 156, 85, 14);
		contentPane.add(lblDomicilio);
		
		JLabel lblTipoVisita = new JLabel("Tipo de visita");
		lblTipoVisita.setBounds(82, 202, 85, 14);
		contentPane.add(lblTipoVisita);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(179, 153, 160, 20);
		contentPane.add(txtDomicilio);
		txtDomicilio.setColumns(10);
		
		txtTipoVisita = new JTextField();
		txtTipoVisita.setBounds(179, 199, 160, 20);
		contentPane.add(txtTipoVisita);
		txtTipoVisita.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(45, 253, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(162, 253, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(314, 301, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(283, 253, 89, 23);
		contentPane.add(btnEliminar);
	}

}

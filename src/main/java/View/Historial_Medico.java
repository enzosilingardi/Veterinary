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

public class Historial_Medico extends JFrame {

	private JPanel contentPane;
	private JTextField txtIntervencion;
	private JTextField txtVisita;
	private JTextField txtCita;
	private JTextField txtMascota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historial_Medico frame = new Historial_Medico();
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
	public Historial_Medico() {
		setTitle("Historial Médico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Historiales médicos");
		lblTitulo.setBounds(164, 11, 119, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(74, 64, 46, 14);
		contentPane.add(lblMascota);
		
		JLabel lblCita = new JLabel("Cita");
		lblCita.setBounds(74, 120, 46, 14);
		contentPane.add(lblCita);
		
		JLabel lblVisita = new JLabel("Visita");
		lblVisita.setBounds(74, 176, 46, 14);
		contentPane.add(lblVisita);
		
		JLabel lblIntervencion = new JLabel("Intervención");
		lblIntervencion.setBounds(74, 232, 77, 14);
		contentPane.add(lblIntervencion);
		
		txtIntervencion = new JTextField();
		txtIntervencion.setBounds(162, 229, 172, 20);
		contentPane.add(txtIntervencion);
		txtIntervencion.setColumns(10);
		
		txtVisita = new JTextField();
		txtVisita.setBounds(162, 176, 172, 20);
		contentPane.add(txtVisita);
		txtVisita.setColumns(10);
		
		txtCita = new JTextField();
		txtCita.setBounds(162, 117, 172, 20);
		contentPane.add(txtCita);
		txtCita.setColumns(10);
		
		txtMascota = new JTextField();
		txtMascota.setBounds(162, 61, 172, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(43, 293, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(160, 293, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(281, 293, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(312, 341, 89, 23);
		contentPane.add(btnVolver);
	}

}

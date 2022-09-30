package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cita extends JFrame {

	private JPanel contentPane;
	private JTextField txtMascota;
	private JTextField txtVeterinario;
	private JTextField txtSucursal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cita frame = new Cita();
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
	public Cita() {
		setTitle("Cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Citas");
		lblTitulo.setBounds(199, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(81, 53, 46, 14);
		contentPane.add(lblMascota);
		
		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setBounds(81, 96, 70, 14);
		contentPane.add(lblVeterinario);
		
		JLabel lblTipoCita = new JLabel("Tipo de cita");
		lblTipoCita.setBounds(81, 138, 70, 14);
		contentPane.add(lblTipoCita);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(81, 185, 57, 14);
		contentPane.add(lblSucursal);
		
		txtMascota = new JTextField();
		txtMascota.setBounds(178, 50, 160, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtVeterinario = new JTextField();
		txtVeterinario.setBounds(178, 93, 160, 20);
		contentPane.add(txtVeterinario);
		txtVeterinario.setColumns(10);
		
		txtSucursal = new JTextField();
		txtSucursal.setBounds(178, 182, 160, 20);
		contentPane.add(txtSucursal);
		txtSucursal.setColumns(10);
		
		JComboBox cbTipoCita = new JComboBox();
		cbTipoCita.setModel(new DefaultComboBoxModel(new String[] {"Control", "Vacunación", "Asistencia"}));
		cbTipoCita.setBounds(178, 134, 160, 22);
		contentPane.add(cbTipoCita);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(44, 232, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(161, 232, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(282, 232, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(313, 280, 89, 23);
		contentPane.add(btnVolver);
	}
}
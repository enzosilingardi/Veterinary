package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Procedimiento_Medico extends JFrame {

	private JPanel contentPane;
	private JTextField txtVeterinario;
	private JTextField txtMascota;
	private JTextField txtSucursal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Procedimiento_Medico frame = new Procedimiento_Medico();
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
	public Procedimiento_Medico() {
		setTitle("Procedimientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Procedimientos");
		lblTitulo.setBounds(186, 11, 99, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(53, 53, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setBounds(53, 109, 71, 14);
		contentPane.add(lblVeterinario);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(53, 157, 46, 14);
		contentPane.add(lblMascota);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(53, 207, 46, 14);
		contentPane.add(lblSucursal);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(47, 309, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(164, 309, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(285, 309, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(316, 357, 89, 23);
		contentPane.add(btnVolver);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Cita", "Visita", "Intervencion"}));
		cbTipo.setBounds(186, 49, 156, 22);
		contentPane.add(cbTipo);
		
		txtVeterinario = new JTextField();
		txtVeterinario.setBounds(186, 106, 156, 20);
		contentPane.add(txtVeterinario);
		txtVeterinario.setColumns(10);
		
		txtMascota = new JTextField();
		txtMascota.setBounds(186, 154, 156, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtSucursal = new JTextField();
		txtSucursal.setBounds(186, 204, 156, 20);
		contentPane.add(txtSucursal);
		txtSucursal.setColumns(10);
	}
}

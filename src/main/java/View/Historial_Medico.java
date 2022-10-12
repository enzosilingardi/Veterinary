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
import javax.swing.JComboBox;

public class Historial_Medico extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;

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
		setBounds(100, 100, 450, 346);
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
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(43, 202, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(160, 202, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(281, 202, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(312, 250, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(74, 121, 77, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(162, 118, 172, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JComboBox cbMascota = new JComboBox();
		cbMascota.setBounds(164, 60, 170, 22);
		contentPane.add(cbMascota);
	}

}

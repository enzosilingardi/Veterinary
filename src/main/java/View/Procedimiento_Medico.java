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
		setBounds(100, 100, 450, 348);
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
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(53, 112, 46, 14);
		contentPane.add(lblMascota);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(71, 202, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(249, 202, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(309, 250, 89, 23);
		contentPane.add(btnVolver);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(186, 49, 156, 22);
		contentPane.add(cbTipo);
		
		JComboBox cbMascota = new JComboBox();
		cbMascota.setBounds(186, 108, 156, 22);
		contentPane.add(cbMascota);
	}
}

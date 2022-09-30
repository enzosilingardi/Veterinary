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

public class Quirofano extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quirofano frame = new Quirofano();
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
	public Quirofano() {
		setTitle("Quirófano");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Quirófanos");
		lblTitulo.setBounds(185, 11, 72, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNumero = new JLabel("Número de quirófano");
		lblNumero.setBounds(46, 69, 131, 14);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(185, 66, 183, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(68, 144, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(209, 144, 89, 23);
		contentPane.add(btnEliminar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(293, 192, 89, 23);
		contentPane.add(btnVolver);
	}

}
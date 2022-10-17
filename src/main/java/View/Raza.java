package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Raza extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Raza frame = new Raza();
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
	public Raza() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Raza");
		lblTitulo.setBounds(154, 11, 66, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(41, 54, 46, 14);
		contentPane.add(lblTipo);
		
		textField = new JTextField();
		textField.setBounds(97, 51, 186, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(282, 214, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(214, 166, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(40, 166, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRel = new JButton("Asociar con animal");
		btnRel.setBounds(122, 108, 121, 23);
		contentPane.add(btnRel);
	}

}

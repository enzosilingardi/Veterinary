package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Animal_Raza extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animal_Raza frame = new Animal_Raza();
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
	public Animal_Raza() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar raza a animal");
		lblTitulo.setBounds(127, 11, 141, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setBounds(43, 58, 60, 14);
		contentPane.add(lblAnimal);
		
		JComboBox cbAnimal = new JComboBox();
		cbAnimal.setBounds(113, 54, 168, 22);
		contentPane.add(cbAnimal);
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(43, 114, 46, 14);
		contentPane.add(lblRaza);
		
		JComboBox cbRaza = new JComboBox();
		cbRaza.setBounds(113, 110, 168, 22);
		contentPane.add(cbRaza);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(51, 174, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(192, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(253, 227, 89, 23);
		contentPane.add(btnVolver);
	}

}

package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Eliminar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Eliminar frame = new Eliminar();
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
	public Eliminar() {
		setTitle("Eliminar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 314, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPregunta = new JLabel("¿Estás seguro?");
		lblPregunta.setBounds(107, 33, 98, 14);
		contentPane.add(lblPregunta);
		
		JButton btnSi = new JButton("Si");
		btnSi.setBounds(27, 99, 89, 23);
		contentPane.add(btnSi);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(170, 99, 89, 23);
		contentPane.add(btnNo);
	}

}

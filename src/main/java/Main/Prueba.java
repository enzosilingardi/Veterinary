package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.sql.*;

public class Prueba extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba frame = new Prueba();
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
	public Prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bases = "";
				Statement stm = null;
				Connection cn = null;
				try{
					cn = (Connection) Connect.getConexion();
					stm = cn.createStatement();
					String consulta = "SELECT name FROM master.dbo.sysdatabases";
					ResultSet resultado = stm.executeQuery(consulta);
					
					while (resultado.next()) {
						bases += resultado.getString(1) + "\n";
					}
					
					JOptionPane.showMessageDialog(null, bases);
					
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,ex.toString());
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(158, 124, 89, 23);
		contentPane.add(btnNewButton);
	}
}

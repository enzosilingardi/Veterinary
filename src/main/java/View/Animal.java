package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Animal extends JFrame {

	private JPanel contentPane;
	private JTextField txtTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animal frame = new Animal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void limpiar() {
		txtTipo.setText("");
	}
	
	public int existeAnimal(String animal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(type) FROM Animal WHERE type = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, animal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
			}
			return 1;
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e);
			return 1;
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
		
		
	}
	
	public int animalEnUso(String animal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(Pet.id_Animal)\r\n"
					+ "FROM Country\r\n"
					+ "JOIN Province ON Country.id_Country = Province.id_Country\r\n"
					+ "WHERE Country.name LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, animal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
			}
			return 1;
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e);
			return 1;
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
		
		
	}

	/**
	 * Create the frame.
	 */
	public Animal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(291, 170, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(223, 122, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(49, 122, 89, 23);
		contentPane.add(btnAgregar);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(110, 51, 186, 20);
		contentPane.add(txtTipo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(54, 54, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblTitulo = new JLabel("Animal");
		lblTitulo.setBounds(167, 11, 66, 14);
		contentPane.add(lblTitulo);
	}
}

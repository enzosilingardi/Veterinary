package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Perfil extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiar() {
		txtNombre.setText("");
	}
	
	
	public int existePerfil(String perfil) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(profile_Name) FROM Profile WHERE profile_Name = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, perfil);
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
	
	public int perfilEnUso(String perfil) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(Users.id_Profile)\r\n"
					+ "FROM Profile\r\n"
					+ "JOIN Users ON Profile.id_Profile = Users.id_Profile\r\n"
					+ "WHERE Profile.profile_Name LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, perfil);
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
	public Perfil() {
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Perfil");
		lblTitulo.setBounds(175, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				String nombre = txtNombre.getText();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Profile (profile_Name) VALUES (?)" );
					if(existePerfil(nombre) != 0) {
						JOptionPane.showMessageDialog(null, "Perfil ya existe");
					}else {
						ps.setString(1, nombre);
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Perfil guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar perfil");
		                limpiar();
		            }
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(60, 131, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				String nombre = txtNombre.getText();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Profile WHERE profile_Name = ?" );
					if(perfilEnUso(nombre) != 0) {
						JOptionPane.showMessageDialog(null, "Perfil está en uso, por favor elimine todos los registros relacionados");
					}else {
						ps.setString(1, nombre);
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Perfil eliminado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar perfil");
		                limpiar();
		            }
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setBounds(221, 131, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(290, 175, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(44, 68, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(122, 65, 188, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
	}

}

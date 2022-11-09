package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int existeUsuario(String usuario, String contrasenia) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Users WHERE username = ? AND password = ?   ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, usuario);
			pst.setString(2, contrasenia);
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
	
	public String perfilUsuario(String usuario, String contrasenia) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT profile FROM Users WHERE username = ? AND password = ?   ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, usuario);
			pst.setString(2, contrasenia);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getString(1);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
		
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 244);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Ingresar usuario y contraseña");
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 14));
		lblTitulo.setBounds(229, 11, 207, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto", Font.BOLD, 14));
		lblUsuario.setBounds(257, 55, 76, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Roboto", Font.BOLD, 14));
		lblContrasenia.setBounds(257, 106, 76, 14);
		contentPane.add(lblContrasenia);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto", Font.BOLD, 14));
		txtUsuario.setBounds(257, 69, 134, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnIngresar.setBackground(new Color(86, 211, 243));
		btnIngresar.setBorder(null);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = txtUsuario.getText();
				String contrasenia = txtContrasenia.getText();
				
					if(existeUsuario(usuario,contrasenia) != 0) {
						Main main = new Main(perfilUsuario(usuario,contrasenia));
						main.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
					}
					
				
				
			}
		});
		btnIngresar.setBounds(217, 161, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnCerrar.setBackground(new Color(86, 211, 243));
		btnCerrar.setBorder(null);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(347, 161, 89, 23);
		contentPane.add(btnCerrar);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto", Font.BOLD, 14));
		txtContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContrasenia.getText().length() >= 16 ) 
		            e.consume(); 
			}
		});
		txtContrasenia.setBounds(257, 120, 134, 20);
		contentPane.add(txtContrasenia);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(86, 211, 243));
		panel.setBounds(0, 0, 207, 207);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 207, 207);
		panel.add(lblNewLabel);
	}
}
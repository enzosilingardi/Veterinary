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
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import java.awt.Toolkit;
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
	
	public int existeUsuario(String usuario, String contrasenia) {              //Revisa si existe el usuario
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();               //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Users WHERE username = ? AND password = ?   ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, usuario);
			pst.setString(2, contrasenia);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);               //Si existe, la variable se pone en 1
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
	
	public String perfilUsuario(String usuario, String contrasenia) {               //Toma el perfil del usuario ingresado según su nombre de usuario y contraseña
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();          //Realiza la contraseña
			
			String SSQL = "SELECT profile FROM Users WHERE username = ? AND password = ?   ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, usuario);
			pst.setString(2, contrasenia);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getString(1);        //Toma el contenido de el campo profile
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
		
	}

	
	private void setScaleImage(JLabel lblFoto, String rutaFoto) {          //Setea la escala de la imagen ingresada
		ImageIcon foto = new ImageIcon(rutaFoto);
		Icon icono = new ImageIcon(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), 1));
		lblFoto.setIcon(icono);
	}
	/**
	 * Create the frame.
	 */
	public Login() {          //Crea la ventana
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
		setBounds(100, 100, 520, 280);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Ingresar usuario y contraseña");
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 14));
		lblTitulo.setBounds(250, 11, 254, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Roboto", Font.BOLD, 14));
		lblUsuario.setBounds(291, 55, 76, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Roboto", Font.BOLD, 14));
		lblContrasenia.setBounds(291, 126, 98, 14);
		contentPane.add(lblContrasenia);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Roboto", Font.BOLD, 14));
		txtUsuario.setBounds(291, 80, 134, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");                //Este botón realiza el login tras ingresar el usuario y contraseña
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnIngresar.setBackground(new Color(86, 211, 243));
		btnIngresar.setBorder(null);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = txtUsuario.getText();
				String contrasenia = txtContrasenia.getText();
				
					if(existeUsuario(usuario,contrasenia) != 0) {                       //Revisa si el usuario ingresado existe
						
						Main main = new Main(perfilUsuario(usuario,contrasenia));         //Abre la ventana principal usando como parámetro el perfil del usuario
						main.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");    //Si el usuario ingresado no existe, lo avisa en pantalla
					}
					
				
				
			}
		});
		btnIngresar.setBounds(244, 196, 89, 23);
		contentPane.add(btnIngresar);
		
		JButton btnCerrar = new JButton("Cerrar");                //Cierra el programa
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnCerrar.setBackground(new Color(86, 211, 243));
		btnCerrar.setBorder(null);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(394, 196, 89, 23);
		contentPane.add(btnCerrar);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setFont(new Font("Roboto", Font.BOLD, 14));
		txtContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContrasenia.getText().length() >= 16 )         //Permite que solo se ingresen hasta 16 caracteres en el campo contraseña
		            e.consume(); 
			}
		});
		txtContrasenia.setBounds(291, 151, 134, 20);
		contentPane.add(txtContrasenia);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(86, 211, 243));
		panel.setBounds(0, 0, 234, 241);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(0, 0, 234, 241);
		panel.add(lblLogo);
		

		setScaleImage(lblLogo,"src/main/java/images/vet.png");         //Sete la imagen del logo
		
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setBounds(260, 78, 26, 26);
		contentPane.add(lblUserIcon);
		setScaleImage(lblUserIcon,"src/main/java/images/user.png");        //Setea el icono de usuario
		
		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setBounds(258, 145, 32, 32);
		contentPane.add(lblPasswordIcon);
		setScaleImage(lblPasswordIcon,"src/main/java/images/pass.png");     //Setea el icono de contraseña
	}
}
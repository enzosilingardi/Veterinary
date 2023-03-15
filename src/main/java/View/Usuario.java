package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Control.Control_Usuario;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtContrasenia;
	private JTextField txtNombreUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JComboBox cbPerfil;
	

	
	
	
	public static Boolean validaEmail (String email) {     //Verifica el formato del E-Mail
		
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public int existeUsuario(String nombre) {    //Este procedimiento revisa si ya exise el usuario
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			
			String SSQL = "SELECT count(username) FROM Users WHERE username = ?;";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);      //Si ya existe, la variable se pone en 1
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario frame = new Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void limpiar() {                //Este procedimiento limpia los campos
		txtNombre.setText("");
		cbPerfil.setSelectedIndex(0);
		txtContrasenia.setText("");
		txtNombreUsuario.setText("");
		txtEmail.setText("");
		txtApellido.setText("");
		
	}

	/**
	 * Create the frame.
	 */
	public Usuario() {         //Crea la ventana
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));      //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Usuarios");
		lblTitulo.setBounds(189, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(70, 56, 110, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(70, 97, 74, 14);
		contentPane.add(lblContrasenia);
		
		JButton btnAgregar = new JButton("Agregar");              //Este botón permite agregar un usuario
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				String perfil = cbPerfil.getSelectedItem().toString();
				String nombreU = txtNombreUsuario.getText();
				String apellido = txtApellido.getText();
				String contrasenia = txtContrasenia.getText();
				String email = txtEmail.getText();
				
				Control_Usuario.agregar(perfil, nombre, apellido, nombreU, contrasenia, email);
				
				limpiar();
				
			}
		});
		btnAgregar.setBounds(168, 361, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios tu = new Tabla_Usuarios();
				tu.setVisible(true);		//Abre la ventana Tabla_Usuarios
				dispose();
			}
		});
		btnVolver.setBounds(309, 410, 89, 23);
		contentPane.add(btnVolver);
		
		txtContrasenia = new JTextField();
		txtContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {                    
				if (txtContrasenia.getText().length() >= 16 )     //Impide que se inserten más de 16 caracteres en la contraseña
		            e.consume(); 
			}
		});
		txtContrasenia.setBounds(189, 94, 163, 20);
		contentPane.add(txtContrasenia);
		txtContrasenia.setColumns(10);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(189, 53, 163, 20);
		contentPane.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(70, 145, 46, 14);
		contentPane.add(lblPerfil);
		
		cbPerfil = new JComboBox();
		cbPerfil.setBounds(189, 141, 163, 22);
		contentPane.add(cbPerfil);
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "Manager", "Regular"}));   //Crea un comboBox con los tipos de perfiles
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(70, 198, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(189, 195, 163, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(70, 249, 59, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(189, 246, 163, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(70, 302, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(189, 299, 164, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
	}

}
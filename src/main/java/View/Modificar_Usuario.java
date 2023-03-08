package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Modificar_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtContrasenia;
	private JTextField txtNombreUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtId;
	private JComboBox cbPerfil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Usuario frame = new Modificar_Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Boolean validaEmail (String email) {       //Valida el formato del E-Mail
		
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private void cargarCampos(String usuario) {        //Carga los campos recibiendo como parámetro el id del usuario
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(usuario);
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			String SSQL = "SELECT profile, name, surname, username, password, email\r\n"
					+ "FROM Users WHERE id_User = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                           //Carga los campos según los resultados de la base de datos
			cbPerfil.setSelectedItem(result.getString(1));	
			txtNombre.setText(result.getString(2));
			txtApellido.setText(result.getString(3));
			txtNombreUsuario.setText(result.getString(4));
			txtContrasenia.setText(result.getString(5));
			txtEmail.setText(result.getString(6));
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	/**
	 * Create the frame.
	 */
	public Modificar_Usuario(String usuario) {                   //Crea la ventana recibiendo como parámetro el id del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setBounds(66, 32, 110, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(66, 73, 74, 14);
		contentPane.add(lblContrasenia);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(185, 70, 163, 20);
		contentPane.add(txtContrasenia);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(185, 29, 163, 20);
		contentPane.add(txtNombreUsuario);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(66, 121, 46, 14);
		contentPane.add(lblPerfil);
		
		cbPerfil = new JComboBox();
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "Manager", "Regular"}));    //Crea un ComboBox con los perfiles que puede tener un usuario
		cbPerfil.setBounds(185, 117, 163, 22);
		contentPane.add(cbPerfil);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(66, 174, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(185, 171, 163, 20);
		contentPane.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(66, 225, 59, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(185, 222, 163, 20);
		contentPane.add(txtApellido);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(66, 278, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(185, 275, 164, 20);
		contentPane.add(txtEmail);
		
		JButton btnModificar = new JButton("Modificar");            //Este botón permite modificar el usuario de acuerdo a los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String perfil = cbPerfil.getSelectedItem().toString();
				String nombreU = txtNombreUsuario.getText();
				String apellido = txtApellido.getText();
				String contrasenia = txtContrasenia.getText();
				String email = txtEmail.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();           //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Users SET profile = ?, name = ?, surname = ?, username = ?, password = ?, email = ? WHERE id_User = ?" );
					
					
					if (perfil == "") {              //Revisa si el ComboBox está en blanco
						
						JOptionPane.showMessageDialog(null, "Seleccione un perfil");
					}else {
					
						ps.setString(1, perfil);
						ps.setString(2, nombre);
						ps.setString(3, apellido);
						ps.setString(4, nombreU);
						
						if(contrasenia.length()<8) {          //Verifica si le contraseña tiene por lo menos 8 caracteres
							
							JOptionPane.showMessageDialog(null, "La contraseña debe tener por lo menos 8 caracteres");
						}else {
							ps.setString(5, contrasenia);
						}
						
						
						if(validaEmail(email)) {             //Verifica que el E-Mail sea valido
							ps.setString(6,email);
						} else {
							JOptionPane.showMessageDialog(null, "E-Mail no válido");
						}
						
						ps.setInt(7, id);
						
					}
						
					
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario modificado");         //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, despues regresa a la ventana Tabla_Usuarios
		                ControlFiles.addContent("Se ha modificado el usuario "+nombreU);
		                Tabla_Usuarios tu = new Tabla_Usuarios();
						tu.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar usuario");      //En caso de fallar, lo avisa en pantalla
		               
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(87, 336, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");             //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios tu = new Tabla_Usuarios();
				tu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(254, 336, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 26, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(usuario);
		txtId.setText(usuario);
	}

	public Modificar_Usuario() {
		// TODO Auto-generated constructor stub
	}

}

package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Ciudad.ComboItem;

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
	
	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)      //Genera el label que se verá en el combobox y el valor del objeto seleccionado
	    {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public String toString()
	    {
	        return key;
	    }

	    public String getKey()
	    {
	        return key;
	    }

	    public String getValue()
	    {
	        return value;
	    }
	}
	
	public DefaultComboBoxModel cargarPerfil() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Profile ORDER BY id_Profile";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("profile_Name"),result.getString("id_Profile")));
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }
	
	public static Boolean validaEmail (String email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public int existeUsuario(String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(username) FROM Users WHERE username = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
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
	
	private void limpiar() {
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
	public Usuario() {
		setTitle("Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				String perfil = cbPerfil.getSelectedItem().toString();
				String nombreU = txtNombreUsuario.getText();
				String apellido = txtApellido.getText();
				String contrasenia = txtContrasenia.getText();
				String email = txtEmail.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Users (profile,name,surname,username,password,email) VALUES (?,?,?,?,?,?)" );
					
					
					if (perfil == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un perfil");
					}else {
						if(existeUsuario(nombreU)!=0) {
						JOptionPane.showMessageDialog(null, "Usuario ya existe");
					}else {
						ps.setString(1, perfil);
						ps.setString(2, nombre);
						ps.setString(3, apellido);
						ps.setString(4, nombreU);
						
						if(contrasenia.length()<8) {
							JOptionPane.showMessageDialog(null, "La contraseña debe tener por lo menos 8 caracteres");
						}else {
							ps.setString(5, contrasenia);
						}
						
						
						if(validaEmail(email)) {
							ps.setString(6,email);
						} else {
							JOptionPane.showMessageDialog(null, "E-Mail no válido");
						}
						
						
						
					}
						
					}
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar usuario");
		                limpiar();
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAgregar.setBounds(168, 361, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios tu = new Tabla_Usuarios();
				tu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(309, 410, 89, 23);
		contentPane.add(btnVolver);
		
		txtContrasenia = new JTextField();
		txtContrasenia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContrasenia.getText().length() >= 16 ) 
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
		cbPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "Admin", "Manager", "Regular"}));
		
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
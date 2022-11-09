package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;
import View.Direccion.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JDateChooser txtFechaNacimiento;
	private JTextField txtTelefono;
	private JComboBox cbGenero;
	private JTextField txtEmail;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtTelefonoOp;
	
	
	
	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)
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
	
	public DefaultComboBoxModel cargarDireccion() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT *\r\n"
					+ "FROM Address\r\n"
					+ "INNER JOIN City ON Address.id_City = City.id_City";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number")+" - "+result.getString("name"),result.getString("id_Address")));
				
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// Validaciones de los formatos de E-Mail, Telefono y Fecha
	
	 public static Boolean validaEmail (String email) {
			Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
	    

	public int existeCliente(String nombre, String dni) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Client WHERE name = ? AND dni = ?   ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,nombre);
			pst.setString(2,dni);
			
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
	
	
	
	
	
	private void limpiar() {
		txtNombre.setText("");
		txtDireccion.setText("");
		txtDni.setText("");
		txtTelefono.setText("");
		cbGenero.setSelectedIndex(0);
		txtEmail.setText("");
		txtDireccion.setText("");
		
	}
	/**
	 * Create the frame.
	 */
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(185, 11, 46, 14);
		contentPane.add(lblCliente);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 60, 153, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(37, 137, 153, 14);
		contentPane.add(lblDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(224, 54, 171, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(37, 181, 153, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(224, 175, 171, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(37, 229, 153, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFechaNacimiento.setBounds(224, 223, 171, 20);
		contentPane.add(txtFechaNacimiento);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(37, 278, 153, 14);
		contentPane.add(lblGenero);
		
		cbGenero = new JComboBox();
		cbGenero.setBounds(224, 271, 171, 22);
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));
		contentPane.add(cbGenero);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(37, 332, 153, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(224, 326, 171, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String direccion = txtDireccion.getText();
				String dni = txtDni.getText();
				String telefono = txtTelefono.getText();
				String genero = cbGenero.getSelectedItem().toString();
				String email = txtEmail.getText();
				String fecha = ((JTextField) txtFechaNacimiento.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					
					if (txtTelefonoOp.getText().isBlank()) {
						ps = con.prepareStatement("INSERT INTO Client (address, dni, name,surname,  phone_Number , birthdate, gender, email) VALUES (?,?,?,?,?,?,?,?)" );
					} else {
						ps = con.prepareStatement("INSERT INTO Client (address, dni, name,surname,  phone_Number , birthdate, gender, email, phone_Optional) VALUES (?,?,?,?,?,?,?,?,?)" );
						String telefonoOp = txtTelefonoOp.getText();
						ps.setString(9,telefonoOp);
					}
					
					
						if(existeCliente(nombre,dni)!=0) {
						JOptionPane.showMessageDialog(null, "Cliente ya existe");
					}else {
						ps.setString(1, direccion);
						ps.setString(2, dni);
						ps.setString(3, nombre);
						ps.setString(4, apellido);
						
						ps.setString(5,telefono);
						
						
						
						ps.setDate(6, date);
						

						ps.setString(7, genero);
						
						if(validaEmail(email)) {
							ps.setString(8,email);
						} else {
							JOptionPane.showMessageDialog(null, "E-Mail no válido");
						}
						
						
						
					}
						
					
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Cliente guardado");
		                ControlFiles.addContent("Se ha añadido un cliente de nombre "+nombre+" "+apellido);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar cliente");
		                limpiar();
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAgregar.setBounds(169, 473, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(296, 502, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Clientes tc = new Tabla_Clientes();
				tc.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(37, 417, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(224, 414, 171, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(37, 95, 46, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(224, 92, 171, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(224, 134, 171, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelefonoOp = new JLabel("Telefono Secundario (Opcional)");
		lblTelefonoOp.setBounds(37, 373, 171, 14);
		contentPane.add(lblTelefonoOp);
		
		txtTelefonoOp = new JTextField();
		txtTelefonoOp.setBounds(224, 370, 171, 20);
		contentPane.add(txtTelefonoOp);
		txtTelefonoOp.setColumns(10);
		
		
	}
}
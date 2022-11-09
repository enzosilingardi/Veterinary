package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;
import View.Cliente.ComboItem;

public class Modificar_Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JDateChooser txtFechaNacimiento;
	private JTextField txtTelefono;
	private JComboBox cbGenero;
	private JTextField txtEmail;
	private JTextField txtApellido;
	private JTextField txtId;
	private JLabel lblTelefonoOp;
	private JTextField txtTelefonoOp;
	private JTextField txtDir;
	
	
	
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
					Modificar_Cliente frame = new Modificar_Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public static Boolean validaEmail (String email) {
			Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
	 
	 private void cargarCampos(String cliente) {
			Connection cn = null;
			PreparedStatement pst = null;
			ResultSet result = null;
			
			int id = Integer.parseInt(cliente);
			
			try {
				cn = (Connection) Connect.getConexion();
				String SSQL = "SELECT dni, name, surname, phone_Number, email, birthdate FROM Client WHERE id_Client = ?";
				pst = cn.prepareStatement(SSQL);
				pst.setInt(1, id);
				
				
				result = pst.executeQuery();
				while (result.next()){
				txtDni.setText(result.getString(1));
				txtNombre.setText(result.getString(2));
				txtApellido.setText(result.getString(3));
				txtTelefono.setText(result.getString(4));
				txtEmail.setText(result.getString(5));
				txtFechaNacimiento.setDate(result.getDate(6));
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
	public Modificar_Cliente(String cliente) {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 41, 153, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(39, 118, 153, 14);
		contentPane.add(lblDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(226, 38, 171, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(39, 162, 153, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(226, 156, 171, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(39, 210, 153, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFechaNacimiento.setBounds(226, 204, 171, 20);
		contentPane.add(txtFechaNacimiento);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(39, 259, 153, 14);
		contentPane.add(lblGenero);
		
		cbGenero = new JComboBox();
		cbGenero.setBounds(226, 252, 171, 22);
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));
		contentPane.add(cbGenero);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(39, 313, 153, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(226, 307, 171, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(254, 447, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Clientes tc = new Tabla_Clientes();
				tc.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(39, 396, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(226, 393, 171, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(39, 76, 46, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(226, 73, 171, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String direccion = txtDir.getText();
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
						ps = con.prepareStatement("UPDATE Client SET address = ?, dni = ?, name = ? ,surname = ?,  phone_Number = ? , birthdate = ?, gender = ?, email = ? WHERE id_Client = ?" );
						ps.setInt(9, id);
					} else {
						ps = con.prepareStatement("UPDATE Client SET address = ?, dni = ?, name = ? ,surname = ?,  phone_Number = ? , birthdate = ?, gender = ?, email = ? , phone_Optional = ? WHERE id_Client = ?" );
						String telefonoOp = txtTelefonoOp.getText();
						
						ps.setString(9,telefonoOp);
						ps.setInt(10, id);
					}
					
						
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
						
						

					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Cliente modificado");
		                ControlFiles.addContent("Se ha modificado un cliente con el nombre "+nombre+" "+apellido);
		                Tabla_Clientes tc = new Tabla_Clientes();
		                tc.setVisible(true);
		                dispose();
		                
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar cliente");
		                
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
		btnModificar.setBounds(76, 447, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(37, 11, 40, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(cliente);
		txtId.setText(cliente);
		
		lblTelefonoOp = new JLabel("Telefono Secundario (Opcional)");
		lblTelefonoOp.setBounds(39, 356, 177, 14);
		contentPane.add(lblTelefonoOp);
		
		txtTelefonoOp = new JTextField();
		txtTelefonoOp.setBounds(226, 353, 171, 20);
		contentPane.add(txtTelefonoOp);
		txtTelefonoOp.setColumns(10);
		
		txtDir = new JTextField();
		txtDir.setBounds(226, 115, 171, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);
	}

	public Modificar_Cliente() {
		// TODO Auto-generated constructor stub
	}
}

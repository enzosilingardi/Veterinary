package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Proveedor.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombrePro;
	private JTextField txtApellido;
	private JTextField txtCuit;
	private JTextField txtId;
	private JComboBox cbTipo;
	private JTextField txtDireccion;

	
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
	
	public DefaultComboBoxModel cargarTipo() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Provider_Type ORDER BY id_Provider_Type";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type_Name"),result.getString("id_Provider_Type")));
				
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
					Modificar_Proveedor frame = new Modificar_Proveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargarCampos(String proveedor) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(proveedor);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT provider_Name, name, surname, phone_Number, email, cuit FROM Provider WHERE id_Provider = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtNombrePro.setText(result.getString(1));
			txtNombre.setText(result.getString(2));
			txtApellido.setText(result.getString(3));
			txtTelefono.setText(result.getString(4));
			txtEmail.setText(result.getString(5));
			txtCuit.setText(result.getString(6));
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public static Boolean validaEmail (String email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	/**
	 * Create the frame.
	 */
	public Modificar_Proveedor(String proveedor) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre Titular");
		lblNombre.setBounds(54, 113, 84, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(54, 202, 66, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(54, 246, 66, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(54, 70, 46, 14);
		contentPane.add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(177, 110, 182, 20);
		contentPane.add(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(177, 243, 182, 20);
		contentPane.add(txtTelefono);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(177, 66, 182, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(54, 285, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(177, 282, 182, 20);
		contentPane.add(txtEmail);
		
		JLabel lblNombrePro = new JLabel("Nombre Proveedor");
		lblNombrePro.setBounds(54, 38, 107, 14);
		contentPane.add(lblNombrePro);
		
		txtNombrePro = new JTextField();
		txtNombrePro.setColumns(10);
		txtNombrePro.setBounds(177, 35, 182, 20);
		contentPane.add(txtNombrePro);
		
		JLabel lblApellido = new JLabel("Apellido Titular");
		lblApellido.setBounds(54, 156, 84, 14);
		contentPane.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(177, 153, 182, 20);
		contentPane.add(txtApellido);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(54, 329, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(177, 326, 182, 20);
		contentPane.add(txtCuit);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Proveedor tp = new Tabla_Proveedor();
				tp.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(265, 387, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String direccion = txtDireccion.getText();
				Object tipo = cbTipo.getSelectedItem();
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String email = txtEmail.getText();
				String nombrePro = txtNombrePro.getText();
				String apellido = txtApellido.getText();
				String cuit = txtCuit.getText();
				int id = Integer.parseInt(txtId.getText());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Provider SET id_Provider_Type = ?, address = ?, provider_Name = ?, name = ?, surname = ?, phone_Number = ?, email = ?, cuit = ? WHERE id_Provider = ?" );
					
					
					
						if (((ComboItem) tipo).getValue() == "") {
							JOptionPane.showMessageDialog(null, "Seleccione un tipo");
						}else {
							
								ps.setString(1, ((ComboItem) tipo).getValue());
								ps.setString(2, direccion);
								ps.setString(3, nombrePro);
								ps.setString(4, nombre);
								ps.setString(5, apellido);
								
								ps.setString(6, telefono); 
								
								
								
								if(validaEmail(email)) {
									ps.setString(7,email);
								} else {
									JOptionPane.showMessageDialog(null, "E-Mail no válido");
								}
								
								ps.setString(8, cuit);
								
								ps.setInt(9, id);
							}
						
						
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Proveedor modificado");
		                ControlFiles.addContent("Se ha modificado el proveedor "+nombre);
		                Tabla_Proveedor tp = new Tabla_Proveedor();
						tp.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar proveedor");
		                
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(67, 387, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(54, 11, 27, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(proveedor);
		txtId.setText(proveedor);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(177, 199, 182, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
	}

	public Modificar_Proveedor() {
		// TODO Auto-generated constructor stub
	}

}

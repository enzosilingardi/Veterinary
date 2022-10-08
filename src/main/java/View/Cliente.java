package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Direccion.ComboItem;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtFechaNacimiento;
	private JTextField txtTelefono;
	private JComboBox cbDireccion;
	private JComboBox cbGenero;
	private JTextField txtEmail;
	
	
	
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
			String SSQL = "SELECT * FROM Address ORDER BY id_Address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number"),result.getString("id_City")));
				
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
	    
	    public static Boolean validaTelefono (String tele){
	        Pattern pattern = Pattern.compile("(\\d{2,4})-\\d{6}");
			Matcher matcher = pattern.matcher(tele);
			return matcher.matches();
	    }
	    
	    public static Boolean validaFecha (String fecha){
	       try {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	            formatoFecha.setLenient(false);
	            formatoFecha.parse(fecha);
	        } catch (ParseException e) {
	            return false;
	        }
	        return true;
	    
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
		cbDireccion.setSelectedIndex(0);
		txtDni.setText("");
		txtTelefono.setText("");
		txtFechaNacimiento.setText("");
		cbGenero.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Cliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 529);
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
		lblDireccion.setBounds(37, 100, 153, 14);
		contentPane.add(lblDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(224, 54, 171, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(37, 144, 153, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(224, 138, 171, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setBounds(37, 192, 153, 14);
		contentPane.add(lblFechaNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(224, 186, 171, 20);
		contentPane.add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(37, 241, 153, 14);
		contentPane.add(lblGenero);
		
		cbGenero = new JComboBox();
		cbGenero.setBounds(224, 234, 171, 22);
		cbGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer", "Otros"}));
		contentPane.add(cbGenero);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(37, 295, 153, 14);
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(224, 289, 171, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				Object direccion = cbDireccion.getSelectedItem();
				String dni = txtDni.getText();
				String fecha = txtFechaNacimiento.getText();
				String telefono = txtTelefono.getText();
				String genero = cbGenero.getSelectedItem().toString();
				String email = txtEmail.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Client (dni, name, id_Address, phone_Number , birthdate, gender, email) VALUES (?,?,?,?,?,?,?)" );
					
					
					if (((ComboItem) direccion).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una ciudad");
					}else {
						if(existeCliente(nombre,dni)!=0) {
						JOptionPane.showMessageDialog(null, "Cliente ya existe");
					}else {
						ps.setString(1, dni);
						ps.setString(2, nombre);
						ps.setString(3, ((ComboItem) direccion).getValue());
						
						if(validaTelefono(telefono)) {
							ps.setString(4,telefono);
						} else {
							JOptionPane.showMessageDialog(null, "Teléfono no válido");
						}
						
						if(validaFecha(fecha)) {
							ps.setString(5, fecha);
						} else {
							JOptionPane.showMessageDialog(null, "Fecha no válida");
						}

						ps.setString(6, genero);
						
						if(validaEmail(email)) {
							ps.setString(7,email);
						} else {
							JOptionPane.showMessageDialog(null, "E-Mail no válido");
						}
						
						
						
					}
						
					}
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Cliente guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar cliente");
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
		btnAgregar.setBounds(60, 408, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(242, 408, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(305, 456, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		cbDireccion = new JComboBox();
		cbDireccion.setBounds(224, 96, 171, 22);
		contentPane.add(cbDireccion);
		cbDireccion.setModel(cargarDireccion());
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(37, 349, 46, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(224, 346, 171, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
	}
}
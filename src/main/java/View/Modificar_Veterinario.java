package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Veterinario.ComboItem;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Modificar_Veterinario extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtApellido;
	private JTextField txtNombre;
private JTextField txtId;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Veterinario frame = new Modificar_Veterinario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargarCampos(String veterinario) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(veterinario);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT name, surname, medical_License\r\n"
					+ "FROM Veterinarian WHERE id_Veterinarian = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtNombre.setText(result.getString(1));
			txtApellido.setText(result.getString(2));
			txtMatricula.setText(result.getString(3));
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
	public Modificar_Veterinario(String veterinario) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(170, 187, 163, 20);
		contentPane.add(txtMatricula);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setBounds(51, 190, 57, 14);
		contentPane.add(lblMatricula);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(51, 140, 66, 14);
		contentPane.add(lblDireccion);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(170, 87, 163, 20);
		contentPane.add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(51, 90, 59, 14);
		contentPane.add(lblApellido);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(51, 39, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(170, 36, 163, 20);
		contentPane.add(txtNombre);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Veterinario tv = new Tabla_Veterinario();
				tv.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(228, 261, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String direccion = txtDireccion.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String matricula = txtMatricula.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Veterinarian SET address = ?, name = ? ,surname = ? ,medical_License = ?  WHERE id_Veterinarian = ?" );
					
					
						ps.setString(1, direccion);
						ps.setString(2,nombre);
						ps.setString(3,apellido);
						ps.setString(4,matricula);
						ps.setInt(5, id);
					
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Veterinario guardado");
		                ControlFiles.addContent("Se ha modificado el veterinario "+nombre+" "+apellido);
		                Tabla_Veterinario tv = new Tabla_Veterinario();
						tv.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar veterinario");
		                
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(61, 261, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(51, 8, 37, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(veterinario);
		txtId.setText(veterinario);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(170, 137, 173, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
	}

	public Modificar_Veterinario() {
		// TODO Auto-generated constructor stub
	}
}

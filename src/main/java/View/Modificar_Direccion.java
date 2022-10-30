package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Direccion.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Direccion extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNumero;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JTextField txtId;
	private JComboBox cbCiudad;
	
	
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
	
	public DefaultComboBoxModel cargarCiudad() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM City ORDER BY id_City";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name"),result.getString("id_City")));
				
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
					Modificar_Direccion frame = new Modificar_Direccion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void cargarCampos(String direccion) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(direccion);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT address_Name, address_Number, floor_Number, dept_Number\r\n"
					+ "FROM Address WHERE id_Address = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtNombre.setText(result.getString(1));
			txtNumero.setText(result.getString(2));
			txtPiso.setText(result.getString(3));
			txtDepto.setText(result.getString(4));
			
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
	public Modificar_Direccion(String direccion, final String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(59, 88, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(59, 140, 46, 14);
		contentPane.add(lblNumero);
		
		JLabel lblPiso = new JLabel("Piso (Opcional)");
		lblPiso.setBounds(59, 191, 89, 14);
		contentPane.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Departamento (Opcional)");
		lblDepto.setBounds(59, 240, 139, 14);
		contentPane.add(lblDepto);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(59, 41, 46, 14);
		contentPane.add(lblCiudad);
		
		cbCiudad = new JComboBox();
		cbCiudad.setBounds(241, 37, 162, 22);
		contentPane.add(cbCiudad);
		cbCiudad.setModel(cargarCiudad());
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(241, 85, 162, 20);
		contentPane.add(txtNombre);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(241, 137, 162, 20);
		contentPane.add(txtNumero);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(241, 188, 162, 20);
		contentPane.add(txtPiso);
		
		txtDepto = new JTextField();
		txtDepto.setColumns(10);
		txtDepto.setBounds(241, 237, 162, 20);
		contentPane.add(txtDepto);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				String nombre = txtNombre.getText();
				Object ciudad = cbCiudad.getSelectedItem();
				int numero = Integer.parseInt(txtNumero.getText());
				int id = Integer.parseInt(txtId.getText());
				
				
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					
					if(txtPiso.getText().isBlank() && txtDepto.getText().isBlank()) {
						ps = con.prepareStatement("UPDATE Address SET id_City = ?, address_Name = ?, address_Number = ? WHERE id_Address = ?" );
						ps.setInt(4, id);
					}else {
						ps = con.prepareStatement("UPDATE Address SET id_City = ?, address_Name = ?, address_Number = ?, floor_Number = ?, dept_Number = ? WHERE id_Address = ?" );
						Integer piso = Integer.parseInt(txtPiso.getText());
						String depto = txtDepto.getText();
						ps.setInt(4,piso);
						
						ps.setString(5,depto);
						ps.setInt(6, id);
					}
					
					
					
					
					if (((ComboItem) ciudad).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una ciudad");
					}else {
						
						ps.setString(1, ((ComboItem) ciudad).getValue());
						ps.setString(2, nombre);
						ps.setInt(3,numero);
						
						
						
					}
						
					
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Direccion modificada");
		                Tabla_Direccion td = new Tabla_Direccion(perfil);
						td.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar direccion");
		                
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
		btnModificar.setBounds(102, 296, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Direccion td = new Tabla_Direccion(perfil);
				td.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(261, 296, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 17, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(direccion);
		txtId.setText(direccion);
	}

	public Modificar_Direccion() {
		// TODO Auto-generated constructor stub
	}
}

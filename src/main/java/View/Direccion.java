package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Sucursal.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Direccion extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtNumero;
	private JTextField txtPiso;
	private JTextField txtDepto;
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
	
	public int existeDireccion(Object ciudad, String nombre, int numero) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Address WHERE id_City = ? AND address_Name = ? AND address_Number = ?  ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) ciudad);
			pst.setString(2,nombre);
			pst.setInt(3,numero);
			
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
					Direccion frame = new Direccion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	private void limpiar() {
		txtNombre.setText("");
		cbCiudad.setSelectedIndex(0);
		txtNumero.setText("");
		txtPiso.setText("");
		txtDepto.setText("");
		
	}
	/**
	 * Create the frame.
	 */
	public Direccion() {
		setTitle("Dirección");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Dirección");
		lblTitulo.setBounds(207, 11, 43, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(46, 100, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(46, 152, 46, 14);
		contentPane.add(lblNumero);
		
		JLabel lblPiso = new JLabel("Piso (Opcional)");
		lblPiso.setBounds(46, 203, 89, 14);
		contentPane.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Departamento (Opcional)");
		lblDepto.setBounds(46, 252, 139, 14);
		contentPane.add(lblDepto);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(46, 53, 46, 14);
		contentPane.add(lblCiudad);
		
		cbCiudad = new JComboBox();
		cbCiudad.setBounds(228, 49, 162, 22);
		contentPane.add(cbCiudad);
		cbCiudad.setModel(cargarCiudad());
		
		txtNombre = new JTextField();
		txtNombre.setBounds(228, 97, 162, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(228, 149, 162, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(228, 200, 162, 20);
		contentPane.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepto = new JTextField();
		txtDepto.setBounds(228, 249, 162, 20);
		contentPane.add(txtDepto);
		txtDepto.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				String nombre = txtNombre.getText();
				Object ciudad = cbCiudad.getSelectedItem();
				int numero = Integer.parseInt(txtNumero.getText());
				
				
				
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					
					if(txtPiso.getText().isBlank() && txtDepto.getText().isBlank()) {
						ps = con.prepareStatement("INSERT INTO Address (id_City, address_Name, address_Number) VALUES (?,?,?)" );
					}else {
						ps = con.prepareStatement("INSERT INTO Address (id_City, address_Name, address_Number, floor_Number, dept_Number) VALUES (?,?,?,?,?)" );
						Integer piso = Integer.parseInt(txtPiso.getText());
						String depto = txtDepto.getText();
						ps.setInt(4,piso);
						
						ps.setString(5,depto);
					}
					
					
					
					
					if (((ComboItem) ciudad).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una ciudad");
					}else {
						if(existeDireccion(((ComboItem) cbCiudad.getSelectedItem()).getValue(),nombre,numero)!=0) {
						JOptionPane.showMessageDialog(null, "Direccion ya existe");
					}else {
						ps.setString(1, ((ComboItem) ciudad).getValue());
						ps.setString(2, nombre);
						ps.setInt(3,numero);
						
						
						
					}
						
					}
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Direccion guardada");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar direccion");
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
		btnAgregar.setBounds(88, 303, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				int numero = Integer.parseInt(txtNumero.getText());
				Object ciudad = cbCiudad.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Address WHERE id_City = ? AND address_Name = ? AND address_Number = ? " );

					ps.setString(1, ((ComboItem) ciudad).getValue());
					ps.setString(2, nombre);
					ps.setInt(3,numero);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Sucursal borrada");
					limpiar();
					con.close();
					
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Dirección en uso. Por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(262, 303, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(330, 351, 89, 23);
		contentPane.add(btnVolver);
	}
}

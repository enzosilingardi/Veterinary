package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Sucursal_Producto.ComboItem;

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

public class Usuario_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbUsuario;
	private JComboBox cbSucursal;


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
	

	public DefaultComboBoxModel cargarUsuario() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Users ORDER BY id_User";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("username"),result.getString("id_User")));
				
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
	
	public DefaultComboBoxModel cargarSucursal() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "Select *\r\n"
					+ "FROM Branch\r\n"
					+ "INNER JOIN Address ON Branch.id_Address = Address.id_Address\r\n"
					+ "ORDER BY Branch.id_Address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number"),result.getString("id_Branch")));
				
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
					Usuario_Sucursal frame = new Usuario_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object usuario, Object sucursal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Users_Branch WHERE id_User = ? AND id_Branch = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) usuario);
			pst.setString(2, (String) sucursal);
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
		cbUsuario.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Usuario_Sucursal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar usuario con sucursal");
		lblTitulo.setBounds(115, 11, 184, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(39, 58, 65, 14);
		contentPane.add(lblUsuario);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(115, 54, 184, 22);
		contentPane.add(cbUsuario);
		cbUsuario.setModel(cargarUsuario());
		
		JLabel lblNewLabel = new JLabel("Sucursal");
		lblNewLabel.setBounds(39, 119, 65, 14);
		contentPane.add(lblNewLabel);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(115, 115, 184, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object usuario = cbUsuario.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Users_Branch (id_User,id_Branch) VALUES (?,?)" );
					
					
					if (((ComboItem) usuario).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							if(existeRel(((ComboItem) cbUsuario.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Usuario ya se encuentra en la sucursal");
							}else {
								ps.setString(1, ((ComboItem) usuario).getValue());
								ps.setString(2, ((ComboItem) sucursal).getValue());
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario colocado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar usuario");
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
		btnAgregar.setBounds(62, 174, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object usuario = cbUsuario.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Users_Branch WHERE id_Branch = ? AND id_User = ?;" );
					ps.setString(1, ((ComboItem) sucursal).getValue());
					ps.setString(2, ((ComboItem) usuario).getValue());
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario removido de la sucursal");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al remover usuario");
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
		btnEliminar.setBounds(203, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(264, 227, 89, 23);
		contentPane.add(btnVolver);
	}
}

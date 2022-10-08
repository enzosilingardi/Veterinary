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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Quirofano extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnVolver;
	private JLabel lblSucursal;
	private JComboBox cbSucursal;
	
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
	
	public DefaultComboBoxModel cargarSucursal() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Branch JOIN Address ON Branch.id_Address = Address.id_Address ORDER BY id_Branch";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("Address.address_Name")+" - "+result.getString("Address.address_Number"),result.getString("Branch.id_Branch")));
				
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
					Quirofano frame = new Quirofano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public int existeQuirofano(Object sucursal, int numero) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Operating_Room WHERE room_Number = ? AND id_Branch = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, numero);
			pst.setString(2,(String) sucursal);

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
	
	public int quirofanoEnUso(int quirofano) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(Medical_Instrument.id_Operating_Room)\r\n"
					+ "FROM Operating_Room\r\n"
					+ "JOIN Medical_Instrument ON Operating_Room.id_Operating_Room = Medical_Instrument.id_Operating_Room\r\n"
					+ "WHERE Operating_Room.room_Number LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, quirofano);
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
		txtNumero.setText("");
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Quirofano() {
		setTitle("Quirófano");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Quirófanos");
		lblTitulo.setBounds(185, 11, 72, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNumero = new JLabel("Número de quirófano");
		lblNumero.setBounds(46, 69, 131, 14);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(185, 66, 183, 20);
		contentPane.add(txtNumero);
		txtNumero.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = Integer.parseInt(txtNumero.getText());
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Operating_Room (room_Number, id_Branch) VALUES (?,?)" );
					
					
					if (((ComboItem) sucursal).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
					}else {
						if(existeQuirofano(((ComboItem) cbSucursal.getSelectedItem()).getValue(),numero)!=0) {
						JOptionPane.showMessageDialog(null, "Quirófano ya existe");
					}else {
						ps.setInt(1, numero);
						ps.setString(2, ((ComboItem) sucursal).getValue());
					}
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Quirófano guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar quirófano");
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
		btnAgregar.setBounds(70, 179, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int result = 0;
				int numero = Integer.parseInt(txtNumero.getText());
				Object sucursal = cbSucursal.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Operating_Room WHERE room_Number = ? AND id_Branch = ?;" );
					if(quirofanoEnUso(numero) != 0) {
						JOptionPane.showMessageDialog(null, "Quirófano está en uso, por favor elimine todos los registros relacionados");
					}else {
						ps.setInt(1, numero);
						ps.setString(2, ((ComboItem) sucursal).getValue());;
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Quirófano eliminado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar quirofano");
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
		btnEliminar.setBounds(250, 179, 89, 23);
		contentPane.add(btnEliminar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(295, 227, 89, 23);
		contentPane.add(btnVolver);
		
		lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(46, 123, 59, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(185, 119, 183, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
	}
}
package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Artefacto.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Tipo_Proveedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitular;
	private JTextField txtCuit;
	private JComboBox cbTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tipo_Proveedor frame = new Tipo_Proveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int existeTipo(String titular, String cuit) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Provider_Type WHERE owner = ? AND cuit = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, titular);
			pst.setString(2, cuit);

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
	
	public int tipoEnUso(String tipo, String cuit) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(Provider.id_Provider_Type)\r\n"
					+ "FROM Provider_Type\r\n"
					+ "JOIN Provider ON Provider.id_Provider_Type = Provider_Type.id_Provider_Type\r\n"
					+ "WHERE Provider_Type.type_Name LIKE ? AND Provider_Type.cuit LIKE ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, tipo);
			pst.setString(2, cuit);
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
		cbTipo.setSelectedIndex(0);
		txtTitular.setText("");
		txtCuit.setText("");
		
	}

	/**
	 * Create the frame.
	 */
	public Tipo_Proveedor() {
		setTitle("Tipo Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Tipo de proveedor");
		lblTitulo.setBounds(168, 11, 119, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblTitular = new JLabel("Titular");
		lblTitular.setBounds(61, 108, 46, 14);
		contentPane.add(lblTitular);
		
		txtTitular = new JTextField();
		txtTitular.setBounds(142, 105, 198, 20);
		contentPane.add(txtTitular);
		txtTitular.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(61, 163, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(142, 160, 198, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tipo = cbTipo.getSelectedItem().toString();
				String titular = txtTitular.getText();
				String cuit = txtCuit.getText();

				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Provider_Type (type_Name,owner, cuit) VALUES (?,?,?)" );
					
					
					
					if(existeTipo(titular,cuit)!=0) {
						JOptionPane.showMessageDialog(null, "Artefacto ya existe");
					}else {
						ps.setString(1, tipo);
						ps.setString(2, titular);
						ps.setString(3, cuit);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar tipo");
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
		btnAgregar.setBounds(55, 223, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = 0;
				String tipo = cbTipo.getSelectedItem().toString();
				String cuit = txtCuit.getText();

				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Provider_Type WHERE type_Name = ? AND cuit = ?;" );
					if(tipoEnUso(tipo,cuit) != 0) {
						JOptionPane.showMessageDialog(null, "Tipo está en uso, por favor elimine todos los registros relacionados");
					}else {
						ps.setString(1, tipo);
						ps.setString(2, cuit);;
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo eliminado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar tipo");
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
		btnEliminar.setBounds(251, 223, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(304, 271, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(61, 57, 46, 14);
		contentPane.add(lblTipo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Empresa", "Farmaceutico", "Autonomo"}));
		comboBox.setBounds(142, 53, 198, 22);
		contentPane.add(comboBox);
	}

}

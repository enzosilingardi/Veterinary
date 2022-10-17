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
import View.Instrumento_Quirofano.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Animal_Raza extends JFrame {

	private JPanel contentPane;
	private JComboBox cbAnimal;
	private JComboBox cbRaza;
	
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
	
	public DefaultComboBoxModel cargarAnimal() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Animal ORDER BY id_Animal";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));   
				
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
	
	public DefaultComboBoxModel cargarRaza() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Breed ORDER BY id_Breed";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Breed")));    
				
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
					Animal_Raza frame = new Animal_Raza();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public int existeRel(Object animal, Object raza) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Animal_Breed WHERE id_Animal = ? AND id_Breed = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) animal);
			pst.setString(2, (String) raza);
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
		cbAnimal.setSelectedIndex(0);
		cbRaza.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Animal_Raza() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar raza a animal");
		lblTitulo.setBounds(127, 11, 141, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setBounds(43, 58, 60, 14);
		contentPane.add(lblAnimal);
		
		cbAnimal = new JComboBox();
		cbAnimal.setBounds(113, 54, 168, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(43, 114, 46, 14);
		contentPane.add(lblRaza);
		
		cbRaza = new JComboBox();
		cbRaza.setBounds(113, 110, 168, 22);
		contentPane.add(cbRaza);
		cbRaza.setModel(cargarRaza());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Animal_Breed (id_Animal ,id_Breed) VALUES (?,?)" );
					
					
					if (((ComboItem) animal).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un animal");     
					}else {                                                                    //Revisa que los combobox no estén en blanco
						if(((ComboItem) raza).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una raza");
						}else {
							if(existeRel(((ComboItem) cbAnimal.getSelectedItem()).getValue(),((ComboItem) cbRaza.getSelectedItem()).getValue())!=0) {        //Revisa que no exista la relación
								JOptionPane.showMessageDialog(null, "Raza ya se encuentra asociada al animal");
							}else {
								ps.setString(1, ((ComboItem) animal).getValue());
								ps.setString(2, ((ComboItem) raza).getValue());
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Raza asociada");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al asociar raza");
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
		btnAgregar.setBounds(51, 174, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				
				int result = 0;
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Animal_Breed WHERE id_Animal = ? AND id_Breed = ?;" );
					ps.setString(1, ((ComboItem) animal).getValue());
					ps.setString(2, ((ComboItem) raza).getValue());
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Raza removida del animal");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al remover instrumento");
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
		btnEliminar.setBounds(192, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(253, 227, 89, 23);
		contentPane.add(btnVolver);
	}

}

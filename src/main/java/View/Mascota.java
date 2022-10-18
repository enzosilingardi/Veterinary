package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.Breed;
import View.Instrumento.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Mascota extends JFrame {

	private JPanel contentPane;
	private JComboBox cbDuenio;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbAnimal;
	private JComboBox cbRaza;

	
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
	

	
	public DefaultComboBoxModel cargarCliente() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Client ORDER BY id_Client";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name")+" - "+result.getString("dni"),result.getString("id_Client")));
				
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
			modelo.addElement(new ComboItem("Seleccionar animal",""));
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));   
				
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }
	
	public DefaultComboBoxModel cargarRaza(Object animal) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT *\r\n"
					+ "FROM Breed\r\n"
					+ "INNER JOIN Rel_Animal_Breed ON Rel_Animal_Breed.id_Breed = Breed.id_Breed\r\n"   //Toma solo las razas que están relacionadas al animal elegido
					+ "WHERE Rel_Animal_Breed.id_Animal = "+animal;
			pst = cn.prepareStatement(SSQL);
			
			result = pst.executeQuery();
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Breed")));    
				
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
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
					Mascota frame = new Mascota();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeMascota(Object duenio, String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Pet WHERE name = ? AND id_Client = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			pst.setString(2,(String) duenio);

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
		cbAnimal.setSelectedIndex(0);
		cbDuenio.setSelectedIndex(0);
		txtEdad.setText("");
		buttonGroup.clearSelection();
		cbRaza.setSelectedIndex(0);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Mascota() {
		setTitle("Mascota");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(45, 101, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Mascotas");
		lblNewLabel.setBounds(165, 11, 89, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(175, 98, 141, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo de Animal");
		lblTipo.setBounds(45, 154, 98, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(45, 205, 46, 14);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(175, 202, 141, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(45, 254, 46, 14);
		contentPane.add(lblGenero);
		
		final JRadioButton rdbtnMacho = new JRadioButton("Macho");
		buttonGroup.add(rdbtnMacho);
		rdbtnMacho.setBounds(152, 250, 67, 23);
		contentPane.add(rdbtnMacho);
		
		final JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		buttonGroup.add(rdbtnHembra);
		rdbtnHembra.setBounds(245, 250, 109, 23);
		contentPane.add(rdbtnHembra);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				String nombre = txtNombre.getText();
				Object duenio = cbDuenio.getSelectedItem();
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				int edad = Integer.parseInt(txtEdad.getText());
				String genero;
				if(rdbtnMacho.isSelected()) {
					genero = "Macho";
				} else if (rdbtnHembra.isSelected()) {
					genero = "Hembra";
				} else {
					genero = "Macho";
				}
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					
					
						ps = con.prepareStatement("INSERT INTO Pet (id_Client,name,id_Animal,age,gender,id_Breed ) VALUES (?,?,?,?,?,?)" );
						

					
					if (((ComboItem) duenio).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un cliente");
					}else {
						if(existeMascota(((ComboItem) cbDuenio.getSelectedItem()).getValue(),nombre)!=0) {
						JOptionPane.showMessageDialog(null, "Mascota ya existe");
					}else {
						ps.setString(1, ((ComboItem) duenio).getValue());
						ps.setString(2, nombre);
						ps.setString(3, ((ComboItem) animal).getValue());
						ps.setInt(4, edad);
						ps.setString(5, genero);
						ps.setString(6, ((ComboItem) raza).getValue());
					}
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Mascota guardada");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar mascota");
		                limpiar();
		            }
				
					con.close();
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAgregar.setBounds(54, 338, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(245, 338, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(289, 386, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDuenio = new JLabel("Dueño");
		lblDuenio.setBounds(45, 57, 46, 14);
		contentPane.add(lblDuenio);
		
		cbDuenio = new JComboBox();
		cbDuenio.setBounds(175, 53, 141, 22);
		contentPane.add(cbDuenio);
		cbDuenio.setModel(cargarCliente());
		
		JLabel lblRaza = new JLabel("Raza ");
		lblRaza.setBounds(45, 300, 98, 14);
		contentPane.add(lblRaza);
		
		cbAnimal = new JComboBox();
		cbAnimal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object animal = (ComboItem) cbAnimal.getSelectedItem();             // Revisa el estado del primer combobox y según este cambia el model del segundo
				cbRaza.setModel(cargarRaza(((ComboItem) animal).getValue()));
				
				
			}
		});
		cbAnimal.setBounds(175, 150, 141, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		cbRaza = new JComboBox();
		cbRaza.setBounds(175, 296, 141, 22);
		contentPane.add(cbRaza);
		
		
		JButton btnAnimales = new JButton("Animales");
		btnAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal animal = new Animal();
				animal.setVisible(true);
			}
		});
		btnAnimales.setBounds(326, 150, 89, 23);
		contentPane.add(btnAnimales);
		
		JButton btnRazas = new JButton("Razas");
		btnRazas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Raza raza = new Raza();
				raza.setVisible(true);
			}
		});
		btnRazas.setBounds(326, 296, 89, 23);
		contentPane.add(btnRazas);
	}
}
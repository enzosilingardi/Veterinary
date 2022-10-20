package View;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Mascota.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Mascota extends JFrame {

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
					Modificar_Mascota frame = new Modificar_Mascota();
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
	public Modificar_Mascota() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 71, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(164, 68, 141, 20);
		contentPane.add(txtNombre);
		
		JLabel lblTipo = new JLabel("Tipo de Animal");
		lblTipo.setBounds(34, 124, 98, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(34, 175, 46, 14);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(164, 172, 141, 20);
		contentPane.add(txtEdad);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(34, 224, 46, 14);
		contentPane.add(lblGenero);
		
		JRadioButton rdbtnMacho = new JRadioButton("Macho");
		rdbtnMacho.setBounds(141, 220, 67, 23);
		contentPane.add(rdbtnMacho);
		
		JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		rdbtnHembra.setBounds(234, 220, 109, 23);
		contentPane.add(rdbtnHembra);
		
		JLabel lblDuenio = new JLabel("Dueño");
		lblDuenio.setBounds(34, 27, 46, 14);
		contentPane.add(lblDuenio);
		
		cbDuenio = new JComboBox();
		cbDuenio.setBounds(164, 23, 141, 22);
		contentPane.add(cbDuenio);
		cbDuenio.setModel(cargarCliente());
		
		JLabel lblRaza = new JLabel("Raza ");
		lblRaza.setBounds(34, 270, 98, 14);
		contentPane.add(lblRaza);
		
		cbAnimal = new JComboBox();
		cbAnimal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object animal = (ComboItem) cbAnimal.getSelectedItem();             // Revisa el estado del primer combobox y según este cambia el model del segundo
				cbRaza.setModel(cargarRaza(((ComboItem) animal).getValue()));
				
				
			}
		});
		cbAnimal.setBounds(164, 120, 141, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		cbRaza = new JComboBox();
		cbRaza.setBounds(164, 266, 141, 22);
		contentPane.add(cbRaza);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota();
				tm.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(216, 328, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(34, 328, 89, 23);
		contentPane.add(btnModificar);
	}
}

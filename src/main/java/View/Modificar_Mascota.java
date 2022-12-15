package View;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;
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
	private JTextField txtNombre;
	private JTextField txtEdad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbAnimal;
	private JComboBox cbRaza;
	private JTextField txtId;
	private JRadioButton rdbtnHembra;
	private JRadioButton rdbtnMacho;
	private JTextField txtDue;
	private JTextField txtIdDue;
	private JDateChooser txtFecha;

	
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
	
	private void cargarCampos(String mascota) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(mascota);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT name, age FROM Pet WHERE id_Pet = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtNombre.setText(result.getString(1));
			txtEdad.setText(result.getString(2));
			
			
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
	public Modificar_Mascota(final String mascota,String id,String nom) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 479);
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
		lblEdad.setBounds(34, 234, 46, 14);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(164, 231, 141, 20);
		contentPane.add(txtEdad);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(34, 283, 46, 14);
		contentPane.add(lblGenero);
		
		final JRadioButton rdbtnMacho = new JRadioButton("Macho");
		rdbtnMacho.setBounds(141, 279, 67, 23);
		contentPane.add(rdbtnMacho);
		
		final JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		rdbtnHembra.setBounds(234, 279, 109, 23);
		contentPane.add(rdbtnHembra);
		
		JLabel lblDuenio = new JLabel("Dueño");
		lblDuenio.setBounds(34, 27, 46, 14);
		contentPane.add(lblDuenio);
		
		JLabel lblRaza = new JLabel("Raza ");
		lblRaza.setBounds(34, 329, 98, 14);
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
		cbRaza.setBounds(164, 325, 141, 22);
		contentPane.add(cbRaza);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota();
				tm.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(216, 387, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				int idDue = Integer.parseInt(txtIdDue.getText());
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
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
					
					
						ps = con.prepareStatement("UPDATE Pet SET id_Client = ?, name = ?, id_Animal = ?, age = ?, gender = ?, id_Breed = ?, birthdate = ? WHERE id_Pet = ?" );
						

					
					
						
						ps.setInt(1, idDue);
						ps.setString(2, nombre);
						ps.setString(3, ((ComboItem) animal).getValue());
						ps.setInt(4, edad);
						ps.setString(5, genero);
						ps.setString(6, ((ComboItem) raza).getValue());
						ps.setDate(7, date);
						ps.setInt(8, id);
					
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Mascota modificada");
		                ControlFiles.addContent("Se ha modificado la mascota "+nombre);
		                Tabla_Mascota tm = new Tabla_Mascota();
						tm.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar mascota");
		                
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
		btnModificar.setBounds(34, 387, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 0, 29, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(mascota);
		txtId.setText(mascota);
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setBounds(164, 24, 141, 20);
		contentPane.add(txtDue);
		txtDue.setColumns(10);
		txtDue.setText(nom);
		
		JButton btnSelec = new JButton("Seleccionar");
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Buscar_Cliente_ModMasc bcm = new Buscar_Cliente_ModMasc(mascota);
				bcm.setVisible(true);
				dispose();
			}
		});
		btnSelec.setBounds(315, 23, 114, 23);
		contentPane.add(btnSelec);
		
		txtIdDue = new JTextField();
		txtIdDue.setEditable(false);
		txtIdDue.setBounds(315, 0, 46, 20);
		contentPane.add(txtIdDue);
		txtIdDue.setColumns(10);
		txtIdDue.setVisible(false);
		txtIdDue.setText(id);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(164, 178, 141, 20);
		contentPane.add(txtFecha);
		
		JButton btnGen = new JButton("Generar edad");
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				
				LocalDate date = LocalDate.parse(fecha);
				LocalDate dateA = LocalDate.now(); 
				Period diff_anio = Period.between(date, dateA);
				txtEdad.setText(diff_anio.getYears()+"");
			}
		});
		btnGen.setBounds(315, 177, 114, 23);
		contentPane.add(btnGen);
		txtIdDue.setVisible(false);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setBounds(34, 181, 120, 14);
		contentPane.add(lblFecha);
	}

	public Modificar_Mascota(final String mascota) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 477);
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
		lblEdad.setBounds(34, 221, 46, 14);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setColumns(10);
		txtEdad.setBounds(164, 218, 141, 20);
		contentPane.add(txtEdad);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(34, 270, 46, 14);
		contentPane.add(lblGenero);
		
		final JRadioButton rdbtnMacho = new JRadioButton("Macho");
		rdbtnMacho.setBounds(141, 266, 67, 23);
		contentPane.add(rdbtnMacho);
		
		final JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		rdbtnHembra.setBounds(234, 266, 109, 23);
		contentPane.add(rdbtnHembra);
		
		JLabel lblDuenio = new JLabel("Dueño");
		lblDuenio.setBounds(34, 27, 46, 14);
		contentPane.add(lblDuenio);
		
		JLabel lblRaza = new JLabel("Raza ");
		lblRaza.setBounds(34, 316, 98, 14);
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
		cbRaza.setBounds(164, 312, 141, 22);
		contentPane.add(cbRaza);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota();
				tm.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(216, 374, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				int idDue = Integer.parseInt(txtIdDue.getText());
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
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
					
					
						ps = con.prepareStatement("UPDATE Pet SET id_Client = ?, name = ?, id_Animal = ?, age = ?, gender = ?, id_Breed = ?, birthdate = ? WHERE id_Pet = ?" );
						

					
					
						
						ps.setInt(1, idDue);
						ps.setString(2, nombre);
						ps.setString(3, ((ComboItem) animal).getValue());
						ps.setInt(4, edad);
						ps.setString(5, genero);
						ps.setString(6, ((ComboItem) raza).getValue());
						ps.setDate(7, date);
						ps.setInt(8, id);
					
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Mascota modificada");
		                ControlFiles.addContent("Se ha modificado la mascota "+nombre);
		                Tabla_Mascota tm = new Tabla_Mascota();
						tm.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar mascota");
		                
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
		btnModificar.setBounds(34, 374, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 0, 29, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		cargarCampos(mascota);
		txtId.setText(mascota);
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setBounds(164, 24, 141, 20);
		contentPane.add(txtDue);
		txtDue.setColumns(10);
		
		JButton btnSelec = new JButton("Seleccionar");
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente_ModMasc bcm = new Buscar_Cliente_ModMasc(mascota);
				bcm.setVisible(true);
				dispose();
			}
		});
		btnSelec.setBounds(315, 23, 114, 23);
		contentPane.add(btnSelec);
		
		txtIdDue = new JTextField();
		txtIdDue.setEditable(false);
		txtIdDue.setBounds(315, 0, 46, 20);
		contentPane.add(txtIdDue);
		txtIdDue.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setBounds(34, 172, 120, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(164, 169, 141, 20);
		contentPane.add(txtFecha);
		
		JButton btnGen = new JButton("Generar edad");
		btnGen.setBounds(315, 168, 114, 23);
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				
				LocalDate date = LocalDate.parse(fecha);
				LocalDate dateA = LocalDate.now(); 
				Period diff_anio = Period.between(date, dateA);
				txtEdad.setText(diff_anio.getYears()+"");
			}
		});
		contentPane.add(btnGen);
		txtIdDue.setVisible(false);
	}

	public Modificar_Mascota() {
		// TODO Auto-generated constructor stub
	}
}

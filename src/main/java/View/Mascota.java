package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.Breed;
import Model.ControlFiles;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Mascota extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEdad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbAnimal;
	private JComboBox cbRaza;
	private JTextField txtDuenio;
	private JTextField txtIdDue;
	private JDateChooser txtFecha;

	
	class ComboItem                    //Clase usada para armar el ComboBox
	{
	    private String key;              //Label visible del ComboBox
	    
	    private String value;           //Valor del ComboBox

	    public ComboItem(String key, String value)       //Genera el label que se verá en el ComboBox y el valor del objeto seleccionado
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
	

	
	public DefaultComboBoxModel cargarCliente() {                 //Este ComboBox no se utiliza en la versión actual
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
	
	public DefaultComboBoxModel cargarAnimal() {           //Carga el ComboBox animal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
		
		
		try {
			cn = (Connection) Connect.getConexion();        //Realiza la conexión
			
			String SSQL = "SELECT * FROM Animal ORDER BY id_Animal";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("Seleccionar animal",""));                //El primer elemento es "Seleccionar animal"
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));    //El elemento del ComboBox recibe el tipo de animal como label y el id como valor
				
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
	
	public DefaultComboBoxModel cargarRaza(Object animal) {                  //Carga el ComboBox raza recibiendo como parámetro el animal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();                 //Realiza la conexión
			String SSQL = "SELECT *\r\n"
					+ "FROM Breed\r\n"
					+ "INNER JOIN Rel_Animal_Breed ON Rel_Animal_Breed.id_Breed = Breed.id_Breed\r\n"   //Toma solo las razas que están relacionadas al animal elegido
					+ "WHERE Rel_Animal_Breed.id_Animal = "+animal;
			pst = cn.prepareStatement(SSQL);
			
			result = pst.executeQuery();
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Breed")));       //El elemento del ComboBox recibe el tipo de raza como label y el id como valor  
				
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

	public int existeMascota(int duenio, String nombre) {                   //Ese procedimiento determina si ya existe la mascota
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();        //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Pet WHERE name = ? AND id_Client = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			pst.setInt(2,duenio);

			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);          //Si la mascota existe, la variable se pone en 1
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
	
	
	
	private void limpiar() {                     //Ese procedimiento limpia los campos
		txtNombre.setText("");
		cbAnimal.setSelectedIndex(0);
		txtIdDue.setText("");
		txtDuenio.setText("");
		txtEdad.setText("");
		buttonGroup.clearSelection();
		cbRaza.setSelectedIndex(0);
		
	}
	
	/**
	 * Create the frame.
	 */ 
	public Mascota(String id, String nom) {                          //Crea la ventana recibiendo como parámetros en id y nombre de la mascota
		setTitle("Mascota");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));        //Setea el ícono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(45, 101, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Mascotas");
		lblNewLabel.setBounds(186, 11, 89, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(175, 98, 141, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo de Animal");
		lblTipo.setBounds(45, 154, 98, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(45, 256, 46, 14);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();                     
		txtEdad.setEditable(false);
		txtEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {                    //Impide que se inserten letras en el campo edad
					e.consume();
				}
			}
		});
		txtEdad.setBounds(175, 253, 141, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(45, 305, 46, 14);
		contentPane.add(lblGenero);
		
		final JRadioButton rdbtnMacho = new JRadioButton("Macho");
		buttonGroup.add(rdbtnMacho);
		rdbtnMacho.setBounds(152, 301, 67, 23);
		contentPane.add(rdbtnMacho);
		
		final JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		buttonGroup.add(rdbtnHembra);
		rdbtnHembra.setBounds(245, 301, 109, 23);
		contentPane.add(rdbtnHembra);
		
		JButton btnAgregar = new JButton("Agregar");               //Este botón permite agregar una mascota
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				String nombre = txtNombre.getText();
				int idDue = Integer.parseInt(txtIdDue.getText());
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				int edad = Integer.parseInt(txtEdad.getText());
				String genero;
				if(rdbtnMacho.isSelected()) {                     //Revisa el género seleccionado
					genero = "Macho";
				} else if (rdbtnHembra.isSelected()) {
					genero = "Hembra";
				} else {
					genero = "Macho";                 //En caso de no seleccionarse alguno, se coloca macho por defecto
				}
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();            //Realiza la conexión
					
					
						ps = con.prepareStatement("INSERT INTO Pet (id_Client,name,id_Animal,age,gender,id_Breed,birthdate ) VALUES (?,?,?,?,?,?,?)" );       
						

					
					
						if(existeMascota(idDue,nombre)!=0) {                        //Revisa si ya existe la mascota
						JOptionPane.showMessageDialog(null, "Mascota ya existe");
					}else {
						ps.setInt(1, idDue);
						ps.setString(2, nombre);
						ps.setString(3, ((ComboItem) animal).getValue());
						ps.setInt(4, edad);
						ps.setString(5, genero);
						ps.setString(6, ((ComboItem) raza).getValue());
						ps.setDate(7, date);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Mascota guardada");                       //Si es exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha añadido una mascota de nombre "+ nombre);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar mascota");        //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(165, 401, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");                 //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota();
				tm.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(289, 437, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDuenio = new JLabel("Dueño");
		lblDuenio.setBounds(45, 57, 46, 14);
		contentPane.add(lblDuenio);
		
		JLabel lblRaza = new JLabel("Raza ");
		lblRaza.setBounds(45, 351, 98, 14);
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
		cbRaza.setBounds(175, 347, 141, 22);
		contentPane.add(cbRaza);
		
		
		JButton btnAnimales = new JButton("Animales");              //Abre la ventana Animales
		btnAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal animal = new Animal();
				animal.setVisible(true);
			}
		});
		btnAnimales.setBounds(326, 150, 121, 23);
		contentPane.add(btnAnimales);
		
		JButton btnRazas = new JButton("Razas");                  //Abre la ventana Razas
		btnRazas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Raza raza = new Raza();
				raza.setVisible(true);
			}
		});
		btnRazas.setBounds(326, 347, 121, 23);
		contentPane.add(btnRazas);
		
		JButton btnSelec = new JButton("Seleccionar");                  //Este boton permite seleccionar un cliente
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente_Masc bcm = new Buscar_Cliente_Masc();
				bcm.setVisible(true);
				dispose();
			}
		});
		btnSelec.setBounds(326, 53, 121, 23);
		contentPane.add(btnSelec);
		
		txtDuenio = new JTextField();
		txtDuenio.setEditable(false);
		txtDuenio.setBounds(175, 54, 141, 20);
		contentPane.add(txtDuenio);
		txtDuenio.setColumns(10);
		
		txtDuenio.setText(nom);
		
		txtIdDue = new JTextField();
		txtIdDue.setEditable(false);
		txtIdDue.setBounds(326, 22, 27, 20);
		contentPane.add(txtIdDue);
		txtIdDue.setColumns(10);
		txtIdDue.setVisible(false);
		
		txtIdDue.setText(id);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(175, 201, 141, 20);
		contentPane.add(txtFecha);
		
		JButton btnGen = new JButton("Generar edad");             //Al presionarse calcula la edad de la mascota segun la fecha de nacimiento ingresada
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				
				LocalDate date = LocalDate.parse(fecha);
				LocalDate dateA = LocalDate.now(); 
				Period diff_anio = Period.between(date, dateA);
				txtEdad.setText(diff_anio.getYears()+"");               //Coloca la edad obtenida en el campo edad
			}
		});
		btnGen.setBounds(326, 200, 121, 23);
		contentPane.add(btnGen);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setBounds(45, 207, 120, 14);
		contentPane.add(lblFecha);
	}

	public Mascota() {                                //Crea la ventana
		setTitle("Mascota");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(45, 101, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Mascotas");
		lblNewLabel.setBounds(186, 11, 89, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(175, 98, 141, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo de Animal");
		lblTipo.setBounds(45, 154, 98, 14);
		contentPane.add(lblTipo);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(45, 256, 46, 14);
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {             //Impide que se inserten letras en el campo edad
					e.consume();
				}
			}
		});
		txtEdad.setBounds(175, 253, 141, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(45, 305, 46, 14);
		contentPane.add(lblGenero);
		
		final JRadioButton rdbtnMacho = new JRadioButton("Macho");
		buttonGroup.add(rdbtnMacho);
		rdbtnMacho.setBounds(152, 301, 67, 23);
		contentPane.add(rdbtnMacho);
		
		final JRadioButton rdbtnHembra = new JRadioButton("Hembra");
		buttonGroup.add(rdbtnHembra);
		rdbtnHembra.setBounds(245, 301, 109, 23);
		contentPane.add(rdbtnHembra);
		
		JButton btnAgregar = new JButton("Agregar");                 //Este boton permite agregar una mascota
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement ps = null;
				String nombre = txtNombre.getText();
				int idDue = Integer.parseInt(txtIdDue.getText());
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				int edad = Integer.parseInt(txtEdad.getText());
				String genero; 
				if(rdbtnMacho.isSelected()) {                     //Revisa el género seleccionado
					genero = "Macho";
				} else if (rdbtnHembra.isSelected()) {
					genero = "Hembra";
				} else {
					genero = "Macho";        //En caso de no seleccionarse alguno, se coloca macho por defecto
				}
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();          //Realiza la conexión
					
					
						ps = con.prepareStatement("INSERT INTO Pet (id_Client,name,id_Animal,age,gender,id_Breed,birthdate ) VALUES (?,?,?,?,?,?,?)" );
						

					
					
						if(existeMascota(idDue,nombre)!=0) {                          //Revisa si ya existe la mascota
						JOptionPane.showMessageDialog(null, "Mascota ya existe");
					}else {
						ps.setInt(1, idDue);
						ps.setString(2, nombre);
						ps.setString(3, ((ComboItem) animal).getValue());
						ps.setInt(4, edad);
						ps.setString(5, genero);
						ps.setString(6, ((ComboItem) raza).getValue());
						ps.setDate(7, date);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){                   
		                JOptionPane.showMessageDialog(null, "Mascota guardada");                    //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha añadido una mascota de nombre "+ nombre);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar mascota");          //En caso de fallar, LO avisa en pantalla
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
		btnAgregar.setBounds(165, 401, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota();
				tm.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(289, 437, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDuenio = new JLabel("Dueño");
		lblDuenio.setBounds(45, 57, 46, 14);
		contentPane.add(lblDuenio);
		
		JLabel lblRaza = new JLabel("Raza ");
		lblRaza.setBounds(45, 351, 98, 14);
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
		cbRaza.setBounds(175, 347, 141, 22);
		contentPane.add(cbRaza);
		
		
		JButton btnAnimales = new JButton("Animales");              //Abre la ventana Animal
		btnAnimales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal animal = new Animal();
				animal.setVisible(true);
			}
		});
		btnAnimales.setBounds(326, 150, 121, 23);
		contentPane.add(btnAnimales);
		
		JButton btnRazas = new JButton("Razas");                 //Abre la ventana Raza
		btnRazas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Raza raza = new Raza();
				raza.setVisible(true);
			}
		});
		btnRazas.setBounds(326, 347, 121, 23);
		contentPane.add(btnRazas);
		
		JButton btnSelec = new JButton("Seleccionar");               //Este boton permite seleccionar un cliente
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente_Masc bcm = new Buscar_Cliente_Masc();
				bcm.setVisible(true);
				dispose();
			}
		});
		btnSelec.setBounds(326, 53, 121, 23);
		contentPane.add(btnSelec);
		
		txtDuenio = new JTextField();
		txtDuenio.setEditable(false);
		txtDuenio.setBounds(175, 54, 141, 20);
		contentPane.add(txtDuenio);
		txtDuenio.setColumns(10);
		
		txtIdDue = new JTextField();
		txtIdDue.setEditable(false);
		txtIdDue.setBounds(326, 22, 27, 20);
		contentPane.add(txtIdDue);
		txtIdDue.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setBounds(45, 204, 121, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(175, 201, 141, 20);
		contentPane.add(txtFecha);
		
		JButton btnGen = new JButton("Generar edad");               //Al presionarse calcula la edad de la mascota segun la fecha de nacimiento ingresada
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();     
				
				LocalDate date = LocalDate.parse(fecha);
				LocalDate dateA = LocalDate.now(); 
				Period diff_anio = Period.between(date, dateA);
				txtEdad.setText(diff_anio.getYears()+"");               //Coloca la edad obtenida en el campo edad
			}
		});
		btnGen.setBounds(326, 200, 121, 23);
		contentPane.add(btnGen);
		txtIdDue.setVisible(false);
	}
}
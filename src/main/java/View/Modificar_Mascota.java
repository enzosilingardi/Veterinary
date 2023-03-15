package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
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

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Mascota;
import Model.ComboItem;
import Model.ControlFiles;


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
	public static JTextField txtNombre;
	public static JTextField txtEdad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbAnimal;
	private JComboBox cbRaza;
	private JTextField txtId;
	private JRadioButton rdbtnHembra;
	private JRadioButton rdbtnMacho;
	private JTextField txtDue;
	private JTextField txtIdDue;
	private JDateChooser txtFecha;



	
	public DefaultComboBoxModel cargarCliente() {            //Este ComboBox no es utilizado en la versión actual
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
		
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
		
		Control_ComboBoxes.CBAnimal(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarRaza(Object animal) {          //Carga el ComboBox raza recibiendo como parámetro el animal
		
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBRaza(modelo,animal);
		
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

	public int existeMascota(Object duenio, String nombre) {          //Ese procedimiento determina si ya existe la mascota
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Pet WHERE name = ? AND id_Client = ? ;";	//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			pst.setString(2,(String) duenio);

			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);                  //Si la mascota existe, la variable se pone en 1
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
	 * Create the frame.
	 */
	public Modificar_Mascota(final String mascota,String id,String nom,final String perfil) {          //Crea la ventana recibiendo por parámetro el id de la mascota, y el ide y el nombre del cliente
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));          //Setea el logo de la ventana

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
				Object animal = (ComboItem) cbAnimal.getSelectedItem();            // Revisa el estado del primer combobox y según este cambia el model del segundo
				cbRaza.setModel(cargarRaza(((ComboItem) cbAnimal.getSelectedItem()).getValue()));
				
				
			}
		});
		cbAnimal.setBounds(164, 120, 141, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		cbRaza = new JComboBox();
		cbRaza.setBounds(164, 325, 141, 22);
		contentPane.add(cbRaza);
		
		JButton btnVolver = new JButton("Volver");           // Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota(perfil);
				tm.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(216, 387, 89, 23);
		contentPane.add(btnVolver);
		  
		JButton btnModificar = new JButton("Modificar");            //Este botón modifica la mascota de acuerdo a los datos ingresados
		btnModificar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				int idDue = Integer.parseInt(txtIdDue.getText());
				
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				int edad = Integer.parseInt(txtEdad.getText());
				String genero;
				if(rdbtnMacho.isSelected()) {              //Revisa el genero seleccionado
					genero = "Macho";
				} else if (rdbtnHembra.isSelected()) {
					genero = "Hembra";
				} else {
					genero = "Macho";                  //En caso de no seleccionarse alguno, se coloca macho por defecto
				}
				
				Control_Mascota.modificar(idDue,nombre, ((ComboItem) cbAnimal.getSelectedItem()).getValue(), edad, genero, ((ComboItem) cbRaza.getSelectedItem()).getValue(),date, id);
				
		                Tabla_Mascota tm = new Tabla_Mascota(perfil);
						tm.setVisible(true);
						dispose();
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
		
		Control_Mascota.cargar(mascota);
		txtId.setText(mascota);
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setBounds(164, 24, 141, 20);
		contentPane.add(txtDue);
		txtDue.setColumns(10);
		txtDue.setText(nom);
		
		JButton btnSelec = new JButton("Seleccionar");           //Este botón permite seleccionar un cliente
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Buscar_Cliente_ModMasc bcm = new Buscar_Cliente_ModMasc(mascota,perfil);
				bcm.setVisible(true);		//Abre la ventana Buscar_Cliente_ModMasc recibiendo como parámetro el id de la mascota
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
		
		JButton btnGen = new JButton("Generar edad");           //Al presionarse calcula la edad de la mascota segun la fecha de nacimiento ingresada
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				
				LocalDate date = LocalDate.parse(fecha);
				LocalDate dateA = LocalDate.now(); 
				Period diff_anio = Period.between(date, dateA);
				txtEdad.setText(diff_anio.getYears()+"");            //Coloca la edad obtenida en el campo txtEdad
			}
		});
		btnGen.setBounds(315, 177, 114, 23);
		contentPane.add(btnGen);
		txtIdDue.setVisible(false);
		
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setBounds(34, 181, 120, 14);
		contentPane.add(lblFecha);
	}

	public Modificar_Mascota(final String mascota, final String perfil) {          //Crea la ventana recibiendo por parámetro el id de la mascota
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));           //Setea el icono de la ventana
		
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
				Object animal = (ComboItem) cbAnimal.getSelectedItem();            // Revisa el estado del primer combobox y según este cambia el model del segundo
				cbRaza.setModel(cargarRaza(((ComboItem) cbAnimal.getSelectedItem()).getValue()));
				
				
			}
		});
		cbAnimal.setBounds(164, 120, 141, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		cbRaza = new JComboBox();
		cbRaza.setBounds(164, 312, 141, 22);
		contentPane.add(cbRaza);
		
		JButton btnVolver = new JButton("Volver");                   //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota(perfil);
				tm.setVisible(true);		//Abre la ventana Tabla_Mascota
				dispose();
			}
		});
		btnVolver.setBounds(216, 374, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");                 //Este botón modifica la mascota de acuerdo a los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				int idDue = Integer.parseInt(txtIdDue.getText());
				
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				int edad = Integer.parseInt(txtEdad.getText());
				String genero;
				if(rdbtnMacho.isSelected()) {              //Revisa el genero seleccionado
					genero = "Macho";
				} else if (rdbtnHembra.isSelected()) {
					genero = "Hembra";
				} else {
					genero = "Macho";                  //En caso de no seleccionarse alguno, se coloca macho por defecto
				}
				
				Control_Mascota.modificar(idDue,nombre, ((ComboItem) cbAnimal.getSelectedItem()).getValue(), edad, genero, ((ComboItem) cbRaza.getSelectedItem()).getValue(),date, id);
				
		                Tabla_Mascota tm = new Tabla_Mascota(perfil);
						tm.setVisible(true);
						dispose();
		          
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
		
		Control_Mascota.cargar(mascota);
		txtId.setText(mascota);
		
		txtDue = new JTextField();
		txtDue.setEditable(false);
		txtDue.setBounds(164, 24, 141, 20);
		contentPane.add(txtDue);
		txtDue.setColumns(10);
		
		JButton btnSelec = new JButton("Seleccionar");                    //Este botón permite seleccionar un cliente
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente_ModMasc bcm = new Buscar_Cliente_ModMasc(mascota,perfil);
				bcm.setVisible(true);		//Abre la ventana Buscar_Cliente_ModMasc recibiendo como parámetro el id de la mascota
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
		
		JButton btnGen = new JButton("Generar edad");        //Al presionarse calcula la edad de la mascota segun la fecha de nacimiento ingresada
		btnGen.setBounds(315, 168, 114, 23);
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				
				LocalDate date = LocalDate.parse(fecha);
				LocalDate dateA = LocalDate.now(); 
				Period diff_anio = Period.between(date, dateA);
				txtEdad.setText(diff_anio.getYears()+"");             //Coloca la edad obtenida en el campo txtEdad
			}
		});
		contentPane.add(btnGen);
		txtIdDue.setVisible(false);
	}

	public Modificar_Mascota() {
		// TODO Auto-generated constructor stub
	}
}

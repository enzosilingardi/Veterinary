package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Control.Consulta_Veterinario;
import Model.ComboItem;
import Model.ControlFiles;

import javax.swing.JTextField;
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

public class Modificar_Veterinario extends JFrame {

	private JPanel contentPane;
	public static JTextField txtMatricula;
	public static JTextField txtApellido;
	public static JTextField txtNombre;
private JTextField txtId;
private JTextField txtDireccion;
	

	
	public DefaultComboBoxModel cargarDireccion() {      //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT *\r\n"
					+ "FROM Address\r\n"
					+ "INNER JOIN City ON Address.id_City = City.id_City";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number")+" - "+result.getString("name"),result.getString("id_Address")));
				
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
					Modificar_Veterinario frame = new Modificar_Veterinario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Modificar_Veterinario(String veterinario) {         //Crea la ventana recibiendo por parámetro el id del veterinario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(170, 187, 163, 20);
		contentPane.add(txtMatricula);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setBounds(51, 190, 57, 14);
		contentPane.add(lblMatricula);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(51, 140, 66, 14);
		contentPane.add(lblDireccion);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(170, 87, 163, 20);
		contentPane.add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(51, 90, 59, 14);
		contentPane.add(lblApellido);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(51, 39, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(170, 36, 163, 20);
		contentPane.add(txtNombre);
		
		JButton btnVolver = new JButton("Volver");                 //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Veterinario tv = new Tabla_Veterinario();
				tv.setVisible(true);		//Abre la ventana Tabla_Veterinario
				dispose();
			}
		});
		btnVolver.setBounds(228, 261, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");            //Este botón permite modificar el veterinario de acuerdo a los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String direccion = txtDireccion.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String matricula = txtMatricula.getText();
				
				Consulta_Veterinario.modificar(direccion, nombre, apellido, matricula, id);
				
		                Tabla_Veterinario tv = new Tabla_Veterinario();
						tv.setVisible(true);
						dispose();
		            
			}
		});
		btnModificar.setBounds(61, 261, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(51, 8, 37, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		Consulta_Veterinario.cargar(veterinario);
		txtId.setText(veterinario);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(170, 137, 173, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
	}

	public Modificar_Veterinario() {
		// TODO Auto-generated constructor stub
	}
}

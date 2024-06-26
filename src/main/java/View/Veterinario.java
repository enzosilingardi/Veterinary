package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Control.Control_Veterinario;
import Model.ComboItem;
import Model.ControlFiles;

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

public class Veterinario extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	

	
	public DefaultComboBoxModel cargarDireccion() {        //Este ComboBox no es utilizado en la versión actual
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
					Veterinario frame = new Veterinario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	private void limpiar() {         //Este procedimiento limpia los campos
		txtDireccion.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtMatricula.setText("");
		
	}
	
	/**
	 * Create the frame.
	 */
	public Veterinario() {             //Crea la ventana
		setTitle("Veterinario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));  //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Veterinarios");
		lblTitulo.setBounds(185, 11, 86, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setBounds(56, 213, 57, 14);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(175, 210, 163, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");            //Este botón permite agregar un veterinario
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String direccion = txtDireccion.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String matricula = txtMatricula.getText();
				
				Control_Veterinario.agregar(direccion, nombre, apellido, matricula);
				
				limpiar();
				
			}
		});
		btnAgregar.setBounds(148, 270, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Veterinario tv = new Tabla_Veterinario();
				tv.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(282, 317, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(56, 62, 59, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(175, 59, 163, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(175, 110, 163, 20);
		contentPane.add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(56, 113, 59, 14);
		contentPane.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(56, 163, 66, 14);
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(175, 160, 163, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
	}
}
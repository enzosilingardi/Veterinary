package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Control.Control_Quirofano;
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

public class Quirofano extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JButton btnAgregar;
	private JButton btnVolver;
	 

	
	public DefaultComboBoxModel cargarSucursal() {       //Este ComboBox no es utilizado en la versión actual
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


	
	
	
	private void limpiar() {         //Este procedimiento limpia los campos
		txtNumero.setText("");

		
	}
	/**
	 * Create the frame.
	 */
	public Quirofano(final String perfil) {            //Crea la ventana recibiendo como parámetro el perfil del usuario
		setTitle("Quirófano");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

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
		
		btnAgregar = new JButton("Agregar");                       //Este botón permite agregar un quirófano
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = Integer.parseInt(txtNumero.getText());

				Control_Quirofano.agregar(numero);
				
				limpiar();
				
			}
		});
		btnAgregar.setBounds(168, 142, 89, 23);
		contentPane.add(btnAgregar);
		
		btnVolver = new JButton("Volver");                   //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano(perfil);
				tq.setVisible(true);		//Abre la ventana Tabla_Quirofano
				dispose();
			}
		});
		btnVolver.setBounds(289, 190, 89, 23);
		contentPane.add(btnVolver);
	}




	public Quirofano() {
		// TODO Auto-generated constructor stub
	}
}
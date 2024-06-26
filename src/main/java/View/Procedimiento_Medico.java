package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Turno;
import Model.ComboItem;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class Procedimiento_Medico extends JFrame {

	private JPanel contentPane;
	private JComboBox cbTipo;
	private JDateChooser txtFecha;
	private JDateChooser txtHora;
	private JTextField txtMascota;
	private JTextField txtIdM;
	

	
	
	public DefaultComboBoxModel cargarMascota() {        //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT id_Pet,Pet.name as petN, Client.name as clientN, Client.surname as ClientS\r\n"
					+ "FROM Pet\r\n"
					+ "INNER JOIN Client ON Pet.id_Client = Client.id_Client\r\n"
					+ "ORDER BY id_Pet";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("petN")+" - Dueño: "+result.getString("clientN")+" "+result.getString("clientS"),result.getString("id_Pet")));
				
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
	
	public DefaultComboBoxModel cargarTipo() {            //Carga el ComboBox tipo
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBTipoProc(modelo);
		
		return modelo;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Procedimiento_Medico frame = new Procedimiento_Medico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	
	private void limpiar() {          //Este procedimiento limpia los campos
		cbTipo.setSelectedIndex(0);
		txtMascota.setText("");
		txtIdM.setText("");
		
	}
	/**
	 * Create the frame.
	 */
	public Procedimiento_Medico(String idMas, String nomMas) {       //Crea la table recibiendo como parámetros el id y el nombre de la mascota
		setTitle("Procedimientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Procedimientos");
		lblTitulo.setBounds(155, 11, 99, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(53, 53, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(53, 92, 77, 14);
		contentPane.add(lblMascota);
		
		JButton btnAgregar = new JButton("Agregar");           //Este boton permite agregar un turno
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object tipo = cbTipo.getSelectedItem();
				int idM = Integer.parseInt(txtIdM.getText());
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				String hora = ((JTextField) txtHora.getDateEditor().getUiComponent()).getText();
				Time start = Time.valueOf(hora);
				
				
					
					if (((ComboItem) tipo).getValue() == "") {                      //Revisa si el ComboBox está en blanco                    
						JOptionPane.showMessageDialog(null, "Seleccione un tipo");
					}else {
						
						Control_Turno.agregar(((ComboItem) cbTipo.getSelectedItem()).getValue(), idM, date, start);
						
							
						}
						
					limpiar();
					
					
				
			}
		});
		btnAgregar.setBounds(155, 240, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");              //Cierra la ventana
		btnVolver.setBounds(366, 292, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos();
				tt.setVisible(true);    // Abre la ventana Tabla_Turnos
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(155, 49, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(53, 134, 46, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(155, 131, 187, 20);
		contentPane.add(txtFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(53, 179, 92, 14);
		contentPane.add(lblHora);
		
		txtHora = new JDateChooser("HH:mm:ss", "##:##:##", '_');
		txtHora.getCalendarButton().setEnabled(false);
		txtHora.getCalendarButton().setVisible(false);
		txtHora.setBounds(155, 176, 99, 20);
		contentPane.add(txtHora);
		
		 
		JButton btnTipos = new JButton("Nuevo");               //Abre la ventana Tipo_Procedimiento
		btnTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Procedimiento tp = new Tipo_Procedimiento();
				tp.setVisible(true);
				dispose();
			}
		});
		btnTipos.setBounds(355, 49, 89, 23);
		contentPane.add(btnTipos);
		
		JButton btnBuscar = new JButton("Buscar");               //Este botón permite buscar una mascota
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota_Pro bmp = new Buscar_Mascota_Pro();
				bmp.setVisible(true);			//Abre la ventana Buscar_Mascota_Pro
				dispose();
			}
		});
		btnBuscar.setBounds(355, 88, 89, 23);
		contentPane.add(btnBuscar);
		
		txtMascota = new JTextField();
		txtMascota.setEditable(false);
		txtMascota.setBounds(155, 89, 187, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtIdM = new JTextField();
		txtIdM.setEditable(false);
		txtIdM.setVisible(false);
		txtIdM.setBounds(10, 8, 46, 20);
		contentPane.add(txtIdM);
		txtIdM.setColumns(10);
		
		txtIdM.setText(idMas);
		txtMascota.setText(nomMas);
		
	}
 
	public Procedimiento_Medico() {          //Crea la ventana
		setTitle("Procedimientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Procedimientos");
		lblTitulo.setBounds(155, 11, 99, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(53, 53, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(53, 92, 77, 14);
		contentPane.add(lblMascota);
		
		JButton btnAgregar = new JButton("Agregar");             //Este botón permite agregar un turno
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object tipo = cbTipo.getSelectedItem();
				int idM = Integer.parseInt(txtIdM.getText());
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				String hora = ((JTextField) txtHora.getDateEditor().getUiComponent()).getText();
				Time start = Time.valueOf(hora);
				
				
					
					if (((ComboItem) tipo).getValue() == "") {                      //Revisa si el ComboBox está en blanco                    
						JOptionPane.showMessageDialog(null, "Seleccione un tipo");
					}else {
						
						Control_Turno.agregar(((ComboItem) cbTipo.getSelectedItem()).getValue(), idM, date, start);
						
							
						}
						
					limpiar();
				
			}
		});
		btnAgregar.setBounds(155, 240, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.setBounds(366, 292, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos();
				tt.setVisible(true);		// Abre la ventana Tabla_Turnos
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(155, 49, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(53, 134, 46, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(155, 131, 187, 20);
		contentPane.add(txtFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(53, 179, 92, 14);
		contentPane.add(lblHora);
		
		txtHora = new JDateChooser("HH:mm:ss", "##:##:##", '_');
		txtHora.getCalendarButton().setEnabled(false);
		txtHora.getCalendarButton().setVisible(false);
		txtHora.setBounds(155, 176, 99, 20);
		contentPane.add(txtHora);
		
		
		JButton btnTipos = new JButton("Nuevo");                  //Abre la ventana Tipo_Procedimiento
		btnTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Procedimiento tp = new Tipo_Procedimiento();
				tp.setVisible(true);
				dispose();
			}
		});
		btnTipos.setBounds(355, 49, 89, 23);
		contentPane.add(btnTipos);
		
		JButton btnBuscar = new JButton("Buscar");               //Este botón permite buscar una mascota
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota_Pro bmp = new Buscar_Mascota_Pro();
				bmp.setVisible(true);		// Abre la ventana Buscar_Mascota_Pro
				dispose();
			}
		});
		btnBuscar.setBounds(355, 88, 89, 23);
		contentPane.add(btnBuscar);
		
		txtMascota = new JTextField();
		txtMascota.setEditable(false);
		txtMascota.setBounds(155, 89, 187, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtIdM = new JTextField();
		txtIdM.setEditable(false);
		txtIdM.setVisible(false);
		txtIdM.setBounds(10, 8, 46, 20);
		contentPane.add(txtIdM);
		txtIdM.setColumns(10);
	}
}

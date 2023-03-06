package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;
import View.Procedimiento_Medico.ComboItem;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Turno extends JFrame {

	private JPanel contentPane;
	private JComboBox cbTipo;
	private JDateChooser txtFecha;
	private JDateChooser txtHora;
	private JButton btnModificar;
	private JButton btnVolver;
	private JTextField txtId;
	private JTextField txtMascota;
	private JTextField txtIdM;
	private JButton btnBuscar;
	

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
	
	public DefaultComboBoxModel cargarMascota() {
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
	
	public DefaultComboBoxModel cargarTipo() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Procedure_Type ORDER BY id_Procedure_Type";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("proced_Name"),result.getString("id_Procedure_Type")));
				
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
					Modificar_Turno frame = new Modificar_Turno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargarCampos(String turno) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(turno);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT proced_Date, proced_Time FROM Medical_Procedure WHERE id_Procedure = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtFecha.setDate(result.getDate(1));
			txtHora.setDate(result.getTime(2));
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
	public Modificar_Turno(final String turno) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(137, 38, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(35, 81, 77, 14);
		contentPane.add(lblMascota);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(35, 42, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(35, 123, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(35, 168, 92, 14);
		contentPane.add(lblHora);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(137, 117, 187, 20);
		contentPane.add(txtFecha);
		
		txtHora = new JDateChooser("HH:mm:ss", "##:##:##", '_');
		txtHora.getCalendarButton().setEnabled(false);
		txtHora.getCalendarButton().setVisible(false);
		txtHora.setBounds(137, 162, 99, 20);
		contentPane.add(txtHora);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				Object tipo = cbTipo.getSelectedItem();
				int idM = Integer.parseInt(txtIdM.getText());
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				String hora = ((JTextField) txtHora.getDateEditor().getUiComponent()).getText();
				Time start = Time.valueOf(hora);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Medical_Procedure SET id_Procedure_Type = ?, id_Pet = ?, proced_Date = ?,proced_Time = ? WHERE id_Procedure = ?" );
					
					
					if (((ComboItem) tipo).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un tipo");
					}else {
						if(((ComboItem) tipo).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una mascota");
					}else {
						ps.setString(1, ((ComboItem) tipo).getValue());
						ps.setInt(2, idM);
						ps.setDate(3, date);
						ps.setTime(4, start);
						ps.setInt(5, id);
						
						
							
						}
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Turno modificado");
		                ControlFiles.addContent("Se ha modificado el turno para la fecha "+date+" y hora "+start);
		                Tabla_Turnos tt = new Tabla_Turnos();
						tt.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar turno");
		                
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(57, 230, 89, 23);
		contentPane.add(btnModificar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos();
				tt.setVisible(true);
				dispose();
			
			}
		});
		btnVolver.setBounds(211, 230, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 69, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		txtId.setText(turno);
		
		txtMascota = new JTextField();
		txtMascota.setEditable(false);
		txtMascota.setBounds(137, 78, 187, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtIdM = new JTextField();
		txtIdM.setEditable(false);
		txtIdM.setVisible(false);
		txtIdM.setBounds(270, 11, 86, 20);
		contentPane.add(txtIdM);
		txtIdM.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota_ModPro bmm = new Buscar_Mascota_ModPro(turno);
				bmm.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(334, 77, 89, 23);
		contentPane.add(btnBuscar);
		cargarCampos(turno);
	}

	public Modificar_Turno(final String turno, String idMas, String nomMas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(137, 38, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(35, 81, 77, 14);
		contentPane.add(lblMascota);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(35, 42, 46, 14);
		contentPane.add(lblTipo);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(35, 123, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(35, 168, 92, 14);
		contentPane.add(lblHora);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(137, 117, 187, 20);
		contentPane.add(txtFecha);
		
		txtHora = new JDateChooser("HH:mm:ss", "##:##:##", '_');
		txtHora.getCalendarButton().setEnabled(false);
		txtHora.getCalendarButton().setVisible(false);
		txtHora.setBounds(137, 162, 99, 20);
		contentPane.add(txtHora);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				Object tipo = cbTipo.getSelectedItem();
				int idM = Integer.parseInt(txtIdM.getText());
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				String hora = ((JTextField) txtHora.getDateEditor().getUiComponent()).getText();
				Time start = Time.valueOf(hora);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Medical_Procedure SET id_Procedure_Type = ?, id_Pet = ?, proced_Date = ?,proced_Time = ? WHERE id_Procedure = ?" );
					
					
					if (((ComboItem) tipo).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un tipo");
					}else {
						if(((ComboItem) tipo).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una mascota");
					}else {
						ps.setString(1, ((ComboItem) tipo).getValue());
						ps.setInt(2, idM);
						ps.setDate(3, date);
						ps.setTime(4, start);
						ps.setInt(5, id);
						
						
							
						}
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Turno modificado");
		                ControlFiles.addContent("Se ha modificado el turno para la fecha "+date+" y hora "+start);
		                Tabla_Turnos tt = new Tabla_Turnos();
						tt.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar turno");
		                
		            }
				
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(57, 230, 89, 23);
		contentPane.add(btnModificar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos();
				tt.setVisible(true);
				dispose();
			
			}
		});
		btnVolver.setBounds(211, 230, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 69, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		txtId.setText(turno);
		
		txtMascota = new JTextField();
		txtMascota.setEditable(false);
		txtMascota.setBounds(137, 78, 187, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		txtIdM = new JTextField();
		txtIdM.setEditable(false);
		txtIdM.setVisible(false);
		txtIdM.setBounds(270, 11, 86, 20);
		contentPane.add(txtIdM);
		txtIdM.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota_ModPro bmm = new Buscar_Mascota_ModPro(turno);
				bmm.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(334, 77, 89, 23);
		contentPane.add(btnBuscar);
		cargarCampos(turno);
		
		txtIdM.setText(idMas);
		txtMascota.setText(nomMas);
	}

	public Modificar_Turno() {
		// TODO Auto-generated constructor stub
	}

}

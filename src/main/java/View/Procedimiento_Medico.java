package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import View.Historial_Medico.ComboItem;

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
	private JComboBox cbMascota;
	private JDateChooser txtFecha;
	private JDateChooser txtInicio;
	private JDateChooser txtFin;
	

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
					Procedimiento_Medico frame = new Procedimiento_Medico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeFecha(Date date) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Medical_Procedure WHERE proced_Date = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setDate(1,date);
			
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
	
	public boolean existeTurno(Date date,Time start, Time end) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT CONVERT(varchar,proced_Start,8), CONVERT(varchar,proced_End,8) FROM Medical_Procedure WHERE proced_Date = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setDate(1,date);
			
			result = pst.executeQuery();
			
			while (result.next()) {
				if(start.after(result.getTime(1)) && start.before(result.getTime(2))){
					return true;
				}else {
					if(end.after(result.getTime(1)) && end.before(result.getTime(2))){
						return true;
					} else {
						return false;
					}
				}
			}
			
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e);
			
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rootPaneCheckingEnabled;
		
		
		
	}
	
	private void limpiar() {
		cbTipo.setSelectedIndex(0);
		cbMascota.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Procedimiento_Medico() {
		setTitle("Procedimientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object tipo = cbTipo.getSelectedItem();
				Object mascota = cbMascota.getSelectedItem();
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				String inicio = ((JTextField) txtInicio.getDateEditor().getUiComponent()).getText();
				String fin = ((JTextField) txtFin.getDateEditor().getUiComponent()).getText();
				Time start = Time.valueOf(inicio);
				Time end = Time.valueOf(fin);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_Procedure (id_Procedure_Type, id_Pet, proced_Date,proced_Start, proced_End) VALUES (?,?,?,?,?)" );
					
					
					if (((ComboItem) tipo).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un tipo");
					}else {
						if(((ComboItem) tipo).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una mascota");
					}else {
						ps.setString(1, ((ComboItem) tipo).getValue());
						ps.setString(2, ((ComboItem) mascota).getValue());
						
						if(existeFecha(date)!=0) {
							if(existeTurno(date,start,end)) {
								
								JOptionPane.showMessageDialog(null, "Turno ocupado");
							}else {
								ps.setDate(3, date);
								ps.setTime(4, start);
								ps.setTime(5, end);
							}
						}
						
					}
						
					}
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Turno guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar turno");
		                limpiar();
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
		btnAgregar.setBounds(86, 244, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(225, 244, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(286, 292, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(155, 49, 187, 22);
		contentPane.add(cbTipo);
		cbTipo.setModel(cargarTipo());
		
		cbMascota = new JComboBox();
		cbMascota.setBounds(155, 88, 187, 22);
		contentPane.add(cbMascota);
		cbMascota.setModel(cargarMascota());
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(53, 134, 46, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(155, 131, 187, 20);
		contentPane.add(txtFecha);
		
		JLabel lblHora = new JLabel("Hora inicio y fin");
		lblHora.setBounds(53, 179, 92, 14);
		contentPane.add(lblHora);
		
		txtInicio = new JDateChooser("HH:mm:ss", "##:##:##", '_');
		txtInicio.getCalendarButton().setEnabled(false);
		txtInicio.getCalendarButton().setVisible(false);
		txtInicio.setBounds(155, 176, 89, 20);
		contentPane.add(txtInicio);
		
		txtFin = new JDateChooser("HH:mm:ss", "##:##:##", '_');
		txtFin.getCalendarButton().setEnabled(false);
		txtFin.getCalendarButton().setVisible(false);
		txtFin.setBounds(254, 176, 86, 20);
		contentPane.add(txtFin);
	}
}

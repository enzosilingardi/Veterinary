package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;
import View.Historial_Medico.ComboItem;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Historial extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JComboBox cbMascota;
	private JDateChooser txtFecha;
	private JTextField txtId;
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Historial frame = new Modificar_Historial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void cargarCampos(String historial) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(historial);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT description, date\r\n"
					+ "FROM Medical_History\r\n"
					+ "WHERE id_Medical_History = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtDescripcion.setText(result.getString(1));
			txtFecha.setDate(result.getDate(2));
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
	public Modificar_Historial(String historial) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbMascota = new JComboBox();
		cbMascota.setBounds(149, 33, 206, 22);
		contentPane.add(cbMascota);
		cbMascota.setModel(cargarMascota());
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(59, 37, 77, 14);
		contentPane.add(lblMascota);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(59, 94, 77, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(147, 91, 208, 20);
		contentPane.add(txtDescripcion);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(149, 146, 206, 20);
		contentPane.add(txtFecha);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(59, 149, 53, 14);
		contentPane.add(lblFecha);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String descripcion = txtDescripcion.getText();
				Object mascota = cbMascota.getSelectedItem();
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Medical_History SET id_Pet = ?, description = ?, date = ? WHERE id_Medical_History = ?" );
					
					
					if (((ComboItem) mascota).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una mascota");
					}else {
					
						ps.setString(1, ((ComboItem) mascota).getValue());
						ps.setString(2, descripcion);
						ps.setDate(3, date);
						ps.setInt(4, id);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Historial guardado");
		                ControlFiles.addContent("Se ha modificado un historial de la mascota "+mascota);
		                Tabla_Historial th = new Tabla_Historial();
						th.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar historial");
		                
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
		btnModificar.setBounds(99, 209, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial();
				th.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(239, 209, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 21, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		txtId.setText(historial);
		
		cargarCampos(historial);
		
	}

	public Modificar_Historial() {
		// TODO Auto-generated constructor stub
	}
}

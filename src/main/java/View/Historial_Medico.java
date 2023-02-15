package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;
import View.Instrumento_Quirofano.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Historial_Medico extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JDateChooser txtFecha;
	private JTextField txtMascota;
	private JTextField txtIdM;

	
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
					Historial_Medico frame = new Historial_Medico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeHistorial(Object mascota) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Medical_History WHERE id_Pet = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) mascota);
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
	
	
	
	
	private void limpiar() {
		txtDescripcion.setText("");
		txtMascota.setText("");
		txtIdM.setText("");
		
	}
	
	/**
	 * Create the frame.
	 */
	public Historial_Medico(final String perfil, String idMas, String nomMas) {
		setTitle("Historial Médico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 519, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Historiales médicos");
		lblTitulo.setBounds(164, 11, 119, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(74, 64, 77, 14);
		contentPane.add(lblMascota);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				String mascota = txtMascota.getText();
				int idM = Integer.parseInt(txtIdM.getText());
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_History (id_Pet,description,date) VALUES (?,?,?)" );
					
					
				
					
						ps.setInt(1, idM);
						ps.setString(2, descripcion);
						ps.setDate(3, date);
					
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Historial guardado");
		                ControlFiles.addContent("Se ha añadido un historial para la mascota "+mascota);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar historial");
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
		btnAgregar.setBounds(164, 259, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial(perfil);
				th.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(335, 311, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(74, 121, 77, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(162, 118, 208, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(74, 191, 53, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(164, 188, 206, 20);
		contentPane.add(txtFecha);
		
		txtMascota = new JTextField();
		txtMascota.setEditable(false);
		txtMascota.setBounds(164, 61, 206, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota_Hist bmh = new Buscar_Mascota_Hist(perfil);
				bmh.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(380, 60, 104, 23);
		contentPane.add(btnNewButton);
		
		txtIdM = new JTextField();
		txtIdM.setEditable(false);
		txtIdM.setBounds(380, 27, 86, 20);
		contentPane.add(txtIdM);
		txtIdM.setColumns(10);
		txtIdM.setVisible(false);
		
		txtIdM.setText(idMas);
		txtMascota.setText(nomMas);
	}
	public Historial_Medico(final String perfil) {
		setTitle("Historial Médico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 519, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Historiales médicos");
		lblTitulo.setBounds(164, 11, 119, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(74, 64, 77, 14);
		contentPane.add(lblMascota);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				String mascota = txtMascota.getText();
				int idM = Integer.parseInt(txtIdM.getText());
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date date = Date.valueOf(fecha);
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_History (id_Pet,description,date) VALUES (?,?,?)" );
					
					
				
					
						ps.setInt(1, idM);
						ps.setString(2, descripcion);
						ps.setDate(3, date);
					
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Historial guardado");
		                ControlFiles.addContent("Se ha añadido un historial para la mascota "+mascota);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar historial");
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
		btnAgregar.setBounds(164, 259, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial(perfil);
				th.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(335, 311, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(74, 121, 77, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(162, 118, 208, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(74, 191, 53, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(164, 188, 206, 20);
		contentPane.add(txtFecha);
		
		txtMascota = new JTextField();
		txtMascota.setEditable(false);
		txtMascota.setBounds(164, 61, 206, 20);
		contentPane.add(txtMascota);
		txtMascota.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota_Hist bmh = new Buscar_Mascota_Hist(perfil);
				bmh.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(380, 60, 104, 23);
		contentPane.add(btnNewButton);
		
		txtIdM = new JTextField();
		txtIdM.setEditable(false);
		txtIdM.setBounds(380, 27, 86, 20);
		contentPane.add(txtIdM);
		txtIdM.setColumns(10);
		txtIdM.setVisible(false);
	}
	public Historial_Medico() {
		// TODO Auto-generated constructor stub
	}
}

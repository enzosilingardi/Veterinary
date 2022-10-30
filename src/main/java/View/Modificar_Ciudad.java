package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Ciudad.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modificar_Ciudad extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtId;
	private JComboBox cbProvincias;
	private JButton btnModificar;
	private JButton btnVolver;
	
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
	
	public DefaultComboBoxModel cargarProvincia() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Province ORDER BY id_Province";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name"),result.getString("id_Country")));
				
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
					Modificar_Ciudad frame = new Modificar_Ciudad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	private void cargarCampos(String ciudad) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(ciudad);
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT name FROM City WHERE id_City = ?";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){
			txtNombre.setText(result.getString(1));
			
			
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
	public Modificar_Ciudad(String ciudad, final String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 32, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(39, 80, 79, 14);
		contentPane.add(lblProvincia);
		
		cbProvincias = new JComboBox();
		cbProvincias.setBounds(128, 76, 179, 22);
		contentPane.add(cbProvincias);
		cbProvincias.setModel(cargarProvincia());
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(128, 29, 179, 20);
		contentPane.add(txtNombre);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 11, 22, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				Object provincia = cbProvincias.getSelectedItem();
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE City SET name = ?, id_Province = ? WHERE id_City = ?" );
					
					
					if (((ComboItem) provincia).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione una provincia");
					}else {
						
						ps.setString(1, nombre);
						ps.setString(2, ((ComboItem) provincia).getValue());
						ps.setInt(3, id);
					}
						
					
					
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Ciudad modificada");
		                Ciudad ciudad = new Ciudad(perfil);
						ciudad.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar ciudad");
		               
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(63, 135, 89, 23);
		contentPane.add(btnModificar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ciudad ciudad = new Ciudad(perfil);
				ciudad.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(215, 135, 89, 23);
		contentPane.add(btnVolver);
		
		txtId.setVisible(false);
		
		cargarCampos(ciudad);
		txtId.setText(ciudad);
		
	}


	public Modificar_Ciudad() {
		// TODO Auto-generated constructor stub
	}

}

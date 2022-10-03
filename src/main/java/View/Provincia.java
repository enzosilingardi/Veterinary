package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Provincia extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JComboBox cbPaises;

	
	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)
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
	
	public void consultarPaises(JComboBox paises) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		String SSQL = "SELECT * FROM Country ORDER BY id_Country";
		
		try {
			cn = (Connection) Connect.getConexion();
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			paises.addItem(new ComboItem(result.getString("name"),result.getString("id_Country")));
			
			while (result.next()) {
				paises.addItem(new ComboItem(result.getString("name"),result.getString("id_Country")));
				
			}
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		

	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Provincia frame = new Provincia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiar() {
		txtNombre.setText("");
		cbPaises.setSelectedIndex(0);
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public Provincia() {
		setTitle("Provincia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Provincias");
		lblTitulo.setBounds(184, 11, 77, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(84, 61, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(173, 58, 179, 20);
		contentPane.add(txtNombre);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				int pais = (Integer) cbPaises.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Province (name,id_Country) VALUES (?,?)" );
					ps.setString(1, nombre);
					ps.setInt(2, pais);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Provincia guardada");
					limpiar();
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(82, 166, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				int pais = (Integer) cbPaises.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Province WHERE name=? AND id_Country=?)" );
					ps.setString(1, nombre);
					ps.setInt(2, pais);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Provincia borrada");
					limpiar();
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEliminar.setBounds(239, 166, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(316, 210, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblPais = new JLabel("País");
		lblPais.setBounds(84, 114, 46, 14);
		contentPane.add(lblPais);
		
		JComboBox cbPaises = new JComboBox();
		cbPaises.setBounds(173, 110, 179, 22);
		contentPane.add(cbPaises);
		
		consultarPaises(cbPaises);
		
	}

}
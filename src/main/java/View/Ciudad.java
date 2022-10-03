package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Provincia.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Ciudad extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JComboBox cbProvincias;
	
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
	
	public void consultarProvincias(JComboBox provincias) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		String SSQL = "SELECT * FROM Province ORDER BY id_Province";
		
		try {
			cn = (Connection) Connect.getConexion();
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			provincias.addItem(new ComboItem(result.getString("name"),result.getString("id_Province")));
			
			while (result.next()) {
				provincias.addItem(new ComboItem(result.getString("name"),result.getString("id_Province")));
				
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
					Ciudad frame = new Ciudad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void limpiar() {
		txtNombre.setText("");
		cbProvincias.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Ciudad() {
		setTitle("Ciudad");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Ciudades");
		lblTitulo.setBounds(182, 11, 65, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(73, 56, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(162, 53, 179, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				int provincia = (Integer) cbProvincias.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO City (name,id_Province) VALUES (?,?)" );
					ps.setString(1, nombre);
					ps.setInt(2, provincia);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Ciudad guardada");
					limpiar();
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAgregar.setBounds(88, 158, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(313, 206, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				int pais = (Integer) cbProvincias.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM City WHERE name=? AND id_Province=?)" );
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
		btnEliminar.setBounds(229, 158, 89, 23);
		contentPane.add(btnEliminar);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(73, 104, 46, 14);
		contentPane.add(lblProvincia);
		
		JComboBox cbProvincias = new JComboBox();
		cbProvincias.setBounds(162, 100, 179, 22);
		contentPane.add(cbProvincias);
		
		consultarProvincias(cbProvincias);
	}
}
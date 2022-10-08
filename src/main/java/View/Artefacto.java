package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Quirofano.ComboItem;

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

public class Artefacto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JComboBox cbQuirofano;

	
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
	
	public DefaultComboBoxModel cargarQuirofano() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Operating_Room  ORDER BY id_Operating_Room";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("room_Number"),result.getString("id_Operating_Room")));
				
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
					Artefacto frame = new Artefacto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeArtefacto(Object quirofano, String nombre) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Medical_Instrument WHERE name = ? AND id_Operating_Room = ? ;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			pst.setString(2,(String) quirofano);

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
		txtNombre.setText("");
		txtDescripcion.setText("");
		cbQuirofano.setSelectedIndex(0);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Artefacto() {
		setTitle("Artefacto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Artefactos");
		lblTitulo.setBounds(195, 11, 80, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(75, 65, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(163, 62, 184, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(75, 115, 78, 14);
		contentPane.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(163, 112, 184, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblQuirofano = new JLabel("Quirofano");
		lblQuirofano.setBounds(75, 165, 70, 14);
		contentPane.add(lblQuirofano);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				Object quirofano = cbQuirofano.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Medical_Instrument (id_Operating_Room, instrument_Name, instrument_Description) VALUES (?,?,?)" );
					
					
					if (((ComboItem) quirofano).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un quirófano");
					}else {
						if(existeArtefacto(((ComboItem) cbQuirofano.getSelectedItem()).getValue(),nombre)!=0) {
						JOptionPane.showMessageDialog(null, "Artefacto ya existe");
					}else {
						ps.setString(2, nombre);
						ps.setString(3, descripcion);
						ps.setString(1, ((ComboItem) quirofano).getValue());
					}
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Artefacto guardado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar artefacto");
		                limpiar();
		            }
				
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(46, 227, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();
				Object quirofano = cbQuirofano.getSelectedItem();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Instrument WHERE instrument_Name = ? AND id_Operating_Room = ?" );
					ps.setString(1, nombre);
					ps.setString(2, ((ComboItem) quirofano).getValue());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Artefacto borrado");
					limpiar();
					
				}catch(SQLException E) {
					JOptionPane.showMessageDialog(null,E);
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setBounds(284, 227, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(315, 275, 89, 23);
		contentPane.add(btnVolver);
		
		cbQuirofano = new JComboBox();
		cbQuirofano.setBounds(163, 161, 184, 22);
		contentPane.add(cbQuirofano);
		cbQuirofano.setModel(cargarQuirofano());
	}

}

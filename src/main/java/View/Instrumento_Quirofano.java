package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import View.Ciudad.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Instrumento_Quirofano extends JFrame {

	private JPanel contentPane;
	private JComboBox cbQuirofano;
	private JComboBox cbInstrumento;

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
	
	public DefaultComboBoxModel cargarQuirofano() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Operating_Room ORDER BY id_Operating_Room";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
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
	
	public DefaultComboBoxModel cargarInstrumento() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Medical_Instrument ORDER BY id_Medical_Instrument";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("instrument_Name"),result.getString("id_Medical_Instrument")));
				
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
					Instrumento_Quirofano frame = new Instrumento_Quirofano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object quirofano, Object instrumento) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Operating_R_Medical_I WHERE id_Operating_Room = ? AND id_Medical_Instrument = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) quirofano);
			pst.setString(2, (String) instrumento);
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
		cbQuirofano.setSelectedIndex(0);
		cbInstrumento.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Instrumento_Quirofano() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 383, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuirofano = new JLabel("Quirófano");
		lblQuirofano.setBounds(39, 63, 77, 14);
		contentPane.add(lblQuirofano);
		
		cbQuirofano = new JComboBox();
		cbQuirofano.setBounds(126, 59, 160, 22);
		contentPane.add(cbQuirofano);
		cbQuirofano.setModel(cargarQuirofano());
		
		JLabel lblInstrumento = new JLabel("Instrumento");
		lblInstrumento.setBounds(39, 115, 77, 14);
		contentPane.add(lblInstrumento);
		
		cbInstrumento = new JComboBox();
		cbInstrumento.setBounds(126, 111, 160, 22);
		contentPane.add(cbInstrumento);
		cbInstrumento.setModel(cargarInstrumento());
		
		JLabel lblTitulo = new JLabel("Agregar instrumento a quirófano");
		lblTitulo.setBounds(111, 11, 193, 14);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnVolver.setBounds(268, 227, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object quirofano = cbQuirofano.getSelectedItem();
				Object instrumento = cbInstrumento.getSelectedItem();
				
				int result = 0;
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Operating_R_Medical_I WHERE id_Operating_Room = ? AND id_Medical_Instrument = ?;" );
					ps.setString(1, ((ComboItem) quirofano).getValue());
					ps.setString(2, ((ComboItem) instrumento).getValue());
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento removido del quirófano");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al remover instrumento");
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
		btnEliminar.setBounds(207, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object quirofano = cbQuirofano.getSelectedItem();
				Object instrumento = cbInstrumento.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Operating_R_Medical_I (id_Operating_Room,id_Medical_Instrument) VALUES (?,?)" );
					
					
					if (((ComboItem) quirofano).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un quirófano");
					}else {
						if(((ComboItem) instrumento).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione un instrumento");
						}else {
							if(existeRel(((ComboItem) cbQuirofano.getSelectedItem()).getValue(),((ComboItem) cbInstrumento.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Instrumento ya se encuentra en el quirófano");
							}else {
								ps.setString(1, ((ComboItem) quirofano).getValue());
								ps.setString(2, ((ComboItem) instrumento).getValue());
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento colocado");
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar instrumento");
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
		btnAgregar.setBounds(66, 174, 89, 23);
		contentPane.add(btnAgregar);
	}
}

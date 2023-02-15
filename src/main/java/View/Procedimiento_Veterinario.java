package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Model.ControlFiles;
import View.Veterinario_Sucursal.ComboItem;

public class Procedimiento_Veterinario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbProcedimiento;
	private JComboBox cbVeterinario;

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
	

	public DefaultComboBoxModel cargarVeterinario() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "Select *"
					+ "FROM Veterinarian";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name")+" "+result.getString("surname"),result.getString("id_Veterinarian")));
				
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
	

	public DefaultComboBoxModel cargarProcedimiento() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT DISTINCT id_Procedure, name, proced_Name, CONVERT(varchar(10),proced_Date,103) as pd,CONVERT(varchar(10),proced_Time,8) as pt\r\n"
					+ "FROM Medical_Procedure\r\n"
					+ "INNER JOIN Pet ON Pet.id_Pet = Medical_Procedure.id_Pet\r\n"
					+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
					+ "INNER JOIN Medical_History ON Medical_History.id_Pet = Pet.id_Pet";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name")+" - "+result.getString("proced_Name")+" "+result.getString("pd")+" "+result.getString("pt"),result.getString("id_Procedure")));
				
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
	

	void mostrarTabla(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Veterinario","Procedimiento"});
       
        table.setModel(modelo);
        
        
        
        String datos[] = new String[3];
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT id_VMP ,name, surname, proced_Name,CONVERT(varchar(10),proced_Date,103) AS pd ,CONVERT(varchar(10),proced_Time,8) as pt\r\n"
        			+ "FROM Rel_Veterinarian_Medical_P\r\n"
        			+ "INNER JOIN Medical_Procedure ON Medical_Procedure.id_Procedure = Rel_Veterinarian_Medical_P.id_Procedure\r\n"
        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
        			+ "INNER JOIN Veterinarian ON Veterinarian.id_Veterinarian = Rel_Veterinarian_Medical_P.id_Veterinarian;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
            	datos[0] = rs.getString(1);
                datos[1] = rs.getString(2)+" "+rs.getString(3);
                datos[2] = rs.getString(4)+" - "+rs.getString(5)+" "+rs.getString(6);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);

            table.getColumnModel().getColumn(0).setMaxWidth(0);
    		table.getColumnModel().getColumn(0).setMinWidth(0);
    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
    		table.getColumnModel().getColumn(0).setResizable(false);
        } catch(SQLException E) {
			JOptionPane.showMessageDialog(null,E);
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
					Procedimiento_Veterinario frame = new Procedimiento_Veterinario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object procedimiento, Object veterinario) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Veterinarian_Medical_P WHERE id_Procedure = ? AND id_Veterinarian = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) procedimiento);
			pst.setString(2, (String) veterinario);
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
		cbProcedimiento.setSelectedIndex(0);
		cbVeterinario.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Procedimiento_Veterinario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar veterinario a procedimiento");
		lblTitulo.setBounds(108, 11, 224, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProcedimiento = new JLabel("Procedimiento");
		lblProcedimiento.setBounds(430, 53, 92, 14);
		contentPane.add(lblProcedimiento);
		
		cbProcedimiento = new JComboBox();
		cbProcedimiento.setBounds(532, 49, 192, 22);
		contentPane.add(cbProcedimiento);
		cbProcedimiento.setModel(cargarProcedimiento());
		
		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setBounds(430, 107, 92, 14);
		contentPane.add(lblVeterinario);
		
		cbVeterinario = new JComboBox();
		cbVeterinario.setBounds(532, 103, 192, 22);
		contentPane.add(cbVeterinario);
		cbVeterinario.setModel(cargarVeterinario());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object proced = cbProcedimiento.getSelectedItem();
				Object veterinario = cbVeterinario.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Veterinarian_Medical_P (id_Veterinarian,id_Procedure) VALUES (?,?)" );
					
					
					if (((ComboItem) proced).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un procedimiento");
					}else {
						if(((ComboItem) veterinario).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione un veterinario");
						}else {
							if(existeRel(((ComboItem) cbProcedimiento.getSelectedItem()).getValue(),((ComboItem) cbVeterinario.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Veterinario ya está asociado al procedimiento");
							}else {
								ps.setString(2, ((ComboItem) proced).getValue());
								ps.setString(1, ((ComboItem) veterinario).getValue());
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Veterinario colocado");

		                ControlFiles.addContent("Se ha asociado el veterinario "+veterinario+" al procedimiento "+proced);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar veterinario");
		                limpiar();
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(477, 168, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Veterinarian_Medical_P WHERE id_VMP = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Veterinario eliminado de Procedimiento");
		                ControlFiles.addContent("Se ha eliminado el veterinario "+table.getValueAt(fila,1).toString()+" del procedimiento "+table.getValueAt(fila,2).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar veterinario");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(621, 168, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(621, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 53, 348, 358);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		mostrarTabla();
	}

}

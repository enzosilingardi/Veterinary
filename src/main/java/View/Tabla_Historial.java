package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;

public class Tabla_Historial extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Mascota","Procedimiento","Fecha","Hora","Descripción"});
	       
	        table.setModel(modelo);
	        
	        
	        
	        String datos[] = new String[6];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT id_MHMP, name, proced_Name,CONVERT(varchar(10),proced_Date,103) AS pd ,CONVERT(varchar(10),proced_Time,8) as pt, description\r\n"
	        			+ "FROM Rel_Medical_H_Medical_P\r\n"
	        			+ "INNER JOIN Medical_Procedure ON Medical_Procedure.id_Procedure = Rel_Medical_H_Medical_P.id_Procedure\r\n"
	        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
	        			+ "INNER JOIN Medical_History ON Rel_Medical_H_Medical_P.id_Medical_History = Medical_History.id_Medical_History\r\n"
	        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_History.id_Pet;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                datos[4] = rs.getString(5);
	                datos[5] = rs.getString(6);
	                
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
					Tabla_Historial frame = new Tabla_Historial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tabla_Historial(String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 11, 729, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(668, 351, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
		JButton btnHistoriales = new JButton("ABM Historiales");
		btnHistoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historial_Medico hm = new Historial_Medico();
				hm.setVisible(true);
				dispose();
			}
		});
		btnHistoriales.setBounds(203, 327, 127, 23);
		contentPane.add(btnHistoriales);
		
		JButton btnEliminar = new JButton("Eliminar del historial");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Medical_H_Medical_P WHERE id_MHMP = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Eliminado del historial");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar del historial");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Historial está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(28, 327, 165, 23);
		contentPane.add(btnEliminar);
		
		}
		
		mostrarTabla();
	}

	public Tabla_Historial() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 11, 729, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(668, 351, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnHistoriales = new JButton("ABM Historiales");
		btnHistoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historial_Medico hm = new Historial_Medico();
				hm.setVisible(true);
				dispose();
			}
		});
		btnHistoriales.setBounds(203, 327, 127, 23);
		contentPane.add(btnHistoriales);
		
		JButton btnEliminar = new JButton("Eliminar del historial");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Medical_H_Medical_P WHERE id_MHMP = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Eliminado del historial");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar del historial");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Historial está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(28, 327, 165, 23);
		contentPane.add(btnEliminar);
		
		mostrarTabla();
	}
}

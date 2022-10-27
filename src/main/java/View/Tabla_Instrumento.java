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

public class Tabla_Instrumento extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Instrumento","Descripción"});
	       
	        table.setModel(modelo);
	        
	        
	        String datos[] = new String[3];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT * From Medical_Instrument;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                
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
					Tabla_Instrumento frame = new Tabla_Instrumento();
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
	public Tabla_Instrumento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(467, 309, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnRel = new JButton("Añadir a quirófano");
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instrumento_Quirofano iq = new Instrumento_Quirofano();
				iq.setVisible(true);
			}
		});
		btnRel.setBounds(40, 309, 188, 23);
		contentPane.add(btnRel);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombre = table.getValueAt(fila,1).toString();
				String descripcion = table.getValueAt(fila,2).toString();
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Medical_Instrument SET instrument_Name = ?, instrument_Description = ? WHERE id_Medical_Instrument = ?");
					ps.setString(1, nombre);
					ps.setString(2, descripcion);
					ps.setInt(3, id);
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento modificado");
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar instrumento");
		                mostrarTabla();
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
		btnModificar.setBounds(141, 270, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Instrument WHERE id_Medical_Instrument = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento eliminado");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar instrumento");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Instrumento está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(240, 270, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instrumento instrumento = new Instrumento();
				instrumento.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 270, 91, 23);
		contentPane.add(btnAgregar);
		
		mostrarTabla();
	}
}

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
import Model.ControlFiles;

public class Tabla_Quirofano extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Quirófano","Sucursal"});
	       
	        table.setModel(modelo);
	        
	        
	        String datos[] = new String[3];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT Operating_Room.id_Operating_Room, address, Operating_Room.room_Number\r\n"
	        			+ "FROM Operating_Room\r\n"
	        			+ "INNER JOIN Rel_Branch_Operating_R ON Rel_Branch_Operating_R.id_Operating_Room = Operating_Room.id_Operating_Room\r\n"
	        			+ "INNER JOIN Branch ON Rel_Branch_Operating_R.id_Branch = Branch.id_Branch;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	            	datos[0] = rs.getString(1);
	            	datos[1] = rs.getString(3);
	                datos[2] = rs.getString(2);
	                
	                
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
					Tabla_Quirofano frame = new Tabla_Quirofano();
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
	public Tabla_Quirofano(final String perfil) {
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
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
		JButton btnRel = new JButton("Añadir a sucursal");
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quirofano_Sucursal qs = new Quirofano_Sucursal(perfil);
				qs.setVisible(true);
				dispose();
			}
		});
		btnRel.setBounds(40, 309, 161, 23);
		contentPane.add(btnRel);
		
		}
	
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Quirofano mq = new Modificar_Quirofano(table.getValueAt(fila,0).toString(),perfil);
				mq.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(211, 309, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Operating_Room WHERE id_Operating_Room = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Quirofano eliminado");
		                ControlFiles.addContent("Se ha eliminado el quirofano "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar quirofano");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Quirofano está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(312, 277, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quirofano quirofano = new Quirofano(perfil);
				quirofano.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(211, 277, 91, 23);
		contentPane.add(btnAgregar);
		
		}
		
		mostrarTabla();
	}

	public Tabla_Quirofano() {
		// TODO Auto-generated constructor stub
		
		
	}

}

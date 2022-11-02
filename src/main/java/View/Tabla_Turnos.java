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
import View.Instrumento_Quirofano.ComboItem;

public class Tabla_Turnos extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","ID H","Mascota","Procedimiento","Fecha","Hora"});
	       
	        table.setModel(modelo);
	        
	        
	        String datos[] = new String[6];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT id_Procedure,Medical_History.id_Medical_History, name, proced_Name, CONVERT(varchar(10),proced_Date,103),CONVERT(varchar(10),proced_Time,8)\r\n"
	        			+ "FROM Medical_Procedure\r\n"
	        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_Procedure.id_Pet\r\n"
	        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
	        			+ "INNER JOIN Medical_History ON Medical_History.id_Pet = Pet.id_Pet;" );
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
	    		table.getColumnModel().getColumn(1).setMaxWidth(0);
	    		table.getColumnModel().getColumn(1).setMinWidth(0);
	    		table.getColumnModel().getColumn(1).setPreferredWidth(0);
	    		table.getColumnModel().getColumn(1).setResizable(false);
	        } catch(SQLException E) {
				JOptionPane.showMessageDialog(null,E);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	    }

	public int existeRel(int historial, int proced) {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(*) FROM Rel_Medical_H_Medical_P WHERE id_Medical_History = ? AND id_Procedure = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, historial);
			pst.setInt(2, proced);
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Turnos frame = new Tabla_Turnos();
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
	public Tabla_Turnos(String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 11, 516, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(497, 327, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
		
		JButton btnVeterinario = new JButton("Asociar veterinario");
		btnVeterinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Veterinario pv = new Procedimiento_Veterinario();
				pv.setVisible(true);
			}
		});
		btnVeterinario.setBounds(40, 289, 155, 23);
		contentPane.add(btnVeterinario);
		
		JButton btnSucursal = new JButton("Asociar sucursal");
		btnSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Sucursal ps = new Procedimiento_Sucursal();
				ps.setVisible(true);
			}
		});
		btnSucursal.setBounds(40, 323, 155, 23);
		contentPane.add(btnSucursal);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Turno mt = new Modificar_Turno(table.getValueAt(fila,0).toString());
				mt.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(205, 255, 155, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Procedure WHERE id_Procedure = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Turno eliminado");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar turno");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Turno está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(370, 255, 155, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar turno");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Medico pm = new Procedimiento_Medico();
				pm.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 255, 155, 23);
		contentPane.add(btnAgregar);
		
		}
		
		mostrarTabla();
	}

	public Tabla_Turnos() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 11, 516, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(497, 327, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnVeterinario = new JButton("Asociar veterinario");
		btnVeterinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Veterinario pv = new Procedimiento_Veterinario();
				pv.setVisible(true);
			}
		});
		btnVeterinario.setBounds(40, 289, 155, 23);
		contentPane.add(btnVeterinario);
		
		JButton btnSucursal = new JButton("Asociar sucursal");
		btnSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Sucursal ps = new Procedimiento_Sucursal();
				ps.setVisible(true);
			}
		});
		btnSucursal.setBounds(40, 323, 155, 23);
		contentPane.add(btnSucursal);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Turno mt = new Modificar_Turno(table.getValueAt(fila,0).toString());
				mt.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(205, 255, 155, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Medical_Procedure WHERE id_Procedure = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Turno eliminado");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar turno");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Turno está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(370, 255, 155, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar turno");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Medico pm = new Procedimiento_Medico();
				pm.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 255, 155, 23);
		contentPane.add(btnAgregar);
		
		mostrarTabla();
	}
}

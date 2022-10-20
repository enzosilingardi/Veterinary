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

public class Tabla_Quirofano extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"Quirófano","Sucursal"});
	       
	        table.setModel(modelo);
	        
	        
	        String datos[] = new String[2];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT Address.address_Name, Address.address_Number, Operating_Room.room_Number\r\n"
	        			+ "FROM Operating_Room\r\n"
	        			+ "INNER JOIN Rel_Branch_Operating_R ON Rel_Branch_Operating_R.id_Operating_Room = Operating_Room.id_Operating_Room\r\n"
	        			+ "INNER JOIN Branch ON Rel_Branch_Operating_R.id_Branch = Branch.id_Branch\r\n"
	        			+ "INNER JOIN Address ON Branch.id_Address = Address.id_Address;" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	            	datos[0] = rs.getString(3);
	                datos[1] = rs.getString(1)+" "+rs.getString(2);
	                
	                
	                modelo.addRow(datos);

	            }
	            table.setModel(modelo);
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
	public Tabla_Quirofano() {
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
		
		JButton btnRel = new JButton("Añadir a sucursal");
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quirofano_Sucursal qs = new Quirofano_Sucursal();
				qs.setVisible(true);
			}
		});
		btnRel.setBounds(40, 309, 161, 23);
		contentPane.add(btnRel);
		
		JButton btnInstrumentos = new JButton("Instrumentos");
		btnInstrumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Instrumento ti = new Tabla_Instrumento();
				ti.setVisible(true);
			}
		});
		btnInstrumentos.setBounds(40, 277, 161, 23);
		contentPane.add(btnInstrumentos);
		
		mostrarTabla();
	}

}

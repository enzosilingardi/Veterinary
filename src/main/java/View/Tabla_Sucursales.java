package View;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabla_Sucursales extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnModificar;
	private JButton btnABM;

	
	
	void mostrarTabla(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Pais","Provincia","Ciudad","Calle","Número"});
       
        table.setModel(modelo);
        
        
        String datos[] = new String[6];
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT Branch.id_Branch, Country.name, Province.name, City.name, Address.address_Name, Address.address_Number\r\n"
        			+ "FROM Country\r\n"
        			+ "INNER JOIN Province ON Province.id_Country = Country.id_Country\r\n"
        			+ "INNER JOIN City ON City.id_Province = Province.id_Province\r\n"
        			+ "INNER JOIN Address ON Address.id_City = City.id_City\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Address = Address.id_Address;" );
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
					Tabla_Sucursales frame = new Tabla_Sucursales();
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
	public Tabla_Sucursales() {
		setTitle("Sucursales");
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
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Sucursal ms = new Modificar_Sucursal(table.getValueAt(fila,0).toString());
				ms.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(40, 260, 140, 23);
		contentPane.add(btnModificar);
		
		btnABM = new JButton("AB Sucursales");
		btnABM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sucursal sucursal = new Sucursal();
				sucursal.setVisible(true);
				dispose();
			}
		});
		btnABM.setBounds(40, 294, 140, 23);
		contentPane.add(btnABM);
		
		mostrarTabla();
	}
}

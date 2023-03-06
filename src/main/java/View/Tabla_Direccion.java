package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabla_Direccion extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnVolver;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCiudad;

	void mostrarTabla(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Ciudad","Calle","Número","Piso","Departamento"});
       
        table.setModel(modelo);
        
        
        String datos[] = new String[6];
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT id_Address, City.name, address_Name, address_Number, floor_Number, dept_Number\r\n"
        			+ "FROM Address\r\n"
        			+ "INNER JOIN City ON City.id_City = Address.id_City;" );
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
					Tabla_Direccion frame = new Tabla_Direccion();
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
	public Tabla_Direccion(final String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 11, 501, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(482, 309, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Direccion direccion = new Direccion(perfil);
				direccion.setVisible(true);
				dispose();
			}
		});
		btnAgregar.setBounds(40, 260, 89, 23);
		contentPane.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Direccion md = new Modificar_Direccion(table.getValueAt(fila,0).toString(),perfil);
				md.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(139, 260, 89, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Address WHERE id_Address = ?" );
					
						ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Dirección eliminada");
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar dirección");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Dirección está en uso, por favor elimine todos los registros relacionados");
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(238, 260, 91, 23);
		contentPane.add(btnEliminar);
		
		}
		
		btnCiudad = new JButton("Ciudades");
		btnCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ciudad ciudad = new Ciudad(perfil);
				ciudad.setVisible(true);
				dispose();
			}
		});
		btnCiudad.setBounds(40, 294, 89, 23);
		contentPane.add(btnCiudad);
		
		mostrarTabla();
		
		
	}
	public Tabla_Direccion() {
		// TODO Auto-generated constructor stub
	}

}

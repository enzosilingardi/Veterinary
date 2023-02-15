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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;

public class Buscar_Cliente_ModMasc extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtDir;
	private JTextField txtId;

	void mostrarTabla(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Apellido","DNI","Dirección"});
       
        table.setModel(modelo);
        
        
        String datos[] = new String[5];
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
        			+ "FROM Client;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
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
	
	void mostrarTablaParametro(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Apellido","DNI","Dirección"});
       
        table.setModel(modelo);
        
        PreparedStatement ps = null;
        
        String datos[] = new String[5];
       
        try {
        	Connection con = Connect.getConexion();
        	
        	if(txtDni.getText().isBlank() && txtDir.getText().isBlank()) {
        		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
            			+ "FROM Client WHERE name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"' ;" );
        	}else {
        		if(txtNombre.getText().isBlank() && txtDir.getText().isBlank()) {
            		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
                			+ "FROM Client WHERE dni ='"+txtDni.getText()+"';" );
            	} else {
            		if(txtNombre.getText().isBlank() && txtDni.getText().isBlank()) {
                		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
                    			+ "FROM Client WHERE address ='"+txtDir.getText()+"';" );
            		
            	} else {
            		if(txtDir.getText().isBlank()) {
                		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
                    			+ "FROM Client WHERE (name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"') AND dni ='"+txtDni.getText() +"';" );
            	} else {
            		if(txtDni.getText().isBlank()) {
                		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
                    			+ "FROM Client WHERE (name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"') AND address ='"+txtDir.getText() +"';" );
            	} else {
            		if(txtNombre.getText().isBlank()) {
                		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
                    			+ "FROM Client WHERE dni ='"+txtDni.getText()+"' AND address ='"+txtDir.getText() +"';" );
            	} else {
            		ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
                			+ "FROM Client WHERE dni ='"+txtDni.getText()+"' AND address ='"+txtDir.getText() +"' AND (name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"');" );
            	}
        	}
            	}
            	}
            	}
        	}
        	
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                
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
					Buscar_Cliente_ModMasc frame = new Buscar_Cliente_ModMasc();
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
	public Buscar_Cliente_ModMasc(final String id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 82, 544, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Mascota mascota = new Modificar_Mascota(id);
				mascota.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(458, 407, 149, 23);
		contentPane.add(btnVolver);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String nom = table.getValueAt(fila,1).toString()+" "+table.getValueAt(fila,2).toString();
				String idC = table.getValueAt(fila,0).toString();
				
				Modificar_Mascota mascota = new Modificar_Mascota(id,idC,nom);
				mascota.setVisible(true);
				dispose();
				
			}
		});
		btnSeleccionar.setBounds(290, 407, 158, 23);
		contentPane.add(btnSeleccionar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(37, 39, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(35, 11, 46, 14);
		contentPane.add(lblNombre);
		
		txtDni = new JTextField();
		txtDni.setBounds(156, 39, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtDir = new JTextField();
		txtDir.setBounds(274, 39, 86, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(156, 11, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblDir = new JLabel("Dirección");
		lblDir.setBounds(274, 11, 68, 14);
		contentPane.add(lblDir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaParametro();
			}
		});
		btnBuscar.setBounds(419, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtDni.setText("");
				txtDir.setText("");
				
				mostrarTabla();
			}
		});
		btnLimpiar.setBounds(419, 38, 89, 23);
		contentPane.add(btnLimpiar);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(521, 8, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		txtId.setText(id);
		
		mostrarTabla();
	}

	public Buscar_Cliente_ModMasc() {
		// TODO Auto-generated constructor stub
	}

}

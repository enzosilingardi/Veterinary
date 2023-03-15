package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
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
import Control.Control_Cliente;

public class Buscar_Cliente_Masc extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtDir;

	void mostrarTabla(){												//Tabla que muestra los clientes
        
DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Cliente.tablaBus(modelo, table);
        
    }
	
	void mostrarTablaParametro(){								//Muestra la tabla segun los parametros recibidos 
        
		 DefaultTableModel modelo = new DefaultTableModel();
	        
	        String url;
	        
	        
	        	
	        	if(txtDni.getText().isBlank() && txtDir.getText().isBlank()) {                         // Realiza la consulta, Dependiendo de cuales campos tengan algo escritos y cuales esten vacios
	        		url = "Select id_Client, name, surname, dni, address\r\n"
	            			+ "FROM Client WHERE name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"' ;" ;
	        	}else {
	        		if(txtNombre.getText().isBlank() && txtDir.getText().isBlank()) {
	            		url = "Select id_Client, name, surname, dni, address\r\n"
	                			+ "FROM Client WHERE dni ='"+txtDni.getText()+"';" ;
	            	} else {
	            		if(txtNombre.getText().isBlank() && txtDni.getText().isBlank()) {
	                		url = "Select id_Client, name, surname, dni, address\r\n"
	                    			+ "FROM Client WHERE address ='"+txtDir.getText()+"';";
	            		
	            	} else {
	            		if(txtDir.getText().isBlank()) {
	                		url = "Select id_Client, name, surname, dni, address\r\n"
	                    			+ "FROM Client WHERE (name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"') AND dni ='"+txtDni.getText() +"';" ;
	            	} else {
	            		if(txtDni.getText().isBlank()) {
	                		url = "Select id_Client, name, surname, dni, address\r\n"
	                    			+ "FROM Client WHERE (name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"') AND address ='"+txtDir.getText() +"';" ;
	            	} else {
	            		if(txtNombre.getText().isBlank()) {
	                		url = "Select id_Client, name, surname, dni, address\r\n"
	                    			+ "FROM Client WHERE dni ='"+txtDni.getText()+"' AND address ='"+txtDir.getText() +"';";
	            	} else {
	            		url = "Select id_Client, name, surname, dni, address\r\n"
	                			+ "FROM Client WHERE dni ='"+txtDni.getText()+"' AND address ='"+txtDir.getText() +"' AND (name ='"+txtNombre.getText()+"' OR surname ='"+txtNombre.getText()+"');" ;
	            	}
	        	}
	            	}
	            	}
	            	}
	        	}
	        	
	         Control_Cliente.tablaBusPar(modelo, table, url);
    }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar_Cliente_Masc frame = new Buscar_Cliente_Masc();
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
	public Buscar_Cliente_Masc(final String perfil){			// Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));		//Setea el icono de la ventana

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 82, 544, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");					// Este boton cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mascota mascota = new Mascota(perfil);
				mascota.setVisible(true);					// Abre la ventana mascota
				dispose();
			}
		});
		btnVolver.setBounds(458, 407, 149, 23);
		contentPane.add(btnVolver);
		
		JButton btnSeleccionar = new JButton("Seleccionar");		// Este boton permite selecciona un cliente y lo devuelve a la ventana mascota	
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String nom = table.getValueAt(fila,1).toString()+" "+table.getValueAt(fila,2).toString();
				String id = table.getValueAt(fila,0).toString();
				
				Mascota mascota = new Mascota(id,nom,perfil);			// abre la ventana mascota, recibiendo como parametro el id y el nombre del cliente
				mascota.setVisible(true);
				dispose();								//Cierra la ventana
				
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
		
		JButton btnBuscar = new JButton("Buscar");					//Filtra los resultados vistos en la tabla
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaParametro();
			}
		});
		btnBuscar.setBounds(419, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");				//Limpia los campos de texto
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
		
		mostrarTabla();
	}

	public Buscar_Cliente_Masc() {
		// TODO Auto-generated constructor stub
	}

}

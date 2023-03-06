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

public class Buscar_Cliente_ModMasc extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtDir;
	private JTextField txtId;

	void mostrarTabla(){       											//Tabla utilizada para buscar un cliente y devuelve el cliente seleccionado
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Apellido","DNI","Dirección"});				// Nombre de las columnas
       
        table.setModel(modelo);				//Setea el modelo
        
        
        String datos[] = new String[5];			//Declara que va a haber 5 columnas
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("Select id_Client, name, surname, dni, address\r\n"
        			+ "FROM Client;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){							//Carga las columnas de la base de datos
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);										//Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);				//Las siguientes 4 hacen que la columna id, sea invisible para el usuario
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
	
	void mostrarTablaParametro(){							//Muestra la tabla segun los parametros recibidos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Nombre","Apellido","DNI","Dirección"});				//Nombre de las columnas
       
        table.setModel(modelo);							//Setea el modelo
        
        PreparedStatement ps = null;
        
        String datos[] = new String[5];				//Declara que va a haber 5 columnas
       
        try {
        	Connection con = Connect.getConexion();			//Realiza la conexión
        	
        	if(txtDni.getText().isBlank() && txtDir.getText().isBlank()) {														// Realiza la consulta, Dependiendo de cuales campos tengan algo escritos y cuales esten vacios
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
            while (rs.next()){								//Llena las columnas de la tabla con las columnas de la base de datos
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);							//Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);				//Las siguientes 4 vuelven invisible la columna id, para el usuario
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
	public Buscar_Cliente_ModMasc(final String id) {				//Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));			//Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 82, 544, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");					//Este boton cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Mascota mascota = new Modificar_Mascota(id);
				mascota.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(458, 407, 149, 23);
		contentPane.add(btnVolver);
		
		JButton btnSeleccionar = new JButton("Seleccionar");					// Este boton permite selecciona un cliente y la devuelve a la ventana Modificar_Mascota	
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String nom = table.getValueAt(fila,1).toString()+" "+table.getValueAt(fila,2).toString();
				String idC = table.getValueAt(fila,0).toString();
				
				Modificar_Mascota mascota = new Modificar_Mascota(id,idC,nom); 			// abre la ventana Modificar_Mascota, recibiendo como parametro el id y el nombre del cliente
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

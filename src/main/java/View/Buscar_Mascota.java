package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import View.Mascota.ComboItem;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Buscar_Mascota extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnSelec;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JComboBox cbAnimal;
	
	void mostrarTabla(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Edad","Género","Raza","Dueño"});
       
        table.setModel(modelo);
        
        
        String datos[] = new String[7];
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
        			+ "FROM Pet\r\n"
        			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
        			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7)+" "+rs.getString(8);
                
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
        
        modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Edad","Género","Raza","Dueño"});
       
        table.setModel(modelo);
        
        PreparedStatement ps = null;
        
        Object animal = cbAnimal.getSelectedItem();
        
        String datos[] = new String[7];
       
        try {
        	Connection con = Connect.getConexion();
        	
        	if(((ComboItem) animal).getValue() == "") {
        		ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
            			+ "FROM Pet\r\n"
            			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
            			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
            			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client  WHERE Pet.name ='"+ txtNombre.getText() +"';" );
        	} else {
        		if(txtNombre.getText().isBlank()) {
        			ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
                			+ "FROM Pet\r\n"
                			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
                			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
                			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client  WHERE Animal.type ='"+ animal +"';" );
        		} else {
        			ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
        			+ "FROM Pet\r\n"
        			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
        			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client  WHERE Pet.name ='"+ txtNombre.getText() +"' AND Animal.type ='"+ animal +"';" );
        		}
        	}
        	
        	
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7)+" "+rs.getString(8);
                
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
	
	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)
	    {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public String toString()
	    {
	        return key;
	    }

	    public String getKey()
	    {
	        return key;
	    }

	    public String getValue()
	    {
	        return value;
	    }
	}
	
	
	public DefaultComboBoxModel cargarAnimal() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Animal ORDER BY id_Animal";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("Seleccionar animal",""));
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));   
				
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar_Mascota frame = new Buscar_Mascota();
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
	public Buscar_Mascota(final String perfil) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(38, 78, 516, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial(perfil);
				th.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(497, 386, 89, 23);
		contentPane.add(btnVolver);
		
		btnSelec = new JButton("Seleccionar");
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String id = table.getValueAt(fila,0).toString();
				
				Tabla_Historial th = new Tabla_Historial(perfil,id);
				th.setVisible(true);
				dispose();
			}
		});

		btnSelec.setBounds(378, 386, 109, 23);
		contentPane.add(btnSelec);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(38, 47, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(38, 22, 46, 14);
		contentPane.add(lblNombre);
		
		 cbAnimal = new JComboBox();
		cbAnimal.setBounds(159, 45, 109, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setBounds(159, 22, 46, 14);
		contentPane.add(lblAnimal);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaParametro();
			}
		});
		btnBuscar.setBounds(427, 11, 127, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				cbAnimal.setSelectedIndex(0);
				
				mostrarTabla();
			}
		});
		btnLimpiar.setBounds(427, 44, 127, 23);
		contentPane.add(btnLimpiar);
		
		mostrarTabla();
	}


	public Buscar_Mascota() {
		// TODO Auto-generated constructor stub
	}
}

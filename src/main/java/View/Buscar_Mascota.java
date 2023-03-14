package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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

import Control.ComboBoxes;
import Control.Connect;
import Control.Consulta_Mascota;
import Model.ComboItem;

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
	
	void mostrarTabla(){     //Tabla que muestra las mascotas
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Consulta_Mascota.tablaBus(modelo, table);
    }
	
	
	void mostrarTablaParametro(){     //Muestra la tabla segun los parametros recibidos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String url;
        
       
        
        Object animal = cbAnimal.getSelectedItem();
        
       
        	
        	if(((ComboItem) cbAnimal.getSelectedItem()).getValue() == "") {       // Arma la consulta, Dependiendo de cuales campos tengan algo escrito y cuales esten vacios
        		
        		url = "SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
            			+ "FROM Pet\r\n"
            			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
            			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
            			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client  WHERE Pet.name ='"+ txtNombre.getText() +"';" ;
        	} else {
        		if(txtNombre.getText().isBlank()) {
        			url = "SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
                			+ "FROM Pet\r\n"
                			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
                			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
                			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client  WHERE Animal.type ='"+ animal +"';";
        		} else {
        			url = "SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
        			+ "FROM Pet\r\n"
        			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
        			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client  WHERE Pet.name ='"+ txtNombre.getText() +"' AND Animal.type ='"+ animal +"';" ;
        		}
        	}
        
        	Consulta_Mascota.tablaBusPar(modelo, table, url);
            
        
        
    }
	

	
	public DefaultComboBoxModel cargarAnimal() {      //Carga el ComboBox animal
		DefaultComboBoxModel modelo = new DefaultComboBoxModel(); 
		
		ComboBoxes.CBAnimal(modelo);
		
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
	public Buscar_Mascota(final String perfil) {         //Crea la ventana recibiendo por parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(38, 78, 516, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");       //Este botón cierra la ventana

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial(perfil);
				th.setVisible(true);		//Abre la ventana Tabla_Historial recibiendo como parámetro el perfil del usuario
				dispose();
			}
		});
		btnVolver.setBounds(497, 386, 89, 23);
		contentPane.add(btnVolver);
		
		btnSelec = new JButton("Seleccionar");       //Este boton permite seleccionar una mascota y la devuelve a la ventana Tabla_Historial
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String id = table.getValueAt(fila,0).toString();
				
				Tabla_Historial th = new Tabla_Historial(perfil,id);      //Abre la ventana Tabla_Historial , recibiendo como parámetro el perfil del usuario y el id de la mascota
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
		
		JButton btnBuscar = new JButton("Buscar");         //Filtra los resultados vistos en la tabla
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaParametro();
			}
		});
		btnBuscar.setBounds(427, 11, 127, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");         //Limpia los campos
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

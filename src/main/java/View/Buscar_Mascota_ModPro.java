package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import View.Buscar_Mascota_Pro.ComboItem;

public class Buscar_Mascota_ModPro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnSelec;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JComboBox cbAnimal;
	
	void mostrarTabla(){          //Tabla que muestra las mascotas
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Edad","Género","Raza","Dueño"});      //Nombre de las columnas
       
        table.setModel(modelo);         //Setea el modelo
        
        
        String datos[] = new String[7];         //Declara que va a haber 7 columnas
       
        try {
        	Connection con = Connect.getConexion();        //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT Pet.id_Pet, Pet.name, Animal.type, age, Pet.gender, Breed.type, Client.name, Client.surname\r\n"
        			+ "FROM Pet\r\n"
        			+ "INNER JOIN Animal ON Animal.id_Animal = Pet.id_Animal\r\n"
        			+ "INNER JOIN Breed ON Breed.id_Breed = Pet.id_Breed\r\n"
        			+ "INNER JOIN Client ON Client.id_Client = Pet.id_Client;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                //Llena las columnas de la tabla con las columnas de la base de datos 
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7)+" "+rs.getString(8);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);        //Setea el modelo
             
            table.getColumnModel().getColumn(0).setMaxWidth(0);        //Las siguientes 4 vuelven invisible la columna id, para el usuario
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
	
	
	void mostrarTablaParametro(){    //Muestra la tabla segun los parametros recibidos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"id_Pet","Nombre","Animal","Edad","Género","Raza","Dueño"});   //Nombre de las columnas
       
        table.setModel(modelo);       //Setea el modelo
        
        PreparedStatement ps = null;
        
        Object animal = cbAnimal.getSelectedItem();
        
        String datos[] = new String[7];    //Declara que va a haber 7 columnas
       
        try {
        	Connection con = Connect.getConexion();     //Realiza la conexión
        	
        	if(((ComboItem) animal).getValue() == "") {      // Realiza la consulta, Dependiendo de cuales campos tengan algo escrito y cuales esten vacios
        		
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
            while (rs.next()){                 //Llena las columnas de la tabla con las columnas de la base de datos
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7)+" "+rs.getString(8);
                
                modelo.addRow(datos);

            }
            table.setModel(modelo);     //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);    //Las siguientes 4 vuelven invisible la columna id, para el usuario
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
	
	class ComboItem        //Clase utilizada para armar el ComboBox
	{
	    private String key;     //Label visible del ComboBox
	    
	    private String value;    //Valor del ComboBox

	    public ComboItem(String key, String value)     //Genera el label que se verá en el ComboBox y el valor del objeto seleccionado
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
	
	
	public DefaultComboBoxModel cargarAnimal() {      //Carga el ComboBox animal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Animal ORDER BY id_Animal";     //Realiza una sentencia sql
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("Seleccionar animal",""));      //El primer elemento del ComboBox dice "Seleccionar animal"
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));      //El elemento del ComboBox recibe el tipo de animal como label y el id como valor
				
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
					Buscar_Mascota_ModPro frame = new Buscar_Mascota_ModPro();
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
	public Buscar_Mascota_ModPro(final String turno) {        //Crea la ventana recibiendo por parametro el id de turno
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(38, 78, 516, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");        //Este botón cierra la ventana

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Turno mt = new Modificar_Turno(turno);
				mt.setVisible(true);	//Abre la ventana Modificar_Turno recibiendo como parámetro el id del turno
				dispose();
			}
		});
		btnVolver.setBounds(497, 386, 89, 23);
		contentPane.add(btnVolver);
		
		btnSelec = new JButton("Seleccionar");       //Este boton permite seleccionar una mascota y la devuelve a la ventana Modificar_Turno
		btnSelec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String id = table.getValueAt(fila,0).toString();
				String nom = table.getValueAt(fila,1).toString();
				
				Modificar_Turno mt = new Modificar_Turno(turno,id,nom);     //Abre la ventana Modificar_Turno , recibiendo como parámetro el id del turno, el id de la mascota y su nombre
				mt.setVisible(true);
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
		
		JButton btnBuscar = new JButton("Buscar");       //Filtra los resultados vistos en la tabla
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaParametro();
			}
		});
		btnBuscar.setBounds(427, 11, 127, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");      //Limpia los campos
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


	public Buscar_Mascota_ModPro() {
		// TODO Auto-generated constructor stub
	}
	

}

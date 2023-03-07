package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Model.ControlFiles;
import View.Instrumento_Quirofano.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Animal_Raza extends JFrame {       // Esta clase añade o remueve una relacion entre animal y raza
	private JPanel contentPane;
	private JComboBox cbAnimal;
	private JComboBox cbRaza;
	private JTable table;
	
	class ComboItem                                     //Clase utilizada para armar un ComboBox
	{
	    private String key;                              //Label visible del ComboBox
	    
	    private String value;                           //Valor del ComboBox

	    public ComboItem(String key, String value)      //Genera el label que se verá en el combobox y el valor del objeto seleccionado
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
	
	public DefaultComboBoxModel cargarAnimal() {       // Carga el ComboBox animal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Animal ORDER BY id_Animal";   // Realiza una sentencia sql
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Animal")));    //El elemento del ComboBox recibe el tipo de animal como label y el id como valor
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }
	
	public DefaultComboBoxModel cargarRaza() {            // Carga el ComboBox con la raza del animal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Breed ORDER BY id_Breed";      // realiza una sentencia sql
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("type"),result.getString("id_Breed")));           //El elemento del ComboBox recibe el tipo de raza como label y el id como valor
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }


	void mostrarTabla(){            // carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Animal","Raza"});       // Nombre de las columnas
       
        table.setModel(modelo);                    //Setea el modelo
        
        
        
        String datos[] = new String[3];         // declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT id_AB, Animal.type, Breed.type\r\n"     // sentencia sql
        			+ "FROM Rel_Animal_Breed\r\n"
        			+ "INNER JOIN Animal ON Animal.id_Animal = Rel_Animal_Breed.id_Animal\r\n"
        			+ "INNER JOIN Breed ON Breed.id_Breed = Rel_Animal_Breed.id_Breed;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                                // cargan las columnas de la base de datos a la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);                       					//Se setea el modelo
            table.getColumnModel().getColumn(0).setMaxWidth(0);				// los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Animal_Raza frame = new Animal_Raza();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public int existeRel(Object animal, Object raza) {     					// Es una funcion que determina si ya existe la relacion entre animal y raza
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();              //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Animal_Breed WHERE id_Animal = ? AND id_Breed = ?;"; 			//sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) animal);
			pst.setString(2, (String) raza);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);  				// si la relacion ya existe, entonces la variable se pone en 1
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
	
	private void limpiar() {					// Limpia los campos txt
		cbAnimal.setSelectedIndex(0);
		cbRaza.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Animal_Raza() {												// Arma la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));				// Se cambia el icono de la ventana

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar raza a animal");
		lblTitulo.setBounds(127, 11, 141, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setBounds(467, 60, 60, 14);
		contentPane.add(lblAnimal);
		
		cbAnimal = new JComboBox();						// Se arma el combobox animal
		cbAnimal.setBounds(537, 56, 168, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(467, 116, 46, 14);
		contentPane.add(lblRaza);
		
		cbRaza = new JComboBox();						// se arma el combobox raza
		cbRaza.setBounds(537, 112, 168, 22);
		contentPane.add(cbRaza);
		cbRaza.setModel(cargarRaza());
		
		JButton btnAgregar = new JButton("Agregar"); 			// al presionar este boton se añade una relacion entre animal y raza
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Animal_Breed (id_Animal ,id_Breed) VALUES (?,?)" );
					
					
					if (((ComboItem) animal).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un animal");     
					}else {                                                                    //Revisa que los combobox no estén en blanco
						if(((ComboItem) raza).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una raza");
						}else {
							if(existeRel(((ComboItem) cbAnimal.getSelectedItem()).getValue(),((ComboItem) cbRaza.getSelectedItem()).getValue())!=0) {        //Revisa que no exista la relación
								JOptionPane.showMessageDialog(null, "Raza ya se encuentra asociada al animal");
							}else {
								ps.setString(1, ((ComboItem) animal).getValue());
								ps.setString(2, ((ComboItem) raza).getValue());
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Raza asociada");				// en caso de ser exitoso, muestra un cartel en pantalla indicando que fue un exito
		                limpiar();
		                ControlFiles.addContent("Se ha asociado la raza "+raza+" al animal "+animal);		// añade al log la accion realizada	
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al asociar raza");				// en caso de fallar, lo muestra en un cartel en pantalla
		                limpiar();
		            }
				
					con.close();
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(475, 176, 89, 23);
		contentPane.add(btnAgregar);
			
		JButton btnEliminar = new JButton("Eliminar");				// boton que elimina la relacion seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Animal_Breed WHERE id_AB = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){																						// en caso de ser exitoso lo muestra en pantalla y lo añade al log
		                JOptionPane.showMessageDialog(null, "Raza eliminada de animal");
		                ControlFiles.addContent("Se ha eliminado una relación entre una raza y un tipo de animal");
		               mostrarTabla();
		            } else {																						// en caso de fallar lo muestra por pantalla
		                JOptionPane.showMessageDialog(null, "Error al eliminar raza");
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");			// en caso de fallar lo muestra por pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(616, 176, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				// cierra la ventana
				dispose();
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 47, 363, 373);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}

}

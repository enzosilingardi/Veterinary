package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Model.ControlFiles;
import View.Usuario_Sucursal.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Veterinario_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbVeterinario;
	private JComboBox cbSucursal;
	private JTable table;

	class ComboItem                 //Clase utilizada para armar un ComboBox
	{
	    private String key;          //Label visible del ComboBox
	    
	    private String value;           //Valor del ComboBox

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
	
	public DefaultComboBoxModel cargarSucursal() {         //Carga el ComboBox sucursal 
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();          //Realiza la conexión
			String SSQL = "Select *\r\n"		//Sentencia Sql
					+ "FROM Branch\r\n"
					+ "ORDER BY Branch.address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));          //El primer elemento está en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));     //El elemento del ComboBox recibe la dirección de la sucursal como label y el id como valor
				
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
	
	public DefaultComboBoxModel cargarVeterinario() {       //Carga el comboBox veterinario
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();   //Realiza la conexión
			String SSQL = "Select *"		//Sentencia Sql
					+ "FROM Veterinarian";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));     //El primer elemento está en blanco
			
			while (result.next()) {
				 //El elemento del ComboBox recibe el nombre y apellido del veterinario como label y el id del veterinario como valor
				modelo.addElement(new ComboItem(result.getString("name")+" "+result.getString("surname"),result.getString("id_Veterinarian")));
				
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

	void mostrarTabla(){         // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Usuario","Sucursal"});     //Nombre de las columnas
       
        table.setModel(modelo);    //Setea el modelo
        
        
        
        String datos[] = new String[3];      //Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();       //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_VB, name, surname, address\r\n"
        			+ "FROM Rel_Veterinarian_Branch\r\n"
        			+ "INNER JOIN Veterinarian ON Veterinarian.id_Veterinarian = Rel_Veterinarian_Branch.id_Veterinarian\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Veterinarian_Branch.id_Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
            	datos[0] = rs.getString(1);
                datos[1] = rs.getString(2)+" "+rs.getString(3);
                datos[2] = rs.getString(4);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);      //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);           // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Veterinario_Sucursal frame = new Veterinario_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object veterinario, Object sucursal) {   // Es una funcion que determina si ya existe la relacion entre veterinario y sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();    //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Veterinarian_Branch WHERE id_Veterinarian = ? AND id_Branch = ?;";	//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) veterinario);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);     //Si ya existe, la variable se pone en 1
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
	
	private void limpiar() {                 //Este procedimiento limpia los campos
		cbVeterinario.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Veterinario_Sucursal() {                         //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTítulo = new JLabel("Asociar veterinario a sucursal");
		lblTítulo.setBounds(113, 11, 197, 14);
		contentPane.add(lblTítulo);
		
		JLabel lblVeterinarian = new JLabel("Veterinarian");
		lblVeterinarian.setBounds(456, 67, 86, 14);
		contentPane.add(lblVeterinarian);
		
		cbVeterinario = new JComboBox();
		cbVeterinario.setBounds(552, 63, 172, 22);
		contentPane.add(cbVeterinario);
		cbVeterinario.setModel(cargarVeterinario());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(456, 122, 86, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(552, 118, 172, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");            //Este botón permite agregar un veterinario a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object veterinario = cbVeterinario.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Veterinarian_Branch (id_Veterinarian,id_Branch) VALUES (?,?)" );
					
					
					if (((ComboItem) veterinario).getValue() == "") {                        //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un Veterinario");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si ya existe la relación
							if(existeRel(((ComboItem) cbVeterinario.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Veterinario ya se encuentra en la sucursal");
							}else {
								ps.setString(1, ((ComboItem) veterinario).getValue());
								ps.setString(2, ((ComboItem) sucursal).getValue());
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Veterinario colocado");    //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha colocado el veterinario "+veterinario+" en la sucursal "+sucursal);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar veterinario");      //En caso de fallar, lo avisa en pantalla
		                limpiar();
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(467, 173, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");             //Este botón permite eliminar la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Veterinarian_Branch WHERE id_VB = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Veterinario eliminado de sucursal");          //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el veterinario "+table.getValueAt(fila,1).toString()+" de la sucursal "+table.getValueAt(fila,2).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar veterinario");       //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");   //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(608, 173, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 48, 371, 372);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}
}

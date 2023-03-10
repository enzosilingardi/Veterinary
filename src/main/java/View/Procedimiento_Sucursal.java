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

public class Procedimiento_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbProcedimiento;
	private JComboBox cbSucursal;
	private JTable table;

	class ComboItem                       //Clase usada para armar el ComboBox
	{
	    private String key;              //Label visible del ComboBox
	    
	    private String value;            //Valor del ComboBox
 
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
	
	public DefaultComboBoxModel cargarSucursal() {       //Carga el ComboBox sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			String SSQL = "Select *\r\n"				//Sentencia Sql
					+ "FROM Branch\r\n"
					+ "ORDER BY Branch.address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));     //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));    //El elemento del ComboBox recibe la dirección de la sucursal como label y el id de la sucursal como valor
				
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
	
	public DefaultComboBoxModel cargarProcedimiento() {     //Carga el ComboBox procedimiento
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT DISTINCT id_Procedure, name, proced_Name, CONVERT(varchar(10),proced_Date,103) as pd,CONVERT(varchar(10),proced_Time,8) as pt\r\n"  //Sentencia Sql
					+ "FROM Medical_Procedure\r\n"
					+ "INNER JOIN Pet ON Pet.id_Pet = Medical_Procedure.id_Pet\r\n"
					+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
					+ "INNER JOIN Medical_History ON Medical_History.id_Pet = Pet.id_Pet";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));        //El primer elemento es en blanco
			
			while (result.next()) {
				//El elemento del ComboBox recibe el nombre de la mascota y el nombre, fecha y hora del procedimiento como label, y el id del procedimiento como valor
				modelo.addElement(new ComboItem(result.getString("name")+" - "+result.getString("proced_Name")+" "+result.getString("pd")+" "+result.getString("pt"),result.getString("id_Procedure")));  
				
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
	
	void mostrarTabla(){           // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Sucursal","Procedimiento"});     //Nombre de las columnas
       
        table.setModel(modelo);      //Setea el modelo
        
        
        
        String datos[] = new String[3];      //Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_BMP, address, proced_Name,CONVERT(varchar(10),proced_Date,103) AS pd ,CONVERT(varchar(10),proced_Time,8) as pt\r\n"
        			+ "FROM Rel_Branch_Medical_P\r\n"
        			+ "INNER JOIN Medical_Procedure ON Medical_Procedure.id_Procedure = Rel_Branch_Medical_P.id_Procedure\r\n"
        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Branch_Medical_P.id_Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                 //Carga las columnas de la base de datos a la tabla
            	datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3)+" - "+rs.getString(4)+" "+rs.getString(5);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);      //Setea el modelo
 
            table.getColumnModel().getColumn(0).setMaxWidth(0);      // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Procedimiento_Sucursal frame = new Procedimiento_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object procedimiento, Object sucursal) {      // Es una funcion que determina si ya existe la relacion entre procedimiento y sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Branch_Medical_P WHERE id_Procedure = ? AND id_Branch = ?;";  //Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) procedimiento);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);        // si la relacion ya existe, entonces la variable se pone en 1
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
	
	private void limpiar() {                     //Este procedimiento limpia los campos
		cbProcedimiento.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Procedimiento_Sucursal() {                        //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar procedimiento a sucursal");
		lblTitulo.setBounds(108, 11, 209, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProcedimiento = new JLabel("Procedimiento");
		lblProcedimiento.setBounds(430, 53, 92, 14);
		contentPane.add(lblProcedimiento);
		
		cbProcedimiento = new JComboBox();
		cbProcedimiento.setBounds(532, 49, 192, 22);
		contentPane.add(cbProcedimiento);
		cbProcedimiento.setModel(cargarProcedimiento());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(430, 107, 92, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(532, 103, 192, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");              //Este boton permite agregar un procedimiento a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object proced = cbProcedimiento.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();  //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Branch_Medical_P (id_Branch,id_Procedure) VALUES (?,?)" );
					
					
					if (((ComboItem) proced).getValue() == "") {                               //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un procedimiento");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si ya existe la relación
							if(existeRel(((ComboItem) cbProcedimiento.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Procedimiento ya se encuentra en la sucursal");
							}else {
								ps.setString(2, ((ComboItem) proced).getValue());
								ps.setString(1, ((ComboItem) sucursal).getValue());
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Procedimiento colocado");     //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha asociado el procedimiento "+proced+" a la sucursal "+sucursal);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar procedimiento");    //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(477, 168, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");            //Este botón permite eliminar la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();    //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Medical_P WHERE id_BMP = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Procedimiento eliminado de sucursal");    //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el procedimiento "+table.getValueAt(fila,1).toString()+" de la sucursal "+table.getValueAt(fila,2).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar procedimiento");    //En caso de fallar, lo muesta en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar, lo muestra en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(621, 168, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");               //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(621, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 53, 348, 358);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		mostrarTabla();
	}
}

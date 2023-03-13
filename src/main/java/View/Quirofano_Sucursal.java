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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Quirofano_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbQuirofano;
	private JComboBox cbSucursal;
	private JTable table;
	
	class ComboItem                  //Clase usada para armar el ComboBox
	{
	    private String key;         //Label visible del ComboBox
	    
	    private String value;       //Valor del ComboBox

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
	
	public DefaultComboBoxModel cargarQuirofano() {             //Carga el ComboBox quirofano
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();         //Realiza la conexión
			
			String SSQL = "SELECT * FROM Operating_Room ORDER BY id_Operating_Room";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("room_Number"),result.getString("id_Operating_Room")));       //El elemento del ComboBox recibe el número del quirófano como label y el id como valor
				
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
	
	public DefaultComboBoxModel cargarSucursal() {       //Carga el ComboBox sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();         //Realiza la conexión
			String SSQL = "Select *\r\n"			//Sentencia Sql
					+ "FROM Branch\r\n"
					+ "ORDER BY Branch.address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));        //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));      //El elemento del ComboBox recibe la dirección de la sucursal como label y el id como valor
				
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quirofano_Sucursal frame = new Quirofano_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int existeRel(Object quirofano, Object sucursal) {        //Este procedimiento revisa si ya existe la relacipin entre el quirófano y la sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Branch_Operating_R WHERE id_Operating_Room = ? AND id_Branch = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) quirofano);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);       //Si ya existe, la variable se pone en 1
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
	
void mostrarTabla(){            // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Sucursal","Quirófano"});        //Nombre de las columnas
        
        table.setModel(modelo);       //Setea el modelo
        
        
        
        String datos[] = new String[3];     //Declara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_BOR, address, room_Number\r\n"
        			+ "FROM Rel_Branch_Operating_R\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Branch_Operating_R.id_Branch\r\n"
        			+ "INNER JOIN Operating_Room ON Operating_Room.id_Operating_Room = Rel_Branch_Operating_R.id_Operating_Room;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);     //Setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);     // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	private void limpiar() {              //Este procedimiento limpia los campos
		cbQuirofano.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}

	/**
	 * Create the frame.
	 */
	public Quirofano_Sucursal(final String perfil) {             //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar quirófano con sucursal");
		lblTitulo.setBounds(113, 11, 212, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblQuirofano = new JLabel("Quirófano");
		lblQuirofano.setBounds(469, 64, 66, 14);
		contentPane.add(lblQuirofano);
		
		cbQuirofano = new JComboBox();
		cbQuirofano.setBounds(554, 60, 170, 22);
		contentPane.add(cbQuirofano);
		cbQuirofano.setModel(cargarQuirofano());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(469, 122, 56, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(554, 118, 170, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");           //Este botón permite agregar un quirófano a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Object quirofano = cbQuirofano.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Branch_Operating_R (id_Branch,id_Operating_Room) VALUES (?,?)" );
					
					
					if (((ComboItem) quirofano).getValue() == "") {                        //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un quirófano");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si ya existe la relación
							if(existeRel(((ComboItem) cbQuirofano.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Quirófano ya se encuentra en la sucursal");
							}else {
								ps.setString(1, ((ComboItem) sucursal).getValue());
								ps.setString(2, ((ComboItem) quirofano).getValue());
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Quirófano añadido a sucursal");                            //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha asociado el quirófano "+quirofano+" a la sucursal "+sucursal);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al añadir quirófano");        //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(490, 180, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");         //Este botón elimina la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();     //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Branch_Operating_R WHERE id_BOR = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Quirofano eliminado de sucursal"); //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el quirofano "+table.getValueAt(fila,2).toString()+" de la sucursal "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar quirofano");     //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");    //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEliminar.setBounds(631, 180, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");                 //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano(perfil);
				tq.setVisible(true);		//Abre la ventana Tabla_Quirofano
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

	public Quirofano_Sucursal() {
		// TODO Auto-generated constructor stub
	}
}

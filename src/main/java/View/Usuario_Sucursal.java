package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Model.ControlFiles;
import View.Sucursal_Producto.ComboItem;

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

public class Usuario_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbUsuario;
	private JComboBox cbSucursal;
	private JTable table;


	class ComboItem           //Clase utilizada para armar un ComboBox
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
	

	public DefaultComboBoxModel cargarUsuario() {  //Carga el ComboBox usuario
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try { 
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT * FROM Users ORDER BY id_User";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));     //El primer elemento está en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("username"),result.getString("id_User")));      //El elemento del ComboBox recibe el nombre de usuario como label y el id como valor
				
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
	
	public DefaultComboBoxModel cargarSucursal() {        //Carga el ComboBox sucursal  
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			String SSQL = "Select *\r\n"		//sentencia Sql
					+ "FROM Branch";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));       //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));    //El elemento del ComboBox recibe la dirección de la sucursal como label y el id como valor
				
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
	
	void mostrarTabla(){               // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Usuario","Sucursal"});    //Nombre de las columnas
       
        table.setModel(modelo);         //Setea el modelo
        
        
        
        String datos[] = new String[3];       //Decalara que va a haber 3 columnas
       
        try {
        	Connection con = Connect.getConexion();      //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT id_UB, username, address\r\n"
        			+ "FROM Rel_Users_Branch\r\n"
        			+ "INNER JOIN Users ON Users.id_User = Rel_Users_Branch.id_User\r\n"
        			+ "INNER JOIN Branch ON Branch.id_Branch = Rel_Users_Branch.id_Branch;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                    //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);         // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Usuario_Sucursal frame = new Usuario_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object usuario, Object sucursal) {   // Es una funcion que determina si ya existe la relacion entre usuario y sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();       //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Users_Branch WHERE id_User = ? AND id_Branch = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) usuario);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);           // si la relacion ya existe, entonces la variable se pone en 1
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
		cbUsuario.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */ 
	public Usuario_Sucursal() {                             //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png"))); //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar usuario con sucursal");
		lblTitulo.setBounds(115, 11, 184, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(464, 58, 65, 14);
		contentPane.add(lblUsuario);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(540, 54, 184, 22);
		contentPane.add(cbUsuario);
		cbUsuario.setModel(cargarUsuario());
		
		JLabel lblNewLabel = new JLabel("Sucursal");
		lblNewLabel.setBounds(464, 119, 65, 14);
		contentPane.add(lblNewLabel);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(540, 115, 184, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");                //Este botón permite agregar un usuario a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object usuario = cbUsuario.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();   //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Users_Branch (id_User,id_Branch) VALUES (?,?)" );
					
					
					if (((ComboItem) usuario).getValue() == "") {                       //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un usuario");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si existe la relación
							if(existeRel(((ComboItem) cbUsuario.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Usuario ya se encuentra en la sucursal");
							}else {
								ps.setString(1, ((ComboItem) usuario).getValue());
								ps.setString(2, ((ComboItem) sucursal).getValue());
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario colocado");           //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha colocado el usuario "+usuario+" en la sucursal "+sucursal);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar usuario");       //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(487, 174, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");            //Este botón permite eliminar la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();        //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Users_Branch WHERE id_UB = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Usuario eliminado de sucursal");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el usuario "+table.getValueAt(fila,1).toString()+" de la sucursal "+table.getValueAt(fila,2).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar usuario");     //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");      //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(628, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");         //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 36, 393, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}
}

package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Ciudad.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Sucursal extends JFrame {

	private JPanel contentPane;
	private JTextField txtDireccion;


	class ComboItem                 //Clase usada para armar el combobox
	{
	    private String key;        //Label visible del ComboBox
	    
	    private String value;      //Valor del ComboBox

	    public ComboItem(String key, String value)           //Genera el label que se verá en el combobox y el valor del objeto seleccionado
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
	
	public DefaultComboBoxModel cargarDireccion() {       //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();		//Realiza la Conexión
			String SSQL = "SELECT *\r\n"		//Sentencia Sql
					+ "FROM Address\r\n"
					+ "INNER JOIN City ON Address.id_City = City.id_City";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address_Name")+" - "+result.getString("address_Number")+" - "+result.getString("name"),result.getString("id_Address")));
				
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
					Sucursal frame = new Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeSucursal(String direccion) {          //Este procedimiento revisa si ya existe la sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Branch WHERE id_Branch= ? ;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, direccion);

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
	
	
	private void limpiar() {           //Este procedimiento limpia los campos
		txtDireccion.setText("");;
		
	}
	/**
	 * Create the frame.
	 */
	public Sucursal() {          //Crea la ventana
		setTitle("Sucursal"); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sucursales");
		lblTitulo.setBounds(144, 11, 86, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(36, 76, 67, 14);
		contentPane.add(lblDireccion);
		
		JButton btnAgregar = new JButton("Agregar");               //Este botón permite agregar una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String direccion = txtDireccion.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();     //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Branch (address) VALUES (?)" );
					
					
					
						if(existeSucursal(direccion)!=0) {       //Revisa si ya existe la sucursal
							
						JOptionPane.showMessageDialog(null, "Sucursal ya existe");
					}else {
						ps.setString(1, direccion);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Sucursal guardada");              //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha agregado la sucursal "+direccion);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar sucursal");     //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(141, 117, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Sucursales ts = new Tabla_Sucursales();
				ts.setVisible(true);		//Abre la ventana Tabla_Sucursales
				dispose();
			}
		});
		btnVolver.setBounds(246, 158, 89, 23);
		contentPane.add(btnVolver);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(113, 73, 186, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
	}
}
package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Sucursal.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Modificar_Sucursal extends JFrame {
	
	//Esta ventana no es utilizada en la versión actual
	
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDireccion;

	class ComboItem               //Clase usada para armar el ComboBox
	{
	    private String key;        //Label visible del ComboBox
	    
	    private String value;      //Valor del ComboBox

	    public ComboItem(String key, String value)       //Genera el label que se verá en el combobox y el valor del objeto seleccionado
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
	
	public DefaultComboBoxModel cargarDireccion() {     //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT *\r\n"
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
					Modificar_Sucursal frame = new Modificar_Sucursal();
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
	public Modificar_Sucursal(String sucursal) {              //Crea la ventana recibiendo como parámetro el id de la sucursal
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 352, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));      //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 77, 67, 14);
		contentPane.add(lblDireccion);
		
		JButton btnVolver = new JButton("Volver");                //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Sucursales ts = new Tabla_Sucursales();
				ts.setVisible(true);		//Abre la ventana Tabla_Sucursales
				dispose();
			}
		});
		btnVolver.setBounds(191, 130, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");         //Este boton modifica la sucursal de acuerdo a los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				String direccion = txtDireccion.getText();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Branch SET address = ? WHERE id_Branch = ?" );
					
					
						
						ps.setString(1, direccion);
						ps.setInt(2, id);
						
					
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Sucursal modificada");              //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log, despues vuelve a la ventana Tabla_Sucursales
		                ControlFiles.addContent("Se ha modificado la sucursal "+direccion);
		                Tabla_Sucursales ts = new Tabla_Sucursales();
						ts.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar sucursal");      //En caso de fallar, lo avisa en pantalla
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
		btnModificar.setBounds(57, 130, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(10, 28, 67, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		JLabel lblTitulo = new JLabel("Modificar sucursal");
		lblTitulo.setBounds(115, 31, 148, 14);
		contentPane.add(lblTitulo);
		
		txtId.setText(sucursal);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(87, 74, 193, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
	}


	public Modificar_Sucursal() {
		// TODO Auto-generated constructor stub
	}
}

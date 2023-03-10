package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;
import View.Pedidos.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Modificar_Pedido extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JTextField txtId;
	private JComboBox cbProducto;
	private JComboBox cbSucursal;
	
	class ComboItem                         //Clase utilizada para armar el ComboBox
	{
	    private String key;                //Label visible del ComboBox
	    
	    private String value;               //Valor del ComboBox

	    public ComboItem(String key, String value)                 //Genera el label que se verá en el combobox y el valor del objeto seleccionado
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
	
	
	public DefaultComboBoxModel cargarProducto() {          //Carga el ComboBox Producto
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();                        //Realiza la conexión
			String SSQL = "SELECT * FROM Product ORDER BY id_Product";    //Sentencia sql    
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));                        //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("product_Name"),result.getString("id_Product")));            //El elemento recibe el nombre del producto como label y el id como valor
				
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
	
	public DefaultComboBoxModel cargarSucursal() {             //Carga el combobox sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();              //Realiza la conexión
			String SSQL = "Select *\r\n"		//Sentencia sql
					+ "FROM Branch\r\n";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));              //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));           //El elemento recibe la dirección y el id de la sucursal
				
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
					Modificar_Pedido frame = new Modificar_Pedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargarCampos(String pedido) {             //Carga los campos recibiendo el id del pedido como parámetro
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(pedido);
		
		try {
			cn = (Connection) Connect.getConexion();                            //Realiza la conexión
			String SSQL = "SELECT quantity FROM Orders WHERE id_Order = ?";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                            //Carga los campos según los resultados en la base de datos
			txtCantidad.setText(result.getString(1));
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	/**
	 * Create the frame.
	 */
	public Modificar_Pedido(String pedido) {                     //Crea la ventana recibiendo como parámetro el id del pedido
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));           //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(35, 61, 59, 14);
		contentPane.add(lblProducto);
		
		 cbProducto = new JComboBox();
		cbProducto.setBounds(125, 57, 157, 22);
		contentPane.add(cbProducto);
		cbProducto.setModel(cargarProducto());
		
		 cbSucursal = new JComboBox();
		cbSucursal.setBounds(125, 106, 157, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(35, 110, 59, 14);
		contentPane.add(lblSucursal);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(35, 164, 59, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(125, 161, 157, 20);
		contentPane.add(txtCantidad);
		
		JButton btnVolver = new JButton("Volver");         //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Pedido tp = new Tabla_Pedido();
				tp.setVisible(true);			//Abre la ventana Tabla_Pedido
				dispose();
			}
		});
		btnVolver.setBounds(193, 224, 89, 23);
		contentPane.add(btnVolver);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(35, 11, 26, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		JButton btnModificar = new JButton("Modificar");                    //Este boton modifica el pedido según los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				int cantidad = Integer.parseInt(txtCantidad.getText());
				Object producto = cbProducto.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Orders SET id_Product = ? , id_Branch = ? , quantity = ? WHERE id_Order = ?" );
					
					
					if (((ComboItem) producto).getValue() == "") {                         //Revisa que los ComboBox no estén en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un producto");
					}else {
						if (((ComboItem) sucursal).getValue() == "") {
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							
						
								ps.setString(1, ((ComboItem) producto).getValue());
								ps.setString(2, ((ComboItem) sucursal).getValue());
								ps.setInt(3, cantidad);
								ps.setInt(4, id);
						
					}
						}
						
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Pedido modificado");                //Si fue exitoso, lo avisa por pantalla mediante un mensaje y lo añade al log, después regresa a la ventana Tabla_Pedido 
		                ControlFiles.addContent("Se ha modificado un pedido de "+producto);
		                Tabla_Pedido tp = new Tabla_Pedido();
						tp.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar pedido");        //En caso de fallar, lo avisa por pantalla
		                
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(35, 224, 89, 23);
		contentPane.add(btnModificar);
		
		cargarCampos(pedido);
		txtId.setText(pedido);
	}

	public Modificar_Pedido() {
		// TODO Auto-generated constructor stub
	}
}

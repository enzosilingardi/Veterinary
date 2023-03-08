package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Connect;
import Model.ControlFiles;

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
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Sucursal_Producto extends JFrame {

	private JPanel contentPane;
	private JComboBox cbProducto;
	private JComboBox cbSucursal;
	private JTextField txtCantidad;

	class ComboItem               //Clase usada para armar el ComboBox
	{
	    private String key;        //Label visible del ComboBox
	    
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
	
	public DefaultComboBoxModel cargarProducto() {      //Carga el ComboBox producto
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT * FROM Product ORDER BY id_Product";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));    //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("product_Name"),result.getString("id_Product")));     //El elemento del ComboBox recibe el nombre del producto como label y el id como valor
				
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
	
	public DefaultComboBoxModel cargarSucursal() {     //Carga el combobox sucursal
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();    //Realiza la conexión
			String SSQL = "Select *\r\n"
					+ "FROM Branch\r\n"
					+ "ORDER BY Branch.address";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));       //El primer elemento es en blanco
			
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
					Sucursal_Producto frame = new Sucursal_Producto();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public int existeRel(Object producto, Object sucursal) {        //Este procedimiento revisa si ya existe la relación
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Branch_Product WHERE id_Product = ? AND id_Branch = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) producto);
			pst.setString(2, (String) sucursal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);      //Si ya existe, la variable se pone en 1
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
		cbProducto.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		txtCantidad.setText("");
	}
	/**
	 * Create the frame.
	 */
	public Sucursal_Producto() {        //Crea la ventana
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar producto con sucursal");
		lblTitulo.setBounds(113, 11, 209, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(51, 53, 60, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(127, 49, 190, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(51, 119, 60, 14);
		contentPane.add(lblProducto);
		
		cbProducto = new JComboBox();
		cbProducto.setBounds(127, 115, 190, 22);
		contentPane.add(cbProducto);
		cbProducto.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");           //Este botón permite agregar un producto a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Object producto = cbProducto.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				
				int result = 0;
				
				
				
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Branch_Product (id_Branch,id_Product,amount) VALUES (?,?,?)" );
					
					
					if (((ComboItem) producto).getValue() == "") {                     //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un Producto");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si la relación ya existe
							if(existeRel(((ComboItem) cbProducto.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Producto ya se encuentra en la sucursal");
							}else {
								ps.setString(1, ((ComboItem) sucursal).getValue());
								ps.setString(2, ((ComboItem) producto).getValue());
								
								
								
								if(cantidad >250000) {      //Revisa si la cantidad exede el límite
									JOptionPane.showMessageDialog(null, "Número excede el límite (250000)",null,JOptionPane.ERROR_MESSAGE);
									
									
								}else if(cantidad<0){        //Revisa si en la cantidad hay números negativos
									JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);
									
							
								}else {
									
									ps.setInt(3, cantidad);
							
								}
								
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Producto colocado");         //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		
		                ControlFiles.addContent("Se ha asociado el producto "+producto+" con la sucursal "+sucursal);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar producto");    //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(142, 241, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Stock ts = new Tabla_Stock();
				ts.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(270, 296, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(51, 179, 60, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					e.consume();
				}
			}
		});
		txtCantidad.setBounds(127, 176, 190, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
	}
}

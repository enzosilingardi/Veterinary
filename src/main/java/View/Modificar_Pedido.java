package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ComboBoxes;
import Control.Connect;
import Control.Consulta_Pedido;
import Model.ComboItem;
import Model.ControlFiles;

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
	public static JTextField txtCantidad;
	private JTextField txtId;
	private JComboBox cbProducto;
	private JComboBox cbSucursal;
	

	
	
	public DefaultComboBoxModel cargarProducto() {          //Carga el ComboBox Producto

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBProducto(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarSucursal() {             //Carga el combobox sucursal

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();   
		
		ComboBoxes.CBSucursal(modelo);
		
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
				String nombreProducto = cbProducto.getSelectedItem().toString();
				
				
					
					if (((ComboItem) producto).getValue() == "") {                         //Revisa que los ComboBox no estén en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un producto");
					}else {
						if (((ComboItem) sucursal).getValue() == "") {
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							
						Consulta_Pedido.modificar(((ComboItem) cbProducto.getSelectedItem()).getValue(), ((ComboItem) cbSucursal.getSelectedItem()).getValue(), cantidad, id, nombreProducto);
						
					}
						}
						
						
		                Tabla_Pedido tp = new Tabla_Pedido();
						tp.setVisible(true);
						dispose();
		          
			}
		});
		btnModificar.setBounds(35, 224, 89, 23);
		contentPane.add(btnModificar);
		
		Consulta_Pedido.cargar(pedido);
		txtId.setText(pedido);
	}

	public Modificar_Pedido() {
		// TODO Auto-generated constructor stub
	}
}

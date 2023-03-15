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

public class Pedidos extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JComboBox cbProducto;
	private JComboBox cbSucursal;

	
	public DefaultComboBoxModel cargarProducto() {           //Carga el ComboBox producto

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBProducto(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarSucursal() {      //Carga el ComboBox sucursal

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
					Pedidos frame = new Pedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	private void limpiar() {              //Este procedimiento limpia los campos
		cbProducto.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		txtCantidad.setText("");
		
	}
	/**
	 * Create the frame.
	 */
	public Pedidos() {           //Crea la ventana
		setTitle("Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Pedidos");
		lblTitulo.setBounds(154, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(57, 60, 59, 14);
		contentPane.add(lblProducto);
		
		cbProducto = new JComboBox();
		cbProducto.setBounds(147, 56, 157, 22);
		contentPane.add(cbProducto);
		cbProducto.setModel(cargarProducto());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(57, 109, 59, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(147, 105, 157, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(57, 163, 59, 14);
		contentPane.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(147, 160, 157, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");          //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Pedido tp = new Tabla_Pedido();
				tp.setVisible(true);		//Abre la ventana Tabla_Pedido
				dispose();
			}
		});
		btnVolver.setBounds(270, 268, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");              //Este botón permite agregar un pedido de acuerdo a los datos ingresados
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int cantidad = Integer.parseInt(txtCantidad.getText());
				Object producto = cbProducto.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				String nombreProducto = cbProducto.getSelectedItem().toString();
				String nombreSucursal = cbSucursal.getSelectedItem().toString();
				
					
					if (((ComboItem) producto).getValue() == "") {                       //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un producto");
					}else {
						if (((ComboItem) sucursal).getValue() == "") {
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							if(Consulta_Pedido.existe(((ComboItem) cbProducto.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
						         //Revisa si ya existe el pedido
								JOptionPane.showMessageDialog(null, "Pedido ya existe");
					
							}else {
								
								Consulta_Pedido.agregar(((ComboItem) cbProducto.getSelectedItem()).getValue(), ((ComboItem) cbSucursal.getSelectedItem()).getValue(), cantidad, nombreProducto, nombreSucursal);
								
						
					}


						}
					}
						
						
					
				
			}
		});
		btnAgregar.setBounds(135, 223, 89, 23);
		contentPane.add(btnAgregar);
	}
}

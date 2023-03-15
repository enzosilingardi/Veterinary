package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Stock;
import Model.ComboItem;
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


	
	public DefaultComboBoxModel cargarProducto() {      //Carga el ComboBox producto
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBProducto(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarSucursal() {     //Carga el combobox sucursal
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBSucursal(modelo);
		
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
				String productoNombre = cbProducto.getSelectedItem().toString();
				String sucursalNombre = cbSucursal.getSelectedItem().toString();
				
					if (((ComboItem) producto).getValue() == "") {                     //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un Producto");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si la relación ya existe
							if(Control_Stock.existe(((ComboItem) cbProducto.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Producto ya se encuentra en la sucursal");
							}else {
								
								Control_Stock.agregar(((ComboItem) cbSucursal.getSelectedItem()).getValue(), ((ComboItem) cbProducto.getSelectedItem()).getValue(), cantidad, productoNombre, sucursalNombre);
								
							}
						}
						
						
					}
					
					limpiar();
					
				
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

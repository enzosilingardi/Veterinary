package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Control.Control_Proveedor;

public class Buscar_Proveedor_ModPro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtCuit;
	private JTextField txtDir;
	private JTextField txtId;

	void mostrarTabla(){         //Tabla que muestra los proveedores
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Proveedor.tablaBus(modelo, table);
    }
	
	void mostrarTablaParametro(){      //Muestra la tabla segun los parametros recibidos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String url;
    	
    	if(txtCuit.getText().isBlank() && txtDir.getText().isBlank()) {                     // Realiza la consulta, Dependiendo de cuales campos tengan algo escrito y cuales esten vacios
    		url = "Select id_Provider, provider_Name, cuit, address\r\n"
        			+ "FROM Provider WHERE provider_Name ='"+txtNombre.getText()+"';" ;
    	}else {
    		if(txtNombre.getText().isBlank() && txtDir.getText().isBlank()) {
    			url =  "Select id_Provider, provider_Name, cuit, address\r\n"
            			+ "FROM Provider WHERE cuit ='"+txtCuit.getText()+"';" ;
        	} else {
        		if(txtNombre.getText().isBlank() && txtCuit.getText().isBlank()) {
        			url = "Select id_Provider, provider_Name,  cuit, address\r\n"
                			+ "FROM Provider WHERE address ='"+txtDir.getText()+"';" ;
        		
        	} else {
        		if(txtDir.getText().isBlank()) {
        			url = "Select id_Provider, provider_Name, cuit, address\r\n"
                			+ "FROM Provider WHERE provider_Name ='"+txtNombre.getText()+"' AND cuit ='"+txtCuit.getText() +"';" ;
        	} else {
        		if(txtCuit.getText().isBlank()) {
        			url = "Select id_Provider, provider_Name, cuit, address\r\n"
                			+ "FROM Provider WHERE provider_Name ='"+txtNombre.getText()+"' AND address ='"+txtDir.getText() +"';" ;
        	} else {
        		if(txtNombre.getText().isBlank()) {
        			url = "Select id_Provider, provider_Name, cuit, address\r\n"
                			+ "FROM Provider WHERE cuit ='"+txtCuit.getText()+"' AND address ='"+txtDir.getText() +"';" ;
        	} else {
        		url = "Select id_Provider, provider_Name, cuit, address\r\n"
            			+ "FROM Provider WHERE cuit ='"+txtCuit.getText()+"' AND address ='"+txtDir.getText() +"' AND provider_Name ='"+txtNombre.getText()+"';";
        	}
    	}
        	}
        	}
        	}
    	}
    	
        Control_Proveedor.tablaBusPar(modelo, table, url);
    
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar_Proveedor_ModPro frame = new Buscar_Proveedor_ModPro();
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
	public Buscar_Proveedor_ModPro(final String id) {       //Crea la ventana recibiendo por parámetro el id del producto
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 82, 544, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");             //Este botón cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Producto producto = new Modificar_Producto(id);
				producto.setVisible(true);		//Abre la ventana Modificar_Producto recibiendo como parámetro el id del producto
				dispose();
			}
		});
		btnVolver.setBounds(458, 407, 149, 23);
		contentPane.add(btnVolver);
		
		JButton btnSeleccionar = new JButton("Seleccionar");    //Este boton permite seleccionar un proveedor y la devuelve a la ventana Modificar_Producto
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String nom = table.getValueAt(fila,1).toString();
				String idP = table.getValueAt(fila,0).toString();
				
				Modificar_Producto producto = new Modificar_Producto(id,idP,nom);       //Abre la ventana Modificar_Producto recibiendo como parámetro el id del producto, el id del proveedor y su nombre
				producto.setVisible(true);
				dispose();
				
			}
		});
		btnSeleccionar.setBounds(290, 407, 158, 23);
		contentPane.add(btnSeleccionar);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(37, 39, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(35, 11, 46, 14);
		contentPane.add(lblNombre);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(156, 39, 86, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		txtDir = new JTextField();
		txtDir.setBounds(274, 39, 86, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(156, 11, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblDir = new JLabel("Dirección");
		lblDir.setBounds(274, 11, 68, 14);
		contentPane.add(lblDir);
		
		JButton btnBuscar = new JButton("Buscar");          //Filtra los resultados vistos en la tabla
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarTablaParametro();
			}
		});
		btnBuscar.setBounds(419, 7, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");          //Limpia los campos
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNombre.setText("");
				txtCuit.setText("");
				txtDir.setText("");
				
				mostrarTabla();
			}
		});
		btnLimpiar.setBounds(419, 38, 89, 23);
		contentPane.add(btnLimpiar);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(518, 8, 40, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		txtId.setText(id);
		
		mostrarTabla();
	}

	public Buscar_Proveedor_ModPro() {
		// TODO Auto-generated constructor stub
	}

}

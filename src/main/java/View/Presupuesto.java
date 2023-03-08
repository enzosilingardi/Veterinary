package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import Control.Connect;
import View.Factura.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Presupuesto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNro;
	private JDateChooser txtFecha;
	private JTextField txtTotal;
	private JComboBox cbEmisor;
	private JComboBox cbCliente;
	private JComboBox cbPunto;
	private JTextField txtCliente;
	private JTextField txtDom;
	private JTextField txtDni;
	private JTextField txtDir;
	private JTable table;
	private JComboBox cbPro;
	private JTextField txtCuit;
	private JTextField txtEmisor;
	
	class ComboItem         //Clase utilizada para armar el ComboBox
	{
	    private String key;      //Label visible del ComboBox
	    
	    private String value;      //Valor del ComboBox

	    public ComboItem(String key, String value)          //Genera el label que se verá en el ComboBox y el valor del objeto seleccionado
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
	

	public DefaultComboBoxModel cargarCliente() {       //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Client ORDER BY id_Client";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name")+" "+result.getString("surname"),result.getString("id_Client")));
				
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
	

	public DefaultComboBoxModel cargarUsuario() {          //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Users ORDER BY id_User";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("name")+" "+result.getString("surname"),result.getString("id_User")));
				
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
	
	public DefaultComboBoxModel cargarSucursal() {           //Este ComboBox no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "Select *\r\n"
					+ "FROM Branch";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("id_Branch"),result.getString("id_Branch")));
				
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
	

	public DefaultComboBoxModel cargarProducto() {         //Carga el ComboBox Producto
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			
			String SSQL = "SELECT * FROM Product ORDER BY id_Product";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));      //El primer elemento es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("product_Name"),result.getString("sale_Price")));      //El elemento del ComboBox recibe el nombre del producto como label y el precio del producto como valor
				
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
	
	void cargarEmisor() {                   //Este proceso carga los datos del emisor actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		
		try {
			cn = (Connection) Connect.getConexion();      //Realiza la conexión
			String SSQL = "SELECT * FROM Emitter";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			
			
			while (result.next()) {                            //Carga los campos según los resultados de la base de datos
				txtEmisor.setText(result.getString("name"));
				txtCuit.setText(result.getString("cuit"));
				txtDir.setText(result.getString("address"));
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
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
					Presupuesto frame = new Presupuesto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void totalV() {            //Este procedimiento calcula el total de la factura
		float t = 0;
		float p1 = 0;
		float p2 = 0;
		float p3 = 0;
		
		
		if (table.getRowCount() > 0) {                                       //Recorre la tabla y suma los precios de los productos multiplicados por su cantidad
			for (int i = 0; i <= table.getRowCount()-1; i++) {
				p1 = Float.parseFloat(table.getValueAt(i, 2).toString());
				p2 = Float.parseFloat(table.getValueAt(i, 1).toString());
				p3 = p1*p2;
				t += p3;
				
			}
			
			txtTotal.setText(String.valueOf(t));        //Carga el valor total al campo
		}
	}

	public void generar(String nombre) throws FileNotFoundException,DocumentException {           //Genera un PDF con los datos
		//Revisa si todos los campos estan llenos
		if(!(txtNro.getText().isEmpty()  || txtTotal.getText().isEmpty() || txtEmisor.getText().isEmpty() || cbPunto.getSelectedItem().toString().equals("") || txtCliente.getText().isEmpty() || cbEmisor.getSelectedItem().toString().equals("") || txtCuit.getText().isEmpty() || txtDom.getText().isEmpty() || txtDir.getText().isEmpty() || txtDni.getText().isEmpty() )) {
			Object punto = cbPunto.getSelectedItem();
			
			FileOutputStream archivo = new FileOutputStream("c:/rsc/Presupuesto "+nombre+".pdf");     //Genera la ruta del pdf
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);         //Prepara el documento
			documento.open();
			
			Paragraph parrafo = new Paragraph("Presupuesto");       //Añade el Titulo
			parrafo.setAlignment(1);
			documento.add(parrafo);
			
			//Escribe todos los datos
			
			documento.add(new Paragraph("Punto de Venta: "+((ComboItem) punto).getValue()));      
			documento.add(new Paragraph("Nro de Comprobante: "+txtNro.getText()));
			documento.add(new Paragraph("Emisor: "+txtEmisor.getText()));
			documento.add(new Paragraph("CUIT: "+txtCuit.getText()));
			documento.add(new Paragraph("Direccion Fiscal: "+txtDir.getText()));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph("Cliente: "+txtCliente.getText()));
			documento.add(new Paragraph("DNI: "+txtDni.getText()));
			documento.add(new Paragraph("Domicilio: "+txtDom.getText()));
			documento.add(new Paragraph(" "));
			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
	          
            for (int i = 0; i < table.getColumnCount(); i++) {           //Agrega la tabla con los productos
                pdfTable.addCell(table.getColumnName(i));
            }
      
            for (int rows = 0; rows < table.getRowCount(); rows++) {
                for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

                }
            }
            documento.add(pdfTable);
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph("Precio Total: "+txtTotal.getText()));
			
			
			documento.close();
			
			JOptionPane.showMessageDialog(null, "Presupuesto Creado");    //Muestra un mensaje en pantala indicando que se creó con exito
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");   //Si no todos los campos están llenos, lo muestra en pantalla 
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Presupuesto(String nom, String dni, String dir) {       //Crea la ventana recibiendo por parámetro el nombre, dni y dirección del cliente
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 753, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setBackground(new Color(145, 226, 247));
		contentPane.setLayout(null);
		
		JLabel lblNro = new JLabel("Número de comprobante");
		lblNro.setBounds(283, 14, 147, 14);
		contentPane.add(lblNro);
		
		txtNro = new JTextField();
		txtNro.setColumns(10);
		txtNro.setBounds(418, 11, 107, 20);
		contentPane.add(txtNro);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 62, 60, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(375, 62, 46, 14);
		contentPane.add(lblEmisor);
		
		
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(10, 14, 114, 14);
		contentPane.add(lblPunto);
		
		JComboBox cbPunto = new JComboBox();
		cbPunto.setBounds(117, 10, 114, 22);
		contentPane.add(cbPunto);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(375, 176, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(493, 170, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(596, 262, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(596, 287, 86, 20);
		contentPane.add(txtTotal);
		
		JButton btnGenerar = new JButton("Generar Presupuesto");       //Este botón genera el presupuesto
		btnGenerar.setBorder(null);
		btnGenerar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					generar(txtNro.getText());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGenerar.setBounds(349, 527, 197, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");                //Cierra la ventana
		btnVolver.setBorder(null);
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(556, 527, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(117, 59, 107, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblDom = new JLabel("Domicilio Cliente");
		lblDom.setBounds(10, 99, 107, 14);
		contentPane.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setColumns(10);
		txtDom.setBounds(117, 96, 86, 20);
		contentPane.add(txtDom);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(117, 139, 97, 20);
		contentPane.add(txtDni);
		
		JLabel lblDni = new JLabel("DNI Cliente");
		lblDni.setBounds(10, 142, 86, 14);
		contentPane.add(lblDni);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(493, 139, 114, 20);
		contentPane.add(txtDir);
		
		JLabel lblDir = new JLabel("Dirección Empresa");
		lblDir.setBounds(375, 142, 114, 14);
		contentPane.add(lblDir);
		
		cbPro = new JComboBox();
		cbPro.setBounds(10, 219, 114, 22);
		contentPane.add(cbPro);
		cbPro.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");             //Agrega un producto a la tabla
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object valor = cbPro.getSelectedItem();
				model.addRow(new Object[]{cbPro.getSelectedItem().toString(),"",((ComboItem) valor).getValue()});
			}
		});
		btnAgregar.setBounds(158, 219, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");          //Remueve el producto seleccionado de la tabla
		btnRemover.setBorder(null);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				((DefaultTableModel)table.getModel()).removeRow(fila);
			}
		});
		btnRemover.setBounds(259, 219, 89, 23);
		contentPane.add(btnRemover);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 263, 410, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();                       //Crea la tabla donde se colocarán los productos
		table.setModel(new DefaultTableModel( 
			new Object[][] {
			},
			new String[] {
				"Producto", "Cantidad", "Precio Unitario"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(375, 99, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(493, 96, 109, 20);
		contentPane.add(txtCuit);
		
		JButton btnBuscar = new JButton("Buscar");       //Este botón permite buscar un cliente
		btnBuscar.setBorder(null);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente_Pre bcp = new Buscar_Cliente_Pre();
				bcp.setVisible(true);
				
				dispose();
				
			}
		});
		btnBuscar.setBounds(234, 58, 89, 23);
		contentPane.add(btnBuscar);
		
		txtCliente.setText(nom);    //Setea los parámetros en los campos
		txtDni.setText(dni);
		txtDom.setText(dir);
		
		JButton btnEditar = new JButton("Editar emisor");       //Este botón permite ediar el emisor
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emisor_Pre ep = new Emisor_Pre();
				ep.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBorder(null);
		btnEditar.setBounds(610, 58, 117, 23);
		contentPane.add(btnEditar);
		
		txtEmisor = new JTextField();
		txtEmisor.setBounds(493, 59, 107, 20);
		contentPane.add(txtEmisor);
		txtEmisor.setColumns(10);
		
		JButton btnCalcular = new JButton("Calcular total");        //Calcula el total de los productos seleccionados
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalV();
			}
		});
		btnCalcular.setBorder(null);
		btnCalcular.setBounds(578, 325, 129, 23);
		contentPane.add(btnCalcular);
		
		cargarEmisor();
		
	}


	public Presupuesto() {           //Crea la ventana
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 753, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));   //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setBackground(new Color(145, 226, 247));
		contentPane.setLayout(null);
		
		JLabel lblNro = new JLabel("Número de comprobante");
		lblNro.setBounds(283, 14, 147, 14);
		contentPane.add(lblNro);
		
		txtNro = new JTextField();
		txtNro.setColumns(10);
		txtNro.setBounds(418, 11, 107, 20);
		contentPane.add(txtNro);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 62, 60, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(375, 62, 46, 14);
		contentPane.add(lblEmisor);
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(10, 14, 114, 14);
		contentPane.add(lblPunto);
		
		JComboBox cbPunto = new JComboBox();
		cbPunto.setBounds(117, 10, 114, 22);
		contentPane.add(cbPunto);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(375, 176, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(493, 170, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(596, 262, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(596, 287, 86, 20);
		contentPane.add(txtTotal);
		
		JButton btnGenerar = new JButton("Generar Presupuesto");     //Este botón genera el presupuesto
		btnGenerar.setBorder(null);
		btnGenerar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					generar(txtNro.getText());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGenerar.setBounds(349, 527, 197, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");             //Cierra la ventana
		btnVolver.setBorder(null);
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(556, 527, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(117, 59, 107, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblDom = new JLabel("Domicilio Cliente");
		lblDom.setBounds(10, 99, 107, 14);
		contentPane.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setColumns(10);
		txtDom.setBounds(117, 96, 86, 20);
		contentPane.add(txtDom);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(117, 139, 97, 20);
		contentPane.add(txtDni);
		
		JLabel lblDni = new JLabel("DNI Cliente");
		lblDni.setBounds(10, 142, 86, 14);
		contentPane.add(lblDni);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(493, 127, 114, 20);
		contentPane.add(txtDir);
		
		JLabel lblDir = new JLabel("Dirección Empresa");
		lblDir.setBounds(375, 130, 114, 14);
		contentPane.add(lblDir);
		
		cbPro = new JComboBox();
		cbPro.setBounds(10, 219, 114, 22);
		contentPane.add(cbPro);
		cbPro.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");          //Agrega un producto a la tabla
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object valor = cbPro.getSelectedItem();
				model.addRow(new Object[]{cbPro.getSelectedItem().toString(),"",((ComboItem) valor).getValue()});
			}
		});
		btnAgregar.setBounds(158, 219, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");               //Remueve el producto seleccionado de la tabla
		btnRemover.setBorder(null);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				((DefaultTableModel)table.getModel()).removeRow(fila);
			}
		});
		btnRemover.setBounds(259, 219, 89, 23);
		contentPane.add(btnRemover);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 263, 410, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();                       //Crea la tabla donde se colocarán los productos
		table.setModel(new DefaultTableModel( 
			new Object[][] {
			},
			new String[] {
				"Producto", "Cantidad", "Precio Unitario"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(375, 99, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(493, 96, 109, 20);
		contentPane.add(txtCuit);
		
		JButton btnBuscar = new JButton("Buscar");                //Este botón permite buscar un cliente
		btnBuscar.setBorder(null);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente_Pre bcp = new Buscar_Cliente_Pre();
				bcp.setVisible(true);
				
				dispose();
				
			}
		});
		btnBuscar.setBounds(234, 58, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnEditar = new JButton("Editar emisor");         //Este botón permite editar el emisor
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emisor_Pre ep = new Emisor_Pre();
				ep.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBorder(null);
		btnEditar.setBounds(610, 58, 117, 23);
		contentPane.add(btnEditar);
		
		txtEmisor = new JTextField();
		txtEmisor.setBounds(493, 59, 107, 20);
		contentPane.add(txtEmisor);
		txtEmisor.setColumns(10);
		
		JButton btnCalcular = new JButton("Calcular total");       //Calcula el total de los productos seleccionados
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalV();
			}
		});
		btnCalcular.setBorder(null);
		btnCalcular.setBounds(578, 325, 129, 23);
		contentPane.add(btnCalcular);
		
		cargarEmisor();
	}
}

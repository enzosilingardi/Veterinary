package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
import View.Usuario_Sucursal.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Factura extends JFrame {

	private JPanel contentPane;
	private JTextField txtNro;
	private JComboBox cbPunto;
	private JComboBox cbTipo;
	private JDateChooser txtFecha;
	private JTextField txtIva;
	private JTextField txtTotal;
	private JTextField txtCliente;
	private JTextField txtCuit;
	private JTextField txtDom;
	private JTextField txtDni;
	private JTextField txtDir;
	private JComboBox cbCon;
	private JTable table;
	private JComboBox cbPro;
	private JTextField txtEmisor;

	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)
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
	

	public DefaultComboBoxModel cargarCliente() {
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
	

	public DefaultComboBoxModel cargarUsuario() {
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
	
	public DefaultComboBoxModel cargarSucursal() {
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
	

	public DefaultComboBoxModel cargarProducto() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Product ORDER BY id_Product";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("product_Name"),result.getString("sale_Price")));
				
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
	
	void cargarEmisor() {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT * FROM Emitter";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			
			
			while (result.next()) {
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
					Factura frame = new Factura();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void generar(String nombre) throws FileNotFoundException,DocumentException {
		if(!(txtNro.getText().isEmpty() || txtIva.getText().isEmpty() || txtTotal.getText().isEmpty() || cbPunto.getSelectedItem().toString().equals("") || txtCliente.getText().isEmpty() || txtEmisor.getText().isEmpty() || txtDir.getText().isEmpty() || txtDom.getText().isEmpty() || txtDni.getText().isEmpty() || txtCuit.getText().isEmpty() )) {
			Object punto = cbPunto.getSelectedItem();
			
			FileOutputStream archivo = new FileOutputStream("c:/rsc/Factura "+nombre+".pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			
			Paragraph parrafo = new Paragraph("Factura");
			parrafo.setAlignment(1);
			documento.add(parrafo);
			
			
			Paragraph parrafoT = new Paragraph(cbTipo.getSelectedItem().toString());
			parrafoT.setAlignment(1);
			documento.add(parrafoT);
			
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
			documento.add(new Paragraph("Condición frente al IVA: "+cbCon.getSelectedItem().toString()));
			documento.add(new Paragraph("IVA: "+txtIva.getText()));
			documento.add(new Paragraph(" "));
			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
	          
            for (int i = 0; i < table.getColumnCount(); i++) {
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
			
			JOptionPane.showMessageDialog(null, "Factura Creada");
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Factura(String nom, String dni, String dir) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(145, 226, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo de factura");
		lblTipo.setBounds(10, 11, 90, 14);
		contentPane.add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cbTipo.setBounds(110, 7, 33, 22);
		contentPane.add(cbTipo);
		
		JLabel lblNro = new JLabel("Número de comprobante");
		lblNro.setBounds(433, 11, 147, 14);
		contentPane.add(lblNro);
		
		txtNro = new JTextField();
		txtNro.setBounds(590, 8, 107, 20);
		contentPane.add(txtNro);
		txtNro.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 59, 60, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(468, 59, 46, 14);
		contentPane.add(lblEmisor);
		
		
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(181, 11, 114, 14);
		contentPane.add(lblPunto);
		
		cbPunto = new JComboBox();
		cbPunto.setBounds(272, 7, 114, 22);
		contentPane.add(cbPunto);
		cbPunto.setModel(cargarSucursal());
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(468, 205, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(530, 199, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 194, 46, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setBounds(57, 191, 86, 20);
		contentPane.add(txtIva);
		txtIva.setColumns(10);
		txtIva.setText("0");
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(500, 290, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(500, 315, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnGenerar = new JButton("Generar Factura");
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
		btnGenerar.setBounds(450, 527, 157, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBorder(null);
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(617, 527, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(110, 56, 114, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(468, 107, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(590, 104, 107, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		JLabel lblDom = new JLabel("Domicilio Cliente");
		lblDom.setBounds(10, 107, 107, 14);
		contentPane.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setBounds(110, 104, 86, 20);
		contentPane.add(txtDom);
		txtDom.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI Cliente");
		lblDni.setBounds(206, 107, 86, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(272, 104, 97, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblDir = new JLabel("Dirección Empresa");
		lblDir.setBounds(468, 153, 114, 14);
		contentPane.add(lblDir);
		
		txtDir = new JTextField();
		txtDir.setBounds(590, 150, 107, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);
		
		JLabel lblCon = new JLabel("Condición frente al IVA");
		lblCon.setBounds(10, 153, 157, 14);
		contentPane.add(lblCon);
		
		 cbCon = new JComboBox();
		cbCon.setModel(new DefaultComboBoxModel(new String[] {"Sujeto Exento", "Responsable Inscripto", "Consumidor Final"}));
		cbCon.setBounds(139, 149, 107, 22);
		contentPane.add(cbCon);
		
		 cbPro = new JComboBox();
		cbPro.setBounds(10, 245, 114, 22);
		contentPane.add(cbPro);
		cbPro.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object valor = cbPro.getSelectedItem();
				model.addRow(new Object[]{cbPro.getSelectedItem().toString(),"",((ComboItem) valor).getValue()});
			}
		});
		btnAgregar.setBounds(158, 245, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBorder(null);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				((DefaultTableModel)table.getModel()).removeRow(fila);
			}
		});
		btnRemover.setBounds(259, 245, 89, 23);
		contentPane.add(btnRemover);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 289, 410, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Producto", "Cantidad", "Precio Unitario"
			}
		));
		scrollPane.setViewportView(table);
		
		txtCliente.setText(nom);
		txtDni.setText(dni);
		txtDom.setText(dir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente bc = new Buscar_Cliente();
				bc.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(234, 55, 89, 23);
		contentPane.add(btnBuscar);
		
		cargarEmisor();
		

		txtEmisor = new JTextField();
		txtEmisor.setBounds(590, 56, 107, 20);
		contentPane.add(txtEmisor);
		txtEmisor.setColumns(10);
		
		JButton btnEditar = new JButton("Editar emisor");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emisor emisor = new Emisor();
				emisor.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBorder(null);
		btnEditar.setBounds(590, 190, 138, 23);
		contentPane.add(btnEditar);
	}


	public Factura() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(145, 226, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo de factura");
		lblTipo.setBounds(10, 11, 90, 14);
		contentPane.add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cbTipo.setBounds(110, 7, 33, 22);
		contentPane.add(cbTipo);
		
		JLabel lblNro = new JLabel("Número de comprobante");
		lblNro.setBounds(433, 11, 147, 14);
		contentPane.add(lblNro);
		
		txtNro = new JTextField();
		txtNro.setBounds(590, 8, 107, 20);
		contentPane.add(txtNro);
		txtNro.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 59, 60, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(468, 59, 46, 14);
		contentPane.add(lblEmisor);
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(181, 11, 114, 14);
		contentPane.add(lblPunto);
		
		cbPunto = new JComboBox();
		cbPunto.setBounds(272, 7, 114, 22);
		contentPane.add(cbPunto);
		cbPunto.setModel(cargarSucursal());
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(468, 236, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(532, 230, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 194, 46, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setText("0");
		txtIva.setBounds(57, 191, 86, 20);
		contentPane.add(txtIva);
		txtIva.setColumns(10);
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(500, 290, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(500, 315, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnGenerar = new JButton("Generar Factura");
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
		btnGenerar.setBounds(450, 527, 157, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBorder(null);
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(617, 527, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(110, 56, 114, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(468, 107, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(590, 104, 107, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		JLabel lblDom = new JLabel("Domicilio Cliente");
		lblDom.setBounds(10, 107, 107, 14);
		contentPane.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setBounds(110, 104, 86, 20);
		contentPane.add(txtDom);
		txtDom.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI Cliente");
		lblDni.setBounds(206, 107, 86, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(272, 104, 97, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblDir = new JLabel("Dirección Empresa");
		lblDir.setBounds(468, 153, 114, 14);
		contentPane.add(lblDir);
		
		txtDir = new JTextField();
		txtDir.setBounds(590, 150, 107, 20);
		contentPane.add(txtDir);
		txtDir.setColumns(10);
		
		JLabel lblCon = new JLabel("Condición frente al IVA");
		lblCon.setBounds(10, 153, 157, 14);
		contentPane.add(lblCon);
		
		 cbCon = new JComboBox();
		cbCon.setModel(new DefaultComboBoxModel(new String[] {"Sujeto Exento", "Responsable Inscripto", "Consumidor Final"}));
		cbCon.setBounds(139, 149, 107, 22);
		contentPane.add(cbCon);
		
		 cbPro = new JComboBox();
		cbPro.setBounds(10, 245, 114, 22);
		contentPane.add(cbPro);
		cbPro.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBorder(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object valor = cbPro.getSelectedItem();
				model.addRow(new Object[]{cbPro.getSelectedItem().toString(),"",((ComboItem) valor).getValue()});
			}
		});
		btnAgregar.setBounds(158, 245, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBorder(null);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				((DefaultTableModel)table.getModel()).removeRow(fila);
			}
		});
		btnRemover.setBounds(259, 245, 89, 23);
		contentPane.add(btnRemover);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 289, 410, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Producto", "Cantidad", "Precio Unitario"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Cliente bc = new Buscar_Cliente();
				bc.setVisible(true);
				dispose();
			}
		});
		btnBuscar.setBounds(234, 55, 89, 23);
		contentPane.add(btnBuscar);
		
		txtEmisor = new JTextField();
		txtEmisor.setBounds(590, 56, 107, 20);
		contentPane.add(txtEmisor);
		txtEmisor.setColumns(10);
		
		JButton btnEditar = new JButton("Editar emisor");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Emisor emisor = new Emisor();
				emisor.setVisible(true);
				dispose();
			}
		});
		btnEditar.setBorder(null);
		btnEditar.setBounds(590, 190, 138, 23);
		contentPane.add(btnEditar);
		
		cargarEmisor();
	}
}

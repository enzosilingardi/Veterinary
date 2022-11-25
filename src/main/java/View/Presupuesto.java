package View;

import java.awt.EventQueue;
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


	public void generar(String nombre) throws FileNotFoundException,DocumentException {
		if(!(txtNro.getText().isEmpty()  || txtTotal.getText().isEmpty() || cbPunto.getSelectedItem().toString().equals("") || txtCliente.getText().isEmpty() || cbEmisor.getSelectedItem().toString().equals("") || txtCuit.getText().isEmpty() || txtDom.getText().isEmpty() || txtDir.getText().isEmpty() || txtDni.getText().isEmpty() )) {
			Object punto = cbPunto.getSelectedItem();
			
			FileOutputStream archivo = new FileOutputStream("c:/rsc/Presupuesto "+nombre+".pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			
			Paragraph parrafo = new Paragraph("Presupuesto");
			parrafo.setAlignment(1);
			documento.add(parrafo);
			
			documento.add(new Paragraph("Punto de Venta: "+((ComboItem) punto).getValue()));
			documento.add(new Paragraph("Nro de Comprobante: "+txtNro.getText()));
			documento.add(new Paragraph("Emisor: "+cbEmisor.getSelectedItem().toString()));
			documento.add(new Paragraph("CUIT: "+txtCuit.getText()));
			documento.add(new Paragraph("Direccion Fiscal: "+txtDir.getText()));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph("Cliente: "+txtCliente.getText()));
			documento.add(new Paragraph("DNI: "+txtDni.getText()));
			documento.add(new Paragraph("Domicilio: "+txtDom.getText()));
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
			
			JOptionPane.showMessageDialog(null, "Presupuesto Creado");
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Presupuesto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
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
		lblEmisor.setBounds(10, 119, 46, 14);
		contentPane.add(lblEmisor);
		
		JComboBox cbEmisor = new JComboBox();
		cbEmisor.setBounds(66, 115, 107, 22);
		contentPane.add(cbEmisor);
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(10, 14, 114, 14);
		contentPane.add(lblPunto);
		
		JComboBox cbPunto = new JComboBox();
		cbPunto.setBounds(117, 10, 114, 22);
		contentPane.add(cbPunto);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(375, 173, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(484, 170, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(596, 262, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(596, 287, 86, 20);
		contentPane.add(txtTotal);
		
		JButton btnGenerar = new JButton("Generar Presupuesto");
		btnGenerar.setBounds(450, 527, 157, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(617, 527, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(117, 59, 107, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblDom = new JLabel("Domicilio Cliente");
		lblDom.setBounds(255, 62, 107, 14);
		contentPane.add(lblDom);
		
		txtDom = new JTextField();
		txtDom.setColumns(10);
		txtDom.setBounds(362, 56, 86, 20);
		contentPane.add(txtDom);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(589, 56, 97, 20);
		contentPane.add(txtDni);
		
		JLabel lblDni = new JLabel("DNI Cliente");
		lblDni.setBounds(504, 59, 86, 14);
		contentPane.add(lblDni);
		
		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(375, 119, 114, 20);
		contentPane.add(txtDir);
		
		JLabel lblDir = new JLabel("Dirección Empresa");
		lblDir.setBounds(255, 122, 114, 14);
		contentPane.add(lblDir);
		
		cbPro = new JComboBox();
		cbPro.setBounds(10, 219, 114, 22);
		contentPane.add(cbPro);
		cbPro.setModel(cargarProducto());
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object valor = cbPro.getSelectedItem();
				model.addRow(new Object[]{cbPro.getSelectedItem().toString(),"",((ComboItem) valor).getValue()});
			}
		});
		btnAgregar.setBounds(158, 219, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRemover = new JButton("Remover");
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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Producto", "Cantidad", "Precio Unitario"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(538, 119, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(577, 116, 109, 20);
		contentPane.add(txtCuit);
	}
}

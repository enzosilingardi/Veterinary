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

public class Presupuesto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNro;
	private JDateChooser txtFecha;
	private JTextField txtTotal;
	private JTextPane txtProductos;
	private JComboBox cbEmisor;
	private JComboBox cbCliente;
	private JComboBox cbPunto;
	private JTextField txtCliente;
	
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
				modelo.addElement(new ComboItem(result.getString("address"),result.getString("id_Branch")));
				
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
		if(!(txtNro.getText().isEmpty() || txtProductos.getText().isEmpty()  || txtTotal.getText().isEmpty() || cbPunto.getSelectedItem().toString().equals("") || txtCliente.getText().isEmpty() || cbEmisor.getSelectedItem().toString().equals("") )) {
			FileOutputStream archivo = new FileOutputStream("c:/rsc/Presupuesto "+nombre+".pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			
			Paragraph parrafo = new Paragraph("Presupuesto");
			parrafo.setAlignment(1);
			documento.add(parrafo);
			
			documento.add(new Paragraph("Cliente: "+txtCliente.getText()));
			documento.add(new Paragraph("Emisor: "+cbEmisor.getSelectedItem().toString()));
			documento.add(new Paragraph("Nro de Comprobante: "+txtNro.getText()));
			documento.add(new Paragraph("Punto de Venta: "+cbPunto.getSelectedItem().toString()));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph("Productos / Cantidad / Precio Unitario"));
			documento.add(new Paragraph(txtProductos.getText()));
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
		setBounds(100, 100, 655, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNro = new JLabel("Número de comprobante");
		lblNro.setBounds(10, 14, 147, 14);
		contentPane.add(lblNro);
		
		txtNro = new JTextField();
		txtNro.setColumns(10);
		txtNro.setBounds(167, 11, 107, 20);
		contentPane.add(txtNro);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 62, 60, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(326, 62, 46, 14);
		contentPane.add(lblEmisor);
		
		JComboBox cbEmisor = new JComboBox();
		cbEmisor.setBounds(435, 58, 107, 22);
		contentPane.add(cbEmisor);
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(10, 119, 114, 14);
		contentPane.add(lblPunto);
		
		JComboBox cbPunto = new JComboBox();
		cbPunto.setBounds(167, 115, 114, 22);
		contentPane.add(cbPunto);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(326, 119, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(435, 116, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblProductos = new JLabel("Productos / Cantidad / Precio Unitario");
		lblProductos.setBounds(10, 196, 234, 14);
		contentPane.add(lblProductos);
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(409, 196, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(409, 221, 86, 20);
		contentPane.add(txtTotal);
		
		txtProductos = new JTextPane();
		txtProductos.setBounds(10, 221, 338, 139);
		contentPane.add(txtProductos);
		
		JButton btnGenerar = new JButton("Generar Presupuesto");
		btnGenerar.setBounds(305, 399, 157, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(472, 399, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(167, 59, 107, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
	}
}

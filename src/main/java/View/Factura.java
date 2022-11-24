package View;

import java.awt.EventQueue;
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

public class Factura extends JFrame {

	private JPanel contentPane;
	private JTextField txtNro;
	private JComboBox cbEmisor;
	private JComboBox cbPunto;
	private JComboBox cbTipo;
	private JDateChooser txtFecha;
	private JTextField txtIva;
	private JTextField txtTotal;
	private JTextPane txtProductos;
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
					Factura frame = new Factura();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void generar(String nombre) throws FileNotFoundException,DocumentException {
		if(!(txtNro.getText().isEmpty() || txtProductos.getText().isEmpty() || txtIva.getText().isEmpty() || txtTotal.getText().isEmpty() || cbPunto.getSelectedItem().toString().equals("") || txtCliente.getText().isEmpty() || cbEmisor.getSelectedItem().toString().equals("") )) {
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
			
			documento.add(new Paragraph("Cliente: "+txtCliente.getText()));
			documento.add(new Paragraph("Emisor: "+cbEmisor.getSelectedItem().toString()));
			documento.add(new Paragraph("Nro de Comprobante: "+txtNro.getText()));
			documento.add(new Paragraph("IVA: "+txtIva.getText()));
			documento.add(new Paragraph("Punto de Venta: "+cbPunto.getSelectedItem().toString()));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph("Productos / Cantidad / Precio Unitario"));
			documento.add(new Paragraph(txtProductos.getText()));
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
	public Factura() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo de factura");
		lblTipo.setBounds(10, 11, 90, 14);
		contentPane.add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cbTipo.setBounds(158, 7, 114, 22);
		contentPane.add(cbTipo);
		
		JLabel lblNro = new JLabel("Número de comprobante");
		lblNro.setBounds(326, 11, 147, 14);
		contentPane.add(lblNro);
		
		txtNro = new JTextField();
		txtNro.setBounds(483, 8, 107, 20);
		contentPane.add(txtNro);
		txtNro.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 59, 60, 14);
		contentPane.add(lblCliente);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(326, 59, 46, 14);
		contentPane.add(lblEmisor);
		
		cbEmisor = new JComboBox();
		cbEmisor.setBounds(435, 55, 107, 22);
		contentPane.add(cbEmisor);
		cbEmisor.setModel(cargarUsuario());
		
		JLabel lblPunto = new JLabel("Punto de venta");
		lblPunto.setBounds(10, 112, 114, 14);
		contentPane.add(lblPunto);
		
		cbPunto = new JComboBox();
		cbPunto.setBounds(158, 108, 114, 22);
		contentPane.add(cbPunto);
		cbPunto.setModel(cargarSucursal());
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(326, 112, 76, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JDateChooser("dd-MM-yyyy", "##-##-####", ' ');
		txtFecha.setBounds(435, 109, 107, 20);
		contentPane.add(txtFecha);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(10, 168, 46, 14);
		contentPane.add(lblIva);
		
		txtIva = new JTextField();
		txtIva.setBounds(158, 165, 86, 20);
		contentPane.add(txtIva);
		txtIva.setColumns(10);
		
		txtProductos = new JTextPane();
		txtProductos.setBounds(10, 260, 336, 113);
		contentPane.add(txtProductos);
		
		JLabel lblProductos = new JLabel("Productos / Cantidad / Precio Unitario");
		lblProductos.setBounds(10, 235, 234, 14);
		contentPane.add(lblProductos);
		
		JLabel lblTotal = new JLabel("Precio Total");
		lblTotal.setBounds(409, 235, 90, 14);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(409, 260, 86, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnGenerar = new JButton("Generar Factura");
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
		btnGenerar.setBounds(290, 423, 157, 23);
		contentPane.add(btnGenerar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(457, 423, 157, 23);
		contentPane.add(btnVolver);
		
		txtCliente = new JTextField();
		txtCliente.setBounds(158, 56, 114, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
	}
}

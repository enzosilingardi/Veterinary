package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Control.Connect;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main extends JFrame {

	private JPanel contentPane;
	private ImageIcon imagen;
	private Icon icono;
	private JTable table;
	

	void mostrarTabla(){
			
		
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        modelo.setColumnIdentifiers(new Object[] {"ID","Mascota","Procedimiento","Fecha","Hora"});
	       
	        table.setModel(modelo);
	        
	        
	        String datos[] = new String[5];
	       
	        try {
	        	Connection con = Connect.getConexion();
	        	PreparedStatement ps = con.prepareStatement("SELECT TOP 10 id_Procedure, name, proced_Name, CONVERT(varchar(10),proced_Date,103),CONVERT(varchar(10),proced_Time,8)\r\n"
	        			+ "FROM Medical_Procedure\r\n"
	        			+ "INNER JOIN Pet ON Pet.id_Pet = Medical_Procedure.id_Pet\r\n"
	        			+ "INNER JOIN Procedure_Type ON Procedure_Type.id_Procedure_Type = Medical_Procedure.id_Procedure_Type\r\n"
	        			+ "ORDER BY proced_Date DESC" );
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()){
	                datos[0] = rs.getString(1);
	                datos[1] = rs.getString(2);
	                datos[2] = rs.getString(3);
	                datos[3] = rs.getString(4);
	                datos[4] = rs.getString(5);
	                
	                modelo.addRow(datos);
	                

	            }
	            table.setModel(modelo);
	            table.getColumnModel().getColumn(0).setMaxWidth(0);
	    		table.getColumnModel().getColumn(0).setMinWidth(0);
	    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	    		table.getColumnModel().getColumn(0).setResizable(false);
	        } catch(SQLException E) {
				JOptionPane.showMessageDialog(null,E);
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
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setScaleImage(JLabel lblFoto, String rutaFoto) {
		ImageIcon foto = new ImageIcon(rutaFoto);
		Icon icono = new ImageIcon(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), 1));
		lblFoto.setIcon(icono);
	}

	/**
	 * Create the frame.
	 */
	public Main(final String perfil) {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 22, 207, 541);
		panel.setBackground(new Color(86, 211, 243));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnInstrumentos = new JButton("Instrumentos");
		btnInstrumentos.setForeground(new Color(255, 255, 255));
		btnInstrumentos.setFont(new Font("Roboto", Font.BOLD, 14));
		btnInstrumentos.setBackground(new Color(86, 211, 243));
		btnInstrumentos.setBorder(null);
		btnInstrumentos.setBounds(0, 456, 207, 23);
		panel.add(btnInstrumentos);
		btnInstrumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Instrumento ti = new Tabla_Instrumento(perfil);
				ti.setVisible(true);
			}
		});
		
		JButton btnSucursales = new JButton("Sucursales");
		btnSucursales.setForeground(new Color(255, 255, 255));
		btnSucursales.setFont(new Font("Roboto", Font.BOLD, 14));
		btnSucursales.setBackground(new Color(86, 211, 243));
		btnSucursales.setBorder(null);
		btnSucursales.setBounds(0, 388, 207, 23);
		panel.add(btnSucursales);
		btnSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Sucursales sucursales = new Tabla_Sucursales(perfil);
				sucursales.setVisible(true);
			}
		});
		
		JButton btnVeterinarios = new JButton("Veterinarios");
		btnVeterinarios.setForeground(new Color(255, 255, 255));
		btnVeterinarios.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVeterinarios.setBackground(new Color(86, 211, 243));
		btnVeterinarios.setBorder(null);
		btnVeterinarios.setBounds(0, 354, 207, 23);
		panel.add(btnVeterinarios);
		btnVeterinarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Veterinario tv = new Tabla_Veterinario(perfil);
				tv.setVisible(true);
			}
		});
		
		JButton btnMascotas = new JButton("Mascotas");
		btnMascotas.setForeground(new Color(255, 255, 255));
		btnMascotas.setFont(new Font("Roboto", Font.BOLD, 14));
		btnMascotas.setBackground(new Color(86, 211, 243));
		btnMascotas.setBorder(null);
		btnMascotas.setBounds(0, 286, 207, 23);
		panel.add(btnMascotas);
		
		JButton btnQuirofanos = new JButton("Quirófanos");
		btnQuirofanos.setForeground(new Color(255, 255, 255));
		btnQuirofanos.setFont(new Font("Roboto", Font.BOLD, 14));
		btnQuirofanos.setBackground(new Color(86, 211, 243));
		btnQuirofanos.setBorder(null);
		btnQuirofanos.setBounds(0, 422, 207, 23);
		panel.add(btnQuirofanos);
		btnQuirofanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano(perfil);
				tq.setVisible(true);
			}
		});
		
		JButton btnHistoriales = new JButton("Historiales");
		btnHistoriales.setForeground(new Color(255, 255, 255));
		btnHistoriales.setFont(new Font("Roboto", Font.BOLD, 14));
		btnHistoriales.setBackground(new Color(86, 211, 243));
		btnHistoriales.setBorder(null);
		btnHistoriales.setBounds(0, 320, 207, 23);
		panel.add(btnHistoriales);
		
		JButton btnTurnos = new JButton("Turnos");
		btnTurnos.setBorder(null);
		btnTurnos.setFont(new Font("Roboto", Font.BOLD, 14));
		btnTurnos.setForeground(new Color(255, 255, 255));
		btnTurnos.setBackground(new Color(86, 211, 243));
		btnTurnos.setBounds(0, 218, 207, 23);
		panel.add(btnTurnos);
		btnTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos(perfil);
				tt.setVisible(true);
				
			}
		});
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setForeground(new Color(255, 255, 255));
		btnClientes.setFont(new Font("Roboto", Font.BOLD, 14));
		btnClientes.setBorder(null);
		btnClientes.setBounds(0, 252, 207, 23);
		panel.add(btnClientes);
		btnClientes.setBackground(new Color(86, 211, 243));
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setForeground(new Color(255, 255, 255));
		btnUsuarios.setFont(new Font("Roboto", Font.BOLD, 14));
		btnUsuarios.setBackground(new Color(86, 211, 243));
		btnUsuarios.setBorder(null);
		btnUsuarios.setBounds(0, 490, 207, 23);
		panel.add(btnUsuarios);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBorder(null);
		lblLogo.setBounds(0, 0, 207, 207);
		panel.add(lblLogo);
		
		setScaleImage(lblLogo,"src/main/java/images/vet.png");
		
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios usuarios = new Tabla_Usuarios(perfil);
				usuarios.setVisible(true);
			}
		});
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 22);
		contentPane.add(menuBar);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnVentas);
		
		
		JMenuItem mntmProductos = new JMenuItem("Productos");
		mntmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Productos productos = new Tabla_Productos(perfil);
				productos.setVisible(true);
			}
		});
		mnVentas.add(mntmProductos);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mntmProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Proveedor tp = new Tabla_Proveedor(perfil); 
				tp.setVisible(true);
			}
		});
		mnVentas.add(mntmProveedores);
		
		JMenuItem mntmStock = new JMenuItem("Stock");
		mntmStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Stock ts = new Tabla_Stock(perfil);
				ts.setVisible(true);
			}
		});
		mnVentas.add(mntmStock);
		
		JMenuItem mntmPedidos = new JMenuItem("Pedidos");
		mntmPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Pedido tp = new Tabla_Pedido(perfil);
				tp.setVisible(true);
			}
		});
		mnVentas.add(mntmPedidos);
		
		JMenuItem mntmFacturacion = new JMenuItem("Facturacion");
		mntmFacturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Factura factura = new Factura();
				factura.setVisible(true);
			}
		});
		mnVentas.add(mntmFacturacion);
		
		JMenuItem mntmPresupuestos = new JMenuItem("Presupuestos");
		mntmPresupuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Presupuesto presupuesto = new Presupuesto();
				presupuesto.setVisible(true);
			}
		});
		mnVentas.add(mntmPresupuestos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(145, 226, 247));
		panel_1.setBounds(207, 22, 579, 88);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 110, 579, 453);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Clientes tc = new Tabla_Clientes(perfil);
				tc.setVisible(true);
			}
		});
		btnHistoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial(perfil);
				th.setVisible(true);
			}
		});
		btnMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota(perfil);
				tm.setVisible(true);
			}
		});
		
		
		mostrarTabla();
		
	}

	public Main() {
		// TODO Auto-generated constructor stub
	}
}

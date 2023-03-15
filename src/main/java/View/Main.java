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
import Control.Consulta_Main;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class Main extends JFrame {

	private JPanel contentPane;
	private ImageIcon imagen;
	private Icon icono;
	private JTable table;
	

	void mostrarTabla(){            //Muestra la tabal con los 10 turnos más recientes
			
		
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        Consulta_Main.tabla(modelo, table);
	        
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
	
	private void setScaleImage(JLabel lblFoto, String rutaFoto) {            //Setea la escala de la imagen ingresada
		ImageIcon foto = new ImageIcon(rutaFoto);
		Icon icono = new ImageIcon(foto.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), 1));
		lblFoto.setIcon(icono);
	}

	/**
	 * Create the frame.
	 */
	public Main(final String perfil) {        //Crea la ventana recibiendo como parámetro el perfil del usuario
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));        //Setea el icono de la ventana

		
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
		
		JButton btnInstrumentos = new JButton("Instrumentos");          //Abre la ventana Tabla_Instrumento usando como parámetro el perfil del usuario
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
		
		JButton btnSucursales = new JButton("Sucursales");             //Abre la ventana Tabla_Sucursales usando como parámetro el perfil del usuario
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
		
		JButton btnVeterinarios = new JButton("Veterinarios");          //Abre la ventana Tabla_Veterinarios usando como parámetro el perfil del usuario
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
		
		JButton btnMascotas = new JButton("Mascotas");               //Abre la ventana Tabla_Mascota usando como parámetro el perfil del usuario
		btnMascotas.setForeground(new Color(255, 255, 255));
		btnMascotas.setFont(new Font("Roboto", Font.BOLD, 14));
		btnMascotas.setBackground(new Color(86, 211, 243));
		btnMascotas.setBorder(null);
		btnMascotas.setBounds(0, 286, 207, 23);
		panel.add(btnMascotas);
		
		JButton btnQuirofanos = new JButton("Quirófanos");          //Abre la ventana Tabla_Quirofano usando como parámetro el perfil del usuario
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
		
		JButton btnHistoriales = new JButton("Historiales");      //Abre la ventana Tabla_Historial usando como parámetro el perfil del usuario
		btnHistoriales.setForeground(new Color(255, 255, 255)); 
		btnHistoriales.setFont(new Font("Roboto", Font.BOLD, 14));
		btnHistoriales.setBackground(new Color(86, 211, 243));
		btnHistoriales.setBorder(null);
		btnHistoriales.setBounds(0, 320, 207, 23);
		panel.add(btnHistoriales);
		
		JButton btnTurnos = new JButton("Turnos");            //Abre la ventana Tabla_Turnos 
		btnTurnos.setBorder(null);
		btnTurnos.setFont(new Font("Roboto", Font.BOLD, 14));
		btnTurnos.setForeground(new Color(255, 255, 255));
		btnTurnos.setBackground(new Color(86, 211, 243));
		btnTurnos.setBounds(0, 218, 207, 23);
		panel.add(btnTurnos);
		btnTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos();
				tt.setVisible(true);
				
			}
		});
		
		JButton btnClientes = new JButton("Clientes");              //Abre la ventana Tabla_Clientes usando como parámetro el perfil del usuario
		btnClientes.setForeground(new Color(255, 255, 255));
		btnClientes.setFont(new Font("Roboto", Font.BOLD, 14));
		btnClientes.setBorder(null);
		btnClientes.setBounds(0, 252, 207, 23);
		panel.add(btnClientes);
		btnClientes.setBackground(new Color(86, 211, 243));
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) { 
		
		JButton btnUsuarios = new JButton("Usuarios");            //Abre la ventana Tabla_Usuarios usando como parámetro el perfil del usuario
		btnUsuarios.setForeground(new Color(255, 255, 255));
		btnUsuarios.setFont(new Font("Roboto", Font.BOLD, 14));
		btnUsuarios.setBackground(new Color(86, 211, 243));
		btnUsuarios.setBorder(null);
		btnUsuarios.setBounds(0, 490, 207, 23);
		panel.add(btnUsuarios);
		
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios usuarios = new Tabla_Usuarios(perfil);
				usuarios.setVisible(true);
			}
		});
		
		}
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBorder(null);
		lblLogo.setBounds(0, 0, 207, 207);
		panel.add(lblLogo);
		
		setScaleImage(lblLogo,"src/main/java/images/vet.png");         //Setea la imagen del logo
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 22);
		contentPane.add(menuBar);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnVentas);
		
		
		JMenuItem mntmProductos = new JMenuItem("Productos");          //Abre la ventana Tabla_Productos usando como parámetro el perfil del usuario
		mntmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Productos productos = new Tabla_Productos(perfil);
				productos.setVisible(true);
			}
		});
		mnVentas.add(mntmProductos);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");       //Abre la ventana Tabla_Proveedor usando como parámetro el perfil del usuario
		mntmProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Proveedor tp = new Tabla_Proveedor(perfil); 
				tp.setVisible(true);
			}
		});
		mnVentas.add(mntmProveedores);
		
		JMenuItem mntmStock = new JMenuItem("Stock");             //Abre la ventana Tabla_Stock 
		mntmStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Stock ts = new Tabla_Stock();
				ts.setVisible(true);
			}
		});
		mnVentas.add(mntmStock);
		
		JMenuItem mntmPedidos = new JMenuItem("Pedidos");         //Abre la ventana Tabla_Pedido 
		mntmPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Pedido tp = new Tabla_Pedido();
				tp.setVisible(true);
			}
		});
		mnVentas.add(mntmPedidos);
		
		JMenuItem mntmFacturacion = new JMenuItem("Facturacion");       //Abre la ventana Factura
		mntmFacturacion.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Factura factura = new Factura();
				factura.setVisible(true);
			}
		});
		mnVentas.add(mntmFacturacion);
		
		JMenuItem mntmPresupuestos = new JMenuItem("Presupuestos");        //Abre la ventana Presupuesto
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
		
		JLabel lblProximos = new JLabel("Proximos Turnos");
		lblProximos.setFont(new Font("Roboto Black", Font.BOLD, 14));
		lblProximos.setBounds(262, 37, 144, 17);
		panel_1.add(lblProximos);
		
		JLabel lblPerfil = new JLabel("Usuario: Manager");
		lblPerfil.setFont(new Font("Roboto Black", Font.BOLD, 14));
		lblPerfil.setBounds(10, 11, 153, 17);
		panel_1.add(lblPerfil);
		
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
		
		if(perfil.equals("Admin")) {
			lblPerfil.setText("Usuario: Admin");  //Si el usuario logueado es un Admin, el label dirá "Usuario: Admin"
		}
		
		if(perfil.equals("Manager")) {
			lblPerfil.setText("Usuario: Manager");  //Si el usuario logueado es un Manager, el label dirá "Usuario: Manager"
		}
		
		if(perfil.equals("Regular")) {
			lblPerfil.setText("Usuario: Regular");	  //Si el usuario logueado es un Regular, el label dirá "Usuario: Regular"
		}
		
	}

	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));
		// TODO Auto-generated constructor stub
	}
}

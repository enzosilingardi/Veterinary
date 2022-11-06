package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;

	
	
	public Icon setIcono(String url, JButton boton) {
		
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		
		int ancho = boton.getWidth();
		
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		
		return icono;
		
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

	/**
	 * Create the frame.
	 */
	public Main(final String perfil) {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 555);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		mnVentas.add(mntmFacturacion);
		
		JMenuItem mntmPresupuestos = new JMenuItem("Presupuestos");
		mnVentas.add(mntmPresupuestos);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBackground(new Color(240, 240, 240));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Clientes tc = new Tabla_Clientes(perfil);
				tc.setVisible(true);
			}
		});
		btnClientes.setBounds(10, 11, 104, 81);
		contentPane.add(btnClientes);
		
		JButton btnTurnos = new JButton("Turnos");
		btnTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos(perfil);
				tt.setVisible(true);
			}
		});
		btnTurnos.setBounds(124, 11, 104, 81);
		contentPane.add(btnTurnos);
		
		JButton btnHistoriales = new JButton("Historiales");
		btnHistoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial(perfil);
				th.setVisible(true);
			}
		});
		btnHistoriales.setBounds(238, 11, 104, 81);
		contentPane.add(btnHistoriales);
		
		JButton btnVeterinarios = new JButton("Veterinarios");
		btnVeterinarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Veterinario tv = new Tabla_Veterinario(perfil);
				tv.setVisible(true);
			}
		});
		btnVeterinarios.setBounds(352, 11, 104, 81);
		contentPane.add(btnVeterinarios);
		
		JButton btnSucursales = new JButton("Sucursales");
		btnSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Sucursales sucursales = new Tabla_Sucursales(perfil);
				sucursales.setVisible(true);
			}
		});
		btnSucursales.setBounds(466, 11, 104, 81);
		contentPane.add(btnSucursales);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setIcon(new ImageIcon(Main.class.getResource("/Imagenes/IconoUsuario.png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios usuarios = new Tabla_Usuarios(perfil);
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBounds(461, 371, 205, 114);
		contentPane.add(btnUsuarios);
		
		JButton btnQuirofanos = new JButton("Quirófanos");
		btnQuirofanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano(perfil);
				tq.setVisible(true);
			}
		});
		btnQuirofanos.setBounds(124, 103, 104, 81);
		contentPane.add(btnQuirofanos);
		
		JButton btnMascotas = new JButton("Mascotas");
		btnMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota(perfil);
				tm.setVisible(true);
			}
		});
		btnMascotas.setBounds(238, 103, 104, 81);
		contentPane.add(btnMascotas);
		
		JButton btnDirecciones = new JButton("Direcciones");
		btnDirecciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Direccion td = new Tabla_Direccion(perfil);
				td.setVisible(true);
			}
		});
		btnDirecciones.setBounds(352, 103, 104, 81);
		contentPane.add(btnDirecciones);
		
		JButton btnInstrumentos = new JButton("Instrumentos");
		btnInstrumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Instrumento ti = new Tabla_Instrumento(perfil);
				ti.setVisible(true);
			}
		});
		btnInstrumentos.setBounds(466, 103, 104, 81);
		contentPane.add(btnInstrumentos);
	}

	public Main() {
		// TODO Auto-generated constructor stub
	}
}

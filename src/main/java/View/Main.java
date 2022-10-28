package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;

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
	public Main(String perfil) {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnVentas);
		
		JMenuItem mntmProductos = new JMenuItem("Productos");
		mntmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Productos productos = new Tabla_Productos();
				productos.setVisible(true);
			}
		});
		mnVentas.add(mntmProductos);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mntmProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Proveedor tp = new Tabla_Proveedor(); 
				tp.setVisible(true);
			}
		});
		mnVentas.add(mntmProveedores);
		
		JMenuItem mntmStock = new JMenuItem("Stock");
		mntmStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Stock ts = new Tabla_Stock();
				ts.setVisible(true);
			}
		});
		mnVentas.add(mntmStock);
		
		JMenuItem mntmPedidos = new JMenuItem("Pedidos");
		mntmPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Pedido tp = new Tabla_Pedido();
				tp.setVisible(true);
			}
		});
		mnVentas.add(mntmPedidos);
		
		JMenuItem mntmFacturacion = new JMenuItem("Facturacion");
		mnVentas.add(mntmFacturacion);
		
		JMenuItem mntmPresupuestos = new JMenuItem("Presupuestos");
		mnVentas.add(mntmPresupuestos);
		
		if (perfil.equals("Admin")) {
		
		JMenu mnArchivos = new JMenu("Archivos");
		mnArchivos.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnArchivos);
		
		
		JMenuItem mntmABMCliente = new JMenuItem("ABM Cliente");
		mntmABMCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMCliente);
		
		JMenuItem mntmABMMascota = new JMenuItem("ABM Mascota");
		mntmABMMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mascota mascota = new Mascota();
				mascota.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMMascota);
		
		JMenuItem mntmABMProveedor = new JMenuItem("ABM Proveedor");
		mntmABMProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedor proveedor = new Proveedor();
				proveedor.setVisible(true);
			}
		});
		
		JMenuItem mntmABMProducto = new JMenuItem("ABM Producto");
		mntmABMProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto producto = new Producto();
				producto.setVisible(true);			}
		});
		mnArchivos.add(mntmABMProducto);
		mnArchivos.add(mntmABMProveedor);
		
		JMenuItem mntmABMTipoProveedor = new JMenuItem("ABM Tipo Proveedor");
		mntmABMTipoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tipo_Proveedor tipo = new Tipo_Proveedor();
				tipo.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMTipoProveedor);
		
		JMenuItem mntmABMVeterinario = new JMenuItem("ABM Veterinario");
		mntmABMVeterinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Veterinario veterinario = new Veterinario();
				veterinario.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMVeterinario);
		
		JMenuItem mntmABMSucursal = new JMenuItem("ABM Sucursal");
		mntmABMSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sucursal sucursal = new Sucursal();
				sucursal.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMSucursal);
		
		JMenuItem mntmABMQuirofano = new JMenuItem("ABM Quirofano");
		mntmABMQuirofano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quirofano quirofano = new Quirofano();
				quirofano.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMQuirofano);
		
		JMenuItem mntmABMInstrumento= new JMenuItem("ABM Instrumento");
		mntmABMInstrumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instrumento instrumento = new Instrumento();
				instrumento.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMInstrumento);
		
		JMenuItem mntmABMCiudad = new JMenuItem("ABM Ciudad");
		mntmABMCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ciudad ciudad = new Ciudad();
				ciudad.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMCiudad);
		
		JMenuItem mntmABMProvincia = new JMenuItem("ABM Provincia");
		mntmABMProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Provincia provincia = new Provincia();
				provincia.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMProvincia);
		
		JMenuItem mntmABMPais = new JMenuItem("ABM País");
		mntmABMPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pais pais = new Pais();
				pais.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMPais);
		
		JMenuItem mntmABMUsuario = new JMenuItem("ABM Usuario");
		mntmABMUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMUsuario);
		
		JMenuItem mntmABMProcedimiento = new JMenuItem("ABM Procedimiento");
		mntmABMProcedimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Medico procedimiento = new Procedimiento_Medico();
				procedimiento.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMProcedimiento);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Clientes tc = new Tabla_Clientes();
				tc.setVisible(true);
			}
		});
		btnClientes.setBounds(10, 11, 104, 81);
		contentPane.add(btnClientes);
		
		JButton btnTurnos = new JButton("Turnos");
		btnTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Turnos tt = new Tabla_Turnos();
				tt.setVisible(true);
			}
		});
		btnTurnos.setBounds(124, 11, 104, 81);
		contentPane.add(btnTurnos);
		
		JButton btnHistoriales = new JButton("Historiales");
		btnHistoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Historial th = new Tabla_Historial();
				th.setVisible(true);
			}
		});
		btnHistoriales.setBounds(238, 11, 104, 81);
		contentPane.add(btnHistoriales);
		
		JButton btnVeterinarios = new JButton("Veterinarios");
		btnVeterinarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Veterinario tv = new Tabla_Veterinario();
				tv.setVisible(true);
			}
		});
		btnVeterinarios.setBounds(352, 11, 104, 81);
		contentPane.add(btnVeterinarios);
		
		JButton btnSucursales = new JButton("Sucursales");
		btnSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Sucursales sucursales = new Tabla_Sucursales();
				sucursales.setVisible(true);
			}
		});
		btnSucursales.setBounds(466, 11, 104, 81);
		contentPane.add(btnSucursales);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Usuarios usuarios = new Tabla_Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBounds(10, 103, 104, 81);
		contentPane.add(btnUsuarios);
		
		JButton btnQuirofanos = new JButton("Quirófanos");
		btnQuirofanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano();
				tq.setVisible(true);
			}
		});
		btnQuirofanos.setBounds(124, 103, 104, 81);
		contentPane.add(btnQuirofanos);
		
		JButton btnMascotas = new JButton("Mascotas");
		btnMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Mascota tm = new Tabla_Mascota();
				tm.setVisible(true);
			}
		});
		btnMascotas.setBounds(238, 103, 104, 81);
		contentPane.add(btnMascotas);
	}

	public Main() {
		// TODO Auto-generated constructor stub
	}
}

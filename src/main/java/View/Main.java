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
	public Main() {
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 417);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setVisible(true);
			}
		});
		mntmClientes.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmClientes);
		
		JMenuItem mntmVeterinarios = new JMenuItem("Veterinarios");
		mntmVeterinarios.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmVeterinarios);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnVentas);
		
		JMenu mnProductos = new JMenu("Productos");
		mnVentas.add(mnProductos);
		
		JMenuItem mntmAlimento = new JMenuItem("Alimento");
		mnProductos.add(mntmAlimento);
		
		JMenuItem mntmAccesorios = new JMenuItem("Accesorios");
		mnProductos.add(mntmAccesorios);
		
		JMenuItem mntmMedicos = new JMenuItem("Medicos");
		mnProductos.add(mntmMedicos);
		
		JMenuItem mntmProveedores = new JMenuItem("Proveedores");
		mnVentas.add(mntmProveedores);
		
		JMenuItem mntmStock = new JMenuItem("Stock");
		mnVentas.add(mntmStock);
		
		JMenuItem mntmFacturacion = new JMenuItem("Facturacion");
		mnVentas.add(mntmFacturacion);
		
		JMenuItem mntmPresupuestos = new JMenuItem("Presupuestos");
		mnVentas.add(mntmPresupuestos);
		
		JMenu mnTurnos = new JMenu("Turnos");
		mnTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnTurnos);
		
		JMenuItem mntmCitas = new JMenuItem("Citas");
		mnTurnos.add(mntmCitas);
		
		JMenuItem mntmVisitas = new JMenuItem("Visitas");
		mnTurnos.add(mntmVisitas);
		
		JMenuItem mntmIntervenciones = new JMenuItem("Intervenciones");
		mnTurnos.add(mntmIntervenciones);
		
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
		
		JMenu mnABMProducto = new JMenu("ABM Producto");
		mnArchivos.add(mnABMProducto);
		
		JMenuItem mntmABMAlimenticio = new JMenuItem("Alimenticio");
		mntmABMAlimenticio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto_Alimenticio alimento = new Producto_Alimenticio();
				alimento.setVisible(true);
			}
		});
		mnABMProducto.add(mntmABMAlimenticio);
		
		JMenuItem mntmAccesorio = new JMenuItem("Accesorio");
		mntmAccesorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto_Accesorio accesorio = new Producto_Accesorio();
				accesorio.setVisible(true);
			}
		});
		mnABMProducto.add(mntmAccesorio);
		
		JMenuItem mntmMedico = new JMenuItem("Medico");
		mntmMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto_Medico medico = new Producto_Medico();
				medico.setVisible(true);
			}
		});
		mnABMProducto.add(mntmMedico);
		
		JMenuItem mntmABMProveedor = new JMenuItem("ABM Proveedor");
		mntmABMProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedor proveedor = new Proveedor();
				proveedor.setVisible(true);
			}
		});
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
		
		JMenuItem mntmABMArtefacto = new JMenuItem("ABM Artefacto");
		mntmABMArtefacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Artefacto artefacto = new Artefacto();
				artefacto.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMArtefacto);
		
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
		
		JMenuItem mntmABMGestor = new JMenuItem("ABM Gestor");
		mntmABMGestor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestor gestor = new Gestor();
				gestor.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMGestor);
		
		JMenuItem mntmABMUsuario = new JMenuItem("ABM Usuario");
		mntmABMUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario();
				usuario.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMUsuario);
		
		JMenuItem mntmABMCita = new JMenuItem("ABM Cita");
		mntmABMCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cita cita = new Cita();
				cita.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMCita);
		
		JMenuItem mntmABMVisita = new JMenuItem("ABM Visita");
		mntmABMVisita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visita visita = new Visita();
				visita.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMVisita);
		
		JMenuItem mntmABMIntervencion = new JMenuItem("ABM Intervencion");
		mntmABMIntervencion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intervencion intervencion = new Intervencion();
				intervencion.setVisible(true);
			}
		});
		mnArchivos.add(mntmABMIntervencion);
		
		JMenuItem mntmHistorialesMedicos = new JMenuItem("Historiales médicos");
		mntmHistorialesMedicos.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmHistorialesMedicos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}

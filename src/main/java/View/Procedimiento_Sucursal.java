package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.ComboBoxes;
import Control.Connect;
import Control.Consulta_Procedimiento_Sucursal;
import Model.ComboItem;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Procedimiento_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbProcedimiento;
	private JComboBox cbSucursal;
	private JTable table;


	public DefaultComboBoxModel cargarSucursal() {       //Carga el ComboBox sucursal

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBSucursal(modelo);
		return modelo;
    }
	
	public DefaultComboBoxModel cargarProcedimiento() {     //Carga el ComboBox procedimiento
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBProcedimiento(modelo);
		return modelo;
    }
	
	void mostrarTabla(){           // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Consulta_Procedimiento_Sucursal.tabla(modelo, table);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Procedimiento_Sucursal frame = new Procedimiento_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	private void limpiar() {                     //Este procedimiento limpia los campos
		cbProcedimiento.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Procedimiento_Sucursal() {                        //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar procedimiento a sucursal");
		lblTitulo.setBounds(108, 11, 209, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProcedimiento = new JLabel("Procedimiento");
		lblProcedimiento.setBounds(430, 53, 92, 14);
		contentPane.add(lblProcedimiento);
		
		cbProcedimiento = new JComboBox();
		cbProcedimiento.setBounds(532, 49, 192, 22);
		contentPane.add(cbProcedimiento);
		cbProcedimiento.setModel(cargarProcedimiento());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(430, 107, 92, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(532, 103, 192, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");              //Este boton permite agregar un procedimiento a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object proced = cbProcedimiento.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				String nombreP = cbProcedimiento.getSelectedItem().toString();
				String nombreS = cbSucursal.getSelectedItem().toString();
				
					
					if (((ComboItem) proced).getValue() == "") {                               //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un procedimiento");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si ya existe la relación
							if(Consulta_Procedimiento_Sucursal.existe(((ComboItem) cbProcedimiento.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Procedimiento ya se encuentra en la sucursal");
							}else {
								Consulta_Procedimiento_Sucursal.agregar(((ComboItem) cbProcedimiento.getSelectedItem()).getValue(), ((ComboItem) cbSucursal.getSelectedItem()).getValue(), nombreP, nombreS);
								
							}
						}
						
						
					}
					
					mostrarTabla();
					limpiar();
			}
		});
		btnAgregar.setBounds(477, 168, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");            //Este botón permite eliminar la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombreP = table.getValueAt(fila,1).toString();
				String nombreS = table.getValueAt(fila,2).toString();
				
				Consulta_Procedimiento_Sucursal.eliminar(id, nombreP, nombreS);
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(621, 168, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");               //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(621, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 53, 348, 358);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		mostrarTabla();
	}
}

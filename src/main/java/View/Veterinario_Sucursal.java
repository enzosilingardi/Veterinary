package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Veterinario_Sucursal;
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

public class Veterinario_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbVeterinario;
	private JComboBox cbSucursal;
	private JTable table;


	
	public DefaultComboBoxModel cargarSucursal() {         //Carga el ComboBox sucursal 

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBSucursal(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarVeterinario() {       //Carga el comboBox veterinario

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBVeterinario(modelo);
		
		return modelo;
    }

	void mostrarTabla(){         // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Veterinario_Sucursal.tabla(modelo, table);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Veterinario_Sucursal frame = new Veterinario_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	private void limpiar() {                 //Este procedimiento limpia los campos
		cbVeterinario.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Veterinario_Sucursal() {                         //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTítulo = new JLabel("Asociar veterinario a sucursal");
		lblTítulo.setBounds(113, 11, 197, 14);
		contentPane.add(lblTítulo);
		
		JLabel lblVeterinarian = new JLabel("Veterinarian");
		lblVeterinarian.setBounds(456, 67, 86, 14);
		contentPane.add(lblVeterinarian);
		
		cbVeterinario = new JComboBox();
		cbVeterinario.setBounds(552, 63, 172, 22);
		contentPane.add(cbVeterinario);
		cbVeterinario.setModel(cargarVeterinario());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(456, 122, 86, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(552, 118, 172, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");            //Este botón permite agregar un veterinario a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object veterinario = cbVeterinario.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				String nombreV = cbVeterinario.getSelectedItem().toString();
				String nombreS = cbSucursal.getSelectedItem().toString();
				
				
					
					if (((ComboItem) veterinario).getValue() == "") {                        //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un Veterinario");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si ya existe la relación
							if(Control_Veterinario_Sucursal.existe(((ComboItem) cbVeterinario.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Veterinario ya se encuentra en la sucursal");
							}else {
								Control_Veterinario_Sucursal.agregar(((ComboItem) cbVeterinario.getSelectedItem()).getValue(), ((ComboItem) cbSucursal.getSelectedItem()).getValue(), nombreV, nombreS);
								
							}
						}
						
						
					}
					
					mostrarTabla();
					
			}
		});
		btnAgregar.setBounds(467, 173, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");             //Este botón permite eliminar la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombreV = table.getValueAt(fila,1).toString();
				String nombreS = table.getValueAt(fila,2).toString();
				
				Control_Veterinario_Sucursal.eliminar(id, nombreV, nombreS);
				
				mostrarTabla();
				
			}
		});
		btnEliminar.setBounds(608, 173, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 48, 371, 372);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}
}

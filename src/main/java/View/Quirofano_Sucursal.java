package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Quirofano_Sucursal;
import Model.ComboItem;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Quirofano_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbQuirofano;
	private JComboBox cbSucursal;
	private JTable table;

	
	public DefaultComboBoxModel cargarQuirofano() {             //Carga el ComboBox quirofano
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		Control_ComboBoxes.CBQuirofano(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarSucursal() {       //Carga el ComboBox sucursal
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBSucursal(modelo);
		
		return modelo;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quirofano_Sucursal frame = new Quirofano_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

void mostrarTabla(){            // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Quirofano_Sucursal.tabla(modelo, table);
        
    }
	
	private void limpiar() {              //Este procedimiento limpia los campos
		cbQuirofano.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}

	/**
	 * Create the frame.
	 */
	public Quirofano_Sucursal(final String perfil) {             //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar quirófano con sucursal");
		lblTitulo.setBounds(113, 11, 212, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblQuirofano = new JLabel("Quirófano");
		lblQuirofano.setBounds(469, 64, 66, 14);
		contentPane.add(lblQuirofano);
		
		cbQuirofano = new JComboBox();
		cbQuirofano.setBounds(554, 60, 170, 22);
		contentPane.add(cbQuirofano);
		cbQuirofano.setModel(cargarQuirofano());
		
		JLabel lblSucursal = new JLabel("Sucursal");
		lblSucursal.setBounds(469, 122, 56, 14);
		contentPane.add(lblSucursal);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(554, 118, 170, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");           //Este botón permite agregar un quirófano a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Object quirofano = cbQuirofano.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				
				String nombreQ = cbQuirofano.getSelectedItem().toString();
				String nombreS = cbSucursal.getSelectedItem().toString();
		
				
				
					
					if (((ComboItem) quirofano).getValue() == "") {                        //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un quirófano");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si ya existe la relación
							if(Control_Quirofano_Sucursal.existe(((ComboItem) cbQuirofano.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Quirófano ya se encuentra en la sucursal");
							}else {
								Control_Quirofano_Sucursal.agregar(((ComboItem) cbQuirofano.getSelectedItem()).getValue(), ((ComboItem) cbSucursal.getSelectedItem()).getValue(), nombreQ, nombreS);
							}
						}
						
						
					}
					
					mostrarTabla();
				
			}
		});
		btnAgregar.setBounds(490, 180, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");         //Este botón elimina la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombreQ = table.getValueAt(fila,2).toString();
				String nombreS = table.getValueAt(fila,1).toString();
				
				Control_Quirofano_Sucursal.eliminar(id, nombreQ, nombreS);
				
				mostrarTabla();
				
			}
		});
		btnEliminar.setBounds(631, 180, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");                 //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano(perfil);
				tq.setVisible(true);		//Abre la ventana Tabla_Quirofano
				dispose();
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 47, 363, 373);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}

	public Quirofano_Sucursal() {
		// TODO Auto-generated constructor stub
	}
}

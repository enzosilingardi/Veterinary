package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Procedimiento_Veterinario;
import Model.ComboItem;
import Model.ControlFiles;

public class Procedimiento_Veterinario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbProcedimiento;
	private JComboBox cbVeterinario;



	public DefaultComboBoxModel cargarVeterinario() {     //Carga el ComboBox veterinario

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBVeterinario(modelo);
		
		return modelo;
    }
	

	public DefaultComboBoxModel cargarProcedimiento() {         //Carga el ComboBox procedimiento

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBProcedimiento(modelo);
		return modelo;
    }
	

	void mostrarTabla(){         // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Procedimiento_Veterinario.tabla(modelo, table);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Procedimiento_Veterinario frame = new Procedimiento_Veterinario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	private void limpiar() {                        //Este procedimiento limpia los campos
		cbProcedimiento.setSelectedIndex(0);
		cbVeterinario.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */ 
	public Procedimiento_Veterinario() {                     //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar veterinario a procedimiento");
		lblTitulo.setBounds(108, 11, 224, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblProcedimiento = new JLabel("Procedimiento");
		lblProcedimiento.setBounds(430, 53, 92, 14);
		contentPane.add(lblProcedimiento);
		
		cbProcedimiento = new JComboBox();
		cbProcedimiento.setBounds(532, 49, 192, 22);
		contentPane.add(cbProcedimiento);
		cbProcedimiento.setModel(cargarProcedimiento());
		
		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setBounds(430, 107, 92, 14);
		contentPane.add(lblVeterinario);
		
		cbVeterinario = new JComboBox();
		cbVeterinario.setBounds(532, 103, 192, 22);
		contentPane.add(cbVeterinario);
		cbVeterinario.setModel(cargarVeterinario());
		
		JButton btnAgregar = new JButton("Agregar");                 //Este botón permite agregar un veterinario a un procedimiento
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object proced = cbProcedimiento.getSelectedItem();
				Object veterinario = cbVeterinario.getSelectedItem();
				String nombreP = cbProcedimiento.getSelectedItem().toString();
				String nombreV = cbVeterinario.getSelectedItem().toString();
				
					
					if (((ComboItem) proced).getValue() == "") {                              //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un procedimiento");
					}else {
						if(((ComboItem) veterinario).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione un veterinario");
						}else {
							//Revisa si ya existe la relación
							if(Control_Procedimiento_Veterinario.existe(((ComboItem) cbProcedimiento.getSelectedItem()).getValue(),((ComboItem) cbVeterinario.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Veterinario ya está asociado al procedimiento");
							}else {
								
								Control_Procedimiento_Veterinario.agregar(((ComboItem) cbProcedimiento.getSelectedItem()).getValue(), ((ComboItem) cbVeterinario.getSelectedItem()).getValue(), nombreP, nombreV);
								
							}
						}
						
						
					}
					mostrarTabla();
					limpiar();
			}
		});
		btnAgregar.setBounds(477, 168, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");              //Este botón permite eliminar la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombreV = table.getValueAt(fila,1).toString();
				String nombreP = table.getValueAt(fila,2).toString();
				
				Control_Procedimiento_Veterinario.eliminar(id, nombreV, nombreP);
				
				mostrarTabla();
				
			}
		});
		btnEliminar.setBounds(621, 168, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
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

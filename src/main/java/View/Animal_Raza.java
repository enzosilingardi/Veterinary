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

import Control.ComboBoxes;
import Control.Connect;
import Control.Consulta_Relaciones;
import Model.ControlFiles;
import Model.ComboItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Animal_Raza extends JFrame {       // Esta clase añade o remueve una relacion entre animal y raza
	private JPanel contentPane;
	private JComboBox cbAnimal;
	private JComboBox cbRaza;
	private JTable table;
	

	public DefaultComboBoxModel cargarAnimal() {       // Carga el ComboBox animal
		
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBAnimal(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarRaza() {            // Carga el ComboBox con la raza del animal
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		ComboBoxes.CBRazaS(modelo);
		
		return modelo;
    }


	void mostrarTabla(){            // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Consulta_Relaciones.tablaAR(modelo, table);
        
    }
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animal_Raza frame = new Animal_Raza();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	
	private void limpiar() {					// Limpia los campos txt
		cbAnimal.setSelectedIndex(0);
		cbRaza.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Animal_Raza() {												// Arma la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));				// Se cambia el icono de la ventana

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar raza a animal");
		lblTitulo.setBounds(127, 11, 141, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setBounds(467, 60, 60, 14);
		contentPane.add(lblAnimal);
		
		cbAnimal = new JComboBox();						// Se arma el combobox animal
		cbAnimal.setBounds(537, 56, 168, 22);
		contentPane.add(cbAnimal);
		cbAnimal.setModel(cargarAnimal());
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(467, 116, 46, 14);
		contentPane.add(lblRaza);
		
		cbRaza = new JComboBox();						// se arma el combobox raza
		cbRaza.setBounds(537, 112, 168, 22);
		contentPane.add(cbRaza);
		cbRaza.setModel(cargarRaza());
		
		JButton btnAgregar = new JButton("Agregar"); 			// al presionar este boton se añade una relacion entre animal y raza
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object animal = cbAnimal.getSelectedItem();
				Object raza = cbRaza.getSelectedItem();
				
				
					
					if (((ComboItem) animal).getValue() == "") {
						JOptionPane.showMessageDialog(null, "Seleccione un animal");     
					}else {                                                                    //Revisa que los combobox no estén en blanco
						if(((ComboItem) raza).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una raza");
						}else {
							if(Consulta_Relaciones.existeAR(((ComboItem) cbAnimal.getSelectedItem()).getValue(),((ComboItem) cbRaza.getSelectedItem()).getValue())!=0) {        //Revisa que no exista la relación
								JOptionPane.showMessageDialog(null, "Raza ya se encuentra asociada al animal");
							}else {
								Consulta_Relaciones.agregarAr(((ComboItem) cbAnimal.getSelectedItem()).getValue(), ((ComboItem) cbRaza.getSelectedItem()).getValue());
							}
						}
						
						
					}
					
					mostrarTabla();
			}
		});
		btnAgregar.setBounds(475, 176, 89, 23);
		contentPane.add(btnAgregar);
			
		JButton btnEliminar = new JButton("Eliminar");				// boton que elimina la relacion seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				Consulta_Relaciones.eliminarAR(id);
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(616, 176, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				// cierra la ventana
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

}

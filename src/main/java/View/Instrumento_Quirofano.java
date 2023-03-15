package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Instrumento_Quirofano;
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
import javax.swing.JTextField;

public class Instrumento_Quirofano extends JFrame {

	private JPanel contentPane;
	private JComboBox cbQuirofano;
	private JComboBox cbInstrumento;
	private JTable table;
	private JTextField txtCantidad;


	
	public DefaultComboBoxModel cargarQuirofano() {        //Carga el ComboBox quirófano

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBQuirofano(modelo);
		return modelo;
    }
	
	public DefaultComboBoxModel cargarInstrumento() {         //Carga el ComboBox instrumento

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBInstrumento(modelo);
		return modelo;
    }
	

	void mostrarTabla(){            //Tabla que muestra las relaciones entre instrumento y quirófano
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Instrumento_Quirofano.tabla(modelo, table);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instrumento_Quirofano frame = new Instrumento_Quirofano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void limpiar() {                           //Este procedimiento limpia los campos
		cbQuirofano.setSelectedIndex(0);
		cbInstrumento.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */
	public Instrumento_Quirofano() {                           //Construye la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuirofano = new JLabel("Quirófano");
		lblQuirofano.setBounds(467, 58, 77, 14);
		contentPane.add(lblQuirofano);
		
		cbQuirofano = new JComboBox();
		cbQuirofano.setBounds(554, 54, 160, 22);
		contentPane.add(cbQuirofano);
		cbQuirofano.setModel(cargarQuirofano());
		
		JLabel lblInstrumento = new JLabel("Instrumento");
		lblInstrumento.setBounds(467, 110, 77, 14);
		contentPane.add(lblInstrumento);
		
		cbInstrumento = new JComboBox();
		cbInstrumento.setBounds(554, 106, 160, 22);
		contentPane.add(cbInstrumento);
		cbInstrumento.setModel(cargarInstrumento());
		
		JLabel lblTitulo = new JLabel("Agregar instrumento a quirófano");
		lblTitulo.setBounds(111, 11, 193, 14);
		contentPane.add(lblTitulo);
		
		JButton btnVolver = new JButton("Volver");		//Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");           //Este botón elimina la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombreI = table.getValueAt(fila,2).toString();
				String nombreQ = table.getValueAt(fila,1).toString();
				
				Control_Instrumento_Quirofano.eliminar(id, nombreI, nombreQ);
				
				mostrarTabla();
				
			}
		});
		btnEliminar.setBounds(625, 210, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");          //Este boton permite agregar un instrumento a un quirófano
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object quirofano = cbQuirofano.getSelectedItem();
				Object instrumento = cbInstrumento.getSelectedItem();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				String nombreQ = cbQuirofano.getSelectedItem().toString();
				String nombreI = cbInstrumento.getSelectedItem().toString();
				
				
					
					if (((ComboItem) quirofano).getValue() == "") {                     //Revisa que los ComboBox no estén en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un quirófano");
					}else {
						if(((ComboItem) instrumento).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione un instrumento");
						}else {
							if(Control_Instrumento_Quirofano.existe(((ComboItem) cbQuirofano.getSelectedItem()).getValue(),((ComboItem) cbInstrumento.getSelectedItem()).getValue())!=0) {         //Revisa que el instrumento no se encuentre en el quirófano
								JOptionPane.showMessageDialog(null, "Instrumento ya se encuentra en el quirófano");
							}else {
								Control_Instrumento_Quirofano.agregar(((ComboItem) cbQuirofano.getSelectedItem()).getValue(), ((ComboItem) cbInstrumento.getSelectedItem()).getValue(), cantidad, nombreQ, nombreI);
							}
						}
						
						
					}
					mostrarTabla();
					
				
			}
		});
		btnAgregar.setBounds(496, 210, 89, 23);
		contentPane.add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 55, 356, 356);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(554, 160, 160, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(467, 163, 46, 14);
		contentPane.add(lblCantidad);
		
		JButton btnModificar = new JButton("Modificar cantidad");      //Modifica la cantidad del instrumento seleccionado
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				boolean flagError = false;
				String cantidadAux = table.getValueAt(fila,3).toString();
				
				for(int i=0; i < cantidadAux.length(); i++ ) {           //Revisa que todos los caracteres sean números
					
					if (Character.isLetter(cantidadAux.charAt(i))){
						
						flagError = true;
						break;
					}
					
					
				}
				
				if (flagError) {
					
					JOptionPane.showMessageDialog(null, "Solo se permiten números",null,JOptionPane.ERROR_MESSAGE);      //En caso de haber letras lo avisa en pantalla
					
				}else {
				
				
				int cantidad = Integer.parseInt(table.getValueAt(fila,3).toString());
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombreI = table.getValueAt(fila,2).toString();
				String nombreQ = table.getValueAt(fila,1).toString();
				
				Control_Instrumento_Quirofano.modificar(cantidad, id, nombreI, nombreQ);
				
				
				}
				mostrarTabla();
			}
		});
		btnModificar.setBounds(533, 278, 151, 23);
		contentPane.add(btnModificar);
		
		JButton btnAgregarT = new JButton("Agregar a todos");      //Este botón agrega el instrumento seleccionado a todos los quirófanos
		btnAgregarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object instrumento = cbInstrumento.getSelectedItem();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				int result = 0;
				
				Control_Instrumento_Quirofano.todos(((ComboItem) cbInstrumento.getSelectedItem()).getValue(), cantidad);
				
				mostrarTabla();
				limpiar();
				
				
			}
		});
		btnAgregarT.setBounds(533, 244, 151, 23);
		contentPane.add(btnAgregarT);
		
		mostrarTabla();
	}
}

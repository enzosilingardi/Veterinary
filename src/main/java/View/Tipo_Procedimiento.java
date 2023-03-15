package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Control.Control_Tipo_Procedimiento;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tipo_Procedimiento extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTable table;


	void mostrarTabla(){        // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel(); 
        
        Control_Tipo_Procedimiento.tabla(modelo, table);
        
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tipo_Procedimiento frame = new Tipo_Procedimiento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	
	private void limpiar() {     //Este procedimiento limpia los campos
		txtNombre.setText("");
		
	}
	

	/**
	 * Create the frame.
	 */
	public Tipo_Procedimiento() {            //Crea la ventana
		setTitle("Tipo de Procemiento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));      //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(355, 74, 61, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(426, 71, 129, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");   //Este boton permite agregar un tipo de procedimiento
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				
				Control_Tipo_Procedimiento.agregar(nombre);
				
				mostrarTabla();
			}
		});
		btnAgregar.setBounds(367, 146, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");          //Este botón elimina la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombre = table.getValueAt(fila,1).toString();
				
				Control_Tipo_Procedimiento.eliminar(id, nombre);
				
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(466, 146, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");         //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedimiento_Medico pm = new Procedimiento_Medico();
				pm.setVisible(true);		//Abre la ventana Procedimiento_Medico
				dispose();
			}
		});
		btnVolver.setBounds(466, 321, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");          //Este botón permite modificar el tipo selecconado
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				String tipo = table.getValueAt(fila,1).toString();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombre = table.getValueAt(fila,1).toString();
				
				Control_Tipo_Procedimiento.modificar(tipo, id, nombre);
				
				mostrarTabla();
				
			}
		});
		btnModificar.setBounds(413, 180, 89, 23);
		contentPane.add(btnModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 266, 333);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}
}

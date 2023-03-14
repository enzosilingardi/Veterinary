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

import Control.Connect;
import Control.Consulta_Animal;
import Model.ControlFiles;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Animal extends JFrame {  			//Esta clase añade o remueve un animal

	private JPanel contentPane;
	private JTextField txtTipo;
	private JTable table;

	void mostrarTabla(){      										// Esta es la tabla que muestra los animales 
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Consulta_Animal.tabla(modelo, table);
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animal frame = new Animal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void limpiar() {
		txtTipo.setText("");       //Limpia los campos
	}
	
	
	
	public int animalEnUso(String animal) {				// Esta funcion no esta en unso debido que encontramos otra manera de mostrar dicho registro, posiblemente sera eliminada a futuro
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(Pet.id_Animal)\r\n"
					+ "FROM Animal\r\n"
					+ "JOIN Pet ON Pet.id_Animal = Animal.id_Animal\r\n"
					+ "WHERE Animal.type LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, animal);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
			}
			return 1;
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e);
			return 1;
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
		
		
	}

	/**
	 * Create the frame.
	 */
	public Animal(final String perfil) {												// Construye la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     // setea el icono de la ventana


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mascota mascota = new Mascota(perfil);
				mascota.setVisible(true);
				dispose();											// cierra la ventana y vuelve a la ventana Mascota
			}
		});
		btnVolver.setBounds(466, 321, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");					// elimina el animal seleccionado
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				Consulta_Animal.eliminar(id);
				
		        mostrarTabla();
		         
			}
		});
		btnEliminar.setBounds(447, 99, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");				// Agrega un animal nuevo
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tipo = txtTipo.getText();
				
				Consulta_Animal.agregar(tipo);
				

		                limpiar();
		                mostrarTabla();
		          
			}
		});
		btnAgregar.setBounds(348, 99, 89, 23);
		contentPane.add(btnAgregar);
		
		txtTipo = new JTextField();
		txtTipo.setColumns(10);
		txtTipo.setBounds(369, 54, 186, 20);
		contentPane.add(txtTipo);
		
		JLabel lblTipo = new JLabel("Tipo");					
		lblTipo.setBounds(321, 57, 46, 14);
		contentPane.add(lblTipo);
		
		JButton btnModificar = new JButton("Modificar");			// Boton que modifica el registro seleccionado
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = table.getSelectedRow();
				String tipo = table.getValueAt(fila,1).toString();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				Consulta_Animal.modificar(tipo, id);
				
		                mostrarTabla();
		           
			}
		});
		btnModificar.setBounds(403, 146, 89, 23);
		contentPane.add(btnModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 266, 333);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}
	public Animal() {
		// TODO Auto-generated constructor stub
	}
}

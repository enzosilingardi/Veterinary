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
import Control.Consulta_Raza;
import Model.ControlFiles;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Raza extends JFrame {

	private JPanel contentPane;
	private JTextField txtTipo;
	private JTable table;

	
	void mostrarTabla(){               // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Consulta_Raza.tabla(modelo, table);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Raza frame = new Raza();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private void limpiar() {  //Este procedimiento limpia los campos
		txtTipo.setText("");    
	}
	

	
	public int razaEnUso(String raza) {       //Este procedimiento no es utilizado en la versión actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();		//Realiza la Conexión
			String SSQL = "SELECT count(Pet.id_Breed)\r\n"		//Sentencia Sql
					+ "FROM Breed\r\n"
					+ "JOIN Pet ON Pet.id_Breed = Breed.id_Breed\r\n"
					+ "WHERE Breed.type LIKE ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, raza);
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
	public Raza() {                 //Crea la ventana
		setTitle("Razas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(320, 57, 46, 14);
		contentPane.add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(369, 54, 186, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(466, 321, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");              //Este botón elimina la raza seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String tipo = table.getValueAt(fila,1).toString();
				
				Consulta_Raza.eliminar(id, tipo);
				
				mostrarTabla();
				
			}
		});
		btnEliminar.setBounds(466, 91, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");            //Este botón permite agragar una raza
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tipo = txtTipo.getText();
				
				Consulta_Raza.agregar(tipo);
				limpiar();
				mostrarTabla();
				
			}
		});
		btnAgregar.setBounds(369, 91, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnRel = new JButton("Asociar con animal");       //Abre la ventana Animal_Raza
		btnRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal_Raza ar = new Animal_Raza();
				ar.setVisible(true);
			}
		});
		btnRel.setBounds(411, 241, 121, 23);
		contentPane.add(btnRel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 266, 333);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnModificar = new JButton("Modificar");           //Este botón permite modificar la raza seleccionada
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int fila = table.getSelectedRow();
				String tipo = table.getValueAt(fila,1).toString();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombre = table.getValueAt(fila,1).toString();
				
				Consulta_Raza.modificar(tipo, id, nombre);
				
				mostrarTabla();
				
			}
		});
		btnModificar.setBounds(421, 136, 89, 23);
		contentPane.add(btnModificar);
		
		mostrarTabla();
	}
}

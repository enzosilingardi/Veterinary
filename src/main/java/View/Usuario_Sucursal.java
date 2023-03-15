package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Control_ComboBoxes;
import Control.Connect;
import Control.Control_Usuario_Sucursal;
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

public class Usuario_Sucursal extends JFrame {

	private JPanel contentPane;
	private JComboBox cbUsuario;
	private JComboBox cbSucursal;
	private JTable table;




	public DefaultComboBoxModel cargarUsuario() {  //Carga el ComboBox usuario

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBUsuario(modelo);
		
		return modelo;
    }
	
	public DefaultComboBoxModel cargarSucursal() {        //Carga el ComboBox sucursal  
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		Control_ComboBoxes.CBSucursal(modelo);
		
		return modelo;
    }
	
	void mostrarTabla(){               // Carga la tabla con la informacion de la base de datos
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Control_Usuario_Sucursal.tabla(modelo, table);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario_Sucursal frame = new Usuario_Sucursal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	private void limpiar() {                 //Este procedimiento limpia los campos
		cbUsuario.setSelectedIndex(0);
		cbSucursal.setSelectedIndex(0);
		
	}
	/**
	 * Create the frame.
	 */ 
	public Usuario_Sucursal() {                             //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png"))); //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Asociar usuario con sucursal");
		lblTitulo.setBounds(115, 11, 184, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(464, 58, 65, 14);
		contentPane.add(lblUsuario);
		
		cbUsuario = new JComboBox();
		cbUsuario.setBounds(540, 54, 184, 22);
		contentPane.add(cbUsuario);
		cbUsuario.setModel(cargarUsuario());
		
		JLabel lblNewLabel = new JLabel("Sucursal");
		lblNewLabel.setBounds(464, 119, 65, 14);
		contentPane.add(lblNewLabel);
		
		cbSucursal = new JComboBox();
		cbSucursal.setBounds(540, 115, 184, 22);
		contentPane.add(cbSucursal);
		cbSucursal.setModel(cargarSucursal());
		
		JButton btnAgregar = new JButton("Agregar");                //Este botón permite agregar un usuario a una sucursal
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object usuario = cbUsuario.getSelectedItem();
				Object sucursal = cbSucursal.getSelectedItem();
				String nombreU = cbUsuario.getSelectedItem().toString();
				String nombreS = cbSucursal.getSelectedItem().toString();
				
				
				
					
					if (((ComboItem) usuario).getValue() == "") {                       //Revisa si los ComboBox están en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un usuario");
					}else {
						if(((ComboItem) sucursal).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione una sucursal");
						}else {
							//Revisa si existe la relación
							if(Control_Usuario_Sucursal.existe(((ComboItem) cbUsuario.getSelectedItem()).getValue(),((ComboItem) cbSucursal.getSelectedItem()).getValue())!=0) {
								JOptionPane.showMessageDialog(null, "Usuario ya se encuentra en la sucursal");
							}else {
								Control_Usuario_Sucursal.agregar(((ComboItem) cbUsuario.getSelectedItem()).getValue(), ((ComboItem) cbSucursal.getSelectedItem()).getValue(), nombreU, nombreS);
								
							}
						}
						
						
					}
					
					mostrarTabla();
					
					
				
			}
		});
		btnAgregar.setBounds(487, 174, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");            //Este botón permite eliminar la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String usuario = table.getValueAt(fila,1).toString();
				String sucursal = table.getValueAt(fila,2).toString();
				
				Control_Usuario_Sucursal.eliminar(id, usuario, sucursal);
				
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(628, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");         //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 36, 393, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostrarTabla();
	}
}

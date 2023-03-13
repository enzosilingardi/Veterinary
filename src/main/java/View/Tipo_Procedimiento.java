package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
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
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Tipo de procedimiento"});       //Nombre de las columnas
       
        table.setModel(modelo);        //Setea el modelo
        
        
        
        String datos[] = new String[2];         //Declara que va a haber 2 columnas
       
        try {
        	Connection con = Connect.getConexion();       //Realiza la conexión
        	//Sentencia sql
        	PreparedStatement ps = con.prepareStatement("SELECT * FROM Procedure_Type" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                     //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);        //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);    // los 4 siguientes hacen que la columna del id sea invisible para el usuario
    		table.getColumnModel().getColumn(0).setMinWidth(0);
    		table.getColumnModel().getColumn(0).setPreferredWidth(0);
    		table.getColumnModel().getColumn(0).setResizable(false);
        } catch(SQLException E) {
			JOptionPane.showMessageDialog(null,E);
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
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

	
	public int existeTipo(String nombre) {        //Este procedimiento revisa si ya existe el tipo
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();   //Realiza la conexión
			
			String SSQL = "SELECT count(proced_Name) FROM Procedure_Type WHERE proced_Name = ?;";   //Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);          //Si ya existe el tipo, la variable se pone en 1
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
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Procedure_Type (proced_Name) VALUES (?)" );
					
					if(existeTipo(nombre) != 0) {          //Revisa si ya existe el tipo
						
						JOptionPane.showMessageDialog(null, "Tipo ya existe");
					}else {
						ps.setString(1, nombre);
					}
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo guardado");      //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha añadido el tipo de procedimiento "+nombre);
		                limpiar();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar Tipo");     //En caso de fallar, lo avisa en pantalla
		                limpiar();
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(367, 146, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");          //Este botón elimina la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();     //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Procedure_Type WHERE id_Procedure_Type = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo eliminado");        //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el tipo de procedimiento "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar tipo");    //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Tipo está en uso, por favor elimine todos los registros relacionados");      //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
				int result = 0;
				
				int fila = table.getSelectedRow();
				String tipo = table.getValueAt(fila,1).toString();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				try {
					Connection con = Connect.getConexion();     //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Procedure_Type SET proced_Name = ? WHERE id_Procedure_Type = ?" );  //Crea el statement
					
					ps.setString(1, tipo);
					ps.setInt(2, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo modificado");   //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha modificado el tipo de procedimiento "+table.getValueAt(fila,1).toString());
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar tipo");    //En caso de fallar, lo avisa en pantalla
		                mostrarTabla();
		            }
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

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
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Animal"});			// nombre de las columnas
       
        table.setModel(modelo);
        
        
        
        String datos[] = new String[2]; 							//declara que va a haber 2 columnas
       
        try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT * FROM Animal;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){								// carga las columnas de la base de datos a la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);								// se setea el modelo
            
            table.getColumnModel().getColumn(0).setMaxWidth(0);     // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
	
	public int existeAnimal(String animal) {		// Verifica si ya existe el animal en la base de datos
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();
			String SSQL = "SELECT count(type) FROM Animal WHERE type = ?;";
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, animal);
			result = pst.executeQuery();
			
			if (result.next()) {				// si ya existe, la variable la coloca como 1
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
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("DELETE FROM Animal WHERE id_Animal = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Animal eliminado");			//Si es exitoso lo muestra en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha eliminado un tipo de animal");		
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar animal");	// Si falla, lo muestra en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Animal está en uso, por favor elimine todos los registros relacionados");			// Si falla, lo muestra en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(447, 99, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");				// Agrega un animal nuevo
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				String tipo = txtTipo.getText();
				
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("INSERT INTO Animal (type) VALUES (?)" );  //Crea la sentencia sql
					if(existeAnimal(tipo) != 0) {
						JOptionPane.showMessageDialog(null, "Animal ya existe");            //Revisa si ya existe el registro
					}else {
						ps.setString(1, tipo);
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){																//Si es exitoso lo muestra en pantalla y lo añade al log
		                JOptionPane.showMessageDialog(null, "Animal guardado");
		                ControlFiles.addContent("Se ha añadido el tipo de animal "+tipo);
		                limpiar();
		                mostrarTabla();
		            } else {																	//Si falla lo muestra en pantalla
		                JOptionPane.showMessageDialog(null, "Error al guardar animal");
		                limpiar();
		                mostrarTabla();
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				int result = 0;
				
				int fila = table.getSelectedRow();
				String tipo = table.getValueAt(fila,1).toString();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				try {
					Connection con = Connect.getConexion();
					PreparedStatement ps = con.prepareStatement("UPDATE Animal SET type = ? WHERE id_Animal = ?" );  //Crea la sentencia sql
					
					ps.setString(1, tipo);
					ps.setInt(2, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){																		//Si es exitoso lo muestra por pantalla y lo añade al log
		                JOptionPane.showMessageDialog(null, "Animal modificado");
		                ControlFiles.addContent("Se ha modificado un tipo de animal a "+tipo);
		                mostrarTabla();
		            } else {																			//Si falla lo muestra por pantalla
		                JOptionPane.showMessageDialog(null, "Error al modificar animal");
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

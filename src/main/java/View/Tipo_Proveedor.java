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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tipo_Proveedor extends JFrame {

	private JPanel contentPane;
	private JComboBox cbTipo;
	private JTextField txtNombre;
	private JTable table;

	

	void mostrarTabla(){    // Carga la tabla con la informacion de la base de datos
         
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Tipo de proveedor"});   //Nombre de las columnas
       
        table.setModel(modelo);    //Setea el modelo
        
        
        
        String datos[] = new String[2];      //Declara que va a haber 2 columnas
       
        try {
        	Connection con = Connect.getConexion();       //Realiza la conexión
        	
        	PreparedStatement ps = con.prepareStatement("SELECT * FROM Provider_Type" );		//Sentencia Sql
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                        //Carga las columnas de la base de datos en la tabla
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);       //Setea el modelo

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
					Tipo_Proveedor frame = new Tipo_Proveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int existeTipo(String nombre) {       //Este procedimiento revisa si ya existe el tipo
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();     //Realiza la conexión
			
			String SSQL = "SELECT count(type_Name) FROM Provider_Type WHERE type_Name = ?;";		//Sentencia Sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1, nombre);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);      //Si ya existe el tipo, la variable se vuelve 1
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

	
	
	private void limpiar() {        //Este procedimiento limpia los campos 

		txtNombre.setText("");
		
	}

	/**
	 * Create the frame.
	 */
	public Tipo_Proveedor(final String perfil) {           //Crea la ventana
		setTitle("Tipo Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");              //Este boton permite agregar un tipo de proveedor
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = txtNombre.getText();

				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Provider_Type (type_Name) VALUES (?)" );
					
					
					
					if(existeTipo(nombre)!=0) {       //Revisa si ya existe el tipo
						JOptionPane.showMessageDialog(null, "Tipo ya existe");
					}else {
						ps.setString(1, nombre);
					}
						
					
					
					result = ps.executeUpdate();
					
					if(result > 0){          
						
		                JOptionPane.showMessageDialog(null, "Tipo guardado");       //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha añadido el tipo de proveedor "+nombre);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar tipo");    //En caso de fallar, lo avisa en pantalla
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
		btnAgregar.setBounds(352, 130, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");    //Este botón elimina la fila seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					PreparedStatement ps = con.prepareStatement("DELETE FROM Provider_Type WHERE id_Provider_Type = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo eliminado");    //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha eliminado el tipo de proveedor "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar tipo");      //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Tipo está en uso, por favor elimine todos los registros relacionados");     //En caso de fallar lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(466, 130, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proveedor tp = new Proveedor(perfil);
				tp.setVisible(true);		//Abre la ventana Proveedor
				dispose();
			}
		});
		btnVolver.setBounds(466, 321, 89, 23);
		contentPane.add(btnVolver);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(362, 61, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(418, 58, 137, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 266, 333);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnModificar = new JButton("Modificar");            //Este botón permite modificar el tipo selecconado
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				
				int fila = table.getSelectedRow();
				String tipo = table.getValueAt(fila,1).toString();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Provider_Type SET type_Name = ? WHERE id_Provider_Type = ?" );  //Crea el statement
					
					ps.setString(1, tipo);
					ps.setInt(2, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Tipo modificado");     //Si fue exitoso, lo muestra mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha modificado el tipo de proveedor "+table.getValueAt(fila,1).toString());
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
		btnModificar.setBounds(412, 180, 89, 23);
		contentPane.add(btnModificar);
		
		mostrarTabla();
	}
	public Tipo_Proveedor() {
		// TODO Auto-generated constructor stub
	}

}

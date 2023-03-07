package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Connect;
import Model.ControlFiles;
import View.Ciudad.ComboItem;

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

	class ComboItem                                     //Clase utilizada para armar el ComboBox
	{
	    private String key;                             //Label visible del ComboBox
	    
	    private String value;                           //Valor del ComboBox

	    public ComboItem(String key, String value)      //Genera el label que se verá en el combobox y el valor del objeto seleccionado
	    {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public String toString()
	    {
	        return key;
	    }

	    public String getKey()
	    {
	        return key;
	    }

	    public String getValue()
	    {
	        return value;
	    }
	}
	
	public DefaultComboBoxModel cargarQuirofano() {                   //Carga el ComboBox quirófano
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();                          //Realiza la conexión
			
			String SSQL = "SELECT * FROM Operating_Room ORDER BY id_Operating_Room";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("room_Number"),result.getString("id_Operating_Room")));    //El elemento del ComboBox recibe el número y el id del quirófano
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }
	
	public DefaultComboBoxModel cargarInstrumento() {                        //Carga el ComboBox instrumento
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		
		
		try {
			cn = (Connection) Connect.getConexion();                    //Realiza la conexión
			
			String SSQL = "SELECT * FROM Medical_Instrument ORDER BY id_Medical_Instrument";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			modelo.addElement(new ComboItem("",""));             //El primer elemento del ComboBox es en blanco
			
			while (result.next()) {
				modelo.addElement(new ComboItem(result.getString("instrument_Name"),result.getString("id_Medical_Instrument")));         //El elemento del ComboBox recibe el nombre y el id del instrumento
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return modelo;
    }
	

	void mostrarTabla(){                        //Tabla que muestra las relaciones entre instrumento y quirófano
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnIdentifiers(new Object[] {"ID","Quirófano","Instrumento","Cantidad"});      //Nombre de las columnas
       
        table.setModel(modelo);            //Setea el modelo
        
        
        
        String datos[] = new String[4];           //Declara que va a haber 4 columnas
       
        try {
        	Connection con = Connect.getConexion();         //Realiza la conexión
        	
        	PreparedStatement ps = con.prepareStatement("SELECT id_ORMI, room_Number, instrument_Name, quantity\r\n"
        			+ "FROM Rel_Operating_R_Medical_I\r\n"
        			+ "INNER JOIN Operating_Room ON Operating_Room.id_Operating_Room = Rel_Operating_R_Medical_I.id_Operating_Room\r\n"
        			+ "INNER JOIN Medical_Instrument ON Medical_Instrument.id_Medical_Instrument = Rel_Operating_R_Medical_I.id_Medical_Instrument;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){                          // cargan las columnas de la base de datos a la tabla           
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                modelo.addRow(datos);

            }
            
            table.setModel(modelo);                                   //Setea el modelo

            table.getColumnModel().getColumn(0).setMaxWidth(0);           // los 4 siguientes hacen que la columna del id sea invisible para el usuario
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
					Instrumento_Quirofano frame = new Instrumento_Quirofano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int existeRel(Object quirofano, Object instrumento) {              //Determina si ya se encuentra el instrumento en el quirófano
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			cn = (Connection) Connect.getConexion();                           //Realiza la conexión
			
			String SSQL = "SELECT count(*) FROM Rel_Operating_R_Medical_I WHERE id_Operating_Room = ? AND id_Medical_Instrument = ?;";   //Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setString(1,(String) quirofano);
			pst.setString(2, (String) instrumento);
			result = pst.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);                          //Si la relación ya existe, la variable se pone en 1
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
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnVolver.setBounds(635, 418, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");                             //Este botón elimina la relación seleccionada
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				try {
					Connection con = Connect.getConexion();               //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("DELETE FROM Rel_Operating_R_Medical_I WHERE id_ORMI = ?" );
					
					ps.setInt(1, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento eliminado de quirofano");                  //Si fue existoso, lo avisa mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha removido el instrumento "+table.getValueAt(fila,2).toString() +" del quirófano "+table.getValueAt(fila,1).toString());
		               mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar instrumento");              //En caso de fallar, lo avisa en pantalla
		                
		            }
					con.close();
				}catch(SQLException E) {
					E.printStackTrace();
					JOptionPane.showMessageDialog(null, "Relación está en uso, por favor elimine todos los registros relacionados");            //En caso de fallar, lo avisa en pantalla
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEliminar.setBounds(625, 210, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnAgregar = new JButton("Agregar");                         //Este boton permite agregar un instrumento a un quirófano
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object quirofano = cbQuirofano.getSelectedItem();
				Object instrumento = cbInstrumento.getSelectedItem();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();                 //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Operating_R_Medical_I (id_Operating_Room,id_Medical_Instrument,quantity) VALUES (?,?,?)" );
					
					
					if (((ComboItem) quirofano).getValue() == "") {                          //Revisa que los ComboBox no estén en blanco
						JOptionPane.showMessageDialog(null, "Seleccione un quirófano");
					}else {
						if(((ComboItem) instrumento).getValue() == ""){
							JOptionPane.showMessageDialog(null, "Seleccione un instrumento");
						}else {
							if(existeRel(((ComboItem) cbQuirofano.getSelectedItem()).getValue(),((ComboItem) cbInstrumento.getSelectedItem()).getValue())!=0) {         //Revisa que el instrumento no se encuentre en el quirófano
								JOptionPane.showMessageDialog(null, "Instrumento ya se encuentra en el quirófano");
							}else {
								ps.setString(1, ((ComboItem) quirofano).getValue());
								ps.setString(2, ((ComboItem) instrumento).getValue());
								ps.setInt(3, cantidad);
							}
						}
						
						
					}
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento colocado");                                          //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo añade al log
		                ControlFiles.addContent("Se ha asociado el instrumento "+instrumento+" al quirofano "+quirofano);
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar instrumento");             //En caso de fallar, lo avisa en pantalla
		                limpiar();
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
		
		JButton btnModificar = new JButton("Modificar cantidad");                  //Modifica la cantidad del instrumento seleccionado
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				boolean flagError = false;
				String cantidadAux = table.getValueAt(fila,3).toString();
				
				for(int i=0; i < cantidadAux.length(); i++ ) {              //Revisa que todos los caracteres sean números
					
					if (Character.isLetter(cantidadAux.charAt(i))){
						
						flagError = true;
						break;
					}
					
					
				}
				
				if (flagError) {
					
					JOptionPane.showMessageDialog(null, "Solo se permiten números",null,JOptionPane.ERROR_MESSAGE);         //En caso de haber letras lo avisa en pantalla
					
				}else {
				
				
				int cantidad = Integer.parseInt(table.getValueAt(fila,3).toString());
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();           //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Rel_Operating_R_Medical_I SET quantity = ? WHERE id_ORMI = ?");
					
						
						if(cantidad<0){
							JOptionPane.showMessageDialog(null, "No se permiten números negativos",null,JOptionPane.ERROR_MESSAGE);     //En caso de ser un número negativo, lo avisa en pantalla
							mostrarTabla();
					
						}else {
							
							ps.setInt(1,cantidad);
					
						}
						
					
					
					
					ps.setInt(2, id);
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Cantidad modificada");                                       //Si fue existoso, lo avisa mediante un mensaje en pantalla y lo añade al log
		                
		                ControlFiles.addContent("Se ha modificado la cantidad del instrumento "+table.getValueAt(fila,3).toString()+" en el quirófano "+table.getValueAt(fila,1).toString());
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar cantidad");        //En caso de fallar, lo avisa en pantalla
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
			}
		});
		btnModificar.setBounds(533, 278, 151, 23);
		contentPane.add(btnModificar);
		
		JButton btnAgregarT = new JButton("Agregar a todos");                 //Este botón agrega el instrumento seleccionado a todos los quirófanos
		btnAgregarT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object instrumento = cbInstrumento.getSelectedItem();
				int cantidad = Integer.parseInt(txtCantidad.getText());
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();       //Realiza la conexión
					
					PreparedStatement pre = con.prepareStatement("SELECT id_Operating_Room FROM Operating_Room" );
					
					
					
					
					if(((ComboItem) instrumento).getValue() == ""){                     //Revisa que el ComboBox no esté vacío
						
						JOptionPane.showMessageDialog(null, "Seleccione un instrumento");
						
					}else {
						
						
							
						ResultSet rs = pre.executeQuery();
						
						while (rs.next()){
							
							PreparedStatement ps = con.prepareStatement("INSERT INTO Rel_Operating_R_Medical_I (id_Operating_Room,id_Medical_Instrument,quantity) VALUES (?,?,?)" );
							
							if(existeRel(rs.getString(1),((ComboItem) cbInstrumento.getSelectedItem()).getValue())!=0) {      //Revisa que el instrumento no se encuentre en el quirófano
								
								
							}else {
							
							ps.setString(1, rs.getString(1));
							
							ps.setString(2, ((ComboItem) instrumento).getValue());
							
							ps.setInt(3, cantidad);
							
							result = ps.executeUpdate();
							
							}
			            }
						
						
						
					}
						
				
						
						
					
					
					
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Instrumento colocado en todos los quirófanos");        //Si fue existoso, lo avisa mediante un mensaje en pantalla 
		                limpiar();
		                mostrarTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al colocar instrumento");               //En caso de fallar, lo avisa en pantalla
		                limpiar();
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
		btnAgregarT.setBounds(533, 244, 151, 23);
		contentPane.add(btnAgregarT);
		
		mostrarTabla();
	}
}

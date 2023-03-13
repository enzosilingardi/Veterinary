package View;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class Modificar_Quirofano extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Quirofano frame = new Modificar_Quirofano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargarCampos(String quirofano) {         //Carga los campos recibiendo como parámetro el id del quirófano
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		int id = Integer.parseInt(quirofano);
		
		try {
			cn = (Connection) Connect.getConexion();    //Realiza la conexión
			
			String SSQL = "SELECT room_Number FROM Operating_Room WHERE id_Operating_Room = ?";		//Sentencia sql
			pst = cn.prepareStatement(SSQL);
			pst.setInt(1, id);
			
			
			result = pst.executeQuery();
			while (result.next()){                       //Carga los campos con los resultados de la base de datos
			txtNumero.setText(result.getString(1));
			}
			cn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	/**
	 * Create the frame.
	 */
	public Modificar_Quirofano(String quirofano, final String perfil) {       //Crea la ventana recibiendo como parámetro el id del quirófano y el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));       //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumero = new JLabel("Número de quirófano");
		lblNumero.setBounds(53, 66, 131, 14);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(192, 63, 183, 20);
		contentPane.add(txtNumero);
		
		JButton btnVolver = new JButton("Volver");               //Cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tabla_Quirofano tq = new Tabla_Quirofano(perfil);
				tq.setVisible(true);		//Abre la ventana Tabla_Quirofano recibiendo como parámetro el perfil del usuario
				dispose();
			}
		});
		btnVolver.setBounds(254, 137, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnModificar = new JButton("Modificar");          //Este botón modifica el quirófano según los datos ingresados
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());
				int numero = Integer.parseInt(txtNumero.getText());

				
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();      //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Operating_Room SET room_Number = ? WHERE id_Operating_Room = ?" );
					
					ps.setInt(1, numero);
					ps.setInt(2, id);
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Quirófano modificado");            //Si fue exitoso, lo avisa mediante un mensaje en pantalla y lo agrega al log, después regresa a la ventana Table_Quirofano
		                ControlFiles.addContent("Se ha modificado el quirófano "+numero);
		                Tabla_Quirofano tq = new Tabla_Quirofano();
						tq.setVisible(true);
						dispose();
		                
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar quirófano");     //En caso de fallar, lo avisa en pantalla
		                
		            }
					
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(82, 137, 89, 23);
		contentPane.add(btnModificar);
		
		txtId = new JTextField();
		txtId.setBounds(53, 11, 13, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		txtId.setEnabled(false);
		
		cargarCampos(quirofano);
		txtId.setText(quirofano);
		
	}

	public Modificar_Quirofano() {
		// TODO Auto-generated constructor stub
	}

	
}

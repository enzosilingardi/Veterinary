package View;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.Connect;

public class Emisor_Pre extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmisor;
	private JTextField txtCuit;
	private JTextField txtEmpresa;

	void cargarEmisor() {                                    //Este proceso carga los datos del emisor actual
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		
		
		try {
			cn = (Connection) Connect.getConexion();                   //Realiza la conexión
			String SSQL = "SELECT * FROM Emitter";
			pst = cn.prepareStatement(SSQL);
			result = pst.executeQuery();
			
			
			while (result.next()) {                                     //Carga los campos con los datos en el registro
				txtEmisor.setText(result.getString("name"));
				txtCuit.setText(result.getString("cuit"));
				txtEmpresa.setText(result.getString("address"));
				
			}
			cn.close();
		}catch(SQLException e) {
				JOptionPane.showMessageDialog(null,e);
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
					Emisor_Pre frame = new Emisor_Pre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Emisor_Pre() {                                          //Crea la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 293, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));        //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmisor = new JTextField();
		txtEmisor.setBounds(44, 44, 168, 20);
		contentPane.add(txtEmisor);
		txtEmisor.setColumns(10);
		
		JLabel lblEmisor = new JLabel("Emisor");
		lblEmisor.setBounds(44, 11, 46, 14);
		contentPane.add(lblEmisor);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(44, 90, 46, 14);
		contentPane.add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(44, 115, 168, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		JLabel lblEmpresa = new JLabel("Dirección empresa");
		lblEmpresa.setBounds(44, 169, 168, 14);
		contentPane.add(lblEmpresa);
		
		txtEmpresa = new JTextField();
		txtEmpresa.setBounds(44, 197, 168, 20);
		contentPane.add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		JButton btnEditar = new JButton("Editar");                     //Actualiza los datos del emisor
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtEmisor.getText();
				String cuit = txtCuit.getText();
				String empresa = txtEmpresa.getText();
				int result = 0;
				
				try {
					Connection con = Connect.getConexion();         //Realiza la conexión
					
					PreparedStatement ps = con.prepareStatement("UPDATE Emitter SET name = ?, cuit = ?, address = ? WHERE id_Emitter = 1" );
					
					
				
						
						ps.setString(1, nombre);
						ps.setString(3, empresa);
						ps.setString(2,cuit);
					
						
					
					
					
					
					result = ps.executeUpdate();
					
					if(result > 0){
		                JOptionPane.showMessageDialog(null, "Emisor modificado");            //En caso de ser exitoso, lo muestra en pantalla y vuelve a la ventana Presupuesto
		                Presupuesto pre = new Presupuesto();
						pre.setVisible(true);
						dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al modificar emisor");      //En caso de fallar, lo muestra en pantalla
		               
		            }
				
					
				}catch(SQLException E) {
					E.printStackTrace();
				}catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(23, 252, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnVolver = new JButton("Volver");               //Este botón cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Presupuesto pre = new Presupuesto();
				pre.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(156, 252, 89, 23);
		contentPane.add(btnVolver);
		
		cargarEmisor();
	}

}

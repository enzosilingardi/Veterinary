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
import Control.Control_Emisor;

public class Emisor_Pre extends JFrame {

	private JPanel contentPane;
	public static JTextField txtEmisor;
	public static JTextField txtCuit;
	public static JTextField txtEmpresa;


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
	public Emisor_Pre() {       //Crea la ventana
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
		
		JButton btnEditar = new JButton("Editar");          //Actualiza los datos del emisor
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtEmisor.getText();
				String cuit = txtCuit.getText();
				String empresa = txtEmpresa.getText();
				
				Control_Emisor.editar(nombre, empresa, cuit);
				
				Presupuesto pre = new Presupuesto();
				pre.setVisible(true);
				dispose();
				
				
			}
		});
		btnEditar.setBounds(23, 252, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnVolver = new JButton("Volver");       //Este botón cierra la ventana
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Presupuesto pre = new Presupuesto();
				pre.setVisible(true);		//Abre la ventana Presupuesto
				dispose();
			}
		});
		btnVolver.setBounds(156, 252, 89, 23);
		contentPane.add(btnVolver);
		
		Control_Emisor.cargarPre();
	}

}

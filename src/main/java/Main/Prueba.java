package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxrck.autocompleter.TextAutoCompleter;

import Control.Connect;
import Model.ControlFiles;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Prueba extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrueba;
	private TextAutoCompleter ac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba frame = new Prueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void cargarAuto() {
		try {
        	Connection con = Connect.getConexion();
        	PreparedStatement ps = con.prepareStatement("SELECT name FROM Client;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                
            	ac.addItem(rs.getString(1));

            }
            
        } catch(SQLException E) {
			JOptionPane.showMessageDialog(null,E);
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlFiles.addContent("Hola");
			}
		});
		btnNewButton.setBounds(158, 124, 89, 23);
		contentPane.add(btnNewButton);
		
		txtPrueba = new JTextField();
		txtPrueba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				cargarAuto();
			}
		});
		ac = new TextAutoCompleter(txtPrueba);
		txtPrueba.setBounds(62, 37, 86, 20);
		contentPane.add(txtPrueba);
		txtPrueba.setColumns(10);
	}
}

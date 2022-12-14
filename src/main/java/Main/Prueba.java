package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;

import Control.Connect;
import Model.ControlFiles;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class Prueba extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrueba;
	private TextAutoCompleter ac;
	private JDateChooser txtFecha;
	private JTextField txtEdad;

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
		btnNewButton.setBounds(62, 68, 89, 23);
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
		
		txtFecha = new JDateChooser("yyyy-MM-dd", "####-##-##", '_');
		txtFecha.setBounds(62, 146, 141, 20);
		contentPane.add(txtFecha);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(62, 194, 141, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Calcular edad");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fecha = ((JTextField) txtFecha.getDateEditor().getUiComponent()).getText();
				Date dateN = Date.valueOf(fecha);
				LocalDate date = dateN.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate();
				LocalDate dateA = LocalDate.now(); 
				int anio = date.getYear();
				int mes = date.getMonthValue();
				int dia = date.getDayOfYear();
				int diff_mes = dateA.getMonthValue() - mes ;
				int diff_anio = dateA.getYear() - anio ;
				int diff_dia = dateA.getDayOfYear() - dia ;
				if(diff_mes<0 ||(diff_mes==0 && diff_dia<0)){
					diff_anio =diff_anio-1;
					}
				txtEdad.setText(Integer.toString(diff_anio));
			}
		});
		btnNewButton_1.setBounds(225, 146, 89, 23);
		contentPane.add(btnNewButton_1);
	
	}
}

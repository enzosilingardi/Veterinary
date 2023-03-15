package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Control.Connect;
import Control.Consulta_Historial;
import Model.ControlFiles;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Tabla_Historial extends JFrame {

	private JPanel contentPane;
	private JTable table;

	void mostrarTabla(){         // Carga la tabla con la informacion de la base de datos
	        
	        DefaultTableModel modelo = new DefaultTableModel();
	        
	        Consulta_Historial.tabla(modelo, table);
	        
	    }

	void mostrarTablaId(String id){            //Muestra la tabla según el id de la mascota recibido por parámetro
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        Consulta_Historial.tablaId(modelo, table, id);
        
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Historial frame = new Tabla_Historial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void generar() throws FileNotFoundException,DocumentException {        //Genera un archivo PDF con el historial
		
			
			
			FileOutputStream archivo = new FileOutputStream("c:/rsc/Historial Mascota "+table.getValueAt(0,5).toString()+".pdf");   //Genera la ruta del PDF
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);     //Prepara el documento
			documento.open();
			
			Paragraph parrafo = new Paragraph("Historial médico");   //Añade el título
			parrafo.setAlignment(1);
			documento.add(parrafo);
			
			
			Paragraph parrafoM = new Paragraph("Mascota: "+table.getValueAt(0,1).toString());    //Añade el nombre de la mascota
			parrafoM.setAlignment(1);
			documento.add(parrafoM);
			
			Paragraph parrafoD = new Paragraph("Dueño: "+table.getValueAt(0,2).toString());      //Añade el dueño
			parrafoD.setAlignment(1);
			documento.add(parrafoD);
			
			documento.add(new Paragraph(" "));
            
                for (int i = 0; i < table.getRowCount(); i++) {                                   //Añade el historial
                    documento.add(new Paragraph("Fecha: "+table.getValueAt(i,4).toString()));
                    documento.add(new Paragraph("Descripción: "+table.getValueAt(i,3).toString()));
                    documento.add(new Paragraph(" "));
                }
            
		
	       
			
			documento.close();
			
			JOptionPane.showMessageDialog(null, "Historial creado");      //Muestra un mensaje en pantala indicando que se creó con exito
			
		
	}
	/**
	 * Create the frame.
	 */
	public Tabla_Historial(final String perfil) {             //Crea la ventana recibiendo como parámetro el perfil del usuario
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 458);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));    //Setea el icono de la ventana
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(28, 11, 729, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		JButton btnVolver = new JButton("Volver");            //Cierra la ventana
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(668, 351, 89, 23);
		contentPane.add(btnVolver);
		
		if (perfil.equals("Admin") || perfil.equals("Manager")) {          //Muestra los siguientes botones solo si el usuario es "Admin" o "Manager"
		

			JButton btnHistoriales = new JButton("Agregar");           //Abre la ventana Historial_Medico
			btnHistoriales.setBackground(new Color(86, 211, 243));
			btnHistoriales.setBorder(null);
			btnHistoriales.setForeground(new Color(255, 255, 255));
			btnHistoriales.setFont(new Font("Roboto", Font.BOLD, 14));
			btnHistoriales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Historial_Medico hm = new Historial_Medico(perfil);    //Envía el perfil como parámetro
					hm.setVisible(true);
					dispose();
				}
			});
			btnHistoriales.setBounds(28, 337, 91, 23);
			contentPane.add(btnHistoriales);
			
			JButton btnEliminar = new JButton("Eliminar");         //Este botón elimina la fila seleccionada
			btnEliminar.setBackground(new Color(86, 211, 243));
			btnEliminar.setBorder(null);
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int fila = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(fila,0).toString());
					String nombre = table.getValueAt(fila,1).toString();
					
					Consulta_Historial.eliminar(id, nombre);
					
					mostrarTabla();
				}
			});
			btnEliminar.setBounds(226, 337, 91, 23);
			contentPane.add(btnEliminar);
			
			JButton btnModificar = new JButton("Modificar");        //Abre la ventana Modificar_Historial
			btnModificar.setBackground(new Color(86, 211, 243));
			btnModificar.setBorder(null);
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					
					Modificar_Historial mh = new Modificar_Historial(table.getValueAt(fila,0).toString(),perfil);       //Envía como parámetro el id de la fila seleccionada
					mh.setVisible(true);
					dispose();
				}
			});
			btnModificar.setBounds(127, 337, 91, 23);
			contentPane.add(btnModificar);
		
		}
		

		JButton btnBuscar = new JButton("Buscar Mascota");          //Este botón permite Buscar una mascota
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota bm = new Buscar_Mascota(perfil);     //Envía el perfil como parámetro
				bm.setVisible(true);		//Abre la ventana Buscar_Mascota
				dispose();
			}
		});
		btnBuscar.setBackground(new Color(86, 211, 243));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnBuscar.setBounds(425, 339, 141, 23);
		contentPane.add(btnBuscar);
		
		JButton btnGen = new JButton("Generar historial");      //Este botón genera un PDF con el historial
		btnGen.setBackground(new Color(86, 211, 243));
		btnGen.setBorder(null);
		btnGen.setForeground(new Color(255, 255, 255));
		btnGen.setFont(new Font("Roboto", Font.BOLD, 14));
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					generar();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGen.setBounds(425, 373, 141, 23);
		contentPane.add(btnGen);
		
		mostrarTabla();
	}

	public Tabla_Historial(final String perfil, String id) {        //Crea la ventana recibiendo como parámetro el perfil del usuario y el id de la mascota
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 458);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/vet.png")));     //Setea el icono de la ventana

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(28, 11, 729, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");           //Cierra la ventana
		btnVolver.setBackground(new Color(86, 211, 243));
		btnVolver.setBorder(null);
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Roboto", Font.BOLD, 14));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(668, 351, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnHistoriales = new JButton("Agregar");              //Abre la ventana Historial_Medico
		btnHistoriales.setBackground(new Color(86, 211, 243));
		btnHistoriales.setBorder(null);
		btnHistoriales.setForeground(new Color(255, 255, 255));
		btnHistoriales.setFont(new Font("Roboto", Font.BOLD, 14));
		btnHistoriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historial_Medico hm = new Historial_Medico(perfil);     //Envía el perfil como parámetro
				hm.setVisible(true);
				dispose();
			}
		});
		btnHistoriales.setBounds(28, 337, 91, 23);
		contentPane.add(btnHistoriales);
		
		JButton btnEliminar = new JButton("Eliminar");         //Este botón eliminal la fila seleccionada
		btnEliminar.setBackground(new Color(86, 211, 243));
		btnEliminar.setBorder(null);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(fila,0).toString());
				String nombre = table.getValueAt(fila,1).toString();
				
				Consulta_Historial.eliminar(id, nombre);
				
				mostrarTabla();
			}
		});
		btnEliminar.setBounds(226, 337, 91, 23);
		contentPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");        //Abre la ventana Modificar_Historial
		btnModificar.setBackground(new Color(86, 211, 243));
		btnModificar.setBorder(null);
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				
				Modificar_Historial mh = new Modificar_Historial(table.getValueAt(fila,0).toString(),perfil);   //Envía como parámetro el id de la fila seleccionada
				mh.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(127, 337, 91, 23);
		contentPane.add(btnModificar);
		
		JButton btnBuscar = new JButton("Buscar Mascota");        //Este boton permite buscar una mascota
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar_Mascota bm = new Buscar_Mascota(perfil);    //Envía el perfil como parámetro
				bm.setVisible(true);		//Abre la ventana Buscar_Mascota
				dispose();
			}
		});
		btnBuscar.setBackground(new Color(86, 211, 243));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Roboto", Font.BOLD, 14));
		btnBuscar.setBounds(425, 339, 141, 23);
		contentPane.add(btnBuscar);
		
		mostrarTablaId(id);
		
		JButton btnGen = new JButton("Generar historial");      //Este botón genera un PDF con el historial
		btnGen.setBackground(new Color(86, 211, 243));
		btnGen.setBorder(null);
		btnGen.setForeground(new Color(255, 255, 255));
		btnGen.setFont(new Font("Roboto", Font.BOLD, 14));
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					generar();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGen.setBounds(425, 373, 141, 23);
		contentPane.add(btnGen);
	}

	public Tabla_Historial() {
		
	}
}

package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ControlFiles {
	
	protected static String ruta = "c:/rsc/log.txt";  //Genera ruta al archivo log.txt
	
	
	public static final void createFile(){         // Procedimiento que genera un archivo de texto
		
		File file = new File(ruta);                //Arma una variable tipo File con la ruta
		
		 if (!file.exists()) {                      //Revisa si el archivo no existe
             try {
				file.createNewFile();               // Crea el archivo en la ruta especificada
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();                //Imprime error en caso de fallar
			}
         }
		
	}
	
	public static final void  addContent(String aContent){        // Procedimiento que añade contenido al archivo de texto, recibe el contenido como parámetro
		
		File file = new File(ruta);                            //Arma una variable tipo File con la ruta
		
		
		try {
			if (!file.exists()) {                            //Revisa si el archivo no existe
	             file.createNewFile();                       //Crea el archivo en la ruta especificada
	         }
			
			FileWriter fw;                                 //Crea una variable FileWriter
			fw = new FileWriter(file,true);                //La llena con el archivo
			
			
			BufferedWriter bw = new BufferedWriter(fw);             //Crea una variable BufferedWriter con la variable anterior
	        bw.write(LocalDateTime.now().toString()+"\n");          //Escribe la fecha actual en el archivo y salta al párrafo siguiente
			bw.write(aContent+"\n"+"\n");                           //Agrega el contenido recibido por parámetro al archivo y salta dos párrafos
	        
	        bw.close();                                           //Deja de editar el archivo
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();                             //Imprime el error en caso de fallar
		}
        
		
		
	}
	
}

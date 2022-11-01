package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ControlFiles {
	
	protected static String ruta = "c:/rsc/log.txt";
	
	
	public static final void createFile(){
		
		File file = new File(ruta);
		
		 if (!file.exists()) {
             try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
		
	}
	
	public static final void  addContent(String aContent){
		
		File file = new File(ruta);
		
		
		try {
			if (!file.exists()) {
	             file.createNewFile();
	         }
			
			FileWriter fw;
			fw = new FileWriter(file,true);
			
			
			BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(LocalDateTime.now().toString()+"\n");
			bw.write(aContent+"\n"+"\n");
	        
	        bw.close();
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
	}
	
}

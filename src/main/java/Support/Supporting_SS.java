package Support;

//Esta clase contiene funciones utilizadas para soporte. Ej: validación de datos, uso general, etc
public class Supporting_SS {   

	public static boolean validateNum(String num) {     //Recibe una cadena y devuelve true si todos los caracteres son números
		boolean flagError = false;
		
		for(int i=0; i < num.length(); i++ ) {
			
			if (Character.isLetter(num.charAt(i))){
				
				flagError = true;
				break;
			}
			
			
		}
		return flagError;
	}
	
}

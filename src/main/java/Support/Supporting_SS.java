package Support;

//Esta clase contiene funciones utilizadas para soporte. Ej: validación de datos, uso general, etc
public class Supporting_SS {   

	public boolean validateNum(String num) {     //Recibe una cadena y devuelve true si todos los caracteres son números
		boolean flagError = false;
		
		for(int i=0; i < num.length(); i++ ) {            //Recorre la cadena caracter por caracter
			
			if (Character.isLetter(num.charAt(i))){         //Revisa si el caracter es una letra
				
				flagError = true;                          //Si es una letra devuelve la variable como true
				break;                                  //Detiene el for
			}
			
			
		}
		return flagError;                     //Devuelve la variable flagError
	}
	
}

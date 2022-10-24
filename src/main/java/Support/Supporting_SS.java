package Support;

public class Supporting_SS {

	public static boolean validateNum(String num) {
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

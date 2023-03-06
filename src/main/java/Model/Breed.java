package Model;

public class Breed {

	// Clase Breed con getter, setter y constructor
	
	protected int id_Breed;
	protected String type;
	
	public Breed() {
		super();
	}
	
	public Breed(int id_Breed, String type) {
		super();
		this.id_Breed = id_Breed;
		this.type = type;
	}
	
	public Breed(String type) {
		super();
		this.type = type;
	}
	
	public int getId_Breed() {
		return id_Breed;
	}
	public void setId_Breed(int id_Breed) {
		this.id_Breed = id_Breed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}

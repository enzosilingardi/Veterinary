package Model;

public class Animal {

	// Clase Animal con getter, setter y constructor
	
	protected int id_Animal;
	protected String type;
	
	public Animal() {
		super();
	}
	
	public Animal(int id_Animal, String type) {
		super();
		this.id_Animal = id_Animal;
		this.type = type;
	}
	
	public Animal(String type) {
		super();
		this.type = type;
	}
	
	public int getId_Animal() {
		return id_Animal;
	}
	public void setId_Animal(int id_Animal) {
		this.id_Animal = id_Animal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}

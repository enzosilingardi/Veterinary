package Model;

public class Country {

	//Clase Country que no se utiliza en la versión actual
	
	protected int id_Country;
	protected String name;
	
	public int getId_Country() {
		return id_Country;
	}
	public void setId_Country(int id_Country) {
		this.id_Country = id_Country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Country() {
		super();
	}
	
	public Country(int id_Country, String name) {
		super();
		this.id_Country = id_Country;
		this.name = name;
	}
	
	public Country(String name) {
		super();
		this.name = name;
	}
	
	
	
}


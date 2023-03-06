package Model;

public class Province {

	//Clase Province que no es utilizada en la versión actual
	
	protected int id_Province;
	protected Country id_Country;
	protected String name;
	
	
	
	public int getId_Province() {
		return id_Province;
	}
	public void setId_Province(int id_Province) {
		this.id_Province = id_Province;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getId_Country() {
		return id_Country;
	}
	public void setId_Country(Country id_Country) {
		this.id_Country = id_Country;
	}
	
	public Province() {
		super();
	}
	
	
	
	public Province(int id_Province, Country id_Country, String name) {
		super();
		this.id_Province = id_Province;
		this.id_Country = id_Country;
		this.name = name;
	}
	public Province(Country id_Country, String name) {
		super();
		this.id_Country = id_Country;
		this.name = name;
	}
	
	
	
	
}
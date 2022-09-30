package Model;

public class Province {

	protected int id_province;
	protected String name;
	protected Country id_Country;
	
	public int getId_province() {
		return id_province;
	}
	public void setId_province(int id_province) {
		this.id_province = id_province;
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
	
	public Province(int id_province, String name, Country id_Country) {
		super();
		this.id_province = id_province;
		this.name = name;
		this.id_Country = id_Country;
	}
	
	public Province(String name, Country id_Country) {
		super();
		this.name = name;
		this.id_Country = id_Country;
	}
	
	
	
}
package Model;

public class Province {

	protected int id_province;
	protected Country id_Country;
	protected String name;
	
	
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
	
	public Province(int id_province, Country id_Country, String name) {
		super();
		this.id_province = id_province;
		this.id_Country = id_Country;
		this.name = name;
	}
	
	public Province(Country id_Country, String name) {
		super();
		this.id_Country = id_Country;
		this.name = name;
	}
	
	
	
	
}
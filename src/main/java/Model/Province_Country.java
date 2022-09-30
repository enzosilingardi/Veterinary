package Model;

public class Province_Country {

	protected int id_PC;
	protected Province id_Province;
	protected Country id_Country;
	
	public int getId_PC() {
		return id_PC;
	}
	public void setId_PC(int id_PC) {
		this.id_PC = id_PC;
	}
	public Province getId_Province() {
		return id_Province;
	}
	public void setId_Province(Province id_Province) {
		this.id_Province = id_Province;
	}
	public Country getId_Country() {
		return id_Country;
	}
	public void setId_Country(Country id_Country) {
		this.id_Country = id_Country;
	}
	
	public Province_Country() {
		super();
	}
	
	public Province_Country(int id_PC, Province id_Province, Country id_Country) {
		super();
		this.id_PC = id_PC;
		this.id_Province = id_Province;
		this.id_Country = id_Country;
	}
	
	public Province_Country(Province id_Province, Country id_Country) {
		super();
		this.id_Province = id_Province;
		this.id_Country = id_Country;
	}
	
	
	
}
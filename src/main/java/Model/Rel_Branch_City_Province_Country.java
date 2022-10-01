package Model;

public class Rel_Branch_City_Province_Country {

	protected int id_BCPC;
	protected Branch id_Branch;
	protected City id_City;
	protected Province id_Province;
	protected Country id_Country;
	
	public int getId_BCPC() {
		return id_BCPC;
	}
	public void setId_BCPC(int id_BCPC) {
		this.id_BCPC = id_BCPC;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	public City getId_City() {
		return id_City;
	}
	public void setId_City(City id_City) {
		this.id_City = id_City;
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
	
	public Rel_Branch_City_Province_Country() {
		super();
	}
	
	public Rel_Branch_City_Province_Country(int id_BCPC, Branch id_Branch, City id_City, Province id_Province,
			Country id_Country) {
		super();
		this.id_BCPC = id_BCPC;
		this.id_Branch = id_Branch;
		this.id_City = id_City;
		this.id_Province = id_Province;
		this.id_Country = id_Country;
	}
	
	public Rel_Branch_City_Province_Country(Branch id_Branch, City id_City, Province id_Province, Country id_Country) {
		super();
		this.id_Branch = id_Branch;
		this.id_City = id_City;
		this.id_Province = id_Province;
		this.id_Country = id_Country;
	}
	
	
	
	
}

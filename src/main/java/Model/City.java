package Model;

public class City {

	protected int id_City;
	protected Province id_Province;
	protected String name;
	
	
	public int getId_City() {
		return id_City;
	}
	public void setId_City(int id_City) {
		this.id_City = id_City;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Province getId_Province() {
		return id_Province;
	}
	public void setId_Province(Province id_Province) {
		this.id_Province = id_Province;
	}
	
	public City() {
		super();
	}
	
	public City(int id_City, Province id_Province, String name) {
		super();
		this.id_City = id_City;
		this.id_Province = id_Province;
		this.name = name;
	}
	
	public City(Province id_Province, String name) {
		super();
		this.id_Province = id_Province;
		this.name = name;
	}
	
	
	
	
}
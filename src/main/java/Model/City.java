package Model;

public class City {

	protected int id_City;
	protected String name;
	protected Province id_Province;
	
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
	
	public City(int id_City, String name, Province id_Province) {
		super();
		this.id_City = id_City;
		this.name = name;
		this.id_Province = id_Province;
	}
	
	public City(String name, Province id_Province) {
		super();
		this.name = name;
		this.id_Province = id_Province;
	}
	
	
	
}
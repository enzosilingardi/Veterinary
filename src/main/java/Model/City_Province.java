package Model;

public class City_Province {

	protected int id_CP;
	protected City id_City;
	protected Province id_Province;
	
	public int getId_CP() {
		return id_CP;
	}
	public void setId_CP(int id_CP) {
		this.id_CP = id_CP;
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
	
	public City_Province() {
		super();
	}
	
	public City_Province(int id_CP, City id_City, Province id_Province) {
		super();
		this.id_CP = id_CP;
		this.id_City = id_City;
		this.id_Province = id_Province;
	}
	
	public City_Province(City id_City, Province id_Province) {
		super();
		this.id_City = id_City;
		this.id_Province = id_Province;
	}
	
	
	
}

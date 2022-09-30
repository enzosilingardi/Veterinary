package Model;

public class Branch {

	protected int id_Branch;
	protected String address;
	protected City id_City;
	
	public int getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(int id_Branch) {
		this.id_Branch = id_Branch;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public City getId_City() {
		return id_City;
	}
	public void setId_City(City id_City) {
		this.id_City = id_City;
	}
	
	public Branch() {
		super();
	}
	
	public Branch(int id_Branch, String address, City id_City) {
		super();
		this.id_Branch = id_Branch;
		this.address = address;
		this.id_City = id_City;
	}
	
	public Branch(String address, City id_City) {
		super();
		this.address = address;
		this.id_City = id_City;
	}
	
	
	
	
	
}
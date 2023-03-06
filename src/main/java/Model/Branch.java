package Model;

public class Branch {

	//Clase branch con getter, setter y constructor
	
	protected int id_Branch;
	protected String address;
	
	
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
	
	public Branch() {
		super();
	}
	
	public Branch(int id_Branch, String address) {
		super();
		this.id_Branch = id_Branch;
		this.address = address;
	}
	
	public Branch(String address) {
		super();
		this.address = address;
	}
	
	
	
}
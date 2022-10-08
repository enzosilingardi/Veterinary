package Model;

public class Branch {

	protected int id_Branch;
	protected Address id_Address;
	
	
	public int getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(int id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	
	public Address getId_Address() {
		return id_Address;
	}
	public void setId_Address(Address id_Address) {
		this.id_Address = id_Address;
	}
	
	public Branch() {
		super();
	}
	
	public Branch(int id_Branch, Address id_Address) {
		super();
		this.id_Branch = id_Branch;
		this.id_Address = id_Address;
	}
	
	public Branch(Address id_Address) {
		super();
		this.id_Address = id_Address;
	}
	
	
	
}
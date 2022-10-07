package Model;

public class Branch {

	protected int id_Branch;
	protected Address id_address;
	
	public int getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(int id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	
	public Address getId_address() {
		return id_address;
	}
	public void setId_address(Address id_address) {
		this.id_address = id_address;
	}
	
	public Branch() {
		super();
	}
	
	public Branch(int id_Branch, Address id_address) {
		super();
		this.id_Branch = id_Branch;
		this.id_address = id_address;
	}
	
	public Branch(Address id_address) {
		super();
		this.id_address = id_address;
	}
	
	
	
}
package Model;

public class Address {

	protected int id_Address;
	protected City id_City;
	protected String address_Name;
	protected int address_Number;
	protected int floor_Number;
	protected String dept_Number;
	
	public int getId_Address() {
		return id_Address;
	}
	public void setId_Address(int id_Address) {
		this.id_Address = id_Address;
	}
	public City getId_City() {
		return id_City;
	}
	public void setId_City(City id_City) {
		this.id_City = id_City;
	}
	public String getAddress_Name() {
		return address_Name;
	}
	public void setAddress_Name(String address_Name) {
		this.address_Name = address_Name;
	}
	public int getAddress_Number() {
		return address_Number;
	}
	public void setAddress_Number(int address_Number) {
		this.address_Number = address_Number;
	}
	public int getFloor_Number() {
		return floor_Number;
	}
	public void setFloor_Number(int floor_Number) {
		this.floor_Number = floor_Number;
	}
	
	
	public String getDept_Number() {
		return dept_Number;
	}
	public void setDept_Number(String dept_Number) {
		this.dept_Number = dept_Number;
	}
	
	public Address() {
		super();
	}
	
	public Address(int id_Address, City id_City, String address_Name, int address_Number, int floor_Number,
			String dept_Number) {
		super();
		this.id_Address = id_Address;
		this.id_City = id_City;
		this.address_Name = address_Name;
		this.address_Number = address_Number;
		this.floor_Number = floor_Number;
		this.dept_Number = dept_Number;
	}
	
	public Address(City id_City, String address_Name, int address_Number, int floor_Number, String dept_Number) {
		super();
		this.id_City = id_City;
		this.address_Name = address_Name;
		this.address_Number = address_Number;
		this.floor_Number = floor_Number;
		this.dept_Number = dept_Number;
	}
	
	
	
}

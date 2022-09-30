package Model;

public class Client {

	protected int Id_Client;
	protected String dni;
	protected String name;
	protected String address;
	protected City id_City;
	protected String phone_Number;
	protected String birthdate;
	protected String gender;
	
	public int getId_Client() {
		return Id_Client;
	}
	public void setId_Client(int id_Client) {
		Id_Client = id_Client;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhone_Number() {
		return phone_Number;
	}
	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Client() {
		super();
	}
	
	public Client(int id_Client, String dni, String name, String address, City id_City, String phone_Number,
			String birthdate, String gender) {
		super();
		Id_Client = id_Client;
		this.dni = dni;
		this.name = name;
		this.address = address;
		this.id_City = id_City;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
	}
	
	public Client(String dni, String name, String address, City id_City, String phone_Number, String birthdate,
			String gender) {
		super();
		this.dni = dni;
		this.name = name;
		this.address = address;
		this.id_City = id_City;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
	}
	
	
	
}

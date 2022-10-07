package Model;

public class Client {

	protected int Id_Client;
	protected String dni;
	protected String name;
	protected Address id_Address;
	protected String phone_Number;
	protected String birthdate;
	protected String gender;
	protected String email;
	
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
	
	
	public Address getId_Address() {
		return id_Address;
	}
	public void setId_Address(Address id_Address) {
		this.id_Address = id_Address;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Client() {
		super();
	}
	
	public Client(int id_Client, String dni, String name, Address id_Address, String phone_Number, String birthdate,
			String gender, String email) {
		super();
		Id_Client = id_Client;
		this.dni = dni;
		this.name = name;
		this.id_Address = id_Address;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
		this.email = email;
	}
	
	public Client(String dni, String name, Address id_Address, String phone_Number, String birthdate, String gender,
			String email) {
		super();
		this.dni = dni;
		this.name = name;
		this.id_Address = id_Address;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
		this.email = email;
	}
	
	
	
}

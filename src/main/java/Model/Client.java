package Model;

public class Client {

	protected int id_Client;
	protected String address;
	protected String dni;
	protected String name;
	protected String surname;
	protected String phone_Number;
	protected String birthdate;
	protected String gender;
	protected String email;
	
	
	public int getId_Client() {
		return id_Client;
	}
	public void setId_Client(int id_Client) {
		this.id_Client = id_Client;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	
	public Client(int id_Client, String address, String dni, String name, String surname, String phone_Number,
			String birthdate, String gender, String email) {
		super();
		this.id_Client = id_Client;
		this.address = address;
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
		this.email = email;
	}
	
	public Client(String address, String dni, String name, String surname, String phone_Number, String birthdate,
			String gender, String email) {
		super();
		this.address = address;
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
		this.email = email;
	}
	
	
	
	
	
}

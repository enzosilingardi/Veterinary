package Model;

public class Users {

	protected int id_User;
	protected String username;
    protected String dni;
    protected String name;
    protected Address id_Address;
    protected String phone_Number;
    protected String birthdate;
    protected String gender;
    protected Profile id_Profile;
    protected String email;
	
    public int getId_User() {
		return id_User;
	}
	public void setId_User(int id_User) {
		this.id_User = id_User;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	
	public Profile getId_Profile() {
		return id_Profile;
	}
	public void setId_Profile(Profile id_Profile) {
		this.id_Profile = id_Profile;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Users() {
		super();
	}
	
	public Users(int id_User, String username, String dni, String name, Address id_Address, String phone_Number,
			String birthdate, String gender, Profile id_Profile, String email) {
		super();
		this.id_User = id_User;
		this.username = username;
		this.dni = dni;
		this.name = name;
		this.id_Address = id_Address;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
		this.id_Profile = id_Profile;
		this.email = email;
	}
	
	public Users(String username, String dni, String name, Address id_Address, String phone_Number, String birthdate,
			String gender, Profile id_Profile, String email) {
		super();
		this.username = username;
		this.dni = dni;
		this.name = name;
		this.id_Address = id_Address;
		this.phone_Number = phone_Number;
		this.birthdate = birthdate;
		this.gender = gender;
		this.id_Profile = id_Profile;
		this.email = email;
	}
	
	
    
}

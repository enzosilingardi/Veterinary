package Model;

public class Users {

	protected int id_User;
	protected String profile;
	protected String name;
	protected String surname;
	protected String username;
	protected String password;
    protected String email;
	
    public int getId_User() {
		return id_User;
	}
	public void setId_User(int id_User) {
		this.id_User = id_User;
	}
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public Users(int id_User, String profile, String name, String surname, String username, String password,
			String email) {
		super();
		this.id_User = id_User;
		this.profile = profile;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Users(String profile, String name, String surname, String username, String password, String email) {
		super();
		this.profile = profile;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
	
    
}

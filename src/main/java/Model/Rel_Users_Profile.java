package Model;

public class Rel_Users_Profile {

	protected int id_UP;
	protected Users id_User;
	protected Profile id_Profile;
	
	public int getId_UP() {
		return id_UP;
	}
	public void setId_UP(int id_UP) {
		this.id_UP = id_UP;
	}
	public Users getId_User() {
		return id_User;
	}
	public void setId_User(Users id_User) {
		this.id_User = id_User;
	}
	public Profile getId_Profile() {
		return id_Profile;
	}
	public void setId_Profile(Profile id_Profile) {
		this.id_Profile = id_Profile;
	}
	
	public Rel_Users_Profile() {
		super();
	}
	
	public Rel_Users_Profile(int id_UP, Users id_User, Profile id_Profile) {
		super();
		this.id_UP = id_UP;
		this.id_User = id_User;
		this.id_Profile = id_Profile;
	}
	
	public Rel_Users_Profile(Users id_User, Profile id_Profile) {
		super();
		this.id_User = id_User;
		this.id_Profile = id_Profile;
	}
	
	
	
}

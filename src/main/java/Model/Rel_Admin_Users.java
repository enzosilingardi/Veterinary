package Model;

public class Rel_Admin_Users {

	protected int id_AU;
	protected Admin id_Admin;
	protected Users id_User;
	
	public int getId_AU() {
		return id_AU;
	}
	public void setId_AU(int id_AU) {
		this.id_AU = id_AU;
	}
	public Admin getId_Admin() {
		return id_Admin;
	}
	public void setId_Admin(Admin id_Admin) {
		this.id_Admin = id_Admin;
	}
	public Users getId_User() {
		return id_User;
	}
	public void setId_User(Users id_User) {
		this.id_User = id_User;
	}
	
	public Rel_Admin_Users() {
		super();
	}
	
	public Rel_Admin_Users(int id_AU, Admin id_Admin, Users id_User) {
		super();
		this.id_AU = id_AU;
		this.id_Admin = id_Admin;
		this.id_User = id_User;
	}
	
	public Rel_Admin_Users(Admin id_Admin, Users id_User) {
		super();
		this.id_Admin = id_Admin;
		this.id_User = id_User;
	}
	
	
	
}

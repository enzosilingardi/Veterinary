package Model;

public class Admin {

	protected int id_Admin;
    protected String admin_Name;
    protected String password;
    protected String username;
	
    public int getId_Admin() {
		return id_Admin;
	}
	public void setId_Admin(int id_Admin) {
		this.id_Admin = id_Admin;
	}
	public String getAdmin_Name() {
		return admin_Name;
	}
	public void setAdmin_Name(String admin_Name) {
		this.admin_Name = admin_Name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Admin(int id_Admin, String admin_Name, String password, String username) {
		super();
		this.id_Admin = id_Admin;
		this.admin_Name = admin_Name;
		this.password = password;
		this.username = username;
	}
	
	public Admin(String admin_Name, String password, String username) {
		super();
		this.admin_Name = admin_Name;
		this.password = password;
		this.username = username;
	}
	
    
    

	
}
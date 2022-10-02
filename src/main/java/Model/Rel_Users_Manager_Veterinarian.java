package Model;

public class Rel_Users_Manager_Veterinarian {

	protected int id_UMV;
	protected Users id_User;
	protected Manager id_Manager;
	protected Veterinarian id_Veterinarian;
	
	public int getId_UMV() {
		return id_UMV;
	}
	public void setId_UMV(int id_UMV) {
		this.id_UMV = id_UMV;
	}
	public Users getId_User() {
		return id_User;
	}
	public void setId_User(Users id_User) {
		this.id_User = id_User;
	}
	public Manager getId_Manager() {
		return id_Manager;
	}
	public void setId_Manager(Manager id_Manager) {
		this.id_Manager = id_Manager;
	}
	public Veterinarian getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(Veterinarian id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Rel_Users_Manager_Veterinarian() {
		super();
	}
	
	public Rel_Users_Manager_Veterinarian(int id_UMV, Users id_User, Manager id_Manager, Veterinarian id_Veterinarian) {
		super();
		this.id_UMV = id_UMV;
		this.id_User = id_User;
		this.id_Manager = id_Manager;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Rel_Users_Manager_Veterinarian(Users id_User, Manager id_Manager, Veterinarian id_Veterinarian) {
		super();
		this.id_User = id_User;
		this.id_Manager = id_Manager;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	
	
}

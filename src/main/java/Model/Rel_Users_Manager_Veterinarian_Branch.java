package Model;

public class Rel_Users_Manager_Veterinarian_Branch {

	protected int id_UMVB;
	protected Users id_User;
	protected Manager id_Manager;
	protected Veterinarian id_Veterinarian;
	protected Branch id_Branch;
	
	
	public int getId_UMVB() {
		return id_UMVB;
	}
	public void setId_UMVB(int id_UMVB) {
		this.id_UMVB = id_UMVB;
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
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Rel_Users_Manager_Veterinarian_Branch() {
		super();
	}
	
	public Rel_Users_Manager_Veterinarian_Branch(int id_UMVB, Users id_User, Manager id_Manager,
			Veterinarian id_Veterinarian, Branch id_Branch) {
		super();
		this.id_UMVB = id_UMVB;
		this.id_User = id_User;
		this.id_Manager = id_Manager;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Branch = id_Branch;
	}
	
	public Rel_Users_Manager_Veterinarian_Branch(Users id_User, Manager id_Manager, Veterinarian id_Veterinarian,
			Branch id_Branch) {
		super();
		this.id_User = id_User;
		this.id_Manager = id_Manager;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Branch = id_Branch;
	}
	
	
	
	
	
}

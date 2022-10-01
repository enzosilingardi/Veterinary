package Model;

public class Rel_Manager_Branch {

	protected int id_MB;
	protected Manager id_Manager;
	protected Branch id_Branch;
	
	public int getId_MB() {
		return id_MB;
	}
	public void setId_MB(int id_MB) {
		this.id_MB = id_MB;
	}
	public Manager getId_Manager() {
		return id_Manager;
	}
	public void setId_Manager(Manager id_Manager) {
		this.id_Manager = id_Manager;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Rel_Manager_Branch() {
		super();
	}
	
	public Rel_Manager_Branch(int id_MB, Manager id_Manager, Branch id_Branch) {
		super();
		this.id_MB = id_MB;
		this.id_Manager = id_Manager;
		this.id_Branch = id_Branch;
	}
	
	public Rel_Manager_Branch(Manager id_Manager, Branch id_Branch) {
		super();
		this.id_Manager = id_Manager;
		this.id_Branch = id_Branch;
	}
	
	
	
}

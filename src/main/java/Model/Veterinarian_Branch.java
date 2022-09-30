package Model;

public class Veterinarian_Branch {

	protected int id_VB;
	protected Veterinarian id_Veterinarian;
	protected Branch id_Branch;
	
	public int getId_VB() {
		return id_VB;
	}
	public void setId_VB(int id_VB) {
		this.id_VB = id_VB;
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
	
	public Veterinarian_Branch() {
		super();
	}
	
	public Veterinarian_Branch(int id_VB, Veterinarian id_Veterinarian, Branch id_Branch) {
		super();
		this.id_VB = id_VB;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Branch = id_Branch;
	}
	
	public Veterinarian_Branch(Veterinarian id_Veterinarian, Branch id_Branch) {
		super();
		this.id_Veterinarian = id_Veterinarian;
		this.id_Branch = id_Branch;
	}
	
	
	
}
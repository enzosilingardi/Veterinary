package Model;

public class Rel_Medical_Procedure_Pet_Medical_History_Veterinarian {

	protected int id_MPPMHV;
	protected Pet id_Pet;
	protected Medical_History id_Medical_History;
	protected Medical_Procedure id_Procedure;
	protected Veterinarian id_Veterinarian;
	
	public int getId_MPPMHV() {
		return id_MPPMHV;
	}
	public void setId_MPPMHV(int id_MPPMHV) {
		this.id_MPPMHV = id_MPPMHV;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	public Medical_History getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(Medical_History id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	public Medical_Procedure getId_Procedure() {
		return id_Procedure;
	}
	public void setId_Procedure(Medical_Procedure id_Procedure) {
		this.id_Procedure = id_Procedure;
	}
	public Veterinarian getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(Veterinarian id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Rel_Medical_Procedure_Pet_Medical_History_Veterinarian() {
		super();
	}
	
	public Rel_Medical_Procedure_Pet_Medical_History_Veterinarian(int id_MPPMHV, Pet id_Pet,
			Medical_History id_Medical_History, Medical_Procedure id_Procedure, Veterinarian id_Veterinarian) {
		super();
		this.id_MPPMHV = id_MPPMHV;
		this.id_Pet = id_Pet;
		this.id_Medical_History = id_Medical_History;
		this.id_Procedure = id_Procedure;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Rel_Medical_Procedure_Pet_Medical_History_Veterinarian(Pet id_Pet, Medical_History id_Medical_History,
			Medical_Procedure id_Procedure, Veterinarian id_Veterinarian) {
		super();
		this.id_Pet = id_Pet;
		this.id_Medical_History = id_Medical_History;
		this.id_Procedure = id_Procedure;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	
	
}

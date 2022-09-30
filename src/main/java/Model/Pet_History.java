package Model;

public class Pet_History {

	protected int id_PH;
	protected Pet id_Pet;
	protected Medical_History id_Medical_History;
	
	public int getId_PH() {
		return id_PH;
	}
	public void setId_PH(int id_PH) {
		this.id_PH = id_PH;
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
	
	public Pet_History() {
		super();
	}
	
	public Pet_History(int id_PH, Pet id_Pet, Medical_History id_Medical_History) {
		super();
		this.id_PH = id_PH;
		this.id_Pet = id_Pet;
		this.id_Medical_History = id_Medical_History;
	}
	
	public Pet_History(Pet id_Pet, Medical_History id_Medical_History) {
		super();
		this.id_Pet = id_Pet;
		this.id_Medical_History = id_Medical_History;
	}
	
	
	
}
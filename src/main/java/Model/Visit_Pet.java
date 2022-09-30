package Model;

public class Visit_Pet {

	protected int id_VP;
	protected Medical_Visit id_Visit;
	protected Pet id_Pet;
	
	public int getId_VP() {
		return id_VP;
	}
	public void setId_VP(int id_VP) {
		this.id_VP = id_VP;
	}
	public Medical_Visit getId_Visit() {
		return id_Visit;
	}
	public void setId_Visit(Medical_Visit id_Visit) {
		this.id_Visit = id_Visit;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	
	public Visit_Pet() {
		super();
	}
	
	public Visit_Pet(int id_VP, Medical_Visit id_Visit, Pet id_Pet) {
		super();
		this.id_VP = id_VP;
		this.id_Visit = id_Visit;
		this.id_Pet = id_Pet;
	}
	
	public Visit_Pet(Medical_Visit id_Visit, Pet id_Pet) {
		super();
		this.id_Visit = id_Visit;
		this.id_Pet = id_Pet;
	}
	
	
	
}
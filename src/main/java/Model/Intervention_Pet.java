package Model;

public class Intervention_Pet {

	protected int id_IP;
	protected Intervention id_Intervention;
	protected Pet id_Pet;
	
	public int getId_IP() {
		return id_IP;
	}
	public void setId_IP(int id_IP) {
		this.id_IP = id_IP;
	}
	public Intervention getId_Intervention() {
		return id_Intervention;
	}
	public void setId_Intervention(Intervention id_Intervention) {
		this.id_Intervention = id_Intervention;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	
	public Intervention_Pet() {
		super();
	}
	
	public Intervention_Pet(int id_IP, Intervention id_Intervention, Pet id_Pet) {
		super();
		this.id_IP = id_IP;
		this.id_Intervention = id_Intervention;
		this.id_Pet = id_Pet;
	}
	
	public Intervention_Pet(Intervention id_Intervention, Pet id_Pet) {
		super();
		this.id_Intervention = id_Intervention;
		this.id_Pet = id_Pet;
	}
	
	
	
}
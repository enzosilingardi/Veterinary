package Model;

public class Veterinarian_Intervention {

	protected int id_VI;
	protected Intervention id_Intervention;
	protected Veterinarian id_Veterinarian;
	
	public int getId_VI() {
		return id_VI;
	}
	public void setId_VI(int id_VI) {
		this.id_VI = id_VI;
	}
	public Intervention getId_Intervention() {
		return id_Intervention;
	}
	public void setId_Intervention(Intervention id_Intervention) {
		this.id_Intervention = id_Intervention;
	}
	public Veterinarian getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(Veterinarian id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Veterinarian_Intervention() {
		super();
	}
	
	public Veterinarian_Intervention(int id_VI, Intervention id_Intervention, Veterinarian id_Veterinarian) {
		super();
		this.id_VI = id_VI;
		this.id_Intervention = id_Intervention;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Veterinarian_Intervention(Intervention id_Intervention, Veterinarian id_Veterinarian) {
		super();
		this.id_Intervention = id_Intervention;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	
	
}
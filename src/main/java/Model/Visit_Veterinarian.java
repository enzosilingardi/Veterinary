package Model;

public class Visit_Veterinarian {

	protected int id_VV;
	protected Medical_Visit id_Visit;
	protected Veterinarian id_Veterinarian;
	
	public int getId_VV() {
		return id_VV;
	}
	public void setId_VV(int id_VV) {
		this.id_VV = id_VV;
	}
	public Medical_Visit getId_Visit() {
		return id_Visit;
	}
	public void setId_Visit(Medical_Visit id_Visit) {
		this.id_Visit = id_Visit;
	}
	public Veterinarian getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(Veterinarian id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Visit_Veterinarian() {
		super();
	}
	
	public Visit_Veterinarian(int id_VV, Medical_Visit id_Visit, Veterinarian id_Veterinarian) {
		super();
		this.id_VV = id_VV;
		this.id_Visit = id_Visit;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Visit_Veterinarian(Medical_Visit id_Visit, Veterinarian id_Veterinarian) {
		super();
		this.id_Visit = id_Visit;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	
	
}

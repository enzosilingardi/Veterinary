package Model;

public class Medical_History {

	protected int id_Medical_History;
	protected Pet id_Pet;
	protected Medical_Visit id_Visit;
	protected Medical_Appointment id_App;
	protected Intervention id_Intervention;
	
	public int getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(int id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	public Medical_Visit getId_Visit() {
		return id_Visit;
	}
	public void setId_Visit(Medical_Visit id_Visit) {
		this.id_Visit = id_Visit;
	}
	public Medical_Appointment getId_App() {
		return id_App;
	}
	public void setId_App(Medical_Appointment id_App) {
		this.id_App = id_App;
	}
	public Intervention getId_Intervention() {
		return id_Intervention;
	}
	public void setId_Intervention(Intervention id_Intervention) {
		this.id_Intervention = id_Intervention;
	}
	
	public Medical_History() {
		super();
	}
	
	public Medical_History(int id_Medical_History, Pet id_Pet, Medical_Visit id_Visit, Medical_Appointment id_App,
			Intervention id_Intervention) {
		super();
		this.id_Medical_History = id_Medical_History;
		this.id_Pet = id_Pet;
		this.id_Visit = id_Visit;
		this.id_App = id_App;
		this.id_Intervention = id_Intervention;
	}
	
	public Medical_History(Pet id_Pet, Medical_Visit id_Visit, Medical_Appointment id_App,
			Intervention id_Intervention) {
		super();
		this.id_Pet = id_Pet;
		this.id_Visit = id_Visit;
		this.id_App = id_App;
		this.id_Intervention = id_Intervention;
	}
	
	
	
}
package Model;

public class Appointment_Pet {

	protected int id_AP;
	protected Medical_Appointment id_App;
	protected Pet id_Pet;
	
	public int getId_AP() {
		return id_AP;
	}
	public void setId_AP(int id_AP) {
		this.id_AP = id_AP;
	}
	public Medical_Appointment getId_App() {
		return id_App;
	}
	public void setId_App(Medical_Appointment id_App) {
		this.id_App = id_App;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	
	public Appointment_Pet() {
		super();
	}
	
	public Appointment_Pet(int id_AP, Medical_Appointment id_App, Pet id_Pet) {
		super();
		this.id_AP = id_AP;
		this.id_App = id_App;
		this.id_Pet = id_Pet;
	}
	
	public Appointment_Pet(Medical_Appointment id_App, Pet id_Pet) {
		super();
		this.id_App = id_App;
		this.id_Pet = id_Pet;
	}
	
	
	
}

package Model;

public class Appointment_Veterinarian {

	protected int id_AV;
	protected Medical_Appointment id_App;
	protected Veterinarian id_Veterinarian;
	
	public int getId_AV() {
		return id_AV;
	}
	public void setId_AV(int id_AV) {
		this.id_AV = id_AV;
	}
	public Medical_Appointment getId_App() {
		return id_App;
	}
	public void setId_App(Medical_Appointment id_App) {
		this.id_App = id_App;
	}
	public Veterinarian getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(Veterinarian id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Appointment_Veterinarian() {
		super();
	}
	
	public Appointment_Veterinarian(int id_AV, Medical_Appointment id_App, Veterinarian id_Veterinarian) {
		super();
		this.id_AV = id_AV;
		this.id_App = id_App;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public Appointment_Veterinarian(Medical_Appointment id_App, Veterinarian id_Veterinarian) {
		super();
		this.id_App = id_App;
		this.id_Veterinarian = id_Veterinarian;
	}
	
	
	
}

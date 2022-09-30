package Model;

public class Appointment_History {

	protected int id_AH;
	protected Medical_Appointment id_App;
	protected Medical_History id_Medical_History;
	
	public int getId_AH() {
		return id_AH;
	}
	public void setId_AH(int id_AH) {
		this.id_AH = id_AH;
	}
	public Medical_Appointment getId_App() {
		return id_App;
	}
	public void setId_App(Medical_Appointment id_App) {
		this.id_App = id_App;
	}
	public Medical_History getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(Medical_History id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	
	public Appointment_History() {
		super();
	}
	
	public Appointment_History(int id_AH, Medical_Appointment id_App, Medical_History id_Medical_History) {
		super();
		this.id_AH = id_AH;
		this.id_App = id_App;
		this.id_Medical_History = id_Medical_History;
	}
	
	public Appointment_History(Medical_Appointment id_App, Medical_History id_Medical_History) {
		super();
		this.id_App = id_App;
		this.id_Medical_History = id_Medical_History;
	}
	
	
	
}
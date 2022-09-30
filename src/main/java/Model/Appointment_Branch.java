package Model;

public class Appointment_Branch {

	protected int id_AB;
	protected Medical_Appointment id_App;
	protected Branch id_Branch;
	
	public int getId_AB() {
		return id_AB;
	}
	public void setId_AB(int id_AB) {
		this.id_AB = id_AB;
	}
	public Medical_Appointment getId_App() {
		return id_App;
	}
	public void setId_App(Medical_Appointment id_App) {
		this.id_App = id_App;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Appointment_Branch() {
		super();
	}
	
	public Appointment_Branch(int id_AB, Medical_Appointment id_App, Branch id_Branch) {
		super();
		this.id_AB = id_AB;
		this.id_App = id_App;
		this.id_Branch = id_Branch;
	}
	
	public Appointment_Branch(Medical_Appointment id_App, Branch id_Branch) {
		super();
		this.id_App = id_App;
		this.id_Branch = id_Branch;
	}
	
	
	
}
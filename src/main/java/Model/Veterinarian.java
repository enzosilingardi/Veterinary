package Model;

public class Veterinarian extends Users {

	protected int id_Veterinarian;
	protected String medical_License;
	
	
	public int getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(int id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	public String getMedical_License() {
		return medical_License;
	}
	public void setMedical_License(String medical_License) {
		this.medical_License = medical_License;
	}
	
	
	public Veterinarian() {
		super();
	}
	public Veterinarian(int id_User, String username, String dni, String name, Address id_Address, String phone_Number,
			String birthdate, String gender, Profile id_Profile, String email, int id_Veterinarian,
			String medical_License) {
		super(id_User, username, dni, name, id_Address, phone_Number, birthdate, gender, id_Profile, email);
		this.id_Veterinarian = id_Veterinarian;
		this.medical_License = medical_License;
	}
	public Veterinarian(String username, String dni, String name, Address id_Address, String phone_Number,
			String birthdate, String gender, Profile id_Profile, String email, int id_Veterinarian,
			String medical_License) {
		super(username, dni, name, id_Address, phone_Number, birthdate, gender, id_Profile, email);
		this.id_Veterinarian = id_Veterinarian;
		this.medical_License = medical_License;
	}
	
	
	
}

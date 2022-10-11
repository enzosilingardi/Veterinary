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
	
	public Veterinarian(int id_User, String username, String password, String dni, String name, Address id_Address,
			String phone_Number, String birthdate, String gender, Profile id_Profile, String email,
			String medical_License) {
		super(id_User, username, password, dni, name, id_Address, phone_Number, birthdate, gender, id_Profile, email);
		this.medical_License = medical_License;
	}
	
	public Veterinarian(String username, String password, String dni, String name, Address id_Address,
			String phone_Number, String birthdate, String gender, Profile id_Profile, String email,
			String medical_License) {
		super(username, password, dni, name, id_Address, phone_Number, birthdate, gender, id_Profile, email);
		this.medical_License = medical_License;
	}
	
	
	
}

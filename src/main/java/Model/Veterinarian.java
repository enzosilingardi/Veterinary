package Model;

public class Veterinarian {

	protected int id_Veterinarian;
	protected String address;
	protected String name;
	protected String surname;
	protected String medical_License;
	
	public int getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(int id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	
	public Veterinarian(int id_Veterinarian, String address, String name, String surname, String medical_License) {
		super();
		this.id_Veterinarian = id_Veterinarian;
		this.address = address;
		this.name = name;
		this.surname = surname;
		this.medical_License = medical_License;
	}
	
	public Veterinarian(String address, String name, String surname, String medical_License) {
		super();
		this.address = address;
		this.name = name;
		this.surname = surname;
		this.medical_License = medical_License;
	}
	
	
	
}

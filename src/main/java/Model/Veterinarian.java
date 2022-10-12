package Model;

public class Veterinarian {

	protected int id_Veterinarian;
	protected Address id_Address;
	protected String name;
	protected String surname;
	protected String medical_License;
	
	public int getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(int id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	public Address getId_Address() {
		return id_Address;
	}
	public void setId_Address(Address id_Address) {
		this.id_Address = id_Address;
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
	
	public Veterinarian(int id_Veterinarian, Address id_Address, String name, String surname, String medical_License) {
		super();
		this.id_Veterinarian = id_Veterinarian;
		this.id_Address = id_Address;
		this.name = name;
		this.surname = surname;
		this.medical_License = medical_License;
	}
	
	public Veterinarian(Address id_Address, String name, String surname, String medical_License) {
		super();
		this.id_Address = id_Address;
		this.name = name;
		this.surname = surname;
		this.medical_License = medical_License;
	}
	
	
	
}

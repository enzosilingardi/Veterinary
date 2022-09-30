package Model;

public class Pet {
	
	protected int id_Pet;
	protected String name;
	protected String animal_Type;
	protected int pet_Age;
	protected String gender;
	protected Client id_Client;
	
	public int getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(int id_Pet) {
		this.id_Pet = id_Pet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAnimal_Type() {
		return animal_Type;
	}
	public void setAnimal_Type(String animal_Type) {
		this.animal_Type = animal_Type;
	}
	public int getPet_Age() {
		return pet_Age;
	}
	public void setPet_Age(int pet_Age) {
		this.pet_Age = pet_Age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Client getId_Client() {
		return id_Client;
	}
	public void setId_Client(Client id_Client) {
		this.id_Client = id_Client;
	}
	
	public Pet() {
		super();
	}
	
	public Pet(int id_Pet, String name, String animal_Type, int pet_Age, String gender, Client id_Client) {
		super();
		this.id_Pet = id_Pet;
		this.name = name;
		this.animal_Type = animal_Type;
		this.pet_Age = pet_Age;
		this.gender = gender;
		this.id_Client = id_Client;
	}
	
	public Pet(String name, String animal_Type, int pet_Age, String gender, Client id_Client) {
		super();
		this.name = name;
		this.animal_Type = animal_Type;
		this.pet_Age = pet_Age;
		this.gender = gender;
		this.id_Client = id_Client;
	}
	
	

}

package Model;

public class Pet {
	
	protected int id_Pet;
	protected Client id_Client;
	protected String name;
	protected String animal_Type;
	protected int age;
	protected String gender;
	protected String breed;
	
	public int getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(int id_Pet) {
		this.id_Pet = id_Pet;
	}
	public Client getId_Client() {
		return id_Client;
	}
	public void setId_Client(Client id_Client) {
		this.id_Client = id_Client;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public Pet() {
		super();
	}
	
	public Pet(int id_Pet, Client id_Client, String name, String animal_Type, int age, String gender, String breed) {
		super();
		this.id_Pet = id_Pet;
		this.id_Client = id_Client;
		this.name = name;
		this.animal_Type = animal_Type;
		this.age = age;
		this.gender = gender;
		this.breed = breed;
	}
	
	public Pet(Client id_Client, String name, String animal_Type, int age, String gender, String breed) {
		super();
		this.id_Client = id_Client;
		this.name = name;
		this.animal_Type = animal_Type;
		this.age = age;
		this.gender = gender;
		this.breed = breed;
	}
	
	
	

}

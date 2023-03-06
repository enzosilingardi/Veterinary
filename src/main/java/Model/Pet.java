package Model;

public class Pet {
	
	//Clase Pet con getter, setter y constructor
	
	protected int id_Pet;
	protected Client id_Client;
	protected String name;
	protected Animal id_Animal;
	protected int age;
	protected String gender;
	protected Breed id_Breed;
	
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
	
	
	public Animal getId_Animal() {
		return id_Animal;
	}
	public void setId_Animal(Animal id_Animal) {
		this.id_Animal = id_Animal;
	}
	public Breed getId_Breed() {
		return id_Breed;
	}
	public void setId_Breed(Breed id_Breed) {
		this.id_Breed = id_Breed;
	}
	
	public Pet() {
		super();
	}
	
	public Pet(int id_Pet, Client id_Client, String name, Animal id_Animal, int age, String gender, Breed id_Breed) {
		super();
		this.id_Pet = id_Pet;
		this.id_Client = id_Client;
		this.name = name;
		this.id_Animal = id_Animal;
		this.age = age;
		this.gender = gender;
		this.id_Breed = id_Breed;
	}
	
	public Pet(Client id_Client, String name, Animal id_Animal, int age, String gender, Breed id_Breed) {
		super();
		this.id_Client = id_Client;
		this.name = name;
		this.id_Animal = id_Animal;
		this.age = age;
		this.gender = gender;
		this.id_Breed = id_Breed;
	}
	
	
}

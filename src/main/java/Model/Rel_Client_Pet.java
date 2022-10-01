package Model;

public class Rel_Client_Pet {

	protected int id_CP;
	protected Client id_Client;
	protected Pet id_Pet;
	
	public int getId_CP() {
		return id_CP;
	}
	public void setId_CP(int id_CP) {
		this.id_CP = id_CP;
	}
	public Client getId_Client() {
		return id_Client;
	}
	public void setId_Client(Client id_Client) {
		this.id_Client = id_Client;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	
	public Rel_Client_Pet() {
		super();
	}
	
	public Rel_Client_Pet(int id_CP, Client id_Client, Pet id_Pet) {
		super();
		this.id_CP = id_CP;
		this.id_Client = id_Client;
		this.id_Pet = id_Pet;
	}
	
	public Rel_Client_Pet(Client id_Client, Pet id_Pet) {
		super();
		this.id_Client = id_Client;
		this.id_Pet = id_Pet;
	}
	
	
	
}
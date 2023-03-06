package Model;

public class Medical_History {

	//Clase Medical_History con getter, setter y constructor
	
	protected int id_Medical_History;
	protected Pet id_Pet;
	protected String description;
	protected String date;
	
	public int getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(int id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Medical_History() {
		super();
	}
	
	public Medical_History(int id_Medical_History, Pet id_Pet, String description, String date) {
		super();
		this.id_Medical_History = id_Medical_History;
		this.id_Pet = id_Pet;
		this.description = description;
		this.date = date;
	}
	
	public Medical_History(Pet id_Pet, String description, String date) {
		super();
		this.id_Pet = id_Pet;
		this.description = description;
		this.date = date;
	}
	
	
	
	
	
	
}
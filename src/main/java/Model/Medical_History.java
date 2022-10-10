package Model;

public class Medical_History {

	protected int id_Medical_History;
	protected String description;
	
	public int getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(int id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Medical_History() {
		super();
	}
	
	public Medical_History(int id_Medical_History, String description) {
		super();
		this.id_Medical_History = id_Medical_History;
		this.description = description;
	}
	
	public Medical_History(String description) {
		super();
		this.description = description;
	}

	
	
}
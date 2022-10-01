package Model;

public class Medical_History {

	protected int id_Medical_History;
	protected String description;
	protected Medical_Procedure id_Procedure;
	
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
	public Medical_Procedure getId_Procedure() {
		return id_Procedure;
	}
	public void setId_Procedure(Medical_Procedure id_Procedure) {
		this.id_Procedure = id_Procedure;
	}
	
	public Medical_History() {
		super();
	}
	
	public Medical_History(int id_Medical_History, String description, Medical_Procedure id_Procedure) {
		super();
		this.id_Medical_History = id_Medical_History;
		this.description = description;
		this.id_Procedure = id_Procedure;
	}
	
	public Medical_History(String description, Medical_Procedure id_Procedure) {
		super();
		this.description = description;
		this.id_Procedure = id_Procedure;
	}
	
	
	
	
}
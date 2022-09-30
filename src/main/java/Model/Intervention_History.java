package Model;

public class Intervention_History {

	protected int id_IH;
	protected Intervention id_Intervention;
	protected Medical_History id_Medical_History;
	
	public int getId_IH() {
		return id_IH;
	}
	public void setId_IH(int id_IH) {
		this.id_IH = id_IH;
	}
	public Intervention getId_Intervention() {
		return id_Intervention;
	}
	public void setId_Intervention(Intervention id_Intervention) {
		this.id_Intervention = id_Intervention;
	}
	public Medical_History getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(Medical_History id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	
	public Intervention_History() {
		super();
	}
	
	public Intervention_History(int id_IH, Intervention id_Intervention, Medical_History id_Medical_History) {
		super();
		this.id_IH = id_IH;
		this.id_Intervention = id_Intervention;
		this.id_Medical_History = id_Medical_History;
	}
	
	public Intervention_History(Intervention id_Intervention, Medical_History id_Medical_History) {
		super();
		this.id_Intervention = id_Intervention;
		this.id_Medical_History = id_Medical_History;
	}
	
	
	
}

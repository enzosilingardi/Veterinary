package Model;

public class Visit_History {

	protected int id_VH;
	protected Medical_Visit id_Visit;
	protected Medical_History id_Medical_History;
	
	public int getId_VH() {
		return id_VH;
	}
	public void setId_VH(int id_VH) {
		this.id_VH = id_VH;
	}
	public Medical_Visit getId_Visit() {
		return id_Visit;
	}
	public void setId_Visit(Medical_Visit id_Visit) {
		this.id_Visit = id_Visit;
	}
	public Medical_History getId_Medical_History() {
		return id_Medical_History;
	}
	public void setId_Medical_History(Medical_History id_Medical_History) {
		this.id_Medical_History = id_Medical_History;
	}
	
	public Visit_History() {
		super();
	}
	
	public Visit_History(int id_VH, Medical_Visit id_Visit, Medical_History id_Medical_History) {
		super();
		this.id_VH = id_VH;
		this.id_Visit = id_Visit;
		this.id_Medical_History = id_Medical_History;
	}
	
	public Visit_History(Medical_Visit id_Visit, Medical_History id_Medical_History) {
		super();
		this.id_Visit = id_Visit;
		this.id_Medical_History = id_Medical_History;
	}
	
	
	
}

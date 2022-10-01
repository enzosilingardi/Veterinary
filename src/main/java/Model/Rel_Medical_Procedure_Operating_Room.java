package Model;

public class Rel_Medical_Procedure_Operating_Room {

	protected int id_MPOR;
	protected Medical_Procedure id_Procedure;
	protected Operating_Room id_Operating_Room;
	
	public int getId_MPOR() {
		return id_MPOR;
	}
	public void setId_MPOR(int id_MPOR) {
		this.id_MPOR = id_MPOR;
	}
	public Medical_Procedure getId_Procedure() {
		return id_Procedure;
	}
	public void setId_Procedure(Medical_Procedure id_Procedure) {
		this.id_Procedure = id_Procedure;
	}
	public Operating_Room getId_Operating_Room() {
		return id_Operating_Room;
	}
	public void setId_Operating_Room(Operating_Room id_Operating_Room) {
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Rel_Medical_Procedure_Operating_Room() {
		super();
	}
	
	public Rel_Medical_Procedure_Operating_Room(int id_MPOR, Medical_Procedure id_Procedure,
			Operating_Room id_Operating_Room) {
		super();
		this.id_MPOR = id_MPOR;
		this.id_Procedure = id_Procedure;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Rel_Medical_Procedure_Operating_Room(Medical_Procedure id_Procedure, Operating_Room id_Operating_Room) {
		super();
		this.id_Procedure = id_Procedure;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	
	
}

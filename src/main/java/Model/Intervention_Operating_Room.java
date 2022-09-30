package Model;

public class Intervention_Operating_Room {

	protected int id_IO;
	protected Intervention id_Intervention;
	protected Operating_Room id_Operating_Room;
	
	public int getId_IO() {
		return id_IO;
	}
	public void setId_IO(int id_IO) {
		this.id_IO = id_IO;
	}
	public Intervention getId_Intervention() {
		return id_Intervention;
	}
	public void setId_Intervention(Intervention id_Intervention) {
		this.id_Intervention = id_Intervention;
	}
	public Operating_Room getId_Operating_Room() {
		return id_Operating_Room;
	}
	public void setId_Operating_Room(Operating_Room id_Operating_Room) {
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Intervention_Operating_Room() {
		super();
	}
	
	public Intervention_Operating_Room(int id_IO, Intervention id_Intervention, Operating_Room id_Operating_Room) {
		super();
		this.id_IO = id_IO;
		this.id_Intervention = id_Intervention;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Intervention_Operating_Room(Intervention id_Intervention, Operating_Room id_Operating_Room) {
		super();
		this.id_Intervention = id_Intervention;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	
	
}

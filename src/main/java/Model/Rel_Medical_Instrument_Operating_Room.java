package Model;

public class Rel_Medical_Instrument_Operating_Room {

	protected int id_MIOR;
	protected Medical_Instrument id_Medical_Instrument;
	protected Operating_Room id_Operating_Room;
	
	
	public int getId_MIOR() {
		return id_MIOR;
	}
	public void setId_MIOR(int id_MIOR) {
		this.id_MIOR = id_MIOR;
	}
	public Medical_Instrument getId_Medical_Instrument() {
		return id_Medical_Instrument;
	}
	public void setId_Medical_Instrument(Medical_Instrument id_Medical_Instrument) {
		this.id_Medical_Instrument = id_Medical_Instrument;
	}
	public Operating_Room getId_Operating_Room() {
		return id_Operating_Room;
	}
	public void setId_Operating_Room(Operating_Room id_Operating_Room) {
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Rel_Medical_Instrument_Operating_Room() {
		super();
	}
	
	public Rel_Medical_Instrument_Operating_Room(int id_MIOR, Medical_Instrument id_Medical_Instrument,
			Operating_Room id_Operating_Room) {
		super();
		this.id_MIOR = id_MIOR;
		this.id_Medical_Instrument = id_Medical_Instrument;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Rel_Medical_Instrument_Operating_Room(Medical_Instrument id_Medical_Instrument,
			Operating_Room id_Operating_Room) {
		super();
		this.id_Medical_Instrument = id_Medical_Instrument;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	
	
}
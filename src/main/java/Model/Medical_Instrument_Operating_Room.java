package Model;

public class Medical_Instrument_Operating_Room {

	protected int id_MO;
	protected Medical_Instrument id_Medical_Instrument;
	protected Operating_Room id_Operating_Room;
	
	public int getId_MO() {
		return id_MO;
	}
	public void setId_MO(int id_MO) {
		this.id_MO = id_MO;
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
	
	public Medical_Instrument_Operating_Room() {
		super();
	}
	
	public Medical_Instrument_Operating_Room(int id_MO, Medical_Instrument id_Medical_Instrument,
			Operating_Room id_Operating_Room) {
		super();
		this.id_MO = id_MO;
		this.id_Medical_Instrument = id_Medical_Instrument;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Medical_Instrument_Operating_Room(Medical_Instrument id_Medical_Instrument,
			Operating_Room id_Operating_Room) {
		super();
		this.id_Medical_Instrument = id_Medical_Instrument;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	
	
}
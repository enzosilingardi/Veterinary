package Model;

public class Medical_Instrument {

	protected int id_Medical_Instrument;
	protected String instrument_Name;
	protected String instrument_Description;
	protected Operating_Room id_Operating_Room;
	
	public int getId_Medical_Instrument() {
		return id_Medical_Instrument;
	}
	public void setId_Medical_Instrument(int id_Medical_Instrument) {
		this.id_Medical_Instrument = id_Medical_Instrument;
	}
	public String getInstrument_Name() {
		return instrument_Name;
	}
	public void setInstrument_Name(String instrument_Name) {
		this.instrument_Name = instrument_Name;
	}
	public String getInstrument_Description() {
		return instrument_Description;
	}
	public void setInstrument_Description(String instrument_Description) {
		this.instrument_Description = instrument_Description;
	}
	
	public Operating_Room getId_Operating_Room() {
		return id_Operating_Room;
	}
	public void setId_Operating_Room(Operating_Room id_Operating_Room) {
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Medical_Instrument() {
		super();
	}
	
	public Medical_Instrument(int id_Medical_Instrument, String instrument_Name, String instrument_Description,
			Operating_Room id_Operating_Room) {
		super();
		this.id_Medical_Instrument = id_Medical_Instrument;
		this.instrument_Name = instrument_Name;
		this.instrument_Description = instrument_Description;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	public Medical_Instrument(String instrument_Name, String instrument_Description, Operating_Room id_Operating_Room) {
		super();
		this.instrument_Name = instrument_Name;
		this.instrument_Description = instrument_Description;
		this.id_Operating_Room = id_Operating_Room;
	}
	
	
	
}
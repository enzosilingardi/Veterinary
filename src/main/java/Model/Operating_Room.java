package Model;

public class Operating_Room {

	protected int id_Operating_Room;
	protected int room_Number;
	
	public int getId_Operating_Room() {
		return id_Operating_Room;
	}
	public void setId_Operating_Room(int id_Operating_Room) {
		this.id_Operating_Room = id_Operating_Room;
	}
	public int getRoom_Number() {
		return room_Number;
	}
	public void setRoom_Number(int room_Number) {
		this.room_Number = room_Number;
	}
	
	
	public Operating_Room() {
		super();
	}
	
	public Operating_Room(int id_Operating_Room, int room_Number) {
		super();
		this.id_Operating_Room = id_Operating_Room;
		this.room_Number = room_Number;
	}
	
	public Operating_Room(int room_Number) {
		super();
		this.room_Number = room_Number;
	}
	
	
	
}
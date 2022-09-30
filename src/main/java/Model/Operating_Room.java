package Model;

public class Operating_Room {

	protected int id_Operating_Room;
	protected int room_Number;
	protected Branch id_Branch;
	
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
	
	
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Operating_Room() {
		super();
	}
	
	public Operating_Room(int id_Operating_Room, int room_Number, Branch id_Branch) {
		super();
		this.id_Operating_Room = id_Operating_Room;
		this.room_Number = room_Number;
		this.id_Branch = id_Branch;
	}
	
	public Operating_Room(int room_Number, Branch id_Branch) {
		super();
		this.room_Number = room_Number;
		this.id_Branch = id_Branch;
	}
	
	
	
	
	
}
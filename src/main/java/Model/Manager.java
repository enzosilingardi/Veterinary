package Model;

public class Manager extends User {

	 protected int id_Manager;
	 protected String working_Day;
	
	 public int getId_Manager() {
		return id_Manager;
	}
	
	 public void setId_Manager(int id_Manager) {
		this.id_Manager = id_Manager;
	}
	
	 public String getWorking_Day() {
		return working_Day;
	}
	
	 public void setWorking_Day(String working_Day) {
		this.working_Day = working_Day;
	}
	
	
	 public Manager() {
		super();
	}

	public Manager(int id_User, String username, String dni, String name, String address, String phone_Number,
			String birthdate, String gender, Profile id_Profile, int id_Manager, String working_Day) {
		super(id_User, username, dni, name, address, phone_Number, birthdate, gender, id_Profile);
		this.id_Manager = id_Manager;
		this.working_Day = working_Day;
	}

	public Manager(String username, String dni, String name, String address, String phone_Number, String birthdate,
			String gender, Profile id_Profile, int id_Manager, String working_Day) {
		super(username, dni, name, address, phone_Number, birthdate, gender, id_Profile);
		this.id_Manager = id_Manager;
		this.working_Day = working_Day;
	}

	
	
}
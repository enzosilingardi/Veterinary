package Model;

public class Manager extends Users {

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

	public Manager(int id_User, String username, String dni, String name, Address id_Address, String phone_Number,
			String birthdate, String gender, Profile id_Profile, String email, String working_Day) {
		super(id_User, username, dni, name, id_Address, phone_Number, birthdate, gender, id_Profile, email);
		this.working_Day = working_Day;
	}

	public Manager(String username, String dni, String name, Address id_Address, String phone_Number, String birthdate,
			String gender, Profile id_Profile, String email, String working_Day) {
		super(username, dni, name, id_Address, phone_Number, birthdate, gender, id_Profile, email);
		this.working_Day = working_Day;
	}

	
	
	
	
}
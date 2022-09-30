package Model;

public class Profile {

	protected int id_profile;
	protected String description;
	
	public int getId_profile() {
		return id_profile;
	}
	public void setId_profile(int id_profile) {
		this.id_profile = id_profile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Profile() {
		super();
	}
	
	public Profile(int id_profile, String description) {
		super();
		this.id_profile = id_profile;
		this.description = description;
	}
	
	public Profile(String description) {
		super();
		this.description = description;
	}
	
	
	
}

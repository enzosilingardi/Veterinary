package Model;

public class Profile {

	protected int id_profile;
	protected String profile_Name;
	
	public int getId_profile() {
		return id_profile;
	}
	public void setId_profile(int id_profile) {
		this.id_profile = id_profile;
	}
	
	public String getProfile_Name() {
		return profile_Name;
	}
	public void setProfile_Name(String profile_Name) {
		this.profile_Name = profile_Name;
	}
	
	public Profile() {
		super();
	}
	
	public Profile(int id_profile, String profile_Name) {
		super();
		this.id_profile = id_profile;
		this.profile_Name = profile_Name;
	}
	
	public Profile(String profile_Name) {
		super();
		this.profile_Name = profile_Name;
	}
	
	
	
}

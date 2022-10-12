package Model;

public class Provider_Type {

	protected int id_Provider_Type;
	protected String type_Name;
	
	
	public int getId_Provider_Type() {
		return id_Provider_Type;
	}
	public void setId_Provider_Type(int id_Provider_Type) {
		this.id_Provider_Type = id_Provider_Type;
	}
	
	public String getType_Name() {
		return type_Name;
	}
	public void setType_Name(String type_Name) {
		this.type_Name = type_Name;
	}

	public Provider_Type() {
		super();
	}
	
	public Provider_Type(int id_Provider_Type, String type_Name) {
		super();
		this.id_Provider_Type = id_Provider_Type;
		this.type_Name = type_Name;
	}
	
	public Provider_Type(String type_Name) {
		super();
		this.type_Name = type_Name;
	}
	
	
}
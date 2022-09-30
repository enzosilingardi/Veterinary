package Model;

public class Provider {

	protected int id_Provider;
	protected String phone_Number;
	protected String address;
	protected Provider_Type id_Provider_Type;
	
	public int getId_Provider() {
		return id_Provider;
	}
	public void setId_Provider(int id_Provider) {
		this.id_Provider = id_Provider;
	}
	public String getPhone_Number() {
		return phone_Number;
	}
	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Provider_Type getId_Provider_Type() {
		return id_Provider_Type;
	}
	public void setId_Provider_Type(Provider_Type id_Provider_Type) {
		this.id_Provider_Type = id_Provider_Type;
	}
	public Provider() {
		super();
	}
	
	public Provider(int id_Provider, String phone_Number, String address, Provider_Type id_Provider_Type) {
		super();
		this.id_Provider = id_Provider;
		this.phone_Number = phone_Number;
		this.address = address;
		this.id_Provider_Type = id_Provider_Type;
	}
	
	public Provider(String phone_Number, String address, Provider_Type id_Provider_Type) {
		super();
		this.phone_Number = phone_Number;
		this.address = address;
		this.id_Provider_Type = id_Provider_Type;
	}
	
	
	
	
}
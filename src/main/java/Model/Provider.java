package Model;

public class Provider {

	protected int id_Provider;
	protected Provider_Type id_Provider_Type;
	protected String address;
	protected String provider_Name;
	protected String name;
	protected String surname;
	protected String phone_Number;
	protected String email;
	protected String cuit;
	
	public int getId_Provider() {
		return id_Provider;
	}
	public void setId_Provider(int id_Provider) {
		this.id_Provider = id_Provider;
	}
	public Provider_Type getId_Provider_Type() {
		return id_Provider_Type;
	}
	public void setId_Provider_Type(Provider_Type id_Provider_Type) {
		this.id_Provider_Type = id_Provider_Type;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvider_Name() {
		return provider_Name;
	}
	public void setProvider_Name(String provider_Name) {
		this.provider_Name = provider_Name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone_Number() {
		return phone_Number;
	}
	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public Provider() {
		super();
	}
	
	public Provider(int id_Provider, Provider_Type id_Provider_Type, String address, String provider_Name, String name,
			String surname, String phone_Number, String email, String cuit) {
		super();
		this.id_Provider = id_Provider;
		this.id_Provider_Type = id_Provider_Type;
		this.address = address;
		this.provider_Name = provider_Name;
		this.name = name;
		this.surname = surname;
		this.phone_Number = phone_Number;
		this.email = email;
		this.cuit = cuit;
	}
	
	public Provider(Provider_Type id_Provider_Type, String address, String provider_Name, String name, String surname,
			String phone_Number, String email, String cuit) {
		super();
		this.id_Provider_Type = id_Provider_Type;
		this.address = address;
		this.provider_Name = provider_Name;
		this.name = name;
		this.surname = surname;
		this.phone_Number = phone_Number;
		this.email = email;
		this.cuit = cuit;
	}
	
	
	
	
	
}
package Model;

public class Medical_Product extends Product {

	protected int id_Medical_P;
	protected String expiration_Date;
	
	public int getId_Medical_P() {
		return id_Medical_P;
	}
	public void setId_Medical_P(int id_Medical_P) {
		this.id_Medical_P = id_Medical_P;
	}
	public String getExpiration_Date() {
		return expiration_Date;
	}
	public void setExpiration_Date(String expiration_Date) {
		this.expiration_Date = expiration_Date;
	}
	
	public Medical_Product() {
		super();
	}
	
	public Medical_Product(int id_Product, Provider id_Provider, String product_Name, String description,
			float cost_Price, float sale_Price, String expiration_Date) {
		super(id_Product, id_Provider, product_Name, description, cost_Price, sale_Price);
		this.expiration_Date = expiration_Date;
	}
	
	public Medical_Product(Provider id_Provider, String product_Name, String description, float cost_Price,
			float sale_Price, String expiration_Date) {
		super(id_Provider, product_Name, description, cost_Price, sale_Price);
		this.expiration_Date = expiration_Date;
	}
	
	
	
	
	
}

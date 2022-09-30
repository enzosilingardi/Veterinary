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
	
	public Medical_Product(int id_Product, String product_Name, float cost_Price, float sale_Price, String description,
			Provider id_Provider, int id_Medical_P, String expiration_Date) {
		super(id_Product, product_Name, cost_Price, sale_Price, description, id_Provider);
		this.id_Medical_P = id_Medical_P;
		this.expiration_Date = expiration_Date;
	}
	
	public Medical_Product(String product_Name, float cost_Price, float sale_Price, String description,
			Provider id_Provider, int id_Medical_P, String expiration_Date) {
		super(product_Name, cost_Price, sale_Price, description, id_Provider);
		this.id_Medical_P = id_Medical_P;
		this.expiration_Date = expiration_Date;
	}
	
	
	
}

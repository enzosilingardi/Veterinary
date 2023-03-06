package Model;

public class Product {

	//Clase Product con getter, setter y constructor
	
	protected int id_Product;
	protected int id_Provider;
	protected String product_Name;
	protected String product_Type;
	protected String description;
	protected float cost_Price;
	protected float sale_Price;
	
	public int getId_Product() {
		return id_Product;
	}
	public void setId_Product(int id_Product) {
		this.id_Product = id_Product;
	}
	public int getId_Provider() {
		return id_Provider;
	}
	public void setId_Provider(int id_Provider) {
		this.id_Provider = id_Provider;
	}
	public String getProduct_Name() {
		return product_Name;
	}
	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}
	public String getProduct_Type() {
		return product_Type;
	}
	public void setProduct_Type(String product_Type) {
		this.product_Type = product_Type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getCost_Price() {
		return cost_Price;
	}
	public void setCost_Price(float cost_Price) {
		this.cost_Price = cost_Price;
	}
	public float getSale_Price() {
		return sale_Price;
	}
	public void setSale_Price(float sale_Price) {
		this.sale_Price = sale_Price;
	}
	
	public Product() {
		super();
	}
	
	public Product(int id_Product, int id_Provider, String product_Name, String product_Type, String description,
			float cost_Price, float sale_Price) {
		super();
		this.id_Product = id_Product;
		this.id_Provider = id_Provider;
		this.product_Name = product_Name;
		this.product_Type = product_Type;
		this.description = description;
		this.cost_Price = cost_Price;
		this.sale_Price = sale_Price;
	}
	
	public Product(int id_Provider, String product_Name, String product_Type, String description, float cost_Price,
			float sale_Price) {
		super();
		this.id_Provider = id_Provider;
		this.product_Name = product_Name;
		this.product_Type = product_Type;
		this.description = description;
		this.cost_Price = cost_Price;
		this.sale_Price = sale_Price;
	}
	
	
	
}

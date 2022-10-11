package Model;

public class Product {

	protected int id_Product;
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
	public String getProduct_Name() {
		return product_Name;
	}
	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getProduct_Type() {
		return product_Type;
	}
	public void setProduct_Type(String product_Type) {
		this.product_Type = product_Type;
	}
	
	public Product() {
		super();
	}
	
	public Product(int id_Product, String product_Name, String product_Type, String description, float cost_Price,
			float sale_Price) {
		super();
		this.id_Product = id_Product;
		this.product_Name = product_Name;
		this.product_Type = product_Type;
		this.description = description;
		this.cost_Price = cost_Price;
		this.sale_Price = sale_Price;
	}
	
	public Product(String product_Name, String product_Type, String description, float cost_Price, float sale_Price) {
		super();
		this.product_Name = product_Name;
		this.product_Type = product_Type;
		this.description = description;
		this.cost_Price = cost_Price;
		this.sale_Price = sale_Price;
	}
	
	
}

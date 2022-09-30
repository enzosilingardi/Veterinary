package Model;

public class Product_Provider {

	protected int id_PrP;
	protected Product id_Product;
	protected Provider id_Provider;
	
	public int getId_PrP() {
		return id_PrP;
	}
	public void setId_PrP(int id_PrP) {
		this.id_PrP = id_PrP;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}
	public Provider getId_Provider() {
		return id_Provider;
	}
	public void setId_Provider(Provider id_Provider) {
		this.id_Provider = id_Provider;
	}
	
	public Product_Provider() {
		super();
	}
	
	public Product_Provider(int id_PrP, Product id_Product, Provider id_Provider) {
		super();
		this.id_PrP = id_PrP;
		this.id_Product = id_Product;
		this.id_Provider = id_Provider;
	}
	
	public Product_Provider(Product id_Product, Provider id_Provider) {
		super();
		this.id_Product = id_Product;
		this.id_Provider = id_Provider;
	}
	
	
	
}
package Model;

public class Stock_Product {

	protected int id_SP;
	protected Stock id_Stock;
	protected Product id_Product;
	
	public int getId_SP() {
		return id_SP;
	}
	public void setId_SP(int id_SP) {
		this.id_SP = id_SP;
	}
	public Stock getId_Stock() {
		return id_Stock;
	}
	public void setId_Stock(Stock id_Stock) {
		this.id_Stock = id_Stock;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}
	
	public Stock_Product() {
		super();
	}
	
	public Stock_Product(int id_SP, Stock id_Stock, Product id_Product) {
		super();
		this.id_SP = id_SP;
		this.id_Stock = id_Stock;
		this.id_Product = id_Product;
	}
	
	public Stock_Product(Stock id_Stock, Product id_Product) {
		super();
		this.id_Stock = id_Stock;
		this.id_Product = id_Product;
	}
	
	
	
}
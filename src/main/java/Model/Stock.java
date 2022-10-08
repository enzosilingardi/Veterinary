package Model;

public class Stock {

	protected int id_Stock;
	protected int amount;
	protected Product id_Product;

	
	public int getId_Stock() {
		return id_Stock;
	}
	public void setId_Stock(int id_Stock) {
		this.id_Stock = id_Stock;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}

	public Stock() {
		super();
	}
	
	public Stock(int id_Stock, int amount, Product id_Product) {
		super();
		this.id_Stock = id_Stock;
		this.amount = amount;
		this.id_Product = id_Product;
		
	}
	
	public Stock(int amount, Product id_Product) {
		super();
		this.amount = amount;
		this.id_Product = id_Product;
		
	}
	
	
	
}

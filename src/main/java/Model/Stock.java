package Model;

public class Stock {

	//Clase Stock que no es utilizada en la versión actual
	
	protected int id_Stock;
	protected Product id_Product;
	protected int amount;
	

	
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
	
	public Stock(int id_Stock, Product id_Product, int amount) {
		super();
		this.id_Stock = id_Stock;
		this.id_Product = id_Product;
		this.amount = amount;
	}
	
	public Stock(Product id_Product, int amount) {
		super();
		this.id_Product = id_Product;
		this.amount = amount;
	}
	
	
	
	
}

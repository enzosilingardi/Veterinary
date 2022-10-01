package Model;

public class Rel_Stock_Product_Branch {

	protected int id_SPB;
	protected Stock id_Stock;
	protected Product id_Product;
	protected Branch id_Branch;

	
	public int getId_SPB() {
		return id_SPB;
	}
	public void setId_SPB(int id_SPB) {
		this.id_SPB = id_SPB;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}
	public Stock getId_Stock() {
		return id_Stock;
	}
	public void setId_Stock(Stock id_Stock) {
		this.id_Stock = id_Stock;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Rel_Stock_Product_Branch() {
		super();
	}
	public Rel_Stock_Product_Branch(int id_SPB, Stock id_Stock, Product id_Product, Branch id_Branch) {
		super();
		this.id_SPB = id_SPB;
		this.id_Stock = id_Stock;
		this.id_Product = id_Product;
		this.id_Branch = id_Branch;
	}
	public Rel_Stock_Product_Branch(Stock id_Stock, Product id_Product, Branch id_Branch) {
		super();
		this.id_Stock = id_Stock;
		this.id_Product = id_Product;
		this.id_Branch = id_Branch;
	}
	
	
	
}
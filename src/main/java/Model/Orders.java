package Model;

public class Orders {

	protected int id_Order;
	protected int quantity;
	protected Product id_Product;
	protected Provider id_Provider;
	protected Branch id_Branch;
	
	public int getId_Order() {
		return id_Order;
	}
	public void setId_Order(int id_Order) {
		this.id_Order = id_Order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Orders() {
		super();
	}
	
	public Orders(int id_Order, int quantity, Product id_Product, Provider id_Provider, Branch id_Branch) {
		super();
		this.id_Order = id_Order;
		this.quantity = quantity;
		this.id_Product = id_Product;
		this.id_Provider = id_Provider;
		this.id_Branch = id_Branch;
	}
	
	public Orders(int quantity, Product id_Product, Provider id_Provider, Branch id_Branch) {
		super();
		this.quantity = quantity;
		this.id_Product = id_Product;
		this.id_Provider = id_Provider;
		this.id_Branch = id_Branch;
	}
	
	
}

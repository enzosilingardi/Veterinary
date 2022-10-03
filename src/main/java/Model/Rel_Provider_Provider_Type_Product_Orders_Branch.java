package Model;

public class Rel_Provider_Provider_Type_Product_Orders_Branch {

	protected int id_PPTPOB;
	protected Provider id_Provider;
	protected Provider_Type id_Provider_Type;
	protected Orders id_Order;
	protected Product id_Product;
	protected Branch id_Branch;
	
	
	public int getId_PPTPOB() {
		return id_PPTPOB;
	}
	public void setId_PPTPOB(int id_PPTPOB) {
		this.id_PPTPOB = id_PPTPOB;
	}
	public Provider getId_Provider() {
		return id_Provider;
	}
	public void setId_Provider(Provider id_Provider) {
		this.id_Provider = id_Provider;
	}
	public Provider_Type getId_Provider_Type() {
		return id_Provider_Type;
	}
	public void setId_Provider_Type(Provider_Type id_Provider_Type) {
		this.id_Provider_Type = id_Provider_Type;
	}
	public Orders getId_Order() {
		return id_Order;
	}
	public void setId_Order(Orders id_Order) {
		this.id_Order = id_Order;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	
	public Rel_Provider_Provider_Type_Product_Orders_Branch() {
		super();
	}
	
	public Rel_Provider_Provider_Type_Product_Orders_Branch(int id_PPTPOB, Provider id_Provider,
			Provider_Type id_Provider_Type, Orders id_Order, Product id_Product, Branch id_Branch) {
		super();
		this.id_PPTPOB = id_PPTPOB;
		this.id_Provider = id_Provider;
		this.id_Provider_Type = id_Provider_Type;
		this.id_Order = id_Order;
		this.id_Product = id_Product;
		this.id_Branch = id_Branch;
	}
	
	public Rel_Provider_Provider_Type_Product_Orders_Branch(Provider id_Provider, Provider_Type id_Provider_Type,
			Orders id_Order, Product id_Product, Branch id_Branch) {
		super();
		this.id_Provider = id_Provider;
		this.id_Provider_Type = id_Provider_Type;
		this.id_Order = id_Order;
		this.id_Product = id_Product;
		this.id_Branch = id_Branch;
	}
	
	
}

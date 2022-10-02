package Model;

public class Rel_Provider_Provider_Type_Product_Orders {

	protected int id_PPTPO;
	protected Provider id_Provider;
	protected Provider_Type id_Provider_Type;
	protected Orders id_Order;
	protected Product id_Product;
	
	public int getId_PPTPO() {
		return id_PPTPO;
	}
	public void setId_PPTPO(int id_PPTPO) {
		this.id_PPTPO = id_PPTPO;
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
	
	public Rel_Provider_Provider_Type_Product_Orders() {
		super();
	}
	
	public Rel_Provider_Provider_Type_Product_Orders(int id_PPTPO, Provider id_Provider, Provider_Type id_Provider_Type,
			Orders id_Order, Product id_Product) {
		super();
		this.id_PPTPO = id_PPTPO;
		this.id_Provider = id_Provider;
		this.id_Provider_Type = id_Provider_Type;
		this.id_Order = id_Order;
		this.id_Product = id_Product;
	}
	
	public Rel_Provider_Provider_Type_Product_Orders(Provider id_Provider, Provider_Type id_Provider_Type,
			Orders id_Order, Product id_Product) {
		super();
		this.id_Provider = id_Provider;
		this.id_Provider_Type = id_Provider_Type;
		this.id_Order = id_Order;
		this.id_Product = id_Product;
	}
	
	
	
}

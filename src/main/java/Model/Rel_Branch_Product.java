package Model;

public class Rel_Branch_Product {

	//Clase Rel_Branch_Product con getter, setter y constructor
	
	protected int id_BP;
	protected Branch id_Branch;
	protected Product id_Product;
	
	public int getId_BP() {
		return id_BP;
	}
	public void setId_BP(int id_BP) {
		this.id_BP = id_BP;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}
	
	public Rel_Branch_Product() {
		super();
	}
	
	public Rel_Branch_Product(int id_BP, Branch id_Branch, Product id_Product) {
		super();
		this.id_BP = id_BP;
		this.id_Branch = id_Branch;
		this.id_Product = id_Product;
	}
	
	public Rel_Branch_Product(Branch id_Branch, Product id_Product) {
		super();
		this.id_Branch = id_Branch;
		this.id_Product = id_Product;
	}
	
	
	
}

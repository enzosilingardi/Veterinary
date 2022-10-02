package Model;

public class Rel_Product_Food_Product_Medical_Product_Accesory_Product {

	protected int id_PFPMPAP;
	protected Product id_Product;
	protected Food_Product id_Food_Product;
	protected Medical_Product id_Medical_Product;
	protected Accesory_Product id_Accesory_Product;
	
	public int getId_PFPMPAP() {
		return id_PFPMPAP;
	}
	public void setId_PFPMPAP(int id_PFPMPAP) {
		this.id_PFPMPAP = id_PFPMPAP;
	}
	public Product getId_Product() {
		return id_Product;
	}
	public void setId_Product(Product id_Product) {
		this.id_Product = id_Product;
	}
	public Food_Product getId_Food_Product() {
		return id_Food_Product;
	}
	public void setId_Food_Product(Food_Product id_Food_Product) {
		this.id_Food_Product = id_Food_Product;
	}
	public Medical_Product getId_Medical_Product() {
		return id_Medical_Product;
	}
	public void setId_Medical_Product(Medical_Product id_Medical_Product) {
		this.id_Medical_Product = id_Medical_Product;
	}
	public Accesory_Product getId_Accesory_Product() {
		return id_Accesory_Product;
	}
	public void setId_Accesory_Product(Accesory_Product id_Accesory_Product) {
		this.id_Accesory_Product = id_Accesory_Product;
	}
	
	public Rel_Product_Food_Product_Medical_Product_Accesory_Product() {
		super();
	}
	
	public Rel_Product_Food_Product_Medical_Product_Accesory_Product(int id_PFPMPAP, Product id_Product,
			Food_Product id_Food_Product, Medical_Product id_Medical_Product, Accesory_Product id_Accesory_Product) {
		super();
		this.id_PFPMPAP = id_PFPMPAP;
		this.id_Product = id_Product;
		this.id_Food_Product = id_Food_Product;
		this.id_Medical_Product = id_Medical_Product;
		this.id_Accesory_Product = id_Accesory_Product;
	}
	
	public Rel_Product_Food_Product_Medical_Product_Accesory_Product(Product id_Product, Food_Product id_Food_Product,
			Medical_Product id_Medical_Product, Accesory_Product id_Accesory_Product) {
		super();
		this.id_Product = id_Product;
		this.id_Food_Product = id_Food_Product;
		this.id_Medical_Product = id_Medical_Product;
		this.id_Accesory_Product = id_Accesory_Product;
	}
	
	
	
}

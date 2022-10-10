package Model;

public class Accesory_Product extends Product {

	protected int id_Accesory_P;


	
	public int getId_Accesory_P() {
		return id_Accesory_P;
	}

	public void setId_Accesory_P(int id_Accesory_P) {
		this.id_Accesory_P = id_Accesory_P;
	}



	public Accesory_Product() {
		super();
	}

	public Accesory_Product(int id_Product, Provider id_Provider, String product_Name, String description,
			float cost_Price, float sale_Price) {
		super(id_Product, id_Provider, product_Name, description, cost_Price, sale_Price);
	}

	public Accesory_Product(Provider id_Provider, String product_Name, String description, float cost_Price,
			float sale_Price) {
		super(id_Provider, product_Name, description, cost_Price, sale_Price);
	}

	
	
	
	
}
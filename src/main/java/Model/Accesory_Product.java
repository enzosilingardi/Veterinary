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

	public Accesory_Product(int id_Product, String product_Name, float cost_Price, float sale_Price, String description,
			Provider id_Provider, Stock id_Stock) {
		super(id_Product, product_Name, cost_Price, sale_Price, description, id_Provider, id_Stock);
	}

	public Accesory_Product(String product_Name, float cost_Price, float sale_Price, String description,
			Provider id_Provider, Stock id_Stock) {
		super(product_Name, cost_Price, sale_Price, description, id_Provider, id_Stock);
	}

	
	
	
}
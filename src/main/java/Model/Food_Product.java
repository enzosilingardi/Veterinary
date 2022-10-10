package Model;

public class Food_Product extends Product {

	protected int id_Food_P;
	protected int food_Weight;
	protected String expiration_Date;
	protected float price_Per_Kilo;
	protected float price_Per_Bag;
	
	
	
	public int getId_Food_P() {
		return id_Food_P;
	}

	public void setId_Food_P(int id_Food_P) {
		this.id_Food_P = id_Food_P;
	}

	public int getFood_Weight() {
		return food_Weight;
	}

	public void setFood_Weight(int food_Weight) {
		this.food_Weight = food_Weight;
	}

	public String getExpiration_Date() {
		return expiration_Date;
	}

	public void setExpiration_Date(String expiration_Date) {
		this.expiration_Date = expiration_Date;
	}

	public float getPrice_Per_Kilo() {
		return price_Per_Kilo;
	}

	public void setPrice_Per_Kilo(float price_Per_Kilo) {
		this.price_Per_Kilo = price_Per_Kilo;
	}

	public float getPrice_Per_Bag() {
		return price_Per_Bag;
	}

	public void setPrice_Per_Bag(float price_Per_Bag) {
		this.price_Per_Bag = price_Per_Bag;
	}
	

	public Food_Product() {
		super();
	}

	public Food_Product(int id_Product, Provider id_Provider, String product_Name, String description, float cost_Price,
			float sale_Price, int food_Weight, String expiration_Date, float price_Per_Kilo, float price_Per_Bag) {
		super(id_Product, id_Provider, product_Name, description, cost_Price, sale_Price);
		this.food_Weight = food_Weight;
		this.expiration_Date = expiration_Date;
		this.price_Per_Kilo = price_Per_Kilo;
		this.price_Per_Bag = price_Per_Bag;
	}

	public Food_Product(Provider id_Provider, String product_Name, String description, float cost_Price,
			float sale_Price, int food_Weight, String expiration_Date, float price_Per_Kilo, float price_Per_Bag) {
		super(id_Provider, product_Name, description, cost_Price, sale_Price);
		this.food_Weight = food_Weight;
		this.expiration_Date = expiration_Date;
		this.price_Per_Kilo = price_Per_Kilo;
		this.price_Per_Bag = price_Per_Bag;
	}

	
	
	
}
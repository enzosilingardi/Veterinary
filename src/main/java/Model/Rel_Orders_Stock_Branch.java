package Model;

public class Rel_Orders_Stock_Branch {
	
	protected int id_OSB;
	protected Orders id_Order;
	protected Stock id_Stock;
	protected Branch id_Branch;
	
	
	public int getId_OSB() {
		return id_OSB;
	}
	public void setId_OSB(int id_OSB) {
		this.id_OSB = id_OSB;
	}
	public Orders getId_Order() {
		return id_Order;
	}
	public void setId_Order(Orders id_Order) {
		this.id_Order = id_Order;
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
	
	public Rel_Orders_Stock_Branch() {
		super();
	}
	
	
	public Rel_Orders_Stock_Branch(int id_OSB, Orders id_Order, Stock id_Stock, Branch id_Branch) {
		super();
		this.id_OSB = id_OSB;
		this.id_Order = id_Order;
		this.id_Stock = id_Stock;
		this.id_Branch = id_Branch;
	}
	
	public Rel_Orders_Stock_Branch(Orders id_Order, Stock id_Stock, Branch id_Branch) {
		super();
		this.id_Order = id_Order;
		this.id_Stock = id_Stock;
		this.id_Branch = id_Branch;
	}
	
	
	
	
}

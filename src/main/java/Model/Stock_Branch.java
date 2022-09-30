package Model;

public class Stock_Branch {

	protected int id_SB;
	protected Stock id_Stock;
	protected Branch id_Branch;

	public int getId_SB() {
		return id_SB;
	}
	public void setId_SB(int id_SB) {
		this.id_SB = id_SB;
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
	
	public Stock_Branch() {
		super();
	}
	
	public Stock_Branch(int id_SB, Stock id_Stock, Branch id_Branch) {
		super();
		this.id_SB = id_SB;
		this.id_Stock = id_Stock;
		this.id_Branch = id_Branch;
	}
	
	public Stock_Branch(Stock id_Stock, Branch id_Branch) {
		super();
		this.id_Stock = id_Stock;
		this.id_Branch = id_Branch;
	}
	
	
	
}
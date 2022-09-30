package Model;

public class Branch_City {
	
	protected int id_BC;
	protected Branch id_Branch;
	protected City id_City;
	
	public int getId_BC() {
		return id_BC;
	}
	public void setId_BC(int id_BC) {
		this.id_BC = id_BC;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	public City getId_City() {
		return id_City;
	}
	public void setId_City(City id_City) {
		this.id_City = id_City;
	}
	
	public Branch_City() {
		super();
	}
	
	public Branch_City(int id_BC, Branch id_Branch, City id_City) {
		super();
		this.id_BC = id_BC;
		this.id_Branch = id_Branch;
		this.id_City = id_City;
	}
	
	public Branch_City(Branch id_Branch, City id_City) {
		super();
		this.id_Branch = id_Branch;
		this.id_City = id_City;
	}
	
	

}
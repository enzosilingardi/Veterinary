package Model;

public class Procedure_Type {

	private int id_Procedure_Type;
	private String proced_Name;
	
	public int getId_Procedure_Type() {
		return id_Procedure_Type;
	}
	public void setId_Procedure_Type(int id_Procedure_Type) {
		this.id_Procedure_Type = id_Procedure_Type;
	}
	public String getProced_Name() {
		return proced_Name;
	}
	public void setProced_Name(String proced_Name) {
		this.proced_Name = proced_Name;
	}
	
	public Procedure_Type() {
		super();
	}
	
	public Procedure_Type(int id_Procedure_Type, String proced_Name) {
		super();
		this.id_Procedure_Type = id_Procedure_Type;
		this.proced_Name = proced_Name;
	}
	
	public Procedure_Type(String proced_Name) {
		super();
		this.proced_Name = proced_Name;
	}
	
	
	
}

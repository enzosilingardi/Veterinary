package Model;

public class Procedure_Type {

	private int id_Procedure_Type;
	private String procedure_Name;
	
	public int getId_Procedure_Type() {
		return id_Procedure_Type;
	}
	public void setId_Procedure_Type(int id_Procedure_Type) {
		this.id_Procedure_Type = id_Procedure_Type;
	}
	public String getProcedure_Name() {
		return procedure_Name;
	}
	public void setProcedure_Name(String procedure_Name) {
		this.procedure_Name = procedure_Name;
	}
	
	public Procedure_Type() {
		super();
	}
	
	public Procedure_Type(int id_Procedure_Type, String procedure_Name) {
		super();
		this.id_Procedure_Type = id_Procedure_Type;
		this.procedure_Name = procedure_Name;
	}
	
	public Procedure_Type(String procedure_Name) {
		super();
		this.procedure_Name = procedure_Name;
	}
	
	
	
}

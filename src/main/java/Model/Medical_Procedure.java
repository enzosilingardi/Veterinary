package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Medical_Procedure {

	protected int id_procedure;
	protected String procedure_Type;
	protected LocalDate procedure_Date;
	protected LocalTime procedure_Time;
	protected Veterinarian id_Veterinarian;
	protected Pet id_Pet;
	protected Branch id_Branch;
	public int getId_procedure() {
		return id_procedure;
	}
	public void setId_procedure(int id_procedure) {
		this.id_procedure = id_procedure;
	}
	public String getProcedure_Type() {
		return procedure_Type;
	}
	public void setProcedure_Type(String procedure_Type) {
		this.procedure_Type = procedure_Type;
	}
	public LocalDate getProcedure_Date() {
		return procedure_Date;
	}
	public void setProcedure_Date(LocalDate procedure_Date) {
		this.procedure_Date = procedure_Date;
	}
	public LocalTime getProcedure_Time() {
		return procedure_Time;
	}
	public void setProcedure_Time(LocalTime procedure_Time) {
		this.procedure_Time = procedure_Time;
	}
	public Veterinarian getId_Veterinarian() {
		return id_Veterinarian;
	}
	public void setId_Veterinarian(Veterinarian id_Veterinarian) {
		this.id_Veterinarian = id_Veterinarian;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	public Branch getId_Branch() {
		return id_Branch;
	}
	public void setId_Branch(Branch id_Branch) {
		this.id_Branch = id_Branch;
	}
	public Medical_Procedure() {
		super();
	}
	
	public Medical_Procedure(int id_procedure, String procedure_Type, LocalDate procedure_Date,
			LocalTime procedure_Time, Veterinarian id_Veterinarian, Pet id_Pet, Branch id_Branch) {
		super();
		this.id_procedure = id_procedure;
		this.procedure_Type = procedure_Type;
		this.procedure_Date = procedure_Date;
		this.procedure_Time = procedure_Time;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Pet = id_Pet;
		this.id_Branch = id_Branch;
	}
	
	public Medical_Procedure(String procedure_Type, LocalDate procedure_Date, LocalTime procedure_Time,
			Veterinarian id_Veterinarian, Pet id_Pet, Branch id_Branch) {
		super();
		this.procedure_Type = procedure_Type;
		this.procedure_Date = procedure_Date;
		this.procedure_Time = procedure_Time;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Pet = id_Pet;
		this.id_Branch = id_Branch;
	}
	
	
	
}

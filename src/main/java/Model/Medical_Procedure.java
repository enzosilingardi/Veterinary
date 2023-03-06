package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Medical_Procedure {

	// Clase Medical_Procedure con getter, setter y constructor
	
	protected int id_procedure;
	protected Procedure_Type id_Procedure_Type;
	protected Pet id_Pet;
	protected LocalDate proced_Date;
	protected LocalTime proced_Time;
	
	public int getId_procedure() {
		return id_procedure;
	}
	public void setId_procedure(int id_procedure) {
		this.id_procedure = id_procedure;
	}
	public Procedure_Type getId_Procedure_Type() {
		return id_Procedure_Type;
	}
	public void setId_Procedure_Type(Procedure_Type id_Procedure_Type) {
		this.id_Procedure_Type = id_Procedure_Type;
	}
	public Pet getId_Pet() {
		return id_Pet;
	}
	public void setId_Pet(Pet id_Pet) {
		this.id_Pet = id_Pet;
	}
	public LocalDate getProced_Date() {
		return proced_Date;
	}
	public void setProced_Date(LocalDate proced_Date) {
		this.proced_Date = proced_Date;
	}
	public LocalTime getProced_Time() {
		return proced_Time;
	}
	public void setProced_Time(LocalTime proced_Time) {
		this.proced_Time = proced_Time;
	}
	
	public Medical_Procedure() {
		super();
	}
	
	public Medical_Procedure(int id_procedure, Procedure_Type id_Procedure_Type, Pet id_Pet, LocalDate proced_Date,
			LocalTime proced_Time) {
		super();
		this.id_procedure = id_procedure;
		this.id_Procedure_Type = id_Procedure_Type;
		this.id_Pet = id_Pet;
		this.proced_Date = proced_Date;
		this.proced_Time = proced_Time;
	}
	
	public Medical_Procedure(Procedure_Type id_Procedure_Type, Pet id_Pet, LocalDate proced_Date,
			LocalTime proced_Time) {
		super();
		this.id_Procedure_Type = id_Procedure_Type;
		this.id_Pet = id_Pet;
		this.proced_Date = proced_Date;
		this.proced_Time = proced_Time;
	}
	
	
	
}

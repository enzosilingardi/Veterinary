package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Medical_Visit {
	
	protected int id_Visit;
	protected String visit_Type;
	protected LocalDate visit_Date;
	protected LocalTime visit_Hour;
	protected Client id_Client;
	protected Veterinarian id_Veterinarian;
	protected Pet id_Pet;
	
	public int getId_Visit() {
		return id_Visit;
	}
	public void setId_Visit(int id_Visit) {
		this.id_Visit = id_Visit;
	}
	public String getVisit_Type() {
		return visit_Type;
	}
	public void setVisit_Type(String visit_Type) {
		this.visit_Type = visit_Type;
	}
	public LocalDate getVisit_Date() {
		return visit_Date;
	}
	public void setVisit_Date(LocalDate visit_Date) {
		this.visit_Date = visit_Date;
	}
	public LocalTime getVisit_Hour() {
		return visit_Hour;
	}
	public void setVisit_Hour(LocalTime visit_Hour) {
		this.visit_Hour = visit_Hour;
	}
	
	public Client getId_Client() {
		return id_Client;
	}
	public void setId_Client(Client id_Client) {
		this.id_Client = id_Client;
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
	
	public Medical_Visit() {
		super();
	}
	
	public Medical_Visit(int id_Visit, String visit_Type, LocalDate visit_Date, LocalTime visit_Hour, Client id_Client,
			Veterinarian id_Veterinarian, Pet id_Pet) {
		super();
		this.id_Visit = id_Visit;
		this.visit_Type = visit_Type;
		this.visit_Date = visit_Date;
		this.visit_Hour = visit_Hour;
		this.id_Client = id_Client;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Pet = id_Pet;
	}
	
	public Medical_Visit(String visit_Type, LocalDate visit_Date, LocalTime visit_Hour, Client id_Client,
			Veterinarian id_Veterinarian, Pet id_Pet) {
		super();
		this.visit_Type = visit_Type;
		this.visit_Date = visit_Date;
		this.visit_Hour = visit_Hour;
		this.id_Client = id_Client;
		this.id_Veterinarian = id_Veterinarian;
		this.id_Pet = id_Pet;
	}
	
	
	
}

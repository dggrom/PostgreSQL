package Sempel.FormDocuments.DokComing;

public class PersenDokComing {
	
	private String Number;
	private String Amount;
	private String Koment;
	private Boolean Deleted;
	
	public PersenDokComing(String Number, String Amount, String Koment, Boolean Deleted){
		this.Number = Number;
		this.Amount = Amount;
		this.Koment = Koment;
		this.Deleted = Deleted;
	}

	public void setNumber(String Value) {Number = Value;}
	public String getNumber() {return Number;}
	
	public void setAmount(String Value) {Amount = Value;}
	public String getAmount() {return Amount;}
	
	public void setKoment(String Value) {Koment = Value;}
	public String getKoment() {return Koment;}
	
	public void setDeleted(Boolean Value) {Deleted = Value;}
	public Boolean getDeleted() {return Deleted;}
	
}

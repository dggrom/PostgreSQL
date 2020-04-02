package Sempel.FormDocuments.DokComing;

public class PersenDokComing {
	
	private String Number;
	private String Amount;
	private String Koment;
	
	public PersenDokComing(String Number, String Amount, String Koment){
		this.Number = Number;
		this.Amount = Amount;
		this.Koment = Koment;
	}

	public void setNumber(String Value) {Number = Value;}
	public String getNumber() {return Number;}
	
	public void setAmount(String Value) {Amount = Value;}
	public String getAmount() {return Amount;}
	
	public void setKoment(String Value) {Koment = Value;}
	public String getKoment() {return Koment;}
	
}

package Sempel.FormDocuments.DokComing;


public class PersenDokComing {
	
	private Integer Number;
	private Integer Amount;
	private String Koment;
	private Integer idKontragent;
	private Integer idView;
	private Boolean Deleted;
	
	public PersenDokComing(Integer Number, Integer Amount, String Koment, Boolean Deleted, Integer idKontragent, Integer idView){
		this.idKontragent = idKontragent;
		this.idView = idView;
		this.Number = Number;
		this.Amount = Amount;
		this.Koment = Koment;
		this.Deleted = Deleted;
	}
	

	public void setIdKontragent(Integer Value) {idKontragent = Value;}
	public Integer getIdKontragent() {return idKontragent;}
	
	public void setIdView(Integer Value) {idView = Value;}
	public Integer getIdView() {return idView;}

	public void setNumber(Integer Value) {Number = Value;}
	public Integer getNumber() {return Number;}
	
	public void setAmount(Integer Value) {Amount = Value;}
	public Integer getAmount() {return Amount;}
	
	public void setKoment(String Value) {Koment = Value;}
	public String getKoment() {return Koment;}
	
	public void setDeleted(Boolean Value) {Deleted = Value;}
	public Boolean getDeleted() {return Deleted;}
	
}

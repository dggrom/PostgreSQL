package Sempel.FormDocuments.DokConsumption;

public class PersenDokConsuption {

	private Integer idDokConsu;
	private Integer amount;
	private String koments;
	private Integer idKontragent;
	private Integer idView;
	private Boolean deleted;
	
	PersenDokConsuption(Integer idDokConsu, Integer amount,String koments,Integer idKontragent,Integer idView,Boolean deleted){
		
		this.idDokConsu = idDokConsu;
		this.amount = amount;
		this.koments = koments;
		this.idKontragent = idKontragent;
		this.idView = idView;
		this.deleted = deleted;
		
	}
	
	public void setIdDokConsu(Integer value) {idDokConsu = value;}
	public Integer getIdDokConsu() {return idDokConsu;}
	
	public void setAmount(Integer value) {amount = value;}
	public Integer getAmount() {return amount;}
	
	public void setKoments(String value) {koments = value;}
	public String getKoments() {return koments;}
	
	public void setIdKontragent(Integer value) {idKontragent = value;}
	public Integer getIdKontragent() {return idKontragent;}
	
	public void setIdView(Integer value) {idView = value;}
	public Integer getIdView() {return idView;}
	
	public void setDeleted(Boolean value) {deleted = value;}
	public Boolean getDeleted() {return deleted;}
	
}

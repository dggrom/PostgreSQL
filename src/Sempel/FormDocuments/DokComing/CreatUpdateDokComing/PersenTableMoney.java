package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;

public class PersenTableMoney {

	private Integer NL;
	private String Koll;
	private String Nomen;
	
	public PersenTableMoney(Integer NL, String Koll, String Nomen) {
		this.Koll = Koll;
		this.NL = NL;
		this.Nomen = Nomen;
	}
	
	public void setNL(Integer value) {this.NL = value;}
	public Integer getNL() {return NL;}
	
	public void setKoll(String value) {this.Koll = value;}
	public String getKoll() {return Koll;}
	
	public void setNomen(String value) {this.Nomen = value;}
	public String getNomen() {return Nomen;}
	
}

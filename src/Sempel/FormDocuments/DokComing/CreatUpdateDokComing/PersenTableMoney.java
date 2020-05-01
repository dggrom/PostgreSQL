package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;

public class PersenTableMoney {

	private Integer NL;
	private String Koll;
	private String Nomen;
	private String Sum;
	
	public PersenTableMoney(Integer NL, String Koll, String Nomen, String Sum) {
		this.Koll = Koll;
		this.NL = NL;
		this.Nomen = Nomen;
		this.Sum = Sum;
	}
	
	public void setNL(Integer value) {this.NL = value;}
	public Integer getNL() {return NL;}
	
	public void setKoll(String value) {this.Koll = value;}
	public String getKoll() {return Koll;}
	
	public void setNomen(String value) {this.Nomen = value;}
	public String getNomen() {return Nomen;}
	
	public void setSum(String value) {this.Sum = value;}
	public String getSum() {return Sum;}
}

package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;

public class PersenTableMoney {

	private Integer NL;
	private Integer Koll;
	private String Nomen;
	private Integer Sum;
	
	public PersenTableMoney(Integer NL, Integer Koll, String Nomen, Integer Sum) {
		this.Koll = Koll;
		this.NL = NL;
		this.Nomen = Nomen;
		this.Sum = Sum;
	}
	
	public void setNL(Integer value) {this.NL = value;}
	public Integer getNL() {return NL;}
	
	public void setKoll(Integer value) {this.Koll = value;}
	public Integer getKoll() {return Koll;}
	
	public void setNomen(String value) {this.Nomen = value;}
	public String getNomen() {return Nomen;}
	
	public void setSum(Integer value) {this.Sum = value;}
	public Integer getSum() {return Sum;}
}

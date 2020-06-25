package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;

public class PersenTableMoney {

	private Integer NL;
	private Integer Koll;
	private String Nomen;
	private Integer Sum;
	private Integer Price;
	
	public PersenTableMoney(Integer NL, Integer Koll, String Nomen, Integer Sum, Integer Price) {
		this.Koll = Koll;
		this.NL = NL;
		this.Nomen = Nomen;
		this.Sum = Sum;
		this.Price = Price;
	}
	
	public void setNL(Integer value) {this.NL = value;}
	public Integer getNL() {return NL;}
	
	public void setKoll(Integer value) {this.Koll = value;}
	public Integer getKoll() {return Koll;}
	
	public void setNomen(String value) {this.Nomen = value;}
	public String getNomen() {return Nomen;}
	
	public void setSum(Integer value) {this.Sum = value;}
	public Integer getSum() {return Sum;}
	
	public void setPrice(Integer value) {this.Price = value;}
	public Integer getPrice() {return this.Price;}
	
}

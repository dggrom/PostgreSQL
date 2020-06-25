package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;

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
	
	static ObservableList<PersenTableMoney> getMassivPersenTableMoney(ObservableList<PersenTableMoney> mass, Connection connection, Integer NumberTableLine, String NomDokCreat) throws SQLException{

		SelectPost SelPos = new SelectPost();
    	ResultSet ResultSetTableMoney = SelPos.SelectInfoBase(connection, "SELECT DC.id_dcomtm, DC.id_dcom, Nom.name_nomen, DC.kol_dcomtm, DC.Sum_docmtm, DC.Price_docmtm FROM public.\"DokComingTableMoney\" DC, public.\"Nomenclature\" Nom WHERE DC.id_nomen = Nom.id_nomen and DC.id_dcom = " + NomDokCreat + ";");
    	while (ResultSetTableMoney.next()) {
    		NumberTableLine++;
    		mass.add(new PersenTableMoney(NumberTableLine, ResultSetTableMoney.getInt(4), ResultSetTableMoney.getString(3),ResultSetTableMoney.getInt(5),ResultSetTableMoney.getInt(6)));
    	}
		
		return mass;
	
	}
	
}

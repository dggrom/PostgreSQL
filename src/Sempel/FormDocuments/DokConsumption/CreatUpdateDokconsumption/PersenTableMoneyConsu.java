package Sempel.FormDocuments.DokConsumption.CreatUpdateDokconsumption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenTableMoneyConsu {

	private Integer NL;
	private Integer Koll;
	private String Nomen;
	private Integer Sum;
	
	public PersenTableMoneyConsu(Integer NL, Integer Koll, String Nomen, Integer Sum) {
		this.NL = NL;
		this.Koll = Koll;
		this.Nomen = Nomen;
		this.Sum = Sum;
	}
	
	public void setNL(Integer value) {this.NL = value;}
	public Integer getNL() {return this.NL;}
	
	public void setKoll(Integer value) {this.Koll = value;}
	public Integer getKoll() {return this.Koll;}
	
	public void setNomen(String value) {this.Nomen = value;}
	public String getNomen() {return this.Nomen;}
	
	public void setSum(Integer value) {this.Sum = value;}
	public Integer getSum() {return this.Sum;}

	public static ObservableList<PersenTableMoneyConsu> getMassivTableMoneyCouns(SettingConnectSQL SetCon, ObservableList<PersenTableMoneyConsu> Mass, String NomerDok) throws SQLException{
		
		Connection Con = SetCon.CreatConnect();
		
		SelectPost SelPos = new SelectPost();
		ResultSet resSet = SelPos.SelectInfoBase(Con, "SELECT DC.id_dcontm, DC.id_dcon, Nom.name_nomen, DC.kol_dcontm, DC.sum_dcontm\n" + 
				"	FROM public.\"DokConsumptionTableMoney\" DC, public.\"Nomenclature\" Nom\n" + 
				"	WHERE DC.id_nomen = Nom.id_nomen AND DC.id_dcon = "+NomerDok+";");
		int lineCount = 0;
		while (resSet.next()) {
			lineCount++;
			Mass.add(new PersenTableMoneyConsu(lineCount, resSet.getInt(4), resSet.getString(3), resSet.getInt(5)));
		}
		
		return Mass;
		
	}
	
}
	

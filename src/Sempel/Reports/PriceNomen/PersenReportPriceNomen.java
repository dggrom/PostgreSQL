package Sempel.Reports.PriceNomen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenReportPriceNomen {

	private Integer NP;
	private String NomenName;
	private Integer SumNomen;
	
	public PersenReportPriceNomen(Integer NP, String NomenName, Integer SumNomen){
		
		this.NP = NP;
		this.NomenName = NomenName;
		this.SumNomen = SumNomen;
		
	}
	
	public void setNP(Integer value) {this.NP = value;}
	public Integer getNP() {return this.NP;}
	
	public void setNomenName(String value) {this.NomenName = value;}
	public String getNomenName() {return this.NomenName;}
	
	public void setSumNomen(Integer value) {this.SumNomen = value;}
	public Integer getSumNomen() {return this.SumNomen;}
	
	public static ObservableList<PersenReportPriceNomen> getMassivReportPrice(SettingConnectSQL SetCon) throws SQLException {
		
		ObservableList<PersenReportPriceNomen> Mass = FXCollections.observableArrayList();
		
		Integer linePer = 0;
		String TextSQL = "SELECT Nom.name_nomen, RP.price_rp\n" + 
				"	FROM public.\"RegistrPrice\" RP, public.\"Nomenclature\" Nom\n" + 
				"	WHERE Nom.id_nomen = RP.id_nomen;";
		
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		
		ResultSet ResSet = SelPos.SelectInfoBase(Conn, TextSQL);
		
		while(ResSet.next()) {
			linePer++;
			Mass.add(new PersenReportPriceNomen(linePer, ResSet.getString(1), ResSet.getInt(2)));
		}
		
		Conn.close();
		
		return Mass;
		
	}
	
}

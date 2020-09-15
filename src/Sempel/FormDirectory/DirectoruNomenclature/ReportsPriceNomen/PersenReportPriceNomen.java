package Sempel.FormDirectory.DirectoruNomenclature.ReportsPriceNomen;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sempel.FormDirectory.DirectoruNomenclature.PersenNomenclatura;
import javafx.collections.ObservableList;
import sql.Connect;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenReportPriceNomen {

	private Integer PriceNomen;
	private Date DatePrice;
	private String Nomen;
	
	public PersenReportPriceNomen(Integer PriceNomen, Date DatePrice, String Nomen) {
		this.DatePrice = DatePrice;
		this.PriceNomen = PriceNomen;
		this.Nomen = Nomen;
	}

	public PersenReportPriceNomen(Integer PriceNomen, Date DatePrice) {
		this.DatePrice = DatePrice;
		this.PriceNomen = PriceNomen;
	}
	
	public void setDatePrice(Date value) {this.DatePrice = value;}
	public Date getDatePrice() {return this.DatePrice;}
	
	public void setPriceNomen(Integer value) {this.PriceNomen = value;}
	public Integer getPriceNomen() {return this.PriceNomen;}
	
	public void setNomen(String value) {this.Nomen = value;}
	public String getNomen() {return this.Nomen;}
	
	static ObservableList<PersenReportPriceNomen> getMassivReportPrice(ObservableList<PersenReportPriceNomen> mas, SettingConnectSQL connection, String nameNomen) throws SQLException{
		
		SelectPost SelPos = new SelectPost();
		ResultSet ResultSetRep;
		
		ResultSetRep = SelPos.SelectInfoBase(connection.CreatConnect(), "SELECT Nom.name_nomen, RP.price_rp, RP.date_rp\n" + 
				"	FROM public.\"RegistrPrice\" RP, public.\"Nomenclature\" Nom\n" + 
				"	WHERE RP.id_nomen = Nom.id_nomen and Nom.name_nomen = '"+nameNomen+"';");
		
		while (ResultSetRep.next()) {
			mas.add(new PersenReportPriceNomen(ResultSetRep.getInt(2), ResultSetRep.getDate(3)));
		}
		
		return mas;
		
	}
	
}

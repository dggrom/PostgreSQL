package Sempel.FormReports.ReportView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenTableReportView {

	private String view;
	private String nomen;
	private Integer summ;
	
	public String getView() {return view;}
	public void setView(String value) {view = value;}
	
	public String getNomen() {return nomen;}
	public void setNomen(String value) {nomen = value;}
	
	public Integer getSumm() {return summ;}
	public void setSumm(Integer value){summ = value;}
	
	public PersenTableReportView(String view, String nomen, Integer Summ) {
		this.view = view;
		this.nomen = nomen;
		this.summ = Summ;
	}
	
	//Date
	//Nomen
	//View
	static ObservableList<PersenTableReportView> getMassReportView(ObservableList<PersenTableReportView> mass, SettingConnectSQL setCom,
			Boolean[] MassParBoll, String CheckBoxView, String CheckBoxNomen, String DateFirst, String DateLast) throws SQLException{
		
		mass.clear();
		
		Connection con = setCom.CreatConnect();
		SelectPost selPos = new SelectPost();
		
		String SQLtext = "SELECT RM.coming, NOM.name_nomen, RM.sum_regmk\n" + 
				"	FROM public.\"RegistrMoneyKoll\" RM, public.\"Nomenclature\" NOM\n" + 
				"	WHERE NOM.id_nomen = RM.id_nomen ";
		
		if(MassParBoll[1]) {
			SQLtext = SQLtext + "AND NOM.name_nomen = '"+CheckBoxNomen+"' ";
		}
		
		if(MassParBoll[2]) {
			SQLtext = SQLtext + "  ";
		}
		
		if(MassParBoll[3]) {
			SQLtext = SQLtext + "  ";
		}
		
		ResultSet resSet = selPos.SelectInfoBase(con, SQLtext);
		
		while (resSet.next()) {
			mass.add(new PersenTableReportView(resSet.getString(1), resSet.getString(2), resSet.getInt(3)));
		}
		
		con.close();
		
		return mass;
		
	}
}

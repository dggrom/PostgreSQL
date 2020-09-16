package Sempel.FormReports.ReportView;

import java.sql.Connection;
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
	
	static ObservableList<PersenTableReportView> getMassReportView(ObservableList<PersenTableReportView> mass, SettingConnectSQL setCom) throws SQLException{
		
		mass.clear();
		
		Connection con = setCom.CreatConnect();
		SelectPost selPos = new SelectPost();
		String SQLtext = "";
		ResultSet resSet = selPos.SelectInfoBase(con, SQLtext);
		
		while (resSet.next()) {
			mass.add(new PersenTableReportView(resSet.getString(1), resSet.getString(2), resSet.getInt(3)));
		}
		
		con.close();
		
		return mass;
		
	}
}

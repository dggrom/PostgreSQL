package Sempel.FormDocuments.DokConsumption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenDokConsuption {

	private Integer Number;
	private Integer amount;
	private String koment;
	private Integer idKontragent;
	private Integer idView;
	private Date date;
	private Boolean deleted;
	
	PersenDokConsuption(Integer idDokConsu, Integer amount,String koments,Integer idKontragent,Integer idView,Boolean deleted, Date date){
		
		this.Number = idDokConsu;
		this.amount = amount;
		this.koment = koments;
		this.idKontragent = idKontragent;
		this.idView = idView;
		this.deleted = deleted;
		this.date = date;
	}
	
	public void setDate(Date value) {this.date = value;}
	public Date getDate() {return this.date;}
	
	public void setNumber(Integer value) {Number = value;}
	public Integer getNumber() {return Number;}
	
	public void setAmount(Integer value) {amount = value;}
	public Integer getAmount() {return amount;}
	
	public void setKoment(String value) {koment = value;}
	public String getKoment() {return koment;}
	
	public void setIdKontragent(Integer value) {idKontragent = value;}
	public Integer getIdKontragent() {return idKontragent;}
	
	public void setIdView(Integer value) {idView = value;}
	public Integer getIdView() {return idView;}
	
	public void setDeleted(Boolean value) {deleted = value;}
	public Boolean getDeleted() {return deleted;}
	
	public static ObservableList<PersenDokConsuption> getMassivDokConsuption(SettingConnectSQL SetCon, ObservableList<PersenDokConsuption> Mass) throws SQLException {
		
		Connection Connect = SetCon.CreatConnect();
		
		SelectPost SelPos = new SelectPost();
		ResultSet resSet = SelPos.SelectInfoBase(Connect, "SELECT id_dcon, \"SumMoney_dcon\", \"Komment_dcon\", id_kont, id_viewcc, deleted_dcon, date_dcon\n" + 
				"	FROM public.\"DokConsumption\";");
		
		while (resSet.next()) {
			Mass.add(new PersenDokConsuption(resSet.getInt(1), resSet.getInt(2), resSet.getString(3), resSet.getInt(4), resSet.getInt(5), resSet.getBoolean(6), resSet.getDate(7)));
		}		
		
		Connect.close();
		
		return Mass;
		
	}
	
}

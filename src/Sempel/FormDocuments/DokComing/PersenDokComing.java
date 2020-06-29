package Sempel.FormDocuments.DokComing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

import javafx.collections.ObservableList;
import sql.SelectPost;

public class PersenDokComing {
	
	private Integer Number;
	private Integer Amount;
	private String Koment;
	private Integer idKontragent;
	private Integer idView;
	private Date Date;
	private Boolean Deleted;
	
	public PersenDokComing(Integer Number, Integer Amount, String Koment, Boolean Deleted, Integer idKontragent, Integer idView, Date DateDoc){
		this.idKontragent = idKontragent;
		this.idView = idView;
		this.Number = Number;
		this.Amount = Amount;
		this.Koment = Koment;
		this.Deleted = Deleted;
		this.Date = DateDoc;
	}
	
	public void setDate(Date value) {this.Date = value;}
	public Date getDate() {return Date;}

	public void setIdKontragent(Integer Value) {idKontragent = Value;}
	public Integer getIdKontragent() {return idKontragent;}
	
	public void setIdView(Integer Value) {idView = Value;}
	public Integer getIdView() {return idView;}

	public void setNumber(Integer Value) {Number = Value;}
	public Integer getNumber() {return Number;}
	
	public void setAmount(Integer Value) {Amount = Value;}
	public Integer getAmount() {return Amount;}
	
	public void setKoment(String Value) {Koment = Value;}
	public String getKoment() {return Koment;}
	
	public void setDeleted(Boolean Value) {Deleted = Value;}
	public Boolean getDeleted() {return Deleted;}
	
	static ObservableList<PersenDokComing> getMassivPersenDokComing(ObservableList<PersenDokComing> mas, Connection connection) throws SQLException {
		

    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulDokCom;

		ResulDokCom = SelPos.SelectInfoBase(connection, "SELECT id_dcom, \"SumMoney_dcom\", \"Komment_dcom\", id_kont, id_viewcc, deleted_dcom, date_dcom FROM public.\"DokComing\" ORDER BY id_dcom;");

    	while (ResulDokCom.next()) {
    		mas.add(new PersenDokComing(ResulDokCom.getInt(1), ResulDokCom.getInt(2), ResulDokCom.getString(3), ResulDokCom.getBoolean(6), ResulDokCom.getInt(4),ResulDokCom.getInt(5), ResulDokCom.getDate(7)));
    	}
		
		return mas;
		
	}
	
}

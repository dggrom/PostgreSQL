package Sempel.Registr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class RegistrPrice {

	private Integer IdRegprice;
	private Integer IdNomen;
	private Integer Price;
	private LocalDate	Date;
	private Integer IdDocm;
	
	public RegistrPrice(Integer IdRegprice, Integer IdNomen, Integer Price, LocalDate localDate, Integer IdDocm) {
		
		this.IdRegprice = IdRegprice;
		this.IdNomen = IdNomen;
		this.Price = Price;
		this.Date = localDate;
		this.IdDocm = IdDocm;
		
	}
	
	public void setIdRegprice(Integer value) {this.IdRegprice = value;}
	public Integer getIdRegprice() {return this.IdRegprice;}
	
	public void setIdNomen(Integer value) {this.IdNomen = value;}
	public Integer getIdNomen() {return this.IdNomen;}
	
	public void setPrice(Integer value) {this.Price = value;}
	public Integer getPrice() {return this.Price;}
	
	public void setDate(LocalDate value) {this.Date = value;}
	public LocalDate getDate() {return this.Date;}
	
	public void	setIdDocm(Integer value) {this.IdDocm = value;}
	public Integer getIdDocm() {return this.IdDocm;}
	
	public static ObservableList<RegistrPrice> getMassivRegistrPriceDocm(SettingConnectSQL SetCon, ObservableList<RegistrPrice> mass, String IdDcom) throws SQLException{
		
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		String TextSQL = "SELECT id_regprice, id_nomen, price_rp, date_rp, id_docm\n" + 
				"	FROM public.\"RegistrPrice\"\n" + 
				"	WHERE id_docm = "+IdDcom+";";
		
		ResultSet ResSet = SelPos.SelectInfoBase(Conn, TextSQL);
		
		while(ResSet.next()) {
			mass.add(new RegistrPrice(ResSet.getInt(1),ResSet.getInt(2),ResSet.getInt(3),ResSet.getDate(4).toLocalDate(),ResSet.getInt(5)));
		}
		
		Conn.close();
		
		return mass;
		
	}
	
	public static Boolean dellDocLineRegistrPrice(SettingConnectSQL SetCon, Integer IdDocm) throws SQLException {
		
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		String TextSQL = "DELETE FROM public.\"RegistrPrice\"\n" + 
				"	WHERE id_docm = "+IdDocm.toString()+";";
		
		Boolean ResBool = SelPos.UpdateCreatTable(Conn, TextSQL);
		
		Conn.close();
		
		return ResBool;
		
	}
	
	public static void CreatLineRegistrPrice(SettingConnectSQL SetCon, ObservableList<RegistrPrice> mass) throws SQLException {
		
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		
		for(RegistrPrice lineMass : mass) {
			Boolean CreatBool = SelPos.UpdateCreatTable(Conn, "INSERT INTO public.\"RegistrPrice\"(\n" + 
					"	id_nomen, price_rp, date_rp, id_docm)\n" + 
					"	VALUES ("+lineMass.getIdNomen().toString()+", "+lineMass.getPrice().toString()+", '"+lineMass.getDate().toString()+"', "+lineMass.getIdDocm().toString()+");");
		}
		
		Conn.close();
		
	}
	
}

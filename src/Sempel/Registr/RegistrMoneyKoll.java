package Sempel.Registr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class RegistrMoneyKoll {

	private Integer id_regmonkoll;
	private Integer id_nomen;
	private Integer id_docm;
	private Integer id_docn;
	private Boolean Coming;
	private Integer Sum_regmk;
	private Integer Koll_regmk;
	
	public void setId_regmonkoll(Integer value) {this.id_regmonkoll = value;}
	public Integer getId_regmonkoll() {return this.id_regmonkoll;}
	
	public void setId_nomen(Integer value) {this.id_nomen = value;}
	public Integer getId_nomen() {return this.id_nomen;}
	
	public void setId_docm(Integer value) {this.id_docm = value;}
	public Integer getId_docm() {return this.id_docm;}
	
	public void setId_docn(Integer value) {this.id_docn = value;}
	public Integer getId_docn() {return this.id_docn;}
	
	public void setComing(Boolean value) {this.Coming = value;}
	public Boolean getComing() {return this.Coming;}
	
	public void setSum_regmk(Integer value) {this.Sum_regmk = value;}
	public Integer getSum_regmk() {return this.Sum_regmk;}
	
	public void setKoll_regmk(Integer value) {this.Koll_regmk = value;}
	public Integer getKoll_regmk() {return this.Koll_regmk;}
	
	public RegistrMoneyKoll (Integer id_regmonkoll, Integer id_nomen, Integer id_docm, Integer id_docn, Boolean Coming, Integer Sum_regmk, Integer Koll_regkm){
		
		this.id_regmonkoll = id_regmonkoll;
		this.id_nomen = id_nomen;
		this.id_docm = id_docm;
		this.id_docn = id_docn;
		this.Coming = Coming;
		this.Sum_regmk = Sum_regmk;
		this.Koll_regmk = Koll_regkm;
	
	} 
	
	public static Boolean CreatPostsRegistr(SettingConnectSQL SetCon, ObservableList<RegistrMoneyKoll> mass, Integer id_docm, Integer id_docn) throws SQLException {
		
		String TekstSel;
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		
		for(RegistrMoneyKoll lineMass : mass) {
			
			if(id_docm == 0) {
				TekstSel = "INSERT INTO public.\"RegistrMoneyKoll\"(\n" + 
						"	id_nomen, id_dcom, id_dcon, coming, sum_regmk, koll_regmk)\n" + 
						"	VALUES ("+lineMass.getId_nomen().toString()+", 0, "+lineMass.getId_docn().toString()+", false, "+
						lineMass.getSum_regmk().toString()+", "+lineMass.getKoll_regmk().toString()+");";
			} else {
				TekstSel = "INSERT INTO public.\"RegistrMoneyKoll\"(\n" + 
						"	id_nomen, id_dcom, id_dcon, coming, sum_regmk, koll_regmk)\n" + 
						"	VALUES ("+lineMass.getId_nomen().toString()+", "+lineMass.getId_docm().toString()+", 0, true, "+
						lineMass.getSum_regmk().toString()+", "+lineMass.getKoll_regmk().toString()+");";
			}	
		
			Boolean ResSet = SelPos.UpdateCreatTable(Conn, TekstSel);
			
			if(ResSet) {
				return false;
			}
		}
		
		
		Conn.close();
		return true;
		
	}
	
	public static ObservableList<RegistrMoneyKoll> getMassRegistMoneyKoll(SettingConnectSQL SetCon, ObservableList<RegistrMoneyKoll> mass, Integer id_dcom, Integer id_dcon) throws SQLException {
	
		String TekstSel;
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		
		if(id_dcon == 0) {
			TekstSel = "SELECT id_regmonkoll, id_nomen, id_dcom, id_dcon, coming, sum_regmk, koll_regmk\n" + 
					"	FROM public.\"RegistrMoneyKoll\"\n" + 
					"	WHERE id_dcom = "+id_dcom.toString()+";";
		} else {
			TekstSel = "SELECT id_regmonkoll, id_nomen, id_dcom, id_dcon, coming, sum_regmk, koll_regmk\n" + 
					"	FROM public.\"RegistrMoneyKoll\"\n" + 
					"	WHERE id_dcom = "+id_dcon.toString()+";";
		}
		
		ResultSet ResSet = SelPos.SelectInfoBase(Conn, TekstSel);
		
		while(ResSet.next()) {
			mass.add(new RegistrMoneyKoll(ResSet.getInt(1), ResSet.getInt(2), ResSet.getInt(3), ResSet.getInt(4), ResSet.getBoolean(5), ResSet.getInt(6), ResSet.getInt(7)));
		}
		Conn.close();
		
		return mass;
		
	}
	
	public static Boolean DellAllLines(SettingConnectSQL SetCon, Integer id_dcom, Integer id_dcon) throws SQLException{
		
		String TestSel;
		Connection Conn = SetCon.CreatConnect();
		
		SelectPost SelPos = new SelectPost();

		if(id_dcom == 0){
			TestSel = "DELETE FROM public.\"RegistrMoneyKoll\"\n" + 
							"	WHERE id_dcon = "+id_dcon.toString()+";";
		} else {
			TestSel = "DELETE FROM public.\"RegistrMoneyKoll\"\n" + 
					"	WHERE id_dcom = "+id_dcom.toString()+";";
		}
		
		Boolean ResSet = SelPos.UpdateCreatTable(Conn, TestSel);
		Conn.close();
		
		return ResSet;
		
	}

	
}

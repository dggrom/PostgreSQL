package Sempel.Registr;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Observer;
import java.util.Properties;
import java.util.concurrent.Executor;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class RegistrMoneyKoll {

	private static final String TestSel = null;
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
	
	RegistrMoneyKoll (Integer id_regmonkoll, Integer id_nomen, Integer id_docm, Integer id_docn, Boolean Coming, Integer Sum_regmk, Integer Koll_regkm){
		
		this.id_regmonkoll = id_regmonkoll;
		this.id_nomen = id_nomen;
		this.id_docm = id_docm;
		this.id_docn = id_docn;
		this.Coming = Coming;
		this.Sum_regmk = Sum_regmk;
		this.Koll_regmk = Koll_regkm;
	
	} 
	
	static ObservableList<RegistrMoneyKoll> getMassRegistMoneyKoll(ObservableList<RegistrMoneyKoll> mass, Integer id_dcom, Integer id_dcon) {
	
		return mass;
		
	}
	
	static boolean DellAllLines(SettingConnectSQL SetCon, Integer id_dcom, Integer id_dcon) throws SQLException{
		
		String TestSel;
		Connection Com = SetCon.CreatConnect();
		
		SelectPost SelPos = new SelectPost();

		if(id_dcom == 0){
			TestSel = "DELETE FROM public.\"RegistrMoneyKoll\"\n" + 
							"	WHERE id_dcon = "+id_dcon.toString()+";";
		} else {
			TestSel = "DELETE FROM public.\"RegistrMoneyKoll\"\n" + 
					"	WHERE id_dcon = "+id_dcom.toString()+";";
		}
		
		Boolean ResSet = SelPos.UpdateCreatTable(Com, TestSel);
		
		return ResSet;
		
	}

	
}

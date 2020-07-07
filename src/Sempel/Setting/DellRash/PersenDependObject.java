package Sempel.Setting.DellRash;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenDependObject {

	private String DepObject;
	private Integer Nomer;
	
	public PersenDependObject(String DepObject, Integer Nomer) {
		
		this.DepObject = DepObject;
		this.Nomer = Nomer;
		
	}
	
	public void setDepNomer(String value) {this.DepObject = value;}
	public String getDepNomer() {return this.DepObject;}
	
	public void setNomer(Integer value) {this.Nomer = value;}
	public Integer getNomer() {return this.Nomer;}
	
	public static ObservableList<PersenDependObject> getMassivDepObject(SettingConnectSQL SetCon, ObservableList<PersenDependObject> Mass, String TextSQL) throws SQLException{
		
		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		ResultSet ResSet = SelPos.SelectInfoBase(Conn, TextSQL);
		while(ResSet.next()) {
			Mass.add(new PersenDependObject(ResSet.getString(1), ResSet.getInt(2)));
		}
		
		Conn.close();
		return Mass;
		
	}
	
}

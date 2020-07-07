package Sempel.Setting.DellRash;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenObject {

	private String Object;
	private Integer IdObgect;
	
	public PersenObject(String Object, Integer Idobject) {
		this.Object = Object;
		this.IdObgect = Idobject;
	}	
	
	public void setObgect(String value) {this.Object = value;}
	public String getObject() {return this.Object;}
	
	public void setIdObject(Integer value) {this.IdObgect = value;}
	public Integer getIdObject() {return this.IdObgect;}
	
	public static ObservableList<PersenObject> getMassivPersenObject(SettingConnectSQL SetCon, ObservableList<PersenObject> Mass, String TextSQL) throws SQLException{

		Connection Conn = SetCon.CreatConnect();
		SelectPost SelPos = new SelectPost();
		ResultSet ResSet = SelPos.SelectInfoBase(Conn, TextSQL);
		while(ResSet.next()) {
			Mass.add(new PersenObject(ResSet.getString(1), ResSet.getInt(2)));
		}
		Conn.close();
		
		return Mass;
		
	}
	
}

package Sempel.FormDirectory.DirectoruNomenclature;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenNomenclatura {

	private Integer id;
	private String name;
	private boolean deleted;
	
	public PersenNomenclatura(Integer id, String name, boolean deleted) {
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}
	
	public void setId(Integer value) {id = value;}
	public Integer getId() {return id;}
	
	public void setName(String value) {name = value;}
	public String getName() {return name;}
	
	public void setDeleted(boolean value) {deleted = value;}
	public boolean getDeleted() {return deleted;}
	
	public static ObservableList<PersenNomenclatura> getMassivNomen(SettingConnectSQL SetCon, ObservableList<PersenNomenclatura> Mass) throws SQLException {
		
		Connection connection = SetCon.CreatConnect();
		
		SelectPost SelPos = new SelectPost();
		ResultSet ResulSetTable = SelPos.SelectInfoBase(connection, "SELECT id_nomen, name_nomen \n" + 
																		"	FROM public.\"Nomenclature\" Nom \n" +
																		" 	WHERE Nom.deleted_nomen = false;");
		while (ResulSetTable.next()) {
			Mass.add(new PersenNomenclatura(ResulSetTable.getInt(1), ResulSetTable.getString(2),false));
		}
		
		connection.close();
		
		return Mass;
		
	}
	
	public static ObservableList<PersenNomenclatura> getMassivNomenTable(SettingConnectSQL SetCon, ObservableList<PersenNomenclatura> Mass, boolean TrueDell) throws SQLException{
		
		Connection Conn = SetCon.CreatConnect();
		
		String TextSQl;
		if(TrueDell) {
			TextSQl = "SELECT id_nomen, name_nomen, deleted_nomen FROM public.\"Nomenclature\" WHERE deleted_nomen = false ORDER BY id_nomen;";
    	}else {
    		TextSQl = "SELECT id_nomen, name_nomen, deleted_nomen FROM public.\"Nomenclature\" ORDER BY id_nomen;";
    	}
		SelectPost SelPos = new SelectPost();
		ResultSet ResSet = SelPos.SelectInfoBase(Conn,TextSQl);
		
		while(ResSet.next()) {
			Mass.add(new PersenNomenclatura(ResSet.getInt(1), ResSet.getString(2), ResSet.getBoolean(3)));
		}
		
		Conn.close();
		
		return Mass;
	}
	 
	
}

package Sempel.FormDirectory.DirectoryKontragent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.Connect;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenKontragent {

    private Integer id;
    private String name;
    private Boolean deleted;

    public PersenKontragent(Integer id, String name, Boolean deleted){
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    public void setId(Integer value){ id = value;}
    public Integer getId(){ return id;}

    public void setName(String value){ name = value;}
    public String getName(){ return name;}

    public void setDeleted(Boolean value){deleted = value;}
    public Boolean getDeleted(){ return  deleted;}
    
    public static ObservableList<PersenKontragent> getMassivKontragent(SettingConnectSQL SetCon, ObservableList<PersenKontragent> Mass) throws SQLException{
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulComboKon = SelPos.SelectInfoBase(connection, "SELECT id_kont, name_kont FROM public.\"Kontragent\" Kontr WHERE Kontr.deleted_kont = false;");
    	while (ResulComboKon.next()) {
    		Mass.add(new PersenKontragent(ResulComboKon.getInt(1),ResulComboKon.getString(2),false));
		}
    	
    	connection.close();
    	
    	return Mass;
    	
    }
    
    public static ObservableList<PersenKontragent> getMassivkontragentTable(SettingConnectSQL SetCon, ObservableList<PersenKontragent> Mass, Boolean TrueDell) throws SQLException{
    	
    	Connection Conn = SetCon.CreatConnect();
    	
    	String TextSQL;
    	if(TrueDell) {
    		TextSQL = "SELECT id_kont, name_kont, deleted_kont FROM public.\"Kontragent\" WHERE deleted_kont = false ORDER BY id_kont";
        } else {
        	TextSQL = "SELECT id_kont, name_kont, deleted_kont FROM public.\"Kontragent\" ORDER BY id_kont";
        }
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResSet = SelPos.SelectInfoBase(Conn, TextSQL);
    	while(ResSet.next()) {
    		Mass.add(new PersenKontragent(ResSet.getInt(1), ResSet.getString(2), ResSet.getBoolean(3)));
    	}
    	
    	Conn.close();
    	
    	return Mass;
    }
    
}

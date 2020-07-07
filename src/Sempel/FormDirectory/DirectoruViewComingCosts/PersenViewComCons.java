package Sempel.FormDirectory.DirectoruViewComingCosts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class PersenViewComCons {

    private Integer id;
    private String View;
    private Boolean Deleted;

    public PersenViewComCons(Integer id, String View, Boolean Deleted){
        this.id = id;
        this.View = View;
        this.Deleted = Deleted;
    }

    public void setId(Integer value){id = value;}
    public Integer getId(){return id;}

    public void setView(String value){View = value;}
    public String getView(){return View;}

    public void setDeleted(Boolean value){Deleted = value;}
    public Boolean getDeleted(){return Deleted;}

    public static ObservableList<PersenViewComCons> getMassivViewComCons(SettingConnectSQL SetCon, ObservableList<PersenViewComCons> Mass) throws SQLException{
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulComboView = SelPos.SelectInfoBase(connection, "SELECT id_viewcc, name_viewcc FROM public.\"ViewComingConsumption\" ViCom WHERE ViCom.deleted_viewcc = false;");
    	while (ResulComboView.next()) {
    		Mass.add(new PersenViewComCons(ResulComboView.getInt(1), ResulComboView.getString(2),false));
    	}
    	
    	connection.close();
    	
    	return Mass;
    }
    
    public static ObservableList<PersenViewComCons> getMassivviewComConsTable(SettingConnectSQL SetCon, ObservableList<PersenViewComCons> Mass, boolean TrueDell) throws SQLException{
		
    	Connection Conn = SetCon.CreatConnect();
    	
    	String TextSQL;
    	if(TrueDell) {
    		TextSQL = "SELECT id_viewcc, name_viewcc, deleted_viewcc FROM public.\"ViewComingConsumption\" WHERE deleted_viewcc = false ORDER BY id_viewcc;";
        } else {
        	TextSQL = "SELECT id_viewcc, name_viewcc, deleted_viewcc FROM public.\"ViewComingConsumption\" ORDER BY id_viewcc;";
        }
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResSet = SelPos.SelectInfoBase(Conn, TextSQL);
    	
    	while(ResSet.next()) {
    		Mass.add(new PersenViewComCons(ResSet.getInt(1), ResSet.getString(2), ResSet.getBoolean(3)));
    	}
    	
    	Conn.close();
    	return Mass;
    	
    }
    
}

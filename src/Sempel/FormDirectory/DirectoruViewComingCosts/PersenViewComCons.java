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
    
}

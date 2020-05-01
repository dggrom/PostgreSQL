package Sempel.FormDirectory.DirectoruNomenclature;


	import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SelectPost;
import sql.SettingConnectSQL;

	public class ControllerFormNomenclature {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button ButtonRefresh;

	    @FXML
	    private Button ButtonDeleted;

	    @FXML
	    private Button ButtonCreat;
//
	    @FXML
	    private TableView<PersenNomenclatura> TableNomenclature;

	    @FXML
	    private TableColumn<PersenNomenclatura, Integer> TableColumnsID;

	    @FXML
	    private TableColumn<PersenNomenclatura, String> TableColumnsView;

	    @FXML
	    private TableColumn<PersenNomenclatura, Boolean> TableColumnsDeleted;

	    SettingConnectSQL SetCon;
	    private ObservableList<PersenNomenclatura> PersenNomen = FXCollections.observableArrayList();
	    
	    @FXML
	    void initialize() throws SQLException {
	    	
	    	refrashTableNomenclature();
	    	
	    	TableColumnsID.setCellValueFactory(new PropertyValueFactory<PersenNomenclatura, Integer>("id"));
	    	TableColumnsView.setCellValueFactory(new PropertyValueFactory<PersenNomenclatura, String>("name"));
	    	TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenNomenclatura, Boolean>("deleted"));
	    	
	    	TableNomenclature.setItems(PersenNomen);

	    }
	    
	    public ControllerFormNomenclature(SettingConnectSQL SetCon) {
	    	this.SetCon = SetCon;
	    }
	    
	    private void refrashTableNomenclature() throws SQLException {
	    	PersenNomen.clear();
	    	
	    	SelectPost selPost = new SelectPost();
	    	String SQLtext = "SELECT id_nomen, name_nomen, deleted_nomen FROM public.\"Nomenclature\";";
	    	Connection con = SetCon.CreatConnect();
	    	ResultSet rezSet = selPost.SelectInfoBase(con, SQLtext);
	    	
	    	while (rezSet.next()) {
	    		PersenNomen.add(new PersenNomenclatura(rezSet.getInt("id_nomen"), rezSet.getString("name_nomen"), rezSet.getBoolean("deleted_nomen")));
	    	}
	    	
	    	con.close();
	    	
		}
	}

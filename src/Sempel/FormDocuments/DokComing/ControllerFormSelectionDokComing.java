package Sempel.FormDocuments.DokComing;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

import Sempel.FormDirectory.DirectoryKontragent.CreatUpdateDirectory.ControllerDeletCreatDirectory;
import Sempel.MainForm.ControllerMainForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class ControllerFormSelectionDokComing {

	SettingConnectSQL SetCon;
	private ObservableList<PersenDokComing> DokComing = FXCollections.observableArrayList();
	
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

    @FXML
    private TableView<PersenDokComing> TableNomenclature;

    @FXML
    private TableColumn<PersenDokComing, String> TableColumnsNumber;

    @FXML
    private TableColumn<PersenDokComing, String> TableColumnsAmount;

    @FXML
    private TableColumn<PersenDokComing, String> TableColumnsKoment;

    @FXML
    void initialize() throws SQLException {

    	refreshTableDocComing();
    	
    	TableColumnsAmount.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Amount"));
    	TableColumnsKoment.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Koment"));
    	TableColumnsNumber.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Number"));
    	
    	TableNomenclature.setItems(DokComing);
    }
    
    public ControllerFormSelectionDokComing (SettingConnectSQL SetCon) {
    	this.SetCon = SetCon;
    }   
    
    public void refreshTableDocComing() throws SQLException {
    	
    	DokComing.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulDokCom = SelPos.SelectInfoBase(connection, "SELECT id_dcom, \"SumMoney_dcom\", \"Komment_dcom\" FROM public.\"DokComing\";");
    	while (ResulDokCom.next()) {
    		DokComing.add(new PersenDokComing(ResulDokCom.getString(1), ResulDokCom.getString(2), ResulDokCom.getString(3)));
    	}
    	connection.close();
    	
    }
    
}

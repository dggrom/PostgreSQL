package Sempel.FormDocuments.DokComing;
import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Sempel.FormDocuments.DokComing.CreatUpdateDokComing.ControllerFormDokComingCreatUpdate;
import Sempel.MainForm.ControllerMainForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    private TableColumn<PersenDokComing, Boolean> TableColumnsDeleted;

    @FXML
    void initialize() throws SQLException {

    	refreshTableDocComing();
    	
    	TableColumnsAmount.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Amount"));
    	TableColumnsKoment.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Koment"));
    	TableColumnsNumber.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Number"));
    	TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenDokComing, Boolean>("Deleted"));
    	
    	TableNomenclature.setItems(DokComing);
    	
    	ButtonCreat.setOnAction(event -> {
    			try {
					OpenFormRefreshCreat(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
    	});
    	
    	ButtonRefresh.setOnAction(event -> {
    			try {
					OpenFormRefreshCreat(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
    	});
    	
    }
    
    public ControllerFormSelectionDokComing (SettingConnectSQL SetCon) {
    	this.SetCon = SetCon;
    }   
    
    private void refreshTableDocComing() throws SQLException {
    	
    	DokComing.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulDokCom = SelPos.SelectInfoBase(connection, "SELECT id_dcom, \"SumMoney_dcom\", \"Komment_dcom\", id_kont, id_viewcc, deleted_dcom FROM public.\"DokComing\";");
    	while (ResulDokCom.next()) {
    		DokComing.add(new PersenDokComing(ResulDokCom.getString(1), ResulDokCom.getString(2), ResulDokCom.getString(3), ResulDokCom.getBoolean(6), ResulDokCom.getString(4),ResulDokCom.getString(5)));
    	}
    	connection.close();
    	
    }
    
    
	private void OpenFormRefreshCreat(boolean updateDokCom) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource("/Sempel/FormDocuments/DokComing/CreatUpdateDokComing/FormDokComingCreatUpdate.fxml"));
    
    	PersenDokComing PersSRT = TableNomenclature.getSelectionModel().getSelectedItem();
    	
    	if (updateDokCom) {
    		ControllerFormDokComingCreatUpdate DokComingCreatRefrash = new ControllerFormDokComingCreatUpdate(updateDokCom,SetCon, PersSRT);
    		fxmlLoader.setController(DokComingCreatRefrash);
    	} else {
    		ControllerFormDokComingCreatUpdate DokComingCreatRefrash = new ControllerFormDokComingCreatUpdate(updateDokCom,SetCon);
    		fxmlLoader.setController(DokComingCreatRefrash);
    	}
    	
    	Parent root1 = fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));
    	stage.setOnHidden(event -> {
    		try {
				refreshTableDocComing();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}
    	});
    	stage.setOnCloseRequest(event -> {
    		try {
				refreshTableDocComing();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	});
    	stage.show();
    	
    }
}

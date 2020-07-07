package Sempel.FormDocuments.DokComing;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Alert;
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
    private TableView<PersenDokComing> TableDocKoming; //TableNomenclature;

    @FXML
    private TableColumn<PersenDokComing, String> TableColumnsNumber;

    @FXML
    private TableColumn<PersenDokComing, String> TableColumnsAmount;

    @FXML
    private TableColumn<PersenDokComing, String> TableColumnsKoment;

    @FXML
    private TableColumn<PersenDokComing, Date> TableColumnsDate;
    
    @FXML
    private TableColumn<PersenDokComing, Boolean> TableColumnsDeleted;

    @FXML
    void initialize() throws SQLException {

    	refreshTableDocComing();
    	initTable();
    	
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
    	
    	ButtonDeleted.setOnAction(event -> {
    		try {
    			Boolean preBool = TableDocKoming.getSelectionModel().getSelectedItem().getDeleted();
				if(deletedDokuent(preBool)) {
					Alert AlertForm = new Alert(Alert.AlertType.CONFIRMATION);
					if(preBool) {
						AlertForm.setHeaderText("Документ помечен на удаления");
						AlertForm.setTitle("Документ помечен на удаления");
					}else {
						AlertForm.setHeaderText("Пометку удаления убрана");
						AlertForm.setTitle("Пометку удаления убрана");
					}
				};
				refreshTableDocComing();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	});
    	
    }
    
    private void initTable() {
    	
    	TableColumnsAmount.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Amount"));
    	TableColumnsKoment.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Koment"));
    	TableColumnsNumber.setCellValueFactory(new PropertyValueFactory<PersenDokComing, String>("Number"));
    	TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenDokComing, Boolean>("Deleted"));
    	TableColumnsDate.setCellValueFactory(new PropertyValueFactory<PersenDokComing, Date>("Date"));
    	
    	TableDocKoming.setItems(DokComing);
    	
    }
    
    public ControllerFormSelectionDokComing (SettingConnectSQL SetCon) {
    	this.SetCon = SetCon;
    }   
    
    private Boolean deletedDokuent(Boolean currentDel) throws SQLException {
    	
    	String newBoolDel;
    	if (currentDel) {newBoolDel = "false";} else {newBoolDel = "true";}
    	
    	Connection Conn = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	Boolean updateDell = SelPos.UpdateCreatTable(Conn, "UPDATE public.\"DokComing\"\n" + 
										    			"	SET deleted_dcom = "+newBoolDel+" \n" + 
										    			"	WHERE id_dcom = "+TableDocKoming.getSelectionModel().getSelectedItem().getNumber()+";");
    	return updateDell;
    }
    
    private void refreshTableDocComing() throws SQLException {
    	
    	DokComing.clear();
    	
		Connection connection = SetCon.CreatConnect();
		
		DokComing = PersenDokComing.getMassivPersenDokComing(DokComing, connection);
		
    	connection.close();
    	
    }
    
	private void OpenFormRefreshCreat(boolean updateDokCom) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource("/Sempel/FormDocuments/DokComing/CreatUpdateDokComing/FormDokComingCreatUpdate.fxml"));
    
    	PersenDokComing PersSRT = TableDocKoming.getSelectionModel().getSelectedItem();
    	
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
    	stage.toFront();
    	
    }
}

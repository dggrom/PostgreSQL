package Sempel.FormDocuments.DokConsumption;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Sempel.FormDocuments.DokConsumption.CreatUpdateDokconsumption.ControllerFormDokConsumption;
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
import sql.SettingConnectSQL;

public class ControllerFormSelectDokConsumption {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonUpdate;

    @FXML
    private Button ButtonDeleted;

    @FXML
    private Button ButtonCreat;

    @FXML
    private TableView<PersenDokConsuption> TableCunsu;

    @FXML
    private TableColumn<PersenDokConsuption, Integer> TableColumnsNumber;

    @FXML
    private TableColumn<PersenDokConsuption, Integer> TableColumnsAmount;

    @FXML
    private TableColumn<PersenDokConsuption, String> TableColumnsKoment;
    
    @FXML
    private TableColumn<PersenDokConsuption, Boolean> TableColumnsDeleted;
    
    SettingConnectSQL SetCon;
    ObservableList<PersenDokConsuption> TableForm = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {

    	refreshTableForm();
    	
    	TableColumnsNumber.setCellValueFactory(new PropertyValueFactory<PersenDokConsuption, Integer>("Number"));
    	TableColumnsAmount.setCellValueFactory(new PropertyValueFactory<PersenDokConsuption, Integer>("Amount"));
    	TableColumnsKoment.setCellValueFactory(new PropertyValueFactory<PersenDokConsuption, String>("Koment"));
    	TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenDokConsuption, Boolean>("Deleted"));	
    	
    	TableCunsu.setItems(TableForm);
    	
    	ButtonCreat.setOnAction(event->{
    		try {
				openFormUpdateCreat(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	});
    	
    }
    
    private void openFormUpdateCreat(Boolean updateCreat) throws IOException {
    	
    	FXMLLoader fxmLoad =new FXMLLoader(ControllerMainForm.class.getResource("/Sempel/FormDocuments/DokConsumption/CreatUpdateDokconsumption/FormDokConsumptionCreatUpdate.fxml"));
    	
    	PersenDokConsuption persSRT = TableCunsu.getSelectionModel().getSelectedItem();
    	
    	if(!updateCreat) {
    		ControllerFormDokConsumption DocConsuCreatUpdate = new ControllerFormDokConsumption(updateCreat, SetCon, new PersenDokConsuption(0, 0, "", 0, 0, false));
    		fxmLoad.setController(DocConsuCreatUpdate);
    	} else {
    		ControllerFormDokConsumption DocConsuCreatUpdate = new ControllerFormDokConsumption(updateCreat, SetCon, persSRT);
    		fxmLoad.setController(DocConsuCreatUpdate);
    	}
    	
    	Parent root1 = fxmLoad.load();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));
    	stage.setOnHidden(event -> {
    		try {
				refreshTableForm();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	});
    	
    	stage.setOnCloseRequest(event -> {
    		try {
				refreshTableForm();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	});
    	stage.show();
    	stage.toFront();
    	
    }
    
    private void refreshTableForm() throws SQLException {
    	
    	TableForm.clear();
    	TableForm = PersenDokConsuption.getMassivDokConsuption(SetCon, TableForm);
    	
    }
    
    public ControllerFormSelectDokConsumption(SettingConnectSQL SetCon){
    	
    	this.SetCon = SetCon;
    
    }
    
}

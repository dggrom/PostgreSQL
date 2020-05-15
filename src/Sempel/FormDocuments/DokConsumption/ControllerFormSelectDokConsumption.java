package Sempel.FormDocuments.DokConsumption;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Sempel.FormDocuments.DokComing.PersenDokComing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    	
    }
    
    
    
    private void refreshTableForm() throws SQLException {
    	
    	TableForm.clear();
    	TableForm = PersenDokConsuption.getMassivDokConsuption(SetCon, TableForm);
    	
    }
    
    public ControllerFormSelectDokConsumption(SettingConnectSQL SetCon){
    	
    	this.SetCon = SetCon;
    
    }
    
}

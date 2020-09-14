package Sempel.FormDirectory.DirectoruNomenclature.ReportsPriceNomen;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Sempel.FormDirectory.DirectoruNomenclature.PersenNomenclatura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SettingConnectSQL;

public class ControllerReportPriceNomen {

	     @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TableView<PersenReportPriceNomen> TableNomenclature;

	    @FXML
	    private TableColumn<PersenReportPriceNomen, Integer> TableColumnsPriceNomen;

	    @FXML
	    private TableColumn<PersenReportPriceNomen, Date> TableColumnsDateNomen;

	    private SettingConnectSQL SetCon;
	    private String NomenRequest;
	    private ObservableList<PersenReportPriceNomen> PersRepNom = FXCollections.observableArrayList();
	    
	    @FXML
	    void initialize() throws SQLException {
	       
	    	refreshTableReport();
	    	//
	    	TableColumnsPriceNomen.setCellValueFactory(new PropertyValueFactory<PersenReportPriceNomen, Integer>("PriceNomen"));
	    	TableColumnsDateNomen.setCellValueFactory(new PropertyValueFactory<PersenReportPriceNomen, Date>("DatePrice"));	
	    	
	    	TableNomenclature.setItems(PersRepNom);
	    	
	    }
	    
	    private void refreshTableReport() throws SQLException {
	    
	    	PersRepNom.clear();
	    	PersRepNom = PersenReportPriceNomen.getMassivReportPrice(PersRepNom, SetCon, NomenRequest);
	    	
	    }
	
	    public ControllerReportPriceNomen(String NomenReq, SettingConnectSQL setCon) {
	    	this.NomenRequest = NomenReq;
	    	this.SetCon = setCon;
	    }
}

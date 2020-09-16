package Sempel.FormReports.ReportView;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Sempel.FormDirectory.DirectoruNomenclature.PersenNomenclatura;
import Sempel.FormDirectory.DirectoruNomenclature.ReportsPriceNomen.PersenReportPriceNomen;
import Sempel.FormDirectory.DirectoruViewComingCosts.PersenViewComCons;
import Sempel.FormDirectory.DirectoryKontragent.PersenKontragent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import sql.SettingConnectSQL;

public class ControllerReportView {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TableView<PersenTableReportView> TableReports;

	    @FXML
	    private TableColumn<PersenTableReportView, String> TableColumnReportsView;

	    @FXML
	    private TableColumn<PersenTableReportView, String> TableColumnReportsNomen;

	    @FXML
	    private TableColumn<PersenTableReportView, Integer> TableColumnReportsSumm;

	    @FXML
	    private DatePicker DateFerst;

	    @FXML
	    private DatePicker DateLast;

	    @FXML
	    private ComboBox<PersenViewComCons> ComboBoxView;

	    @FXML
	    private ComboBox<PersenNomenclatura> ComboBoxNomen;

	    @FXML
	    private Button ButtonReport;

	    SettingConnectSQL SetCon;
	    ObservableList<PersenTableReportView> TableReportView = FXCollections.observableArrayList();
	    ObservableList<PersenNomenclatura> ComboBoxNomenclature = FXCollections.observableArrayList();
	    ObservableList<PersenViewComCons> ComboBoxViewComCons = FXCollections.observableArrayList();
	    
	    
	    @FXML
	    void initialize() throws SQLException {
	
	    	refrashComboBoxNomenclature();
	    	refrashComboBoxViewComCons();
	    	refrashTableReportView();
	    	initComboBox();
	    	initTable();
	    	DateConverter();
	    	
	    }
	    
	    private void DateConverter() {
	    
	    	StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {

	    		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    		
				@Override
				public String toString(LocalDate object) {
					if(object != null) {
						return dateFormatter.format(object);
					} else {
						return "";
					}
				}

				@Override
				public LocalDate fromString(String string) {
					if(string != null && !string.isEmpty()) {
						return LocalDate.parse(string,dateFormatter);
					} else {
						return null;
					}
				}
			};
			
			DateFerst.setConverter(converter);
			DateFerst.setPromptText("yyyy-MM-dd");
			DateLast.setConverter(converter);
			DateLast.setPromptText("yyyy-MM-dd");
	    }
	    
	    private void initTable() {
	    	
	    	TableColumnReportsNomen.setCellValueFactory(new PropertyValueFactory<PersenTableReportView, String>("Номенклатура"));
	    	TableColumnReportsSumm.setCellValueFactory(new PropertyValueFactory<PersenTableReportView, Integer>("Сумма"));
	    	TableColumnReportsView.setCellValueFactory(new PropertyValueFactory<PersenTableReportView, String>("Вид"));
	    
	    }
	    
	    
	    private void initComboBox() {
	    	
	    	ComboBoxNomen.getItems().setAll(ComboBoxNomenclature);
	    	ComboBoxNomen.setConverter(new StringConverter<PersenNomenclatura>() {
				
				@Override
				public String toString(PersenNomenclatura object) {
					if(object != null) {
						return object.getName();
					} else {
						return null;
						}
					}
				
				@Override
				public PersenNomenclatura fromString(String string) {
					return ComboBoxNomen.getSelectionModel().getSelectedItem();
				}
			});
	    	
	    	ComboBoxView.getItems().setAll(ComboBoxViewComCons);
	    	ComboBoxView.setConverter(new StringConverter<PersenViewComCons>() {
				
				@Override
				public String toString(PersenViewComCons object) {
					if(object != null) {
						return object.getView();
					} else {
						return "";
					}
				}
				
				@Override
				public PersenViewComCons fromString(String string) {
					return ComboBoxView.getSelectionModel().getSelectedItem();
				}
			});
	    }

	    private void refrashComboBoxNomenclature() throws SQLException {
	    	ComboBoxNomenclature.clear();
	    	ComboBoxNomenclature = PersenNomenclatura.getMassivNomen(SetCon, ComboBoxNomenclature);
	    }
	    
	    private void refrashComboBoxViewComCons() throws SQLException {
	    	ComboBoxViewComCons.clear();
	    	ComboBoxViewComCons = PersenViewComCons.getMassivViewComCons(SetCon, ComboBoxViewComCons);	
	    }
	    
	    private void refrashTableReportView() throws SQLException {
	    	TableReportView.clear();
	    	TableReportView = PersenTableReportView.getMassReportView(TableReportView, SetCon);
	    }
	    
	    public ControllerReportView(SettingConnectSQL SetCon) {
	    	this.SetCon = SetCon;
	    }
}

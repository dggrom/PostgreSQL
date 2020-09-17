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
import javafx.scene.control.CheckBox;
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
	    
	    @FXML
	    private CheckBox CheckBoxDate;

	    @FXML
	    private CheckBox CheckBoxView;

	    @FXML
	    private CheckBox CheckBoxNomen;

	    SettingConnectSQL SetCon;
	    ObservableList<PersenTableReportView> TableReportView = FXCollections.observableArrayList();
	    ObservableList<PersenNomenclatura> ComboBoxNomenclature = FXCollections.observableArrayList();
	    ObservableList<PersenViewComCons> ComboBoxViewComCons = FXCollections.observableArrayList();
	    
	    
	    @FXML
	    void initialize() throws SQLException {
	
	    	CheckBoxDate.setSelected(true);
	    	CheckBoxNomen.setSelected(true);
	    	CheckBoxView.setSelected(true);
	    	DateFerst.setDisable(true);
			DateLast.setDisable(true);
			ComboBoxNomen.setDisable(true);
			ComboBoxView.setDisable(true);
	    	
	    	refrashComboBoxNomenclature();
	    	refrashComboBoxViewComCons();
	    	refrashTableReportView();
	    	initComboBox();
	    	initTable();
	    	DateConverter();
	    	CheckBoxEvents();
	    	
	    	ButtonReport.setOnAction(event -> {
	    		
	    		try {
					refrashTableReportView();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    		
	    	});
	    	
	    }
	    
	    private void CheckBoxEvents() {
	    	
	    	CheckBoxDate.setOnAction(event -> {
	    		if(CheckBoxDate.isSelected()) {
	    			DateFerst.setDisable(true);
	    			DateLast.setDisable(true);
	    		} else {
	    			DateFerst.setDisable(false);
	    			DateLast.setDisable(false);
	    		}
	    	});
	    	
	    	CheckBoxNomen.setOnAction(event -> {
	    		if(CheckBoxNomen.isSelected()) {
	    			ComboBoxNomen.setDisable(true);
	    		} else {
	    			ComboBoxNomen.setDisable(false);
	    		}
	    	});
	    	
	    	CheckBoxView.setOnAction(event -> {
	    		if(CheckBoxView.isSelected()) {
	    			ComboBoxView.setDisable(true);
	    		} else {
	    			ComboBoxView.setDisable(false);
	    		}
	    	});
	    	
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
	    	
	    	TableColumnReportsNomen.setCellValueFactory(new PropertyValueFactory<PersenTableReportView, String>("nomen"));
	    	TableColumnReportsSumm.setCellValueFactory(new PropertyValueFactory<PersenTableReportView, Integer>("summ"));
	    	TableColumnReportsView.setCellValueFactory(new PropertyValueFactory<PersenTableReportView, String>("view"));
	    
	    	TableReports.setItems(TableReportView);
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
	    	
	    	Boolean[] MassParBool= new Boolean[3];
	    	MassParBool[1] = CheckBoxDate.isSelected();
	    	MassParBool[2] = CheckBoxNomen.isSelected();
	    	MassParBool[3] = CheckBoxView.isSelected();
	    	
	    	TableReportView.clear();
	    	TableReportView = PersenTableReportView.getMassReportView(TableReportView, SetCon, MassParBool, CheckBoxView.getText(), CheckBoxNomen.getText(),
	    			DateFerst.getValue().toString(), DateLast.getValue().toString());
	    }
	    
	    public ControllerReportView(SettingConnectSQL SetCon) {
	    	this.SetCon = SetCon;
	    }
}

package Sempel.FormDocuments.DokConsumption.CreatUpdateDokconsumption;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.runtime.output.ForkXmlOutput;

import Sempel.FormDirectory.DirectoruNomenclature.PersenNomenclatura;
import Sempel.FormDirectory.DirectoruViewComingCosts.PersenViewComCons;
import Sempel.FormDirectory.DirectoryKontragent.PersenKontragent;
import Sempel.FormDocuments.DokConsumption.PersenDokConsuption;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sql.SettingConnectSQL;

public class ControllerFormDokConsumption {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ArchPnelForm;

    @FXML
    private Label LabelDokN;

    @FXML
    private TextField EditComments;

    @FXML
    private Label LabelEditComments;

    @FXML
    private Label LabelNumberDoc;

    @FXML
    private TextField AmountDoc;

    @FXML
    private Label LabelAmount;

    @FXML
    private TableView<PersenTableMoneyConsu> TableManeyDoc;

    @FXML
    private TableColumn<PersenTableMoneyConsu, Integer> NLTMD;

    @FXML
    private TableColumn<PersenTableMoneyConsu, PersenNomenclatura> NomenTMD;

    @FXML
    private TableColumn<PersenTableMoneyConsu, Integer> KolTMD;

    @FXML
    private TableColumn<PersenTableMoneyConsu, Integer> SumTMD;

    @FXML
    private Button ButtonSave;

    @FXML
    private Label LabelDokN1;

    @FXML
    private ComboBox<PersenKontragent> ComboBoxKontragent;

    @FXML
    private Label LabelKontragent;

    @FXML
    private ComboBox<PersenViewComCons> ComboBoxViewComCos;

    @FXML
    private Label LabelViewComingCos;

    @FXML
    private Button ButtonTableADD;

    @FXML
    private Button ButtonTableDel;

    private String nomerDok;
    private Boolean updateDok;
    private SettingConnectSQL SetCon;
    private Integer numberLinaTable;
    private PersenDokConsuption persUpdateDok;
    private ObservableList<PersenTableMoneyConsu> TableMoney = FXCollections.observableArrayList();
    private ObservableList<PersenKontragent> ComboKontr = FXCollections.observableArrayList();
    private ObservableList<PersenNomenclatura> ComboNomen = FXCollections.observableArrayList();
    private ObservableList<PersenViewComCons> ComboComCons = FXCollections.observableArrayList();


    @FXML
    void initialize() throws SQLException {
    
    	refrashTableMoney();
    	refrashComboComCons();
    	refrashComboKontr();
    	refrashComboName();
    
    	if(!updateDok) {
    		
    	}
    	
    }
    
    private void refrashTableMoney() throws SQLException {
    	TableMoney.clear();
    	TableMoney = PersenTableMoneyConsu.getMassivTableMoneyCouns(SetCon, TableMoney);
    }
    
    private void refrashComboComCons() throws SQLException {
    	ComboComCons.clear();
    	ComboComCons = PersenViewComCons.getMassivViewComCons(SetCon, ComboComCons);
    }
    
    private void refrashComboName() throws SQLException {
    	ComboNomen.clear();
    	ComboNomen = PersenNomenclatura.getMassivNomen(SetCon, ComboNomen);
    }
    
    private void refrashComboKontr() throws SQLException {
    	ComboKontr.clear();
    	ComboKontr = PersenKontragent.getMassivKontragent(SetCon, ComboKontr);
    }
    
    public ControllerFormDokConsumption(Boolean updateDok, SettingConnectSQL SetCon, PersenDokConsuption persUpdateDok){
    	
		if(!updateDok) {
    		this.nomerDok = "Номер не задан";
    		this.updateDok = updateDok;
    		this.SetCon = SetCon;
    		this.numberLinaTable = 0;
    		this.persUpdateDok = persUpdateDok;
    	} else {
    		this.nomerDok = persUpdateDok.getNumber().toString();
    		this.updateDok = updateDok;
    		this.SetCon = SetCon;
    		this.numberLinaTable = 0;
    		this.persUpdateDok = persUpdateDok;
    	}
    	
    }
    
}


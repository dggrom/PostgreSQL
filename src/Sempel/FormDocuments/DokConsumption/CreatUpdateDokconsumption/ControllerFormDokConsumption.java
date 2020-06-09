package Sempel.FormDocuments.DokConsumption.CreatUpdateDokconsumption;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.runtime.output.ForkXmlOutput;

import Sempel.FormDirectory.DirectoruNomenclature.PersenNomenclatura;
import Sempel.FormDirectory.DirectoruViewComingCosts.PersenViewComCons;
import Sempel.FormDirectory.DirectoryKontragent.PersenKontragent;
import Sempel.FormDocuments.DokComing.CreatUpdateDokComing.PersenTableMoney;
import Sempel.FormDocuments.DokConsumption.PersenDokConsuption;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
    
    	numberLinaTable = 0;
    	
    	refrashComboComCons();
    	refrashComboKontr();
    	refrashComboName();
    
    	if(updateDok) {
    		refrashTableMoney();
    		AmountDoc.setText(persUpdateDok.getAmount().toString());
    		EditComments.setText(persUpdateDok.getKoment());
    	} else {
    		numberLinaTable++;
    		TableMoney.clear();
    		TableMoney.add(new PersenTableMoneyConsu(numberLinaTable, Integer.valueOf(0), "", Integer.valueOf(0)));
    	}
    	
    	ComboBoxKontragent.getItems().setAll(ComboKontr);
    	ComboBoxKontragent.setConverter(new StringConverter<PersenKontragent>() {
			
			@Override
			public String toString(PersenKontragent object) {
				if(object != null) {
					return object.getName();
				} else {
					return "";
				}
			}
			
			@Override
			public PersenKontragent fromString(String string) {
				return ComboBoxKontragent.getSelectionModel().getSelectedItem();
			}
		});
    	
    	ComboBoxViewComCos.getItems().setAll(ComboComCons);
    	ComboBoxViewComCos.setConverter(new StringConverter<PersenViewComCons>() {

			@Override
			public String toString(PersenViewComCons object) {
				if (object != null) {
					return object.getView();
				} else {
					return "";
				}
			}

			@Override
			public PersenViewComCons fromString(String string) {
				return ComboBoxViewComCos.getSelectionModel().getSelectedItem(); 
			}
		});
    	
    	if (updateDok) {
    		for (PersenKontragent x : ComboKontr) {
    			if(x.getId() == persUpdateDok.getIdKontragent()) {
    				ComboBoxKontragent.setValue(x);
    			}
    		}	
    		for (PersenViewComCons y : ComboComCons) {
    			if (y.getId() == persUpdateDok.getIdView()) {
    				ComboBoxViewComCos.setValue(y);
    			}
    		}
    	}
    	
    	//Уникальный строки
    	NLTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoneyConsu, Integer>("NL"));
    	
    	//Номенклатура
    	NomenTMD.setCellValueFactory(new Callback<CellDataFeatures<PersenTableMoneyConsu,PersenNomenclatura>, ObservableValue<PersenNomenclatura>>() {
			
			@Override
			public ObservableValue<PersenNomenclatura> call(CellDataFeatures<PersenTableMoneyConsu, PersenNomenclatura> param) {
				PersenTableMoneyConsu PersNomTab = param.getValue();
				PersenNomenclatura NewNomen = getIdByName(PersNomTab.getNomen());
				return new SimpleObjectProperty<PersenNomenclatura>(NewNomen);
			}
		});
    }
    
    private void refrashTableMoney() throws SQLException {
    	TableMoney.clear();
    	TableMoney = PersenTableMoneyConsu.getMassivTableMoneyCouns(SetCon, TableMoney, nomerDok);
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
    		this.nomerDok = "0";
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
    
    public PersenNomenclatura getIdByName(String IdNomen) {
		
		int lineST = ComboNomen.size() - 1;
		
		 for (int x = 0; x <= lineST; x++) {
	         PersenNomenclatura checkLine = ComboNomen.get(x);  
			 if (checkLine.getName().equals(IdNomen)) {
	               return checkLine;
	           }
	       }
	       return null;
	} 
}


package Sempel.FormDocuments.DokConsumption.CreatUpdateDokconsumption;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


import Sempel.FormDirectory.DirectoruNomenclature.PersenNomenclatura;
import Sempel.FormDirectory.DirectoruViewComingCosts.PersenViewComCons;
import Sempel.FormDirectory.DirectoryKontragent.PersenKontragent;
import Sempel.FormDocuments.DokConsumption.PersenDokConsuption;
import Sempel.Registr.RegistrMoneyKoll;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import sql.SelectPost;
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
    private TableColumn<PersenTableMoneyConsu, Integer> PriceTMD;
    
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
    
    @FXML
    private DatePicker DateDoc;

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
    	DateDokConverter();
    	
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
    		TableMoney.add(new PersenTableMoneyConsu(numberLinaTable, Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0)));
    	}
    	
    	initComboBox();
    	initTable();
    	
    	LabelNumberDoc.setText(nomerDok);
    	
    	ButtonTableADD.setOnAction(event -> {
    		numberLinaTable++;
    		TableMoney.add(new PersenTableMoneyConsu(numberLinaTable, Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0)));
    	});
    	ButtonTableDel.setOnAction(event ->{
    		int tablePositionNow = TableManeyDoc.getSelectionModel().getSelectedItem().getNL() - 1;
    		TableMoney.remove(tablePositionNow);
    		numberLinaTable--;
    		recountTableMoneyNumbe();
    	});
    	
    	ButtonSave.setOnAction(event -> {
    		
    		if(!recountTableManeySum()) {
    			Alert FormAlert = new Alert(AlertType.CONFIRMATION);
    			
    			FormAlert.setTitle("Необходимо еперсчитать итоговую сумму");
    			FormAlert.setHeaderText("Итоговая сумма не соходится, пересчитать ?");
    			
    			Optional<ButtonType> optionalAlert = FormAlert.showAndWait();
    		
    			if(optionalAlert.get() == ButtonType.OK) {
    					AmountDoc.setText(recountStrTableManeySum());
    			}
    		}
    		
    		LabelNumberDoc.setText(nomerDok);
    		
			try {
				if(updateCreatDocComing()) {
					Stage stageForm = (Stage) ButtonSave.getScene().getWindow();
					stageForm.close();
				} else {
					Alert alertForm = new Alert(Alert.AlertType.ERROR);
					if(updateDok) {
						alertForm.setTitle("Ошибка перезаписи документа");
						alertForm.setHeaderText("Ошибка перезаписи документа");	
					} else {
						alertForm.setTitle("Ошибка создания нового документа");
						alertForm.setHeaderText("Ошибка создания нового документа");
					}
					alertForm.show();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

    	});
    }

    private void initTable() {
    	
    	//Уникальный строки
    	NLTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoneyConsu, Integer>("NL"));
    	
    	//Номенклатура
    	NomenTMD.setCellValueFactory(new Callback<CellDataFeatures<PersenTableMoneyConsu,PersenNomenclatura>, ObservableValue<PersenNomenclatura>>() {
			
			@Override
			public ObservableValue<PersenNomenclatura> call(CellDataFeatures<PersenTableMoneyConsu, PersenNomenclatura> param) {
				PersenTableMoneyConsu PersNomTab = param.getValue();
				PersenNomenclatura NewNomen = getIdByName(PersNomTab.getNomen());
				return new SimpleObjectProperty<PersenNomenclatura>(NewNomen);
			};
		});
    	
    	NomenTMD.setCellFactory(ComboBoxTableCell.forTableColumn(new StringConverter<PersenNomenclatura>() {


			@Override
			public String toString(PersenNomenclatura object) {
				if (object != null) {
					return object.getName();
				} else {
					return "";
				}
			}

			@Override
			public PersenNomenclatura fromString(String string) {
				return getIdByName(TableManeyDoc.getSelectionModel().getSelectedItem().getNomen());
			}
    		
    	},ComboNomen));
    	
    	NomenTMD.setOnEditCommit((CellEditEvent<PersenTableMoneyConsu,PersenNomenclatura> event) -> {
    		
    		TablePosition<PersenTableMoneyConsu,PersenNomenclatura> pos = event.getTablePosition();
  
    		PersenNomenclatura NewNomen = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoneyConsu CurrentPer = event.getTableView().getItems().get(rowPos);
    		
    		CurrentPer.setNomen(NewNomen.getName());
    		
    	});
    	
    	//Столбец колличество
    	KolTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoneyConsu, Integer>("Koll"));
    	KolTMD.setCellFactory(TextFieldTableCell.<PersenTableMoneyConsu, Integer>forTableColumn(new IntegerStringConverter()));
    	KolTMD.setOnEditCommit((CellEditEvent<PersenTableMoneyConsu,Integer>event)->{
    		TablePosition<PersenTableMoneyConsu, Integer> pos = event.getTablePosition();
    		
    		Integer newKoll = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoneyConsu CurrentPersen = event.getTableView().getItems().get(rowPos);
    		CurrentPersen.setKoll(newKoll);
    		CurrentPersen.setSum(newKoll * CurrentPersen.getPrice());
    		TableManeyDoc.refresh();
    	});
    	
    	//Столбец с ценой;
    	PriceTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoneyConsu, Integer>("price"));
    	PriceTMD.setCellFactory(TextFieldTableCell.<PersenTableMoneyConsu,Integer>forTableColumn(new IntegerStringConverter()));
    	PriceTMD.setOnEditCommit((CellEditEvent<PersenTableMoneyConsu,Integer>event)->{
    		TablePosition<PersenTableMoneyConsu, Integer> pos = event.getTablePosition();
    		
    		Integer newValue = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoneyConsu CurrentRow = event.getTableView().getItems().get(rowPos);
    		CurrentRow.setPice(newValue);
    		CurrentRow.setSum(newValue * CurrentRow.getKoll());
    		TableManeyDoc.refresh();
    	});
    	
    	//Столбец с Суммами
    	SumTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoneyConsu, Integer>("Sum"));
    	SumTMD.setCellFactory(TextFieldTableCell.<PersenTableMoneyConsu, Integer>forTableColumn(new IntegerStringConverter()));
    	SumTMD.setOnEditCommit((CellEditEvent<PersenTableMoneyConsu, Integer>event) -> {
    		TablePosition<PersenTableMoneyConsu,Integer> pos = event.getTablePosition();
    	
    		Integer newSum = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoneyConsu CurrentPersen = event.getTableView().getItems().get(rowPos);
    		CurrentPersen.setSum(newSum);
    	} );
    	
    	TableManeyDoc.setItems(TableMoney);
    	TableManeyDoc.setEditable(true);
    	
    }
    
    private void initComboBox() {
    	
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
    	
    }
    
    private void DateDokConverter() {
    	
    	StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
    			
    		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		
			@Override
			public String toString(LocalDate object) {
				
				if (object != null) {
					return dateFormatter.format(object);
				} else {
					return "";
				}
				
			}

			@Override
			public LocalDate fromString(String string) {
				if(string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		
		DateDoc.setConverter(converter);
		DateDoc.setPromptText("yyyy-MM-dd");
		DateDoc.setValue(persUpdateDok.getDate().toLocalDate());
		
    }

    private boolean updateCreatDocComing() throws SQLException {
		
    	boolean ResultCreatUpdate;
    	boolean boolUpCre = true;

    	String nomerKont = ComboBoxKontragent.getSelectionModel().getSelectedItem().getId().toString();
    	String nomerView = ComboBoxViewComCos.getSelectionModel().getSelectedItem().getId().toString();
    	
    	Connection connection = SetCon.CreatConnect();
    	SelectPost SelPos = new SelectPost();
    	if (updateDok) {
    		ResultCreatUpdate = SelPos.UpdateCreatTable(connection, "UPDATE public.\"DokConsumption\"\n" + 
    				"	SET id_dcon = "+persUpdateDok.getNumber()+", \"SumMoney_dcon\" = '"+AmountDoc.getText()+"',"+
    				"   \"Komment_dcon\" = '"+EditComments.getText()+"', id_kont = "+nomerKont+", id_viewcc = "+nomerView+
    				",date_dcon = '"+DateDoc.getValue().toString()+"' , deleted_dcon = "+persUpdateDok.getDeleted().toString()+"" + 
    				"	WHERE id_dcon = "+persUpdateDok.getNumber()+";");
    	} else {
    		ResultCreatUpdate = SelPos.UpdateCreatTable(connection, "INSERT INTO public.\"DokConsumption\"(\n" + 
    				"	\"SumMoney_dcon\", \"Komment_dcon\", id_kont, id_viewcc, deleted_dcon, date_dcon)\n" + 
    				"	VALUES ( '"+AmountDoc.getText()+"', '"+EditComments.getText()+"', "+nomerKont+", "+nomerView+", false, '"+DateDoc.getValue().toString()+"');");
    	}
    	
    	//Если создали или обновили доку, работаем с ТЧ
    	if(!ResultCreatUpdate) {
    		
    		ResultSet newNomSelPos = SelPos.SelectInfoBase(connection, "SELECT id_dcon \n" + 
													    				"	FROM public.\"DokConsumption\"\n" + 
													    				"	ORDER BY id_dcon DESC\n" + 
													    				"	limit 1;");
    		
    		if (newNomSelPos.next() && !updateDok) {
    			nomerDok = newNomSelPos.getString(1);
    		} else {
    			nomerDok = persUpdateDok.getNumber().toString();
    		}
    		
    		//Удалить все старые строки
    		Boolean resTableMoney = SelPos.UpdateCreatTable(connection, "DELETE FROM public.\"DokConsumptionTableMoney\"\n" + 
																		"	WHERE id_dcon = "+nomerDok+";");
    		
    		//Заполнить по новым данным ТЧ.
    		for(int x=0; x<numberLinaTable; x++) {
    			PersenTableMoneyConsu lineTableMoneyConsu = TableManeyDoc.getItems().get(x);
    			boolean resTableCreat = SelPos.UpdateCreatTable(connection, "INSERT INTO public.\"DokConsumptionTableMoney\"(\n" + 
    					"	id_dcon, id_nomen, kol_dcontm, sum_dcontm, price_dcontm)\n" + 
    					"	VALUES ("+nomerDok+", "+getIdByName(lineTableMoneyConsu.getNomen()).getId().toString()+", "+lineTableMoneyConsu.getKoll().toString()+","+lineTableMoneyConsu.getSum().toString()+","+lineTableMoneyConsu.getPrice().toString()+");");

    		}
    		
    		updateRegistrMoneyKoll();
    		
    	} else {
    		boolUpCre = false;
    	}
    	
    	connection.close();
    	
    	return boolUpCre;
    	
    }
    
    private void updateRegistrMoneyKoll() {
    	
    	ObservableList<RegistrMoneyKoll> MassReg = FXCollections.observableArrayList();
    	
    	for(PersenTableMoneyConsu lineTable : TableMoney) {
    		MassReg.add(new RegistrMoneyKoll(Integer.valueOf(0), getIdByName(lineTable.getNomen()).getId(), Integer.valueOf(0), Integer.valueOf(nomerDok), true, lineTable.getSum() * -1, lineTable.getKoll() * -1));
    	}
    	
    	try {
			Boolean DellLineRegistr = RegistrMoneyKoll.DellAllLines(SetCon, 0, Integer.valueOf(nomerDok));
			if(!DellLineRegistr) {
				Boolean UpdateLineRegistr = RegistrMoneyKoll.CreatPostsRegistr(SetCon, MassReg, Integer.valueOf(0), Integer.valueOf(nomerDok));
			}
    	} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public String recountStrTableManeySum() {

    	Integer zeroTableManey = 0;
    	
    	for (PersenTableMoneyConsu x : TableMoney) {
    		zeroTableManey += (Integer.valueOf(x.getSum())); 
    	}
    	
    	return String.valueOf(zeroTableManey);
    	
    }
    
    public Boolean recountTableManeySum() {

    	Integer zeroTableManey = 0;
    	
    	for (PersenTableMoneyConsu x : TableMoney) {
    		zeroTableManey += (Integer.valueOf(x.getSum())); 
    	}
    	
    	if(String.valueOf(zeroTableManey).equals(AmountDoc.getText())) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    public void recountTableMoneyNumbe() {
    	
    	for (int x = 0; x < numberLinaTable; x++) {
    		PersenTableMoneyConsu recountLine = TableManeyDoc.getItems().get(x);
    		recountLine.setNL(x + 1);
    	}
    	
    }
    
    private void refrashTableMoney() throws SQLException {
    	TableMoney.clear();
    	TableMoney = PersenTableMoneyConsu.getMassivTableMoneyCouns(SetCon, TableMoney, nomerDok);
    	if (TableMoney.size() == 0) {
    		numberLinaTable++;
    		TableMoney.add(new PersenTableMoneyConsu(numberLinaTable, Integer.valueOf(0), "", Integer.valueOf(0),Integer.valueOf(0)));
    	} else {
    		numberLinaTable = TableMoney.size();
    	}
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


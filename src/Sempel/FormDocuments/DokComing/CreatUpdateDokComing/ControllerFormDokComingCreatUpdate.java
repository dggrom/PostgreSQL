package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


import Sempel.FormDocuments.DokComing.PersenDokComing;
import Sempel.Registr.RegistrMoneyKoll;
import Sempel.Registr.RegistrPrice;
import Sempel.FormDirectory.DirectoruViewComingCosts.*;
import Sempel.FormDirectory.DirectoruNomenclature.*;
import Sempel.FormDirectory.DirectoryKontragent.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class ControllerFormDokComingCreatUpdate {

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
    private Label LabelDateDoc;

    @FXML
    private TextField AmountDoc;
    
    @FXML
    private DatePicker DateDoc;

    @FXML
    private Label LabelAmount;

    @FXML
    private TableView<PersenTableMoney> TableManeyDoc;

    @FXML
    private TableColumn<PersenTableMoney, Integer> NLTMD;

    @FXML
    private TableColumn<PersenTableMoney, PersenNomenclatura> NomenTMD;

    @FXML
    private TableColumn<PersenTableMoney, Integer> KolTMD;

    @FXML
    private TableColumn<PersenTableMoney, Integer> PriceTMD;
    
    @FXML
    private TableColumn<PersenTableMoney, Integer> SumTMD;

    @FXML
    private Button ButtonSave;
    
    @FXML
    private Button ButtonSaveAndClose;

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

    private String NomDokCreat;
    private boolean updateDok;
    private SettingConnectSQL SetCon;
    private Integer NumberTableLine;
    private PersenDokComing persUpdateDok;
    private ObservableList<PersenKontragent> CombKont = FXCollections.observableArrayList();
    private ObservableList<PersenViewComCons> CombView = FXCollections.observableArrayList();
    private ObservableList<PersenTableMoney> TableMoney = FXCollections.observableArrayList();
    private ObservableList<PersenNomenclatura> TableMoneyNomen = FXCollections.observableArrayList();
    
    @FXML
    void initialize() throws SQLException {
    	
    	DateDocConvert();
    	NumberTableLine = 0;
    	
    	refreshComboKontragent();
    	refreshComboView();
    	refreshNomenPersen();
    	if (NomDokCreat != "New dokument") {
    		refreshTableMoney();
    		AmountDoc.setText(persUpdateDok.getAmount().toString());
    		EditComments.setText(persUpdateDok.getKoment());
    		DateDoc.setValue(persUpdateDok.getDate().toLocalDate());
    		} else {
    			NumberTableLine++;
    			TableMoney.clear();
    			TableMoney.add(new PersenTableMoney(NumberTableLine, Integer.valueOf(0), "",Integer.valueOf(0), Integer.valueOf(0)));
    		}
    	
    	initComboBox();
    	initTable();
    	
    	
    	//Создания дркумента
    	ButtonSave.setOnAction(event ->{
    		
    		if(!recountTableManeySum()) {
    			Alert FormAlert = new Alert(AlertType.CONFIRMATION);
    			
    			FormAlert.setTitle("Необходимо пересчитать итоговую сумму");
    			FormAlert.setHeaderText("Итоговая сумма не сходится, пересчитать ?");
    			
    			Optional<ButtonType> optionAlert = FormAlert.showAndWait();
    			
    			if(optionAlert.get() == ButtonType.OK){
    				AmountDoc.setText(recountStrTableManeySum());
    			}
    			
    		}
    		
    		try {
    			
				LabelNumberDoc.setText(NomDokCreat);
	    		
				if(updateCreatDocComing()) {
					Alert aletForm = new Alert(Alert.AlertType.INFORMATION);
					aletForm.setTitle("Документ записан");
					aletForm.setHeaderText("Документ записан");
					aletForm.show();
				} else {
					Alert aletForm = new Alert(Alert.AlertType.ERROR);
					if(updateDok) {
						aletForm.setTitle("Ошибка перезаписи документа");
						aletForm.setHeaderText("Ошибка перезаписи документа");	
					}else {
						aletForm.setTitle("Ошибка создания нового документа");
						aletForm.setHeaderText("Ошибка создания нового документа");
					}
					aletForm.show();
				}
				
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    		    		
    	});
    	
    	ButtonSaveAndClose.setOnAction(event ->{
    		
    		if(!recountTableManeySum()) {
    			Alert FormAlert = new Alert(AlertType.CONFIRMATION);
    			
    			FormAlert.setTitle("Необходимо пересчитать итоговую сумму");
    			FormAlert.setHeaderText("Итоговая сумма не сходится, пересчитать ?");
    			
    			Optional<ButtonType> optionAlert = FormAlert.showAndWait();
    			
    			if(optionAlert.get() == ButtonType.OK){
    				AmountDoc.setText(recountStrTableManeySum());
    			}
    			
    		}
    		
    		try {
    			
				LabelNumberDoc.setText(NomDokCreat);
	    		
				if(updateCreatDocComing()) {
					Stage stageForm = (Stage) ButtonSave.getScene().getWindow();
					stageForm.close();
				} else {
					Alert aletForm = new Alert(Alert.AlertType.ERROR);
					if(updateDok) {
						aletForm.setTitle("Ошибка перезаписи документа");
						aletForm.setHeaderText("Ошибка перезаписи документа");	
					}else {
						aletForm.setTitle("Ошибка создания нового документа");
						aletForm.setHeaderText("Ошибка создания нового документа");
					}
					aletForm.show();
				}
				
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    		    		
    	});
    }

    private void initTable() {
    	
    	//Id строки
    	NLTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, Integer>("NL"));
    	
    	//Столбец с номенклатурой
    	NomenTMD.setCellValueFactory(new Callback<CellDataFeatures<PersenTableMoney, PersenNomenclatura>, ObservableValue<PersenNomenclatura>>() {
	
			@Override
			public ObservableValue<PersenNomenclatura> call(CellDataFeatures<PersenTableMoney, PersenNomenclatura> param) {
				PersenTableMoney PersNomTab = param.getValue();
				PersenNomenclatura NewNomentable = getIdByName(PersNomTab.getNomen());
				return new SimpleObjectProperty<PersenNomenclatura>(NewNomentable);
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
				return getIdByName(TableManeyDoc.getSelectionModel().getSelectedItem().getNomen());//new PersenNomenklatureTable("", "");
			}
    		
    	},TableMoneyNomen));
    	
    	NomenTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, PersenNomenclatura> event) -> {
    	
    		TablePosition<PersenTableMoney, PersenNomenclatura> pos = event.getTablePosition();
    		
    		PersenNomenclatura NewNomen = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoney CurrentPers = event.getTableView().getItems().get(rowPos);
    		
    		CurrentPers.setNomen(NewNomen.getName());
    		
    	});
    	
    	//Столбец с Количеством
    	KolTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, Integer>("Koll"));
    	KolTMD.setCellFactory(TextFieldTableCell.<PersenTableMoney, Integer>forTableColumn(new IntegerStringConverter()));
    	KolTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, Integer>event) -> {
    		TablePosition<PersenTableMoney, Integer> pos = event.getTablePosition();
    		
    		Integer newKoll = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoney CurrentPersen = event.getTableView().getItems().get(rowPos);
    		CurrentPersen.setKoll(newKoll);
    		CurrentPersen.setSum(newKoll * CurrentPersen.getPrice());
    		TableManeyDoc.refresh();
    	});
    	
    	//Работаем со сталбцом цены;
    	PriceTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney,Integer>("Price"));
    	PriceTMD.setCellFactory(TextFieldTableCell.<PersenTableMoney,Integer>forTableColumn(new IntegerStringConverter()));
    	PriceTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, Integer>event) ->{
    		TablePosition<PersenTableMoney, Integer> pos = event.getTablePosition();
    		
    		Integer newValue = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoney CurrentPersen = event.getTableView().getItems().get(rowPos);
    		CurrentPersen.setPrice(newValue);
    		CurrentPersen.setSum(newValue * CurrentPersen.getKoll());
    		TableManeyDoc.refresh();
    	});

    	//Столбец с Суммами
    	SumTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, Integer>("Sum"));
    	SumTMD.setCellFactory(TextFieldTableCell.<PersenTableMoney, Integer>forTableColumn(new IntegerStringConverter()));
    	SumTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, Integer>event) -> {
    		TablePosition<PersenTableMoney, Integer> pos = event.getTablePosition();
    		
    		Integer newSum = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoney CurrentPersen = event.getTableView().getItems().get(rowPos);
    		CurrentPersen.setSum(newSum);
    	});
    	
    	TableManeyDoc.setItems(TableMoney);
    	TableManeyDoc.setEditable(true);
    	
    	LabelNumberDoc.setText(NomDokCreat);
    	
    	//Кнопки для таблиц 
    	ButtonTableADD.setOnAction(event -> {
    		NumberTableLine++;
    		TableMoney.add(new PersenTableMoney(NumberTableLine, Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0)));
    	});
    	ButtonTableDel.setOnAction(event -> {
    		int tablePositionNow = TableManeyDoc.getSelectionModel().getSelectedItem().getNL() - 1;
    		TableMoney.remove(tablePositionNow);
    		NumberTableLine--;
    		recountTableMoneyNumbe();
    	});
    	
    }
    
    private void initComboBox() {
    	
    	ComboBoxKontragent.getItems().setAll(CombKont);
    	ComboBoxKontragent.setConverter(new StringConverter<PersenKontragent>() {

			@Override
			public String toString(PersenKontragent object) {
				if (object != null) { 
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
    	
    	ComboBoxViewComCos.getItems().setAll(CombView);
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
    	
    	if (NomDokCreat != "New dokument") {
    		for (PersenKontragent x : CombKont) {
    			if(x.getId() == persUpdateDok.getIdKontragent()) { 
    				ComboBoxKontragent.setValue(x);
    				}
    		}
    		for (PersenViewComCons y : CombView) {
    			if(y.getId() == persUpdateDok.getIdView()) { 
    				ComboBoxViewComCos.setValue(y);
    				}
    		}
    	}
    	
    }
    
    private void DateDocConvert() {
    	
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
    
    public Boolean recountTableManeySum() {

    	Integer zeroTableManey = 0;
    	
    	for (PersenTableMoney x : TableMoney) {
    		zeroTableManey += (Integer.valueOf(x.getSum())); 
    	}
    	
    	if(String.valueOf(zeroTableManey).equals(AmountDoc.getText())) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    public String recountStrTableManeySum() {

    	Integer zeroTableManey = 0;
    	
    	for (PersenTableMoney x : TableMoney) {
    		zeroTableManey += (Integer.valueOf(x.getSum())); 
    	}
    	
    	return String.valueOf(zeroTableManey);
    	
    }
    
    public PersenNomenclatura getIdByName(String IdNomen) {
		
		int lineST = TableMoneyNomen.size() - 1;
		
		 for (int x = 0; x <= lineST; x++) {
	         PersenNomenclatura checkLine = TableMoneyNomen.get(x);  
			 if (checkLine.getName().equals(IdNomen)) {
	               return checkLine;
	           }
	       }
	       return null;
	} 
    
    public boolean updateCreatDocComing() throws SQLException {
    	
    	boolean ResultCreatUpdate;
    	boolean boolUpCre = true;
    	
    	//Создаем сам документ
    	String nomerkont = ComboBoxKontragent.getSelectionModel().getSelectedItem().getId().toString();
    	String nomerView = ComboBoxViewComCos.getSelectionModel().getSelectedItem().getId().toString();
    	
    	Connection connection = SetCon.CreatConnect();
    	SelectPost SelPost = new SelectPost();
    	if(updateDok) {
    		ResultCreatUpdate = SelPost.UpdateCreatTable(connection, "UPDATE public.\"DokComing\"\n" + 
    				"	SET id_dcom = "+persUpdateDok.getNumber()+", \"SumMoney_dcom\" = '"+AmountDoc.getText()+"',"+
    				"   \"Komment_dcom\" = '"+EditComments.getText()+"', id_kont = "+nomerkont+", id_viewcc = "+nomerView+
    				",date_dcom = '"+DateDoc.getValue().toString()+"', deleted_dcom = "+persUpdateDok.getDeleted().toString()+"" + 
    				"	WHERE id_dcom = "+persUpdateDok.getNumber()+";");
    	} else {
    		ResultCreatUpdate = SelPost.UpdateCreatTable(connection, "INSERT INTO public.\"DokComing\"(\n" + 
    				"	\"SumMoney_dcom\", \"Komment_dcom\", id_kont, id_viewcc, deleted_dcom, date_dcom)\n" + 
    				"	VALUES ( '"+AmountDoc.getText()+"', '"+EditComments.getText()+"', "+nomerkont+", "+nomerView+", false, '"+DateDoc.getValue().toString()+"');");
    	}
    		
    	//Если документ содался, начинаем работать с ТЧ
    	if(!ResultCreatUpdate) {	
    		
    		ResultSet newNomSelPost = SelPost.SelectInfoBase(connection, "SELECT id_dcom \n" + 
														    				"	FROM public.\"DokComing\"\n" + 
														    				"	ORDER BY id_dcom DESC\n" + 
														    				"	limit 1;");
    		
    		if (newNomSelPost.next() && !updateDok) {
    			NomDokCreat = newNomSelPost.getString(1);
    		} else {
    			NomDokCreat = persUpdateDok.getNumber().toString();
    		}
    		
    		//Удаляем все старые строки ТЧ
	    	Boolean resTableMoney = SelPost.UpdateCreatTable(connection, "DELETE FROM public.\"DokComingTableMoney\"\n" + 
	    																		"	WHERE id_dcom = "+NomDokCreat+";");
    		
    		//Заполняем по новой строки ТЧ.
    		for(int x=0; x < NumberTableLine; x++) {
    			PersenTableMoney lineTable = TableManeyDoc.getItems().get(x);
    			boolean resTableCreat = SelPost.UpdateCreatTable(connection, "INSERT INTO public.\"DokComingTableMoney\"(\n" + 
														    					"	id_dcom, id_nomen, kol_dcomtm, sum_docmtm, price_docmtm)\n" + 
														    					"	VALUES ("+NomDokCreat+", "+getIdByName(lineTable.getNomen()).getId().toString()+", "+lineTable.getKoll().toString()+","+lineTable.getSum().toString()+","+lineTable.getPrice().toString()+");");
    		
    		}
    		
    		updateRegistrMoneyKoll();
    		updateCreatRegistrPrice();
    		
    	} else {
    		boolUpCre = false;
    	}	
    	
    	connection.close();
    	
    	return boolUpCre;
    	
    }

    public void updateCreatRegistrPrice() {
    	
    	ObservableList<RegistrPrice> RegMass = FXCollections.observableArrayList();
    	
    	try {
			
    		for(PersenTableMoney linePer : TableMoney) {
    			RegMass.add(new RegistrPrice(0, getIdByName(linePer.getNomen()).getId(), linePer.getPrice(), DateDoc.getValue(), Integer.valueOf(NomDokCreat)));
    		}
    		
    		RegistrPrice.dellDocLineRegistrPrice(SetCon, Integer.valueOf(NomDokCreat));
			RegistrPrice.CreatLineRegistrPrice(SetCon, RegMass);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void updateRegistrMoneyKoll() {
    	
    	ObservableList<RegistrMoneyKoll> RegMass = FXCollections.observableArrayList();
		
    	for(PersenTableMoney lineTable : TableMoney) {
    		RegMass.add(new RegistrMoneyKoll(0, getIdByName(lineTable.getNomen()).getId(), Integer.valueOf(NomDokCreat), Integer.valueOf(0), true, lineTable.getSum(), lineTable.getKoll()));
    	}
    	
    	try {
			Boolean DellRegistrLines = RegistrMoneyKoll.DellAllLines(SetCon, Integer.valueOf(NomDokCreat), Integer.valueOf(0));
		
			if(!DellRegistrLines) {
	    		Boolean CreatLineRegistr = RegistrMoneyKoll.CreatPostsRegistr(SetCon, RegMass, Integer.valueOf(NomDokCreat), 0);
	    	}
			
    	} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void recountTableMoneyNumbe() {
    	
    	for (int x = 0; x < NumberTableLine; x++) {
    		PersenTableMoney recountLine = TableManeyDoc.getItems().get(x);
    		recountLine.setNL(x + 1);
    	}
    	
    }
    
 	public void refreshTableMoney() throws SQLException {
    	
    	TableMoney.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	TableMoney = PersenTableMoney.getMassivPersenTableMoney(TableMoney, connection, NumberTableLine, NomDokCreat.toString());
    	
    	if(TableMoney.size() == 0) {
    		NumberTableLine++;
    		TableMoney.add(new PersenTableMoney(NumberTableLine, Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0)));
    	} else {
    		NumberTableLine = TableMoney.size();
    	}
    	
    	connection.close();
    	
    }
       
	public void refreshComboView() throws SQLException {
    	
    	CombView.clear();
    	CombView = PersenViewComCons.getMassivViewComCons(SetCon, CombView);

    }
    
	public void refreshComboKontragent() throws SQLException {
    	
    	CombKont.clear();
    	CombKont = PersenKontragent.getMassivKontragent(SetCon, CombKont); 
    
    }
	
	public void refreshNomenPersen() throws SQLException {
		
		TableMoneyNomen.clear();
		TableMoneyNomen = PersenNomenclatura.getMassivNomen(SetCon, TableMoneyNomen);
		
	}
    
	public ControllerFormDokComingCreatUpdate (boolean updateDok, SettingConnectSQL SetCon, PersenDokComing PersDok) {
		this.NomDokCreat = PersDok.getNumber().toString();
		this.updateDok = updateDok;
		this.SetCon = SetCon;
		this.persUpdateDok = PersDok;
	}

	public ControllerFormDokComingCreatUpdate (boolean updateDok, SettingConnectSQL SetCon) {
		Integer emptyParam = 0;
		this.NomDokCreat = "New dokument";
		this.updateDok = updateDok;    		
		this.SetCon = SetCon;
		this.persUpdateDok = new PersenDokComing(Integer.valueOf(0), Integer.valueOf(0), "", false,emptyParam,emptyParam, Date.valueOf(LocalDate.now()));
	}
	
}

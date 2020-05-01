package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.StyledEditorKit.BoldAction;

import Sempel.FormDocuments.DokComing.PersenDokComing;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private TextField AmountDoc;

    @FXML
    private Label LabelAmount;

    @FXML
    private TableView<PersenTableMoney> TableManeyDoc;

    @FXML
    private TableColumn<PersenTableMoney, Integer> NLTMD;

    @FXML
    private TableColumn<PersenTableMoney, PersenNomenklatureTable> NomenTMD;

    @FXML
    private TableColumn<PersenTableMoney, String> KolTMD;

    @FXML
    private Button ButtonSave;

    @FXML
    private Label LabelDokN1;

    @FXML
    private ComboBox<PersenComboKontragent> ComboBoxKontragent;

    @FXML
    private Label LabelKontragent;

    @FXML
    private ComboBox<PersenComboView> ComboBoxViewComCos;

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
    private ObservableList<PersenComboKontragent> CombKont = FXCollections.observableArrayList();
    private ObservableList<PersenComboView> CombView = FXCollections.observableArrayList();
    private ObservableList<PersenTableMoney> TableMoney = FXCollections.observableArrayList();
    private ObservableList<PersenNomenklatureTable> TableMoneyNomen = FXCollections.observableArrayList();
    
    @FXML
    void initialize() throws SQLException {
    	
    	NumberTableLine = 0;
    	
    	refreshComboKontragent();
    	refreshComboView();
    	refreshNomenPersen();
    	if (NomDokCreat != "New dokument") {
    		refreshTableMoney();
    		AmountDoc.setText(persUpdateDok.getAmount());
    		EditComments.setText(persUpdateDok.getKoment());
    		} else {
    			NumberTableLine++;
    			TableMoney.clear();
    			TableMoney.add(new PersenTableMoney(NumberTableLine, "0", ""));
    		}
    	
    	ComboBoxKontragent.getItems().setAll(CombKont);
    	ComboBoxKontragent.setConverter(new StringConverter<PersenComboKontragent>() {

			@Override
			public String toString(PersenComboKontragent object) {
				if (object != null) { 
					return object.getName();
					} else {
						return "";
					}
			}

			@Override
			public PersenComboKontragent fromString(String string) {
				return ComboBoxKontragent.getSelectionModel().getSelectedItem();
			}
        });
    	
    	ComboBoxViewComCos.getItems().setAll(CombView);
    	ComboBoxViewComCos.setConverter(new StringConverter<PersenComboView>() {

			@Override
			public String toString(PersenComboView object) {
				if (object != null) {	
					return object.getName();
				} else {
					return "";
				}
			}

			@Override
			public PersenComboView fromString(String string) {
				return ComboBoxViewComCos.getSelectionModel().getSelectedItem();
			}
    		
		});
    	
    	if (NomDokCreat != "New dokument") {
    		for (PersenComboKontragent x : CombKont) {
    			if(x.getCode().equals(persUpdateDok.getIdKontragent())) { 
    				ComboBoxKontragent.setValue(x);
    				}
    		}
    		for (PersenComboView y : CombView) {
    			if(y.getCode().equals(persUpdateDok.getIdView())) { 
    				ComboBoxViewComCos.setValue(y);
    				}
    		}
    	}
    	
    	//Id строки
    	NLTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, Integer>("NL"));
    	
    	//Столбец с номенклатурой
    	NomenTMD.setCellValueFactory(new Callback<CellDataFeatures<PersenTableMoney, PersenNomenklatureTable>, ObservableValue<PersenNomenklatureTable>>() {
	
			@Override
			public ObservableValue<PersenNomenklatureTable> call(CellDataFeatures<PersenTableMoney, PersenNomenklatureTable> param) {
				PersenTableMoney PersNomTab = param.getValue();
				PersenNomenklatureTable NewNomentable = getIdByName(PersNomTab.getNomen());
				return new SimpleObjectProperty<PersenNomenklatureTable>(NewNomentable);
			};
			
		});
    	
    	NomenTMD.setCellFactory(ComboBoxTableCell.forTableColumn(new StringConverter<PersenNomenklatureTable>() {

			@Override
			public String toString(PersenNomenklatureTable object) {
				if (object != null) {	
					return object.getNameNomen();
				} else {
					return "";
				}
			}

			@Override
			public PersenNomenklatureTable fromString(String string) {
				return getIdByName(TableManeyDoc.getSelectionModel().getSelectedItem().getNomen());//new PersenNomenklatureTable("", "");
			}
    		
    	},TableMoneyNomen));
    	
    	NomenTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, PersenNomenklatureTable> event) -> {
    	
    		TablePosition<PersenTableMoney, PersenNomenklatureTable> pos = event.getTablePosition();
    		
    		PersenNomenklatureTable NewNomen = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoney CurrentPers = event.getTableView().getItems().get(rowPos);
    		
    		CurrentPers.setNomen(NewNomen.getNameNomen());
    		
    	});
    	
    	//Столбец с Количеством
    	KolTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, String>("Koll"));
    	KolTMD.setCellFactory(TextFieldTableCell.<PersenTableMoney>forTableColumn());
    	KolTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, String>event) -> {
    		TablePosition<PersenTableMoney, String> pos = event.getTablePosition();
    		
    		String newKoll = event.getNewValue();
    		
    		int rowPos = pos.getRow();
    		PersenTableMoney CurrentPersen = event.getTableView().getItems().get(rowPos);
    		CurrentPersen.setKoll(newKoll);
    	});
    	
    	TableManeyDoc.setItems(TableMoney);
    	TableManeyDoc.setEditable(true);
    	
    	LabelNumberDoc.setText(NomDokCreat);
    	
    	//Кнопки для таблиц 
    	ButtonTableADD.setOnAction(event -> {
    		NumberTableLine++;
    		TableMoney.add(new PersenTableMoney(NumberTableLine, "0", ""));
    	});
    	ButtonTableDel.setOnAction(event -> {
    		int tablePositionNow = TableManeyDoc.getSelectionModel().getSelectedItem().getNL() - 1;
    		TableMoney.remove(tablePositionNow);
    		NumberTableLine--;
    		recountTableMoneyNumbe();
    	});
    	
    	//Создания дркумента
    	ButtonSave.setOnAction(event ->{
    		
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		    		
    	});
    }

    public Boolean recountTableManeySum() {

    	int zeroTableManey = 0;
    	
    	for (PersenTableMoney x : TableMoney) {
    		zeroTableManey = zeroTableManey + Integer.valueOf(x.getKoll()); 
    	}
    	
    	if(zeroTableManey == Integer.valueOf(AmountDoc.getText())) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    public PersenNomenklatureTable getIdByName(String IdNomen) {
		
		int lineST = TableMoneyNomen.size() - 1;
		
		 for (int x = 0; x <= lineST; x++) {
	         PersenNomenklatureTable checkLine = TableMoneyNomen.get(x);  
			 if (checkLine.getNameNomen().equals(IdNomen)) {
	               return checkLine;
	           }
	       }
	       return null;
	} 
    
    public boolean updateCreatDocComing() throws SQLException {
    	
    	boolean ResultCreatUpdate;
    	boolean boolUpCre = true;
    	
    	//Создаем сам документ
    	String nomerkont = ComboBoxKontragent.getSelectionModel().getSelectedItem().getCode();
    	String nomerView = ComboBoxViewComCos.getSelectionModel().getSelectedItem().getCode();
    	
    	Connection connection = SetCon.CreatConnect();
    	SelectPost SelPost = new SelectPost();
    	if(updateDok) {
    		ResultCreatUpdate = SelPost.UpdateCreatTable(connection, "UPDATE public.\"DokComing\"\n" + 
    				"	SET id_dcom = "+persUpdateDok.getNumber()+", \"SumMoney_dcom\" = '"+AmountDoc.getText()+"',"+
    				"   \"Komment_dcom\" = '"+EditComments.getText()+"', id_kont = "+nomerkont+", id_viewcc = "+nomerView+
    				", deleted_dcom = "+persUpdateDok.getDeleted().toString()+"" + 
    				"	WHERE id_dcom = "+persUpdateDok.getNumber()+";");
    	} else {
    		ResultCreatUpdate = SelPost.UpdateCreatTable(connection, "INSERT INTO public.\"DokComing\"(\n" + 
    				"	\"SumMoney_dcom\", \"Komment_dcom\", id_kont, id_viewcc, deleted_dcom)\n" + 
    				"	VALUES ( '"+AmountDoc.getText()+"', '"+EditComments.getText()+"', "+nomerkont+", "+nomerView+", false);");
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
    			NomDokCreat = persUpdateDok.getNumber();
    		}
    		
    		//Удаляем все старые строки ТЧ
	    	Boolean resTableMoney = SelPost.UpdateCreatTable(connection, "DELETE FROM public.\"DokComingTableMoney\"\n" + 
	    																		"	WHERE id_dcom = "+NomDokCreat+";");
    		
    		//Заполняем по новой строки ТЧ.
    		for(int x=0; x < NumberTableLine; x++) {
    			PersenTableMoney lineTable = TableManeyDoc.getItems().get(x);
    			boolean resTableCreat = SelPost.UpdateCreatTable(connection, "INSERT INTO public.\"DokComingTableMoney\"(\n" + 
														    					"	id_dcom, id_nomen, kol_dcomtm)\n" + 
														    					"	VALUES ("+NomDokCreat+", "+getIdByName(lineTable.getNomen()).getIdNomen()+", "+lineTable.getKoll()+");");
    		
    		}
    		
    	} else {
    		boolUpCre = false;
    	}	
    	
    	return boolUpCre;
    	
    }
    
    public void recountTableMoneyNumbe() {
    	
    	for (int x = 0; x <= NumberTableLine; x++) {
    		PersenTableMoney recountLine = TableManeyDoc.getItems().get(x);
    		recountLine.setNL(x + 1);
    	}
    	
    }
    
	public void refreshNomenPersen() throws SQLException {
		
		TableMoneyNomen.clear();
		
		Connection connection = SetCon.CreatConnect();
		
		SelectPost SelPos = new SelectPost();
		ResultSet ResulSetTable = SelPos.SelectInfoBase(connection, "SELECT id_nomen, name_nomen \n" + 
																		"	FROM public.\"Nomenclature\";");
		while (ResulSetTable.next()) {
			TableMoneyNomen.add(new PersenNomenklatureTable(ResulSetTable.getString(1), ResulSetTable.getString(2)));
		}
		
		connection.close();
		
	}
	
 	public void refreshTableMoney() throws SQLException {
    	
    	TableMoney.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResultSetTableMoney = SelPos.SelectInfoBase(connection, "SELECT DC.id_dcomtm, DC.id_dcom, Nom.name_nomen, DC.kol_dcomtm FROM public.\"DokComingTableMoney\" DC, public.\"Nomenclature\" Nom WHERE DC.id_nomen = Nom.id_nomen and DC.id_dcom = " + NomDokCreat.toString() + ";");
    	while (ResultSetTableMoney.next()) {
    		NumberTableLine++;
    		TableMoney.add(new PersenTableMoney(NumberTableLine, ResultSetTableMoney.getString(4), ResultSetTableMoney.getString(3)));
    	}
    	
    	connection.close();
    	
    }
       
	public void refreshComboView() throws SQLException {
    	
    	CombView.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulComboView = SelPos.SelectInfoBase(connection, "SELECT id_viewcc, name_viewcc FROM public.\"ViewComingConsumption\";");
    	NumberTableLine = 0;
    	while (ResulComboView.next()) {
    		CombView.add(new PersenComboView(ResulComboView.getString(1), ResulComboView.getString(2)));
    	}
    	
    	connection.close();
    }
    
	public void refreshComboKontragent() throws SQLException {
    	
    	CombKont.clear();
    
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulComboKon = SelPos.SelectInfoBase(connection, "SELECT id_kont, name_kont FROM public.\"Kontragent\";");
    	while (ResulComboKon.next()) {
    		CombKont.add(new PersenComboKontragent(ResulComboKon.getString(1),ResulComboKon.getString(2)));
		}
    	
    	connection.close();
    
    }
    
	public ControllerFormDokComingCreatUpdate (boolean updateDok, SettingConnectSQL SetCon, PersenDokComing PersDok) {
		this.NomDokCreat = PersDok.getNumber();
		this.updateDok = updateDok;
		this.SetCon = SetCon;
		this.persUpdateDok = PersDok;
	}

	public ControllerFormDokComingCreatUpdate (boolean updateDok, SettingConnectSQL SetCon) {
		this.NomDokCreat = "New dokument";
		this.updateDok = updateDok;    		
		this.SetCon = SetCon;
		this.persUpdateDok = new PersenDokComing("", "", "", false,"","");
	}
	
}

package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;
import java.awt.Event;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.EditorKit;
import javax.swing.text.StringContent;

import com.sun.corba.se.spi.monitoring.MonitoringFactories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.ShortStringConverter;
import sql.SelectPost;
import sql.SettingConnectSQL;
import sun.nio.ch.sctp.ResultContainer;

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
    private TableColumn<PersenTableMoney, String> NomenTMD;

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
    private ObservableList<PersenComboKontragent> CombKont = FXCollections.observableArrayList();
    private ObservableList<PersenComboView> CombView = FXCollections.observableArrayList();
    private ObservableList<PersenTableMoney> TableMoney = FXCollections.observableArrayList();
    
    @FXML
    void initialize() throws SQLException {
    	
    	NumberTableLine = 0;
    	
    	refreshComboKontragent();
    	refreshComboView();
    	if (NomDokCreat != "New dokument") {
    		refreshTableMoney();
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
				return new PersenComboKontragent("", string);
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
				return new PersenComboView("", string);
			}
    		
		});
    	
    	//Id строки
    	NLTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, Integer>("NL"));
    	//Столбец с номенклатурой
    	NomenTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, String>("Nomen"));
    	NomenTMD.setCellFactory(TextFieldTableCell.<PersenTableMoney>forTableColumn());
    	NomenTMD.setOnEditCommit((CellEditEvent<PersenTableMoney, String>event) -> {
    		TablePosition<PersenTableMoney, String> pos = event.getTablePosition();
    		
    		String newNomen = event.getNewValue();
    	
    		int rowPos = pos.getRow();
    		PersenTableMoney Currentpersen = event.getTableView().getItems().get(rowPos);
    		
    		Currentpersen.setNomen(newNomen);
    	});
    	//Столбец с Количеством
    	KolTMD.setCellValueFactory(new PropertyValueFactory<PersenTableMoney, String>("Kol"));
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
    		recountTableMoney();
    	});
    	
    	//Создания дркумента
    	ButtonSave.setOnAction(event ->{
    		
    	});
    }

    public void updateCreatDocComing(boolean creatDok) throws SQLException {
    	
    	//Создаем сам документ
    	String idContragent = ComboBoxKontragent.getSelectionModel().getSelectedItem().getCode();
    	String idView = ComboBoxViewComCos.getSelectionModel().getSelectedItem().getCode();
    	
    	Connection connection = SetCon.CreatConnect();
    	SelectPost SelPost = new SelectPost();
    	boolean ResultCreatUpdate = SelPost.UpdateCreatTable(connection, "INSERT INTO public.\"DokComing\"(\n" + 
    																"	\"SumMoney_dcom\", \"Komment_dcom\", id_kont, id_viewcc, deleted_dcom)\n" + 
    																"	VALUES ( "+AmountDoc.getText()+", "+EditComments.getText()+", "+idContragent+", "+idView+", false);");
    	//Если документ содался, начинаем работать с ТЧ
    	if(ResultCreatUpdate) {	
    		if (NomDokCreat != "New dokument") {
	    		//Удаляем все старые строки ТЧ
	    		Boolean resTableMoney = SelPost.UpdateCreatTable(connection, "DELETE FROM public.\"DokComingTableMoney\"\n" + 
	    																		"	WHERE id_dcom = "+NomDokCreat.toString()+";");
    		};
    		//тут нужно получить последний номер после записи. Иначе не смогу записать ТЧ.
    		
    		//Заполняем по новой строки ТЧ.
    		for(int x=0; x <= NumberTableLine; x++) {
    			PersenTableMoney lineTable = TableManeyDoc.getItems().get(x);
    			boolean resTableCreat = SelPost.UpdateCreatTable(connection, "INSERT INTO public.\"DokComingTableMoney\"(\n" + 
														    					"	id_dcom, id_nomen, kol_dcomtm)\n" + 
														    					"	VALUES ("+NomDokCreat+", 1, "+lineTable.getKoll()+");");
    		}
    	}	
    	
    }
    
    public void recountTableMoney() {
    	
    	for (int x = 0; x <= NumberTableLine; x++) {
    		PersenTableMoney recountLine = TableManeyDoc.getItems().get(x);
    		recountLine.setNL(x + 1);
    	}
    	
    }
    
	public ControllerFormDokComingCreatUpdate (boolean updateDok, SettingConnectSQL SetCon) {
    	if (updateDok) {

    	} else {
    		this.NomDokCreat = "New dokument";
    		this.updateDok = updateDok;    		
    		this.SetCon = SetCon;
    	}
    }

    
	public void refreshTableMoney() throws SQLException {
    	
    	TableMoney.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResultSetTableMoney = SelPos.SelectInfoBase(connection, "SELECT id_dcom, id_nomen, kol_dcomtm FROM public.\"DokComingTableMoney\" WHERE id_dcom = " + NomDokCreat.toString() + ";");
    	while (ResultSetTableMoney.next()) {
    		NumberTableLine++;
    		TableMoney.add(new PersenTableMoney(NumberTableLine, ResultSetTableMoney.getString(1), ResultSetTableMoney.getString(2)));
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
    
}

package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.ShortStringConverter;
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
    private TableView<?> TableManeyDoc;

    @FXML
    private TableColumn<?, ?> NLTMD;

    @FXML
    private TableColumn<?, ?> NomenTMD;

    @FXML
    private TableColumn<?, ?> KolTMD;

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

    private String NomDokCreat;
    private boolean updateDok;
    private SettingConnectSQL SetCon;
    private ObservableList<PersenComboKontragent> CombKont = FXCollections.observableArrayList();
    private ObservableList<PersenComboView> CombView = FXCollections.observableArrayList();
    
    @FXML
    void initialize() throws SQLException {
    	
    	refreshComboKontragent();
    	refreshComboView();
    	
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
    	
    	LabelNumberDoc.setText(NomDokCreat);
    	
    }
    
    public ControllerFormDokComingCreatUpdate (boolean updateDok, SettingConnectSQL SetCon) {
    	if (updateDok) {

    	} else {
    		this.NomDokCreat = "New dokument";
    		this.updateDok = updateDok;    		
    		this.SetCon = SetCon;
    	}
    }

    public void refreshComboView() throws SQLException {
    	
    	CombView.clear();
    	
    	Connection connection = SetCon.CreatConnect();
    	
    	SelectPost SelPos = new SelectPost();
    	ResultSet ResulComboView = SelPos.SelectInfoBase(connection, "SELECT id_viewcc, name_viewcc FROM public.\"ViewComingConsumption\";");
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

package Sempel.FormDocuments.DokComing.CreatUpdateDokComing;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private ComboBox<?> ComboBoxKontragent;

    @FXML
    private Label LabelKontragent;

    @FXML
    private ComboBox<?> ComboBoxViewComCos;

    @FXML
    private Label LabelViewComingCos;

    private String NomDokCreat;
    private boolean updateDok;
    
    @FXML
    void initialize() {
    	
    	LabelNumberDoc.setText(NomDokCreat);
    	
    }
    
    public ControllerFormDokComingCreatUpdate (boolean updateDok) {
    	if (updateDok) {

    	} else {
    		this.NomDokCreat = "New dokument";
    		this.updateDok = updateDok;    		
    	}
    }
}

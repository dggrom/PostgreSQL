package Sempel.FormDocuments.DokConsumption;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerFormSelectDokConsumption {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonRefresh;

    @FXML
    private Button ButtonDeleted;

    @FXML
    private Button ButtonCreat;

    @FXML
    private TableView<?> TableNomenclature;

    @FXML
    private TableColumn<?, ?> TableColumnsNumber;

    @FXML
    private TableColumn<?, ?> TableColumnsAmount;

    @FXML
    private TableColumn<?, ?> TableColumnsKoment;

    @FXML
    void initialize() {

    }
}

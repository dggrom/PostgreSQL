package Sempel.Setting.DellRash;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class ControllerDellRash {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TitledPane TitledPaneDir;

    @FXML
    private RadioButton RadioButtonKontr;

    @FXML
    private RadioButton RadioButtonNomen;

    @FXML
    private RadioButton RadioButtonView;

    @FXML
    private TitledPane TitledPaneDoc;

    @FXML
    private RadioButton RadioButtonDocCon;

    @FXML
    private RadioButton RadioButtonDocCom;

    @FXML
    private TableView<?> TableObject;

    @FXML
    private TableColumn<?, ?> TableColumnObject;

    @FXML
    private TableView<?> TableDependence;

    @FXML
    private TableColumn<?, ?> TableDepColumnViewObject;

    @FXML
    private TableColumn<?, ?> TableDepColumnNomer;

    @FXML
    void initialize() {


    }
}


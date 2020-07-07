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
        assert TitledPaneDir != null : "fx:id=\"TitledPaneDir\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert RadioButtonKontr != null : "fx:id=\"RadioButtonKontr\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert RadioButtonNomen != null : "fx:id=\"RadioButtonNomen\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert RadioButtonView != null : "fx:id=\"RadioButtonView\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert TitledPaneDoc != null : "fx:id=\"TitledPaneDoc\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert RadioButtonDocCon != null : "fx:id=\"RadioButtonDocCon\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert RadioButtonDocCom != null : "fx:id=\"RadioButtonDocCom\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert TableObject != null : "fx:id=\"TableObject\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert TableColumnObject != null : "fx:id=\"TableColumnObject\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert TableDependence != null : "fx:id=\"TableDependence\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert TableDepColumnViewObject != null : "fx:id=\"TableDepColumnViewObject\" was not injected: check your FXML file 'FormDellRash.fxml'.";
        assert TableDepColumnNomer != null : "fx:id=\"TableDepColumnNomer\" was not injected: check your FXML file 'FormDellRash.fxml'.";

    }
}


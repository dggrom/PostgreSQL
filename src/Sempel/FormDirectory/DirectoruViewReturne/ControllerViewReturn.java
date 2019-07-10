package Sempel.FormDirectory.DirectoruViewReturne;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerViewReturn {

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
        private TableView<?> TableViewComCon;

        @FXML
        private TableColumn<?, ?> TableColumnsID;

        @FXML
        private TableColumn<?, ?> TableColumnsView;

        @FXML
        private TableColumn<?, ?> TableColumnsDeleted;

        @FXML
        void initialize() {

        }
    }

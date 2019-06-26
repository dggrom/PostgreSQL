package Sempel.MainForm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Sempel.FormDirectory.ControllerFormDirectory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import sql.SettingConnectSQL;


public class ControllerMainForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar MenyBar;

    @FXML
    private Menu MenuFiles;

    @FXML
    private MenuItem MenuFilesClose;

    @FXML
    private Menu MenuNSI;

    @FXML
    private MenuItem MenuNASIKontragent;

    @FXML
    private Menu MenuDocument;

    //Мои переменные
    SettingConnectSQL SettConn;

    @FXML
    void initialize() {
        MenuNASIKontragent.setOnAction(event -> {
            try {
                CreatFormDirectori();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void CreatFormDirectori() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource("../FormDirectory/FormDirectory.fxml"));

        ControllerFormDirectory ContrDir = new ControllerFormDirectory(SettConn);
        fxmlLoader.setController(ContrDir);

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public ControllerMainForm(SettingConnectSQL Loog){
        this.SettConn = Loog;
    }

}

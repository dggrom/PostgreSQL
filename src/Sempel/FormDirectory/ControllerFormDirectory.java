package Sempel.FormDirectory;

import Sempel.FormDirectory.CreatUpdateDirectory.ControllerDeletCreatDirectory;
import Sempel.MainForm.ControllerMainForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sql.SelectPost;
import sql.SettingConnectSQL;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ControllerFormDirectory {

    SettingConnectSQL SetCon;
    private ObservableList<PersenKontragent> Kontragents = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PersenKontragent> Table;

    @FXML
    private TableColumn<PersenKontragent, Integer> TableColumId;

    @FXML
    private TableColumn<PersenKontragent, String> TableColumName;

    @FXML
    private TableColumn<PersenKontragent, Boolean> TableColumnDeleted;

    @FXML
    private Button ButtonRegresh;

    @FXML
    private Button ButtonDeleted;

    @FXML
    private Button ButtonCreat;

    @FXML
    void initialize() throws SQLException {

        refrashTableKontragent();

        TableColumId.setCellValueFactory(new PropertyValueFactory<PersenKontragent, Integer>("id"));
        TableColumName.setCellValueFactory(new PropertyValueFactory<PersenKontragent, String>("Name"));
        TableColumnDeleted.setCellValueFactory(new PropertyValueFactory<PersenKontragent, Boolean>("Deleted"));

        Table.setItems(Kontragents);

        ButtonCreat.setOnAction(event -> {
            try {
                OpenFormRefreshCreat(false);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ButtonRegresh.setOnAction(event -> {
            try {
                OpenFormRefreshCreat(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    public ControllerFormDirectory(SettingConnectSQL SetCon){
        this.SetCon = SetCon;
    }

    public void OpenFormRefreshCreat(boolean updateKontr) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource("/Sempel/FormDirectory/CreatUpdateDirectory/CreatDeletDirectoryForm.fxml"));

        PersenKontragent PersSRT = Table.getSelectionModel().getSelectedItem();

        ControllerDeletCreatDirectory ContrCreatRefresh = new ControllerDeletCreatDirectory(PersSRT.getId(),PersSRT.getName(),updateKontr,SetCon.CreatConnect());
        fxmlLoader.setController(ContrCreatRefresh);

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setOnCloseRequest(event -> {
                try {
                    refrashTableKontragent();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        });
        stage.show();
    }

    public void refrashTableKontragent() throws SQLException {

        Kontragents.clear();

        Connection connection = SetCon.CreatConnect();

        SelectPost SelPos = new SelectPost();
        ResultSet ResulKontr = SelPos.SelectKontragent(connection);
        while (ResulKontr.next()){
            Kontragents.add(new PersenKontragent(ResulKontr.getInt(1),ResulKontr.getString(2),ResulKontr.getBoolean(3)));
        }
        connection.close();
    }

}

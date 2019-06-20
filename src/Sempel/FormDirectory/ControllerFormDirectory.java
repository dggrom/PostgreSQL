package Sempel.FormDirectory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SelectPost;
import sql.SettingConnectSQL;

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
    void initialize() throws SQLException {

        refrashTableKontragent();
//        Kontragents = FXCollections.observableArrayList(
//                new PersenKontragent(1,"sdsd"),
//                new PersenKontragent(2,"sdfdf")
//        );

        TableColumId.setCellValueFactory(new PropertyValueFactory<PersenKontragent, Integer>("id"));
        TableColumName.setCellValueFactory(new PropertyValueFactory<PersenKontragent, String>("name"));

        Table.setItems(Kontragents);

    }

    public ControllerFormDirectory(SettingConnectSQL SetCon){
        this.SetCon = SetCon;
    }

    public void refrashTableKontragent() throws SQLException {

        Connection connection = SetCon.CreatConnect();

        SelectPost SelPos = new SelectPost();
        ResultSet ResulKontr = SelPos.SelectKontragent(connection);
        while (ResulKontr.next()){
            Kontragents.add(new PersenKontragent(ResulKontr.getInt(1),ResulKontr.getString(2)));
        }
        connection.close();
    }

}

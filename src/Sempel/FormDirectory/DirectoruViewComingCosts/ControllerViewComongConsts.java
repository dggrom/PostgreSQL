package Sempel.FormDirectory.DirectoruViewComingCosts;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class ControllerViewComongConsts {

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
    private TableView<PersenViewComCons> TableViewComCon;

    @FXML
    private TableColumn<PersenViewComCons, Integer> TableColumnsID;

    @FXML
    private TableColumn<PersenViewComCons, String> TableColumnsView;

    @FXML
    private TableColumn<PersenViewComCons, Boolean> TableColumnsDeleted;

    SettingConnectSQL SetCon;
    private ObservableList<PersenViewComCons> PersenViewComCon = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {
        refrashTableView();

        TableColumnsID.setCellValueFactory(new PropertyValueFactory<PersenViewComCons, Integer>("id"));
        TableColumnsView.setCellValueFactory(new PropertyValueFactory<PersenViewComCons, String>("View"));
        TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenViewComCons, Boolean>("Deleted"));

        TableViewComCon.setItems(PersenViewComCon);
    }

    public ControllerViewComongConsts(SettingConnectSQL SetCon){
        this.SetCon = SetCon;
    }

    private void refrashTableView() throws SQLException {
        PersenViewComCon.clear();

        Connection conn = SetCon.CreatConnect();
        SelectPost selPost = new SelectPost();
        ResultSet resSt = selPost.SelectInfoBase(conn,"SELECT id_viewcc, name_viewcc, deleted_viewcc FROM public.\"ViewComingConsumption\";");

        while (resSt.next()) {
            PersenViewComCon.add(new PersenViewComCons(resSt.getInt("id_viewcc"),resSt.getString("name_viewcc"),resSt.getBoolean("deleted_viewcc")));
        }
        conn.close();
    }

}

package Sempel.FormDirectory.DirectoruViewDebt;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class ControllerViewDebt {

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
        private TableView<PersenViewDebt> TableViewComCon;

        @FXML
        private TableColumn<PersenViewDebt, Integer> TableColumnsID;

        @FXML
        private TableColumn<PersenViewDebt, String> TableColumnsView;

        @FXML
        private TableColumn<PersenViewDebt, Boolean> TableColumnsDeleted;

        private SettingConnectSQL SetCon;
        private ObservableList<PersenViewDebt> PersViewDebt = FXCollections.observableArrayList();

        public ControllerViewDebt(SettingConnectSQL SetCon){
                this.SetCon = SetCon;
        }

        @FXML
        void initialize() throws SQLException {

                RefreshTable();

                TableColumnsID.setCellValueFactory(new PropertyValueFactory<PersenViewDebt, Integer>("id"));
                TableColumnsView.setCellValueFactory(new PropertyValueFactory<PersenViewDebt, String>("View"));
                TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenViewDebt, Boolean>("Deleted"));

                TableViewComCon.setItems(PersViewDebt);



        }

        private void RefreshTable() throws SQLException {
                PersViewDebt.clear();

                String textSQl = "SELECT id_debt, view_debt, deleted_debt FROM public.\"ViewDebt\" ORDER BY id_debt;";

                SelectPost SelPost = new SelectPost();
                Connection con = SetCon.CreatConnect();
                ResultSet resSet = SelPost.SelectInfoBase(con, textSQl);

                while (resSet.next()){
                        PersViewDebt.add(new PersenViewDebt(resSet.getInt("id_debt"),resSet.getString("view_debt"),resSet.getBoolean("deleted_debt")));
                }

                con.close();

        }


}

package Sempel.FormDirectory.DirectoryKontragent;

import Sempel.FormDirectory.DirectoryKontragent.CreatUpdateDirectory.ControllerDeletCreatDirectory;
import Sempel.MainForm.ControllerMainForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sql.SelectPost;
import sql.SettingConnectSQL;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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

        ButtonDeleted.setOnAction(event -> {
            try {
                Boolean RezDelet = ShowFormDelet();
                if (RezDelet){
                DeletKontragen();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean ShowFormDelet(){

        PersenKontragent PersKon = Table.getSelectionModel().getSelectedItem();

        Alert AlertForm = new Alert(Alert.AlertType.CONFIRMATION);
        AlertForm.setTitle("Delete/not Deleted");
        AlertForm.setHeaderText("Deleted Kontragent "+ PersKon.getName());

        Optional<ButtonType> optt = AlertForm.showAndWait();

        if (optt.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

    private void DeletKontragen() throws SQLException {

        PersenKontragent PersKont = Table.getSelectionModel().getSelectedItem();
        String textSQL = "";
        if (PersKont.getDeleted()) {

            textSQL = "UPDATE public.\"Kontragent\" " +
                      " SET deleted_kont = false" +
                      " WHERE id_kont = " + PersKont.getId() + ";";

        } else  {

            textSQL = "UPDATE public.\"Kontragent\" " +
                    " SET deleted_kont = true" +
                    " WHERE id_kont = " + PersKont.getId() + ";";
        }

        SelectPost SelPost = new SelectPost();
        boolean RezUpd = SelPost.UpdateCreatTable(SetCon.CreatConnect(),textSQL);

        if (RezUpd){
            JOptionPane.showMessageDialog(null, "Error","Error",JOptionPane.ERROR_MESSAGE);
        } else {
            refrashTableKontragent();
            }
    }

    public ControllerFormDirectory(SettingConnectSQL SetCon){
        this.SetCon = SetCon;
    }

    public void OpenFormRefreshCreat(boolean updateKontr) throws IOException, SQLException {
       
    	FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource("/Sempel/FormDirectory/DirectoryKontragent/CreatUpdateDirectory/CreatDeletDirectoryForm.fxml"));

        PersenKontragent PersSRT = Table.getSelectionModel().getSelectedItem();

        if (updateKontr) {
        	ControllerDeletCreatDirectory ContrCreatRefresh = new ControllerDeletCreatDirectory(PersSRT.getId(),PersSRT.getName(),updateKontr,SetCon.CreatConnect());
 	        fxmlLoader.setController(ContrCreatRefresh);
        }else {
	        ControllerDeletCreatDirectory ContrCreatRefresh = new ControllerDeletCreatDirectory(Integer.valueOf(0),"",updateKontr,SetCon.CreatConnect());
	        fxmlLoader.setController(ContrCreatRefresh);
        }
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setOnHiding(event -> {
            try {
                refrashTableKontragent();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        stage.setOnCloseRequest(event -> {
                try {
                    refrashTableKontragent();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        });
        stage.show();
        stage.toFront();
    }

    public void refrashTableKontragent() throws SQLException {

        Kontragents.clear();

        Connection connection = SetCon.CreatConnect();

        SelectPost SelPos = new SelectPost();
        ResultSet ResulKontr = SelPos.SelectInfoBase(connection,"SELECT id_kont, name_kont, deleted_kont FROM public.\"Kontragent\";");
        while (ResulKontr.next()){
            Kontragents.add(new PersenKontragent(ResulKontr.getInt(1),ResulKontr.getString(2),ResulKontr.getBoolean(3)));
        }
        connection.close();
    }

}

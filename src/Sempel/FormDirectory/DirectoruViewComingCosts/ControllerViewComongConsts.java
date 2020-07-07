package Sempel.FormDirectory.DirectoruViewComingCosts;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import Sempel.FormDirectory.DirectoruViewComingCosts.CreatUpdateViewComingCosts.ContCreatUpdateViewComCon;
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
   	private CheckBox TrueDell;

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
        
    	TrueDell.setSelected(true);
    	
    	refrashTableView();

        TableColumnsID.setCellValueFactory(new PropertyValueFactory<PersenViewComCons, Integer>("id"));
        TableColumnsView.setCellValueFactory(new PropertyValueFactory<PersenViewComCons, String>("View"));
        TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenViewComCons, Boolean>("Deleted"));

        TableViewComCon.setItems(PersenViewComCon);

        ButtonCreat.setOnAction(event -> {
            try {
                OpenFormCreatUpdate(false);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ButtonRefresh.setOnAction(event -> {
            try {
                OpenFormCreatUpdate(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ButtonDeleted.setOnAction(event -> {

            PersenViewComCons PerView = TableViewComCon.getSelectionModel().getSelectedItem();
            boolean delRez = DeletedView();

            if (delRez){
                try {
                    DeletedBaseView(PerView.getDeleted(),PerView.getId().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        
        TrueDell.setOnAction(event -> {
        	try {
				refrashTableView();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        });
    }

    private void DeletedBaseView(boolean Del, String intStr) throws SQLException {
        String sqlText = "";
        SelectPost SelPost = new SelectPost();
        if (Del){
             sqlText = "UPDATE public.\"ViewComingConsumption\" " +
                    " SET deleted_viewcc = false " +
                    " WHERE id_viewcc="+intStr+" ;";
        } else {
             sqlText = "UPDATE public.\"ViewComingConsumption\" " +
                    " SET deleted_viewcc = true " +
                    " WHERE id_viewcc="+intStr+" ;";
        }
        boolean RezUp = SelPost.UpdateCreatTable(SetCon.CreatConnect(),sqlText);
        if (RezUp){
            JOptionPane.showMessageDialog(null,"Error update","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            refrashTableView();
        }
    }

    private boolean DeletedView(){

        PersenViewComCons PerCon = TableViewComCon.getSelectionModel().getSelectedItem();

        Alert alertForm = new Alert(Alert.AlertType.CONFIRMATION);
        alertForm.setTitle("DELETED");
        alertForm.setHeaderText("Deleted/not deleted " + PerCon.getView() + " ?");
        Optional<ButtonType> optt = alertForm.showAndWait();

        if (optt.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }

    private void OpenFormCreatUpdate(Boolean updatedata) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource("/Sempel/FormDirectory/DirectoruViewComingCosts/CreatUpdateViewComingCosts/FornCreatUpdateViewComCon.fxml"));

        PersenViewComCons PerVCC = TableViewComCon.getSelectionModel().getSelectedItem();

        if (updatedata) {
            ContCreatUpdateViewComCon ControllerUpCr = new ContCreatUpdateViewComCon(updatedata, SetCon.CreatConnect(), PerVCC.getId(), PerVCC.getView());
            fxmlLoader.setController(ControllerUpCr);
        }else {
            ContCreatUpdateViewComCon ControllerUpCr = new ContCreatUpdateViewComCon(updatedata, SetCon.CreatConnect(), 0,"");
            fxmlLoader.setController(ControllerUpCr);
        }

        Parent rootS = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(rootS));
        stage.setOnHiding(event -> {
            try {
                refrashTableView();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        stage.setOnCloseRequest(event -> {
            try {
                refrashTableView();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        stage.show();
        stage.toFront();
    }

    public ControllerViewComongConsts(SettingConnectSQL SetCon){
        this.SetCon = SetCon;
    }

    private void refrashTableView() throws SQLException {
        PersenViewComCon.clear();
        PersenViewComCon = PersenViewComCons.getMassivviewComConsTable(SetCon, PersenViewComCon, TrueDell.isSelected());
    }

}

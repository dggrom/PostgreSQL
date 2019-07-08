package Sempel.FormDirectory.DirectoruViewComingCosts.CreatUpdateViewComingCosts;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.Connect;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class ContCreatUpdateViewComCon {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EditID;

    @FXML
    private TextField Editname;

    @FXML
    private Label LabelID;

    @FXML
    private Label LabelName;

    @FXML
    private Button ButtonSave;

    private Integer idView;
    private String nameView;
    private boolean updateViewComCon;
    private Connection ConSQL;

    @FXML
    void initialize() {

        EditID.setText(idView.toString());
        Editname.setText(nameView);

        ButtonSave.setOnAction(event -> {
            if (updateViewComCon){
                String sqlText = "UPDATE public.\"ViewComingConsumption\" " +
                        " SET name_viewcc='"+Editname.getText()+"' " +
                        " WHERE id_viewcc="+EditID.getText()+" ;";
                try {
                    requestSQL(sqlText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                String sqlText = "INSERT INTO public.\"ViewComingConsumption\"(" +
                        " name_viewcc, deleted_viewcc) " +
                        " VALUES ('"+ Editname.getText() +"', false);";
                try {
                    requestSQL(sqlText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void requestSQL(String sqlText) throws SQLException {
        SelectPost selPost = new SelectPost();
        boolean rezRequest = selPost.UpdateCreatTable(ConSQL,sqlText);

        if (rezRequest == false){
            Stage stafeForm = (Stage) ButtonSave.getScene().getWindow();
            stafeForm.close();
        } else {
            Alert alertForm = new Alert(Alert.AlertType.ERROR);
            alertForm.setTitle("ERROR");
            if (updateViewComCon) {
                alertForm.setHeaderText("Error update");
            } else {
                alertForm.setHeaderText("Error Creat");
            }
            alertForm.show();
        }

    }

    public ContCreatUpdateViewComCon(boolean updateViewComCon, Connection ConSQL, Integer idView, String nameView){

        if (updateViewComCon){
            this.idView = idView;
            this.nameView = nameView;
            this.ConSQL = ConSQL;
            this.updateViewComCon = updateViewComCon;
        } else {
            this.idView = 0;
            this.nameView = "";
            this.ConSQL = ConSQL;
            this.updateViewComCon = updateViewComCon;
        }

    }
}

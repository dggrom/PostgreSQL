package Sempel.FormDirectory.DirectoryKontragent.CreatUpdateDirectory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.SelectPost;

import javax.swing.*;

public class ControllerDeletCreatDirectory {

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

    private Integer IdNumber;
    private String  NameKont;
    private Connection ConSQL;
    private boolean updateKontr;

    @FXML
    void initialize() {

        if (IdNumber != Integer.valueOf(0)) {
            EditID.setText(this.IdNumber.toString());
        } else {
            EditID.setText("Autoinkrement");
        }
        Editname.setText(this.NameKont);

        ButtonSave.setOnAction(event -> {

            String textSQL = "";

            if ( this.updateKontr ) {
                textSQL = "UPDATE public.\"Kontragent\" " +
                        "SET name_kont='" + Editname.getText() + "' " +
                        "WHERE id_kont = " + EditID.getText() + ";";
            } else {
                textSQL = "INSERT INTO public.\"Kontragent\"(" +
                        " name_kont, deleted_kont)" +
                        " VALUES ('"+ Editname.getText() +"', false);";
            }
            RefrashCreatKontrSQL(textSQL);
        });



    }

    private void RefrashCreatKontrSQL(String textSQL){
        SelectPost updateSQL = new SelectPost();
        try {

            boolean updateRes = updateSQL.UpdateCreatTable(ConSQL, textSQL);
            if (updateRes == false){
                // get a handle to the stage
                Stage stage = (Stage) ButtonSave.getScene().getWindow();
                // do what you have to do
                stage.close();
            } else {
                JOptionPane.showMessageDialog(null,"False", "не Удачно", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ControllerDeletCreatDirectory(Integer IdNumber, String NameKont, Boolean updateKontr, Connection ConSQL){
            if (updateKontr){
                this.IdNumber = IdNumber;
                this.NameKont = NameKont;
                this.ConSQL = ConSQL;
                this.updateKontr = updateKontr;
            } else {
                this.IdNumber = Integer.valueOf(0);
                this.NameKont = "".toString();
                this.ConSQL = ConSQL;
                this.updateKontr = updateKontr;
            }
    }


}

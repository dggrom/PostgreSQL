package Sempel.FormDirectory.CreatUpdateDirectory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sql.SelectPost;
import sun.misc.resources.Messages;

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

    @FXML
    void initialize() {

        if (IdNumber != 0) {
            EditID.setText(this.IdNumber.toString());
        } else {
            EditID.setText("Код будет сгенерирован");
        }
        Editname.setText(this.NameKont);

        ButtonSave.setOnAction(event -> {
            SelectPost updateSQL = new SelectPost();
            try {
                String textSQL = "UPDATE public.\"Kontragent\" " +
                                "SET name_kont='"+Editname.getText()+"' " +
                                "WHERE id_kont = "+EditID.getText()+";";
                boolean updateRes = updateSQL.UpdateCreatTable(ConSQL, textSQL);
                if (updateRes == false){
                    JOptionPane.showMessageDialog(null,"Good", "Удачно", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,"False", "не Удачно", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });



    }

    public ControllerDeletCreatDirectory(Integer IdNumber, String NameKont, Boolean updateKontr, Connection ConSQL){
            if (updateKontr){
                this.IdNumber = IdNumber;
                this.NameKont = NameKont;
                this.ConSQL = ConSQL;
            } else {
                this.IdNumber = 0;
                this.NameKont = "";
                this.ConSQL = ConSQL;
            }
    }

    private void  updateKontragentSQL(){

    }
}

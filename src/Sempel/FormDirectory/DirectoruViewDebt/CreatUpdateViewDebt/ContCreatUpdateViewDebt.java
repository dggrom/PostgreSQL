package Sempel.FormDirectory.DirectoruViewDebt.CreatUpdateViewDebt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sql.SelectPost;
import sql.SettingConnectSQL;

public class ContCreatUpdateViewDebt {

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

        private SettingConnectSQL SetCon;
        private Integer ID;
        private String name;
        private boolean update;

        public ContCreatUpdateViewDebt(SettingConnectSQL SetCon, Integer ID, String name, boolean update){
            this.SetCon = SetCon;
            this.update = update;
            if (update){
                this.ID = ID;
                this.name = name;
            } else {
                this.name = "";
                this.ID = 0;
            }
        }

        @FXML
        void initialize() {
           EditID.setText(this.ID.toString());
           EditID.setText(this.name);

            ButtonSave.setOnAction(event -> {
                String textSQL = "";

                if (this.update){
                    textSQL = "UPDATE public.\"ViewDebt\" " +
                            " SET view_debt='"+Editname.getText()+"' " +
                            " WHERE id_debt="+EditID.getText()+";";
                } else {
                    textSQL = "INSERT INTO public.\"ViewDebt\"( " +
                            " view_debt) " +
                            " VALUES ('"+Editname.getText()+"');";
                }

                try {
                    UpdateCreatDebt(textSQL);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
        }

        private void UpdateCreatDebt(String textSQL) throws SQLException {

            SelectPost selPos = new SelectPost();
            boolean rezSel = selPos.UpdateCreatTable(SetCon.CreatConnect(),textSQL);
            if (rezSel){
                Alert alertForm = new Alert(Alert.AlertType.ERROR);
                alertForm.setTitle("ERROR");
                if (this.update){
                    alertForm.setHeaderText("Error update !!!");
                } else {
                    alertForm.setHeaderText("Error creat !!!");
                }
                alertForm.show();
            } else {
                Stage stageForm = (Stage) ButtonSave.getScene().getWindow();
                stageForm.close();
            }
        }
    }

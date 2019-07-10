package Sempel.FormDirectory.DirectoruNomenclature.CreatUpdateNomenclature;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sql.Connect;
import sql.SelectPost;

public class ControllerCreatUpdateNomenklature {

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

        Connection ConSql;
        String nameNomen;
        Integer idNomen;
        boolean updateNomen;

        @FXML
        void initialize() {

            EditID.setText(this.idNomen.toString());
            Editname.setText(this.nameNomen);

            ButtonSave.setOnAction(event -> {
                String textSQL;

                if (this.updateNomen){
                    textSQL = "UPDATE public.\"Nomenclature\" " +
                            " SET name_nomen='"+Editname.getText()+"' " +
                            " WHERE id_nomen="+EditID.getText()+" ;";
                } else {
                    textSQL = "INSERT INTO public.\"Nomenclature\"( " +
                            " name_nomen ) " +
                            " VALUES ('"+Editname.getText()+"');";
                }

                try {
                  if (RefrashCreatNomenklature(textSQL)){
                      Alert alertForm = new Alert(Alert.AlertType.ERROR);
                      if (this.updateNomen) {
                          alertForm.setHeaderText("Error update nomenklature");
                      } else {
                          alertForm.setTitle("Error creat nomenklature");
                      }
                      alertForm.show();
                  } else {
                      Stage stagForm = (Stage) ButtonSave.getScene().getWindow();
                      stagForm.close();
                  }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });

        }

        private boolean RefrashCreatNomenklature(String textSQL) throws SQLException {
            SelectPost SelPos = new SelectPost();
            return SelPos.UpdateCreatTable(ConSql,textSQL);
        }

        public ControllerCreatUpdateNomenklature(Connection ConSql, String nameNomen, Integer idNomen, boolean updateNomen){
            this.ConSql = ConSql;
            this.updateNomen = updateNomen;
            if (updateNomen){
                this.nameNomen = nameNomen;
                this.idNomen = idNomen;
            } else {
                this.nameNomen = "";
                this.idNomen = 0;
            }
        }
}

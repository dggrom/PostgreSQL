package Sempel.FormDirectory.CreatUpdateDirectory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    @FXML
    void initialize() {

        EditID.setText(this.IdNumber.toString());
        Editname.setText(this.NameKont);

    }

    public ControllerDeletCreatDirectory(Integer IdNumber, String NameKont){
            this.IdNumber = IdNumber;
            this.NameKont = NameKont;
    }
}

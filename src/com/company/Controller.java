package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Sempel.MainForm.ControllerMainForm;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sql.Connect;
import sql.SettingConnectSQL;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_EditADresServer;

    @FXML
    private Label id_label1;

    @FXML
    private TextField id_EditNameBase;

    @FXML
    private Label id_label2;

    @FXML
    private TextField id_EditNameAdmin;

    @FXML
    private Label id_label3;

    @FXML
    private TextField id_EditPassAdmin;

    @FXML
    private Label id_label4;

    @FXML
    private Button ButtonConnect;

    @FXML
    private Button ButtonExit;

    @FXML
    void initialize() throws IOException {
    	
    	FileConnect baseFile = new FileConnect();
    	
    	String[] st = new String[4];    	
    	st =  baseFile.ReadFileSetting();
    	
    	id_EditADresServer.setText(st[0]);
    	id_EditNameBase.setText(st[1]);
    	id_EditNameAdmin.setText(st[2]);
    	id_EditPassAdmin.setText(st[3]);
    	
    	ButtonConnect.setOnAction(Event -> {
    		ConnectToSQL(id_EditADresServer.getText(), id_EditNameBase.getText(), id_EditNameAdmin.getText(), id_EditPassAdmin.getText());
    	});

    	ButtonExit.setOnAction(Event -> {
           Platform.exit();
        });
    }
    
    void ConnectToSQL(String URL,String NameBase,String Login,String pass) {
    	Connect ConnectSQL = new Connect("jdbc:postgresql://"+URL+"/"+NameBase,Login,pass);
    	Boolean ResConnect = ConnectSQL.ConnectPostgre();
    	if (ResConnect) {
            try {
                OpenMainForm(URL,NameBase,Login,pass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
    		JOptionPane.showMessageDialog(null, "Connect false","Connect false",JOptionPane.ERROR_MESSAGE);
		}
    }

    void OpenMainForm(String URL,String NameBase,String Login,String pass) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("../../Sempel/MainForm/MainForm.fxml"));

        //Запоминаем настройки что бы передать их дальше.
        SettingConnectSQL SettConn = new SettingConnectSQL("jdbc:postgresql://"+URL+"/"+NameBase,Login,pass);
        //

        ControllerMainForm CntMF = new ControllerMainForm(SettConn);
        fxmlLoader.setController(CntMF);

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        Stage stageButt = (Stage) ButtonExit.getScene().getWindow();
        stageButt.close();
    }
}




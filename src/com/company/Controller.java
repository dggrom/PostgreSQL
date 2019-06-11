package com.company;

import java.awt.Window;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sql.Connect;

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
    void initialize() {
    	ButtonConnect.setOnAction(Event -> {
    		ConnectToSQL(id_EditADresServer.getText(), id_EditNameBase.getText(), id_EditNameAdmin.getText(), id_EditPassAdmin.getText());
    	});
    }
    
    static void ConnectToSQL(String URL,String NameBase,String Login,String pass) {
    	Connect ConnectSQL = new Connect("jdbc:postgresql://"+URL+"/"+NameBase,Login,pass);
    	Boolean ResConnect = ConnectSQL.ConnectPostgre();
    	if (ResConnect) {
    		JOptionPane.showMessageDialog(null, "Connect creat","Connect creat",JOptionPane.INFORMATION_MESSAGE);
		} else {
    		JOptionPane.showMessageDialog(null, "Connect false","Connect false",JOptionPane.ERROR_MESSAGE);
		}
    }
}

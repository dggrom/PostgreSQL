package Sempel.MainForm;

import java.io.IOException;

import Sempel.FormDirectory.DirectoruNomenclature.ControllerFormNomenclature;
import Sempel.FormDirectory.DirectoruViewComingCosts.ControllerViewComongConsts;
import Sempel.FormDirectory.DirectoruViewDebt.ControllerViewDebt;
import Sempel.FormDirectory.DirectoryKontragent.ControllerFormDirectory;
import Sempel.FormDocuments.DokComing.ControllerFormSelectionDokComing;
import Sempel.FormDocuments.DokConsumption.ControllerFormSelectDokConsumption;
import Sempel.FormReports.ReportView.ControllerReportView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sql.SettingConnectSQL;



public class ControllerMainForm {

	@FXML
    private MenuBar MenyBar;

    @FXML
    private Menu MenuFiles;

    @FXML
    private MenuItem MenuFilesClose;

    @FXML
    private Menu MenuNSI;

    @FXML
    private MenuItem MenuNASIKontragent;

    @FXML
    private MenuItem MenuNASIViewComCon;

    @FXML
    private MenuItem MenuNASINomenclature;

    @FXML
    private MenuItem MenuNASIViewDebt;

    @FXML
    private MenuItem MenuNASIViewReturn;

    @FXML
    private Menu MenuDocument;

    @FXML
    private MenuItem MenuDokComing;

    @FXML
    private MenuItem MenuDokConsu;

    @FXML
    private ImageView DokComing;
    
    @FXML
    private Menu MenuStting;

    @FXML
    private MenuItem MenuSettingDellRash;

    @FXML
    private Menu MenuReport;

    @FXML
    private MenuItem MenuReportLef;
    
    //Мои переменные
    SettingConnectSQL SettConn;

    @FXML
    void initialize() {
        MenuNASIKontragent.setOnAction(event -> {
            try {
                CreatForm("../FormDirectory/DirectoryKontragent/FormDirectory.fxml", "ControllerFormDirectory");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        MenuNASIViewComCon.setOnAction(event -> {
            try {
                CreatForm("../FormDirectory/DirectoruViewComingCosts/ViewComingConsts.fxml","ControllerViewComongConsts");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        MenuNASINomenclature.setOnAction(event -> {
        	try {
				CreatForm("../FormDirectory/DirectoruNomenclature/FormNomenclature.fxml", "ControllerFormNomenclature");
			} catch (IOException e) {
				e.printStackTrace();
			}
        }); 
        
       MenuDokComing.setOnAction(event ->{
        	try {
        		CreatForm("../FormDocuments/DokComing/FormSelectionDokComing.fxml", "ControllerFormSelectionDokComing");
        	} catch (IOException e) {
				e.printStackTrace();
			}
        });
        
       MenuDokConsu.setOnAction(event ->{
    	   try {
			CreatForm("../FormDocuments/DokConsumption/FormSelectDokConsumption.fxml", "ControllerFormSelectDokConsumption");
		} catch (IOException e) {
			e.printStackTrace();
		}
       });

       MenuReportLef.setOnAction(event -> {
    	   try {
			CreatForm("../FormReports/ReportView/ReportView.fxml", "ControllerReportView");
		} catch (IOException e) {
			e.printStackTrace();
		} 
       });
       
       /* MenuNASIViewDebt.setOnAction(event -> {
            try {
                CreatForm("../FormDirectory/DirectoruViewDebt/DirectoruViewDebt.fxml","ControllerViewDebt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

        /*MenuNASIViewReturn.setOnAction(event -> {
            try {
                CreatForm("../FormDirectory/DirectoruViewReturne/DirectoruViewReturn.fxml","ControllerViewReturn");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    
    void CreatForm(String FXMLdir, String contrName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerMainForm.class.getResource(FXMLdir));

        if (contrName == "ControllerFormDirectory") {
            ControllerFormDirectory ContrDir = new ControllerFormDirectory(SettConn);
            fxmlLoader.setController(ContrDir);
        } else if (contrName == "ControllerViewComongConsts"){
            ControllerViewComongConsts ContrDir = new ControllerViewComongConsts(SettConn);
            fxmlLoader.setController(ContrDir);
        } else if(contrName == "ControllerFormNomenclature") {
        	ControllerFormNomenclature ContrDir = new ControllerFormNomenclature(SettConn);
        	fxmlLoader.setController(ContrDir);
        } else if (contrName == "ControllerViewDebt"){
            ControllerViewDebt ContDir = new ControllerViewDebt(SettConn);
            fxmlLoader.setController(ContDir);
        } else if (contrName == "ControllerViewReturn"){
            //ControllerViewReturn ContDir = new ControllerViewReturn(SettConn);
            //fxmlLoader.setController(ContDir);
        } else if (contrName == "ControllerFormSelectionDokComing") {
        	ControllerFormSelectionDokComing ContrDir = new ControllerFormSelectionDokComing(SettConn);
        	fxmlLoader.setController(ContrDir);
        } else if (contrName == "ControllerFormSelectDokConsumption") {
        	ControllerFormSelectDokConsumption ContrDir = new ControllerFormSelectDokConsumption(SettConn);
        	fxmlLoader.setController(ContrDir);
        } else if (contrName == "ControllerReportView") {
        	ControllerReportView ContrDir = new ControllerReportView(SettConn);
        	fxmlLoader.setController(ContrDir);
        }

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        stage.toFront();
    }

    public ControllerMainForm(SettingConnectSQL Loog){
        this.SettConn = Loog;
    }

}

package Sempel.FormDirectory.DirectoruNomenclature;

	import java.io.IOException;
	import java.net.URL;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Optional;
	import java.util.ResourceBundle;

	import Sempel.FormDirectory.DirectoruNomenclature.CreatUpdateNomenclature.ControllerCreatUpdateNomenklature;
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

public class ControllerFormNomenclature {

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
	    private TableView<PersenNomenclatura> TableNomenclature;

	    @FXML
	    private TableColumn<PersenNomenclatura, Integer> TableColumnsID;

	    @FXML
	    private TableColumn<PersenNomenclatura, String> TableColumnsView;

	    @FXML
	    private TableColumn<PersenNomenclatura, Boolean> TableColumnsDeleted;

	    SettingConnectSQL SetCon;
	    private ObservableList<PersenNomenclatura> PersenNomen = FXCollections.observableArrayList();
	    
	    @FXML
	    void initialize() throws SQLException {
	    	
	    	refrashTableNomenclature();
	    	//
	    	TableColumnsID.setCellValueFactory(new PropertyValueFactory<PersenNomenclatura, Integer>("id"));
	    	TableColumnsView.setCellValueFactory(new PropertyValueFactory<PersenNomenclatura, String>("name"));
	    	TableColumnsDeleted.setCellValueFactory(new PropertyValueFactory<PersenNomenclatura, Boolean>("deleted"));
	    	
	    	TableNomenclature.setItems(PersenNomen);

	    	ButtonCreat.setOnAction(event -> {
				try {
					CreatRefrashNomenklature(false);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

	    	ButtonRefresh.setOnAction(event -> {
				try {
					CreatRefrashNomenklature(true);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

	    	ButtonDeleted.setOnAction(event -> {
	    		boolean RezDelet = DeletedNomenklature();

	    		String textSQL = "";
	    		PersenNomenclatura tekLine = TableNomenclature.getSelectionModel().getSelectedItem();

	    		if (RezDelet){
	    			if (tekLine.getDeleted()){
	    				textSQL = "UPDATE public.\"Nomenclature\" " +
								" SET deleted_nomen=false " +
								" WHERE id_nomen="+tekLine.getId()+";";
					} else {
						textSQL = "UPDATE public.\"Nomenclature\" " +
								" SET deleted_nomen=true " +
								" WHERE id_nomen="+tekLine.getId()+";";
					}
				}

				try {
					DeletedNomenklatureBase(textSQL);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			});

	    }

	    private void DeletedNomenklatureBase(String textSQL) throws SQLException {

	    	PersenNomenclatura tekLine = TableNomenclature.getSelectionModel().getSelectedItem();

	    	SelectPost selPost = new SelectPost();
	    	boolean rezDel = selPost.UpdateCreatTable(SetCon.CreatConnect(),textSQL);

	    	if (rezDel){
				JOptionPane.showMessageDialog(null,"Error deleted nomenklature", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
	    		refrashTableNomenclature();
			}

		}

	    private boolean DeletedNomenklature(){

	    	PersenNomenclatura tekLine = TableNomenclature.getSelectionModel().getSelectedItem();

	    	Alert alertForm = new Alert(Alert.AlertType.CONFIRMATION);
			alertForm.setTitle("DELETED " + tekLine.getName());
			alertForm.setHeaderText("DELETED / not deleted " + tekLine.getName() + " ?");

			Optional<ButtonType> opBut = alertForm.showAndWait();

			if (opBut.get() == ButtonType.OK) {
				return true;
			} else {
				return false;
			}
		}

	    private void CreatRefrashNomenklature(boolean update) throws SQLException, IOException {
			FXMLLoader loderFXM = new FXMLLoader(ControllerFormNomenclature.class.getResource("CreatUpdateNomenclature/CreatUpdateNomenclature.fxml"));

			PersenNomenclatura tekLine = TableNomenclature.getSelectionModel().getSelectedItem();

			if (update) {
				ControllerCreatUpdateNomenklature ContNom = new ControllerCreatUpdateNomenklature(SetCon.CreatConnect(), tekLine.getName(), tekLine.getId(), update );
				loderFXM.setController(ContNom);
			} else {
				ControllerCreatUpdateNomenklature ContNom = new ControllerCreatUpdateNomenklature(SetCon.CreatConnect(), "", 0, update );
				loderFXM.setController(ContNom);
			}

			Parent Par = (Parent) loderFXM.load();
			Stage StagePar = new Stage();
			StagePar.setScene(new Scene(Par));
			StagePar.setOnHiding(event -> {
				try {
					refrashTableNomenclature();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			StagePar.setOnCloseRequest(event -> {
				try {
					refrashTableNomenclature();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			StagePar.show();
			StagePar.toFront();
	    }

	    public ControllerFormNomenclature(SettingConnectSQL SetCon) {
	    	this.SetCon = SetCon;
	    }
	    
	    private void refrashTableNomenclature() throws SQLException {
	    	PersenNomen.clear();
	    	
	    	SelectPost selPost = new SelectPost();
	    	String SQLtext = "SELECT id_nomen, name_nomen, deleted_nomen FROM public.\"Nomenclature\";";
	    	Connection con = SetCon.CreatConnect();
	    	ResultSet rezSet = selPost.SelectInfoBase(con, SQLtext);
	    	
	    	while (rezSet.next()) {
	    		PersenNomen.add(new PersenNomenclatura(rezSet.getInt("id_nomen"), rezSet.getString("name_nomen"), rezSet.getBoolean("deleted_nomen")));
	    	}
	    	
	    	con.close();
	    	
		}
	}

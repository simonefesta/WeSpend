

/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import logic.DeskUtil;
import logic.Session;
import logic.controller.PendingController;
import logic.controller.PendingTable;

public class PendingViewController implements Initializable {

	@FXML
	TableView<PendingTable> tableID;
	@FXML
	TableColumn<PendingTable, Integer> iID;
	
	@FXML
	TableColumn<PendingTable, String> iDate;
	@FXML
	TableColumn<PendingTable, String> iName;
	@FXML
     TableColumn<PendingTable, Float> iPrice;
	@FXML
    TableColumn<PendingTable, String> iUser;
	@FXML
    TableColumn<PendingTable, String> iComment;



	@Override
	public void initialize (URL location, ResourceBundle resources) {
		

	final ObservableList<PendingTable> data = FXCollections.observableArrayList();
	PendingController pendingController = new PendingController();
	List<PendingTable> list =  new ArrayList<>();

	try {
		list = pendingController.getPending(Session.getUsername());
	} catch (SQLException e) {
		printMsg ("There are no data!", AlertType.ERROR);
	}


	for (int i=0; i<list.size(); i++) {
		iID.setCellValueFactory( new PropertyValueFactory<PendingTable, Integer>("rID"));
		iDate.setCellValueFactory( new PropertyValueFactory<PendingTable, String>("rDate"));
		iName.setCellValueFactory( new PropertyValueFactory<PendingTable, String>("rType"));
		iPrice.setCellValueFactory( new PropertyValueFactory<PendingTable, Float>("rPrice"));
		iUser.setCellValueFactory( new PropertyValueFactory<PendingTable, String>("rUser"));
		iComment.setCellValueFactory( new PropertyValueFactory<PendingTable, String>("rComment"));
		data.add(list.get(i));
		tableID.setItems(data);
	}

	}

	private void printMsg(String string, AlertType error) {
		Window owner = tableID.getScene().getWindow();
		AlertHelper.showAlert(error, owner, "", string);
	}



	@FXML
	void accept(ActionEvent event) {
		 if(tableID.getSelectionModel().getSelectedItem().getRID()!=null) {
			 try {
				 
				PendingController.acceptDebt(tableID.getSelectionModel().getSelectedItem().getRID(),Session.getUsername());
				printMsg ("Pending Accepted!", AlertType.INFORMATION);
				MainView view = new MainView();
				view.switchScene(DeskUtil.MAINPAGE);
			} catch (SQLException e) {
				printMsg ("Unable to accept the debt", AlertType.ERROR);
			}
		 }
		
		
	}
	@FXML
	void decline(ActionEvent event) {
		if(tableID.getSelectionModel().getSelectedItem().getRID()!=null) {
			try {
				PendingController.declineDebt(tableID.getSelectionModel().getSelectedItem().getRID());
				printMsg ("Pending Declined!", AlertType.INFORMATION);
				MainView view = new MainView();
				view.switchScene(DeskUtil.MAINPAGE);
			} catch (SQLException e) {
				printMsg ("Unable to decline the debt", AlertType.ERROR);
			}
		}
	}
	
	
	

}

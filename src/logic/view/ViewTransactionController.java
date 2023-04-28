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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import logic.Session;
import logic.controller.ListController;
import logic.controller.Table;
import logic.exceptions.RetrieveDataException;

public class ViewTransactionController implements Initializable {
	
	@FXML
	TableView<Table> tableID;
	@FXML
	TableColumn<Table, String> iDate;
	@FXML
	TableColumn<Table, String> iName;
	@FXML
     TableColumn<Table, Float> iPrice;
	@FXML
    TableColumn<Table, String> iComment;
		
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
	
	final ObservableList<Table> data = FXCollections.observableArrayList();
	ListController listController = new ListController();
	List<Table> list =  new ArrayList<>();
	
	try {
		list = listController.getListTransaction(Session.getUsername());
	} catch (RetrieveDataException e) {
		printMsg (e.getMessage(), AlertType.ERROR);
	} catch (SQLException e) {
		printMsg ("There are no data!", AlertType.ERROR);
	} 
	
	
	for (int i=0; i<list.size(); i++) {
		iDate.setCellValueFactory( new PropertyValueFactory<Table, String>("rDate"));
		iName.setCellValueFactory( new PropertyValueFactory<Table, String>("rType"));
		iPrice.setCellValueFactory( new PropertyValueFactory<Table, Float>("rPrice"));
		iComment.setCellValueFactory( new PropertyValueFactory<Table, String>("rComment"));
		data.add(list.get(i));
		tableID.setItems(data);
	}
	
	}

	private void printMsg(String string, AlertType error) {
		Window owner = tableID.getScene().getWindow();
		AlertHelper.showAlert(error, owner, "", string);
	}
	}
	

	
	


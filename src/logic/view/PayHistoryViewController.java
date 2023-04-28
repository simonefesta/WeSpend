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
import logic.controller.PayController;
import logic.controller.PayHistory;
import logic.exceptions.RetrieveDataException;


public class PayHistoryViewController implements Initializable {
	@FXML
	TableView<PayHistory> tableID;
	@FXML
    TableColumn<PayHistory, Float> iPrice;
	
	
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		final ObservableList<PayHistory> data = FXCollections.observableArrayList();
		PayController payController = new PayController();
		List<PayHistory> list =  new ArrayList<>();
		try {
			list = payController.getPayment(Session.getUsername());
		} catch (RetrieveDataException e) {
			printMsg (e.getMessage(), AlertType.ERROR);
		} catch (SQLException e) {
			printMsg ("There are no data!", AlertType.ERROR);
		}
		
		
		for (int i=0; i<list.size(); i++) {
			iPrice.setCellValueFactory( new PropertyValueFactory<PayHistory, Float>("rPrice"));
			data.add(list.get(i));
			tableID.setItems(data);
		}
		}

		private void printMsg(String string, AlertType error) {
			Window owner = tableID.getScene().getWindow();
			AlertHelper.showAlert(error, owner, "", string);
		}
		
		
		
		
		
		
	
	
	

}

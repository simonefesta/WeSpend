/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import logic.Session;
import logic.controller.GraphController;
import logic.exceptions.RetrieveDataException;

import java.sql.SQLException;

public class GraphViewController implements Initializable{

	@FXML
	private PieChart pieChart;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
		ObservableList<Data> pieChartData = FXCollections.<Data>observableArrayList();

		GraphController graphController = new GraphController();
		List<Data> list = new ArrayList<>();
		
		try {
			list = graphController.getPieChartData(Session.getUsername());
		} catch (RetrieveDataException e) {
			printMsg (e.getMessage(), AlertType.ERROR);
		} catch (SQLException e) {
			printMsg ("There are no data!", AlertType.ERROR);
		}
		
		
		for (int i=0; i<list.size(); i++) {
			pieChartData.add(list.get(i));
		}
		
		pieChart.setData(pieChartData);
		pieChart.setTitle("Outgoings");
		
		
	}

	private void printMsg(String string, AlertType error) {
		Window owner = pieChart.getScene().getWindow();
		AlertHelper.showAlert(error, owner, "", string);
	}
	



	
}





/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import logic.DeskUtil;
import logic.Session;

public class NavBarController implements Initializable {
	
	@FXML
	private MenuBar menuBar;

	@FXML
	private MenuItem usernameField;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		usernameField.setText(Session.getUsername());
		menuBar.setFocusTraversable(true);
	} 
	
	@FXML
    protected void handleNewTransaction(ActionEvent event) {
		
		MainView view = new MainView();
		view.switchScene("AddTransactionForm.fxml");
	}
	
	@FXML
    protected void handleListTr(ActionEvent event) {
		
		MainView view = new MainView();
		view.switchScene("TransactionList.fxml");
    }				
	
	@FXML
    protected void handlePending(ActionEvent event) {

		MainView view = new MainView();
		view.switchScene("PendingList.fxml");
    }	

	@FXML
    protected void handleMainMenu(ActionEvent event) {

		MainView view = new MainView();
		view.switchScene(DeskUtil.MAINPAGE);
    }	
	
	@FXML
    protected void handleViewGoal(ActionEvent event) {

		MainView view = new MainView();
		view.switchScene("../ab/view/GoalView.fxml");
    }
	
	@FXML
    protected void handleExit(ActionEvent event) {
		
		MainView view = new MainView();
		Session.empty();
		view.switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
    }	
}

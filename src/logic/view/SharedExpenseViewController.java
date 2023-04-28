/*
 * Copyright 2020 Alessandro Fato
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import logic.DeskUtil;
import logic.Session;
import logic.controller.SharedExpenseController;
import logic.entity.TransactionManager;
import logic.entity.User;
import logic.entity.decorator.TransactionComponent;


public class SharedExpenseViewController implements Initializable {

	@FXML
    private Label debtUser;
	
	@FXML
    private Label shareUser;
	
	@FXML
	private Button submitButton;

	@FXML
	private Button debtButton;
	
	@FXML
	private Button selectFriendButton;
	
	@FXML
	private ListView<String> userListView;
	
	TransactionManager transactionM;

	ObservableList<String> userList = FXCollections.observableArrayList();
	
	@Override
   	public void initialize(URL location, ResourceBundle resources) {	
   		
   		transactionM = TransactionManager.getInstance();
   		
   		updateGui();
   		
  		// setup user list
  		SharedExpenseController sharedExpenseController = new SharedExpenseController();
   		userListView.setItems(sharedExpenseController.getFriendUsers(Session.getUsername()));
   		userListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
   	}  
   	
    @FXML
    protected void handleSelectFriendsButtonAction(ActionEvent event) {
    	
    	List<User> selectedUser = new ArrayList<>();
    	
    	ObservableList<String> listItems = userListView.getSelectionModel().getSelectedItems();
    	if (listItems.size() <= 0) {
    		printMsg ("Please select one or more friends", AlertType.ERROR);
    		return;
    	}
    	
    	for (int i=0; i<listItems.size(); i++) {
    		selectedUser.add(new User(listItems.get(i)));
    	}
    	    	
  		SharedExpenseController sharedExpenseController = new SharedExpenseController();
		if (!sharedExpenseController.shareExpense(transactionM, selectedUser)) {
    		printMsg ("You have already selected one of these friends", AlertType.ERROR);
    		return;
		}
		
  		updateSharedUserList();
    }
 
    @FXML
    protected void handleDebtButtonAction(ActionEvent event) {
    	
    	MainView view = new MainView();
    	view.switchScene("AddDebtForm.fxml");	
    }
    
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
    	
  		SharedExpenseController sharedExpenseController = new SharedExpenseController();    	
    	if (!sharedExpenseController.commit(transactionM, Session.getUsername()))
    		printMsg ("Failure!", AlertType.ERROR);
    	else
    		printMsg ("Transaction properly added!", AlertType.INFORMATION);
    	
    	
		MainView view = new MainView();
		view.switchScene(DeskUtil.MAINPAGE);
    }
    
    @FXML
    protected void handleCancelButtonAction(ActionEvent event) {
    	
		MainView view = new MainView();
		view.switchScene(DeskUtil.MAINPAGE);
    }
    
    private void updateGui() {
    	updateSharedUserList();
    	updateDebtUserList();
    }
    
    private void updateSharedUserList() {
    	 	
    	StringBuilder bld = new StringBuilder();
    	bld.append("You are sharing your transaction with:\n");
   		
   		TransactionComponent transaction = transactionM.getTransaction();
   		List<User> users = transaction.getSharedUsers(); 
   		
   		for (int i=0; i<users.size(); i++) { 
   			bld.append("\n" + users.get(i).getName());
        } 
  		shareUser.setText(bld.toString());
  		
  		if (transaction.hasSharedExpense()) {
  			selectFriendButton.setVisible(false);
  		}  		
    }
    
    private void updateDebtUserList() {
    	
    	StringBuilder bld = new StringBuilder();
    	bld.append("You are creating a debt to:\n");
   		
   		TransactionComponent transaction = transactionM.getTransaction();
   		List<User> users = new ArrayList<>();
   		if (transaction.getDebtUser() != null)
   			users.add(transaction.getDebtUser());
   		
   		for (int i=0; i<users.size(); i++) { 
   			bld.append("\n" + users.get(i).getName());
        } 
   		debtUser.setText(bld.toString());
  		
  		if (transaction.hasDebt()) {
  			debtButton.setVisible(false);
  		} 	
    }
    
    private void printMsg (String message, AlertType at) {
    	
    	Window owner = submitButton.getScene().getWindow();
    	AlertHelper.showAlert(at, owner, "", message);
    }
}

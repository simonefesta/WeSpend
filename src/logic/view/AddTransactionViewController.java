/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import logic.DeskUtil;
import logic.Session;
import logic.entity.TransactionManager;
import logic.enumerations.Category;
import logic.enumerations.Month;
import logic.exceptions.DataInsertionException;
import logic.view.bean.AddTransactionBean;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;
 
public class AddTransactionViewController implements Initializable {
    @FXML
    private TextField valueField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField commentField;

    @FXML
    private Button submitButton;
    
    @FXML
    private Button debtButton;

    @FXML
    private Button shareButton;

    @FXML
    private ComboBox<String> dayComboBox;
    
    @FXML
    private ComboBox<String> monthComboBox;
    
    @FXML
    private TextField yearField;
    
    @FXML
    private ComboBox<String> hourComboBox;
    
    @FXML
    private ComboBox<String> minuteComboBox;
    
    TransactionManager manager;
    AddTransactionBean bean;
    
    ObservableList<String> typeList = FXCollections.observableArrayList();
    ObservableList<String> monthList = FXCollections.observableArrayList();
    ObservableList<String> dayList = FXCollections.observableArrayList();
    ObservableList<String> hourList = FXCollections.observableArrayList();
    ObservableList<String> minuteList = FXCollections.observableArrayList();
    
	@Override
   	public void initialize(URL location, ResourceBundle resources) {	
   
		manager = TransactionManager.getInstance();
   		bean = new AddTransactionBean();
   		DecimalFormat form = new DecimalFormat("00");
   		
   		for (int i=1; i<32; i++)
   			dayList.add(Integer.toString(i));
   		
   		for (int i=0; i<24; i++) {
   			hourList.add(form.format(i));
   		}
   		
   		for (int i=0; i<60; i++) {
   			minuteList.add(form.format(i));
   		}
   		
   		for (Category cat : Category.values()) {
   			typeList.add(cat.getName());
   		}
   		
   		for (Month mon : Month.values()) {
   			monthList.add(mon.getName());
   		}
   		
   		typeComboBox.setItems(typeList);
   		monthComboBox.setItems(monthList);
   		dayComboBox.setItems(dayList);
   		hourComboBox.setItems(hourList);
   		minuteComboBox.setItems(minuteList);
   		
   		LocalDateTime now = LocalDateTime.now();  
   		
		
   		yearField.setText(Integer.toString(now.getYear()));
   		monthComboBox.setValue(Month.valueOf(now.getMonth().name()).getName());
   		dayComboBox.setValue(Integer.toString(now.getDayOfMonth()));
   		hourComboBox.setValue(form.format(now.getHour()));
   		minuteComboBox.setValue(form.format(now.getMinute()));
   		typeComboBox.setValue(Category.OTHER.getName());
	}
	
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

    	if (!setupBean())
    		return;
    	bean.setupTransaction(manager);
    	if (!bean.commitTransaction(manager, Session.getUsername()))
    		printMsg ("Failure!", AlertType.ERROR);
    	else 
    		printMsg ("Transaction properly added!", AlertType.INFORMATION);

		MainView view = new MainView();
		view.switchScene(DeskUtil.MAINPAGE);
    }
	
    @FXML
    protected void handleDebtButtonAction(ActionEvent event) {
    	
    	if (!setupBean())
    		return;
    	bean.setupTransaction(manager);
    	
		MainView view = new MainView();
		view.switchScene("AddDebtForm.fxml");
    }
    
    @FXML
    protected void handleShareButtonAction(ActionEvent event) {
    	
    	if (!setupBean())
    		return;
    	bean.setupTransaction(manager);
    	
		MainView view = new MainView();
		view.switchScene("SharedExpenseForm.fxml");
    }
    
    @FXML
    protected void handleCancelButtonAction(ActionEvent event) {
    	
		MainView view = new MainView();
		view.switchScene(DeskUtil.MAINPAGE);
    } 
    
    private boolean setupBean () {
    	
		try {
			bean.setComment(commentField.getText());
			bean.setDate(yearField.getText(), monthComboBox.getValue(), 
					dayComboBox.getValue(), hourComboBox.getValue(), minuteComboBox.getValue());
			bean.setType(typeComboBox.getValue());
			bean.setValue(valueField.getText());
		} catch (DataInsertionException e) {
			printMsg (e.getMessage(), AlertType.ERROR);
			return false;
		}
		return true;
    }
    
    
    private void printMsg (String message, AlertType at) {
    	
    	Window owner = submitButton.getScene().getWindow();
    	AlertHelper.showAlert(at, owner, "", message);
    }
    
}

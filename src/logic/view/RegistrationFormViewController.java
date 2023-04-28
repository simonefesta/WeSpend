/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import logic.DeskUtil;
import logic.exceptions.DataInsertionException;
import logic.view.bean.RegistrationBean;

public class RegistrationFormViewController {

	@FXML
	private PasswordField passFieldOne;

	@FXML
	private PasswordField passFieldTwo;
	
	@FXML
	private TextField depositField;
	
	@FXML
	private TextField userField;
	
	@FXML
	private Button registrationButton;
	
	@FXML
	private Button goBack;
	 
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
    	
		MainView view = new MainView();
		view.switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
    }
    
    @FXML
    protected void handleRegistrationButtonAction(ActionEvent event) {
    	
    	RegistrationBean bean = new RegistrationBean();
    	try {
    		bean.setUsername(userField.getText());
    		bean.setPassword(passFieldOne.getText(), passFieldTwo.getText());
    		bean.setStartDeposit(depositField.getText());
		} catch (DataInsertionException e) {
			printMsg (e.getMessage(), AlertType.ERROR);
			return;
		}
    	try {
			if (!bean.register()) {
				printMsg ("Username already exists", AlertType.ERROR);
				return;
			}
			else {
				printMsg ("Account created!", AlertType.INFORMATION);
			}
		} catch (SQLException e) {
			printMsg ("Unable to connect", AlertType.ERROR);
		}
    	
		MainView view = new MainView();
		view.switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
    }
    
    @FXML
    protected void handleGoBack(ActionEvent event) {

		MainView view = new MainView();
		view.switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
    }
    
    private void printMsg (String message, AlertType at) {
    	
    	Window owner = registrationButton.getScene().getWindow();
    	AlertHelper.showAlert(at, owner, "", message);
    }
}

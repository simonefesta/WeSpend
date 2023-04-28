/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import logic.DeskUtil;
import logic.Session;
import logic.controller.FacebookLoginController;
import logic.dao.LoginInformation;
import logic.exceptions.DataInsertionException;
import logic.exceptions.LoginFacebookException;
import logic.view.bean.FacebookLoginBean;
import logic.view.bean.LoginBean;
import javafx.scene.control.Button;

public class LoginFormViewController implements Initializable {

	@FXML
	private PasswordField passwordField;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField startDepositField;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Button registrationButton;

	@FXML
	private Button facebookButton;

	@FXML
	private Button facebookRegButton;

	@FXML
	private Button goBackButton;
	
	@FXML
	private Text usernameLabel;
	
	@FXML
	private Text passwordLabel;
	
	
	String userId;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facebookRegButton.setVisible(false);
		goBackButton.setVisible(false);
		startDepositField.setVisible(false);
	}
	
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
    	 
    	LoginBean bean = new LoginBean();
    	try {
    		bean.setUsername(usernameField.getText());
    		bean.setPassword(passwordField.getText());
		} catch (DataInsertionException e) {
			printMsg (e.getMessage(), AlertType.ERROR);
			return;
		}
    	try {
    		LoginInformation loginInformation = bean.login();
			if (loginInformation == null) {
				printMsg ("Username and password not valid", AlertType.ERROR);
				return;
			}
			else {
				Session.setUsername(loginInformation.getUsername());
				Session.setStartDeposit(loginInformation.getStartDeposit());
				Session.setUserId(loginInformation.getUserId());
			}
		} catch (SQLException e) {
			printMsg ("Unable to connect", AlertType.ERROR);
			return;
		}
    	
		MainView view = new MainView();
		view.switchScene(DeskUtil.MAINPAGE);
    }
    
    @FXML
    protected void handleRegistrationButtonAction(ActionEvent event) {
    	
		MainView view = new MainView();
		view.switchSceneWithSizeValue("RegistrationForm.fxml", 600, 400);
    }
    
    @FXML 
    protected void handleLoginFacebookButtonAction(ActionEvent event) {
    
    	FacebookLoginController fbController = new FacebookLoginController();
		try {
			userId = fbController.login();
		} catch (LoginFacebookException e) {
			printMsg(e.getMessage(), AlertType.ERROR);
			return;
		}
				
		try {
			
			LoginInformation loginInformation = fbController.facebookLogin(userId);
			if (loginInformation != null) {
				Session.setUsername(loginInformation.getUsername());
				Session.setStartDeposit(loginInformation.getStartDeposit());
				Session.setUserId(loginInformation.getUserId());
				MainView view = new MainView();
				view.switchScene(DeskUtil.MAINPAGE);
			}
			else {
				facebookRegButton.setVisible(true);
				goBackButton.setVisible(true);
				startDepositField.setVisible(true);
				usernameLabel.setText("Choose your username");
				usernameLabel.setLayoutX(30);
				usernameLabel.setWrappingWidth(200);
				passwordLabel.setText("Start deposit");
				passwordLabel.setLayoutX(30);
				passwordLabel.setWrappingWidth(200);
				
				passwordField.setVisible(false);
				
				loginButton.setVisible(false);
				registrationButton.setVisible(false);
				facebookButton.setVisible(false);	
			}
		} catch (SQLException e) {
			printMsg("Unable to login with Facebook", AlertType.ERROR);
		}
    }
    
    @FXML
    protected void handlefacebookRegButtonButtonAction(ActionEvent event) {

    	FacebookLoginBean fbBean = new FacebookLoginBean();
    	try {
    		fbBean.setUsername(usernameField.getText());
    		fbBean.setStartDeposit(startDepositField.getText());
		} catch (DataInsertionException e) {
			printMsg (e.getMessage(), AlertType.ERROR);
			return;
		}
    	try {
			if (!fbBean.createAccount(userId)) {
				printMsg ("Username already exists", AlertType.ERROR);
			}
			else {
				printMsg ("Account created!", AlertType.INFORMATION);
				MainView view = new MainView();
				view.switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
			}
		} catch (SQLException e) {
			printMsg ("Unable to connect", AlertType.ERROR);
		} 
    }

    @FXML
    protected void handleGoBack(ActionEvent event) {
    	
		MainView view = new MainView();
		view.switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
    }
    
    private void printMsg (String message, AlertType at) {
    	
    	Window owner = loginButton.getScene().getWindow();
    	AlertHelper.showAlert(at, owner, "", message);
    }
}

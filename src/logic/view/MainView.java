/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.DeskUtil;
import javafx.application.Application;

public class MainView extends Application {
	 
	private static Stage primaryStage;
	
	public static void main(String[] args) {
		launch();
	}
		
	@Override
	public void start(Stage stage) throws Exception {
		
		updateStage(stage);
	
		primaryStage.setResizable(false);
		primaryStage.setTitle("weSpend");
		
		switchSceneWithSizeValue(DeskUtil.LOGINPAGE, 600, 400);
		primaryStage.show();
	}
	
	public void switchScene(String fxmlFile) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
			primaryStage.setScene(new Scene(root, 800, 500));
		} catch (Exception e) {
			printMsg ("Unable to load the page", AlertType.ERROR);
		}
	}
	
	public void switchSceneWithSizeValue(String fxmlFile, double width, double height) { 
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
			primaryStage.setScene(new Scene(root, width, height));
		} catch (Exception e) {
			printMsg ("Unable to load the page", AlertType.ERROR);
		}
	}
	
    private void printMsg (String message, AlertType at) {
    	
    	Window owner = primaryStage.getScene().getWindow();
    	AlertHelper.showAlert(at, owner, "", message);
    }
	private static void updateStage(Stage stage) {
		MainView.primaryStage = stage;
	}
}

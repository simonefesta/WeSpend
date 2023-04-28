/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertPrinter
{
	private AlertPrinter() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static void showAlert(AlertType type, String title, String header, String text)
	{
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);

		alert.showAndWait();
	}

}

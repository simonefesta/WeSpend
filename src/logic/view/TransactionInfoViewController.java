/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import logic.entity.TransactionManager;
import logic.entity.decorator.TransactionComponent;
import logic.enumerations.Month;

public class TransactionInfoViewController implements Initializable {

	@FXML
    private Label commentField;
	
	@FXML
    private Label valueField;
	
	@FXML
    private Label categoryField;
	
	@FXML
    private Label dateField;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		updateTransactionData();
	}

    private void updateTransactionData() {
    	
    	TransactionComponent tr = TransactionManager.getInstance().getTransaction();
   		valueField.setText("Import: " + tr.getValue() + "€");
   		commentField.setText("Comment: " + tr.getComment());
   		categoryField.setText("Category: " + tr.getCategory());
   		dateField.setText("Date: " + tr.getTime().getDayOfMonth() + " " +
   				Month.fromId(tr.getTime().getMonthValue()).getName() + " " +
   				tr.getTime().getYear() + " " +
   				tr.getTime().getHour() + ":" + tr.getTime().getMinute()
   				);
    }
}

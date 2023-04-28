/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import java.time.LocalDateTime;

import logic.entity.Transaction;
import logic.entity.TransactionManager;
import logic.entity.decorator.TransactionComponent;

public class AddTransactionController {

	public void setTransaction (TransactionManager manager, String value, String comment, String type, LocalDateTime date) {
		TransactionComponent transaction = new Transaction (value, comment, type, date);
		manager.setTransaction(transaction);
	}
	
	public boolean commit (TransactionManager manager, String username) {
		return manager.writeToDb(username);
	}	
}

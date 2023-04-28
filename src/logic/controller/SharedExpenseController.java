/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import java.util.List;

import javafx.collections.ObservableList;
import logic.entity.TransactionManager;
import logic.entity.User;
import logic.entity.decorator.TransactionComponent;
import logic.entity.decorator.decorations.SharedExpense;

public class SharedExpenseController {
	
	public boolean shareExpense (TransactionManager manager, List<User> users) {
		
		boolean expression;

		TransactionComponent transaction = manager.getTransaction();
		SharedExpense sharedExpense = new SharedExpense(transaction);
		if (sharedExpense.setSharedUsers(users)) {
			manager.setTransaction(sharedExpense); 
			expression = true;
		}
		else 
			expression = false;

		return expression;
	}
	
	public boolean commit(TransactionManager manager, String username) {
		return manager.writeToDb(username);
	}	
	
	public ObservableList<String> getFriendUsers(String username) {
		SelectFriendsController sfc = new SelectFriendsController();
		return sfc.getFriends(username);
	}
}

/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import javafx.collections.ObservableList;
import logic.entity.TransactionManager;
import logic.entity.User;
import logic.entity.decorator.TransactionComponent;
import logic.entity.decorator.decorations.Debt;

public class AddDebtController {
	
	public boolean convertToDebt (TransactionManager manager, User user) {
		
		boolean expression;
		
		TransactionComponent transaction = manager.getTransaction();
		Debt debt = new Debt(transaction);
		if (debt.setDebtUser(user)) {
			manager.setTransaction(debt);
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

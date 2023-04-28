/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity.decorator.decorations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.dao.TransactionDao;
import logic.entity.User;
import logic.entity.decorator.TransactionComponent;
import logic.entity.decorator.TransactionDecorator;

public class SharedExpense extends TransactionDecorator {
	
	private List<User> sharedUsers;
	
	public SharedExpense(TransactionComponent component) {
		super(component);
	}
	 
	public boolean setSharedUsers(List<User> sharedUsers) {
		 
		boolean expression = true;

		if (hasDebt()) {
			User debtUsers = getDebtUser();
			
			for (int i=0; i<sharedUsers.size(); i++) {
				if (sharedUsers.get(i).getName().equals(debtUsers.getName()))
					expression = false;
			}
			if (expression)
				setSharedUsersReal (sharedUsers);
		}
		else {
			setSharedUsersReal (sharedUsers);
		}
		
		return expression;
	}
	
	public void setSharedUsersReal(List<User> sharedUsers) {
		this.sharedUsers = sharedUsers;
	}
	
	public String getPerUserPrice() {
		
		BigDecimal numberOfPartecipants = new BigDecimal(sharedUsers.size() + 1);
		BigDecimal totalPrice = new BigDecimal(getValue());
		BigDecimal perUserPrice = totalPrice.divide(numberOfPartecipants, RoundingMode.UP);
		return perUserPrice.toString();
	}
	
	public List<User> getSharedUsers() {
		List<User> resultSuper = super.getSharedUsers();
		List<User> mergedUser = new ArrayList<>();
		
		mergedUser.addAll(resultSuper); 
		mergedUser.addAll(sharedUsers);
		
		return mergedUser;
	}
	
	public boolean writeToDb(String username) throws SQLException {
		boolean result = super.writeToDb(username);
		
		if (result) {
			
			TransactionDao transactionDao = new TransactionDao();
			if (!transactionDao.writeSharedExpense(this, username))
				return false;
		}

		return true;
	}
	
	public boolean hasSharedExpense() {
		return true;
	}
}

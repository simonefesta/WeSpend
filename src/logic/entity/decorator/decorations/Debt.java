/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity.decorator.decorations;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import logic.dao.TransactionDao;
import logic.entity.User;
import logic.entity.decorator.TransactionComponent;
import logic.entity.decorator.TransactionDecorator;

public class Debt extends TransactionDecorator {

	private User debtUser;
	
	public Debt(TransactionComponent component) {
		super(component);
	}

	public boolean setDebtUser(User debtUsers) {
		
		boolean expression = true;
		
		if (hasSharedExpense()) {
			List<User> sharedUsers = getSharedUsers();
			
			for (int i=0; i<sharedUsers.size(); i++) {
				if (sharedUsers.get(i).getName().equals(debtUsers.getName()))
					expression = false;
			}
			if (expression)
				setDebtUserReal (debtUsers);
		} 
		else {
			setDebtUserReal (debtUsers);
		}
		
		return expression;
	}

	private void setDebtUserReal(User debtUsers) {
		this.debtUser = debtUsers;
	}
	
	public String getDebtPrice() {

		BigDecimal totalPrice = new BigDecimal(getValue());
		BigDecimal negatePrice = totalPrice.negate();
		return negatePrice.toString();
	}
	
	public User getDebtUser() {		
		return debtUser;
	}
	
	
	public boolean writeToDb(String username) throws SQLException {
		boolean result = super.writeToDb(username);
		
		if (result) {
			
			TransactionDao transactionDao = new TransactionDao();
			if (!transactionDao.writeDebt(this, username))
				result = false;
		}

		return result;
	}
	
	public boolean hasDebt() {
		return true;
	}
}

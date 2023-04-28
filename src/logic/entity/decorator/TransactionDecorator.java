/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity.decorator;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import logic.entity.User;

public class TransactionDecorator implements TransactionComponent {
	
	private TransactionComponent component;

	public TransactionDecorator(TransactionComponent component){
		this.component = component;
	}
	
	public boolean writeToDb(String username) throws SQLException {
		return this.component.writeToDb(username);
	}
	
	public String getValue() {
		return this.component.getValue();
	}

	public String getComment() {
		return this.component.getComment();
	}

	public String getCategory() {
		return this.component.getCategory();
	}

	public LocalDateTime getTime() {
		return this.component.getTime();
	}

	public User getDebtUser() {
		return this.component.getDebtUser(); 
	}

	public List<User> getSharedUsers() {
		return this.component.getSharedUsers();
	}
	
	public boolean hasDebt() {
		return this.component.hasDebt();
	}
 
	public boolean hasSharedExpense() {
		return this.component.hasSharedExpense();
	}
}

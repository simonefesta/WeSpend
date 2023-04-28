/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity;

import java.sql.SQLException;

import logic.entity.decorator.TransactionComponent;

public class TransactionManager {

	private TransactionComponent transaction;
	private static TransactionManager manager = null;
 
	public static TransactionManager getInstance() {
		if (TransactionManager.manager == null)
			TransactionManager.manager = new TransactionManager();
		return manager; 
	}
  
	protected TransactionManager() {
		transaction = null;
	}
	
	public TransactionComponent getTransaction() {
		return transaction;
	}
	
	public void setTransaction(TransactionComponent transaction) {
		this.transaction = transaction;
	}
	
	public boolean writeToDb(String username) {
		
		boolean result;
		
		try {
			result = transaction.writeToDb(username);
		} catch (SQLException e) {
			result = false;
		} finally {
			delete();
		}
		
		return result;
	}
	
	public boolean hasDebt() {
		return transaction.hasDebt();
	}
	  
	public boolean hasSharedExpense() {
		return transaction.hasSharedExpense();
	}
	
	public void delete() {
		transaction = null;
	}
}
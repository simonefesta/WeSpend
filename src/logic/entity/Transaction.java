/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import logic.dao.TransactionDao;
import logic.entity.decorator.TransactionComponent;

public class Transaction implements TransactionComponent {

	private String value;
	private String comment;
	private String category;
	private LocalDateTime time;
	
	public Transaction () {}
	
	public Transaction (String value, String comment, String type, LocalDateTime time) {
		this.value = value;
		this.comment = comment;
		this.category = type;
		this.time = time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public boolean writeToDb(String username) throws SQLException {
		
		TransactionDao transactionDao = new TransactionDao();
		return transactionDao.writeSimpleTransaction(this, username);
	}
	
	public User getDebtUser() {
		return null;
	}
 
	public List<User> getSharedUsers() {
		return new ArrayList<>();
	}
	
	public boolean hasDebt() {
		return false;
	}

	public boolean hasSharedExpense() {
		return false;
	}
}

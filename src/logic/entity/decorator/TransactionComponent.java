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

public interface TransactionComponent {

	public abstract boolean writeToDb(String username) throws SQLException;
	public abstract String getValue();
	public abstract String getComment();
	public abstract String getCategory();
	public abstract LocalDateTime getTime();
	
	public abstract boolean hasDebt();
	public abstract boolean hasSharedExpense();
	
	public abstract User getDebtUser();
	public abstract List<User> getSharedUsers();
}

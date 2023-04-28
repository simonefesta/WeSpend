/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.controller;

import java.sql.SQLException;
import java.util.List;
import logic.Session;
import logic.ab.bean.HistoryBean;
import logic.ab.dao.HistoryDAO;
import logic.ab.entity.Goal;
import logic.ab.entity.History;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.exception.InsertException;
import logic.entity.Transaction;

public class ControllerHistory 
{
	public void addMoney(String money, int idGoal) throws InsertException, SQLException, ConnectionClosedException
	{
		Transaction transaction = new Transaction();
		transaction.setValue(Integer.toString((Integer.parseInt(money)*(-1))));
		transaction.setComment("Goal Id: "+Integer.toString(idGoal));
		transaction.setCategory("Goal");
		transaction.setTime(java.time.LocalDateTime.now());
		transaction.writeToDb(Session.getUsername());
		
		HistoryBean historyB = new HistoryBean();
		historyB.setId(idGoal);		
		historyB.setMoney(money);
		historyB.setDate(java.time.LocalDateTime.now());

		History history = new History(historyB.getMoney(),historyB.getDate(),historyB.getIdGoal());
		
		HistoryDAO.addNewHistory(history);
	}
	
	public List<History> transactionHistory(Goal goal) throws SQLException, ConnectionClosedException 
    {
		return  HistoryDAO.transactionHistory(goal);
    }
}

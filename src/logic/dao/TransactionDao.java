/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import logic.ab.dao.SingletonConnection;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;
import logic.entity.Transaction;
import logic.entity.decorator.decorations.Debt;
import logic.entity.decorator.decorations.SharedExpense;

public class TransactionDao {
	
	
	public boolean writeSimpleTransaction(Transaction transaction, String username) throws SQLException {
		
		TransactionInformation ti = new TransactionInformation();

		ti.setUsername(username);
		ti.setPrice(transaction.getValue());
		ti.setType(transaction.getCategory());
		ti.setComment(transaction.getComment());
		ti.setDate(transaction.getTime());
		ti.setState("2");
		ti.setIsDebt("0");
		ti.setIsShared("0");
		ti.setFromUser(username);
		
		return writeTransaction (ti, 1);
	}
	
	public boolean writeDebt(Debt debt, String username) throws SQLException {
		
		boolean result;
		TransactionInformation ti = new TransactionInformation();

		ti.setUsername(debt.getDebtUser().getName());
		ti.setPrice(debt.getDebtPrice());
		ti.setType(debt.getCategory());
		ti.setComment(debt.getComment());
		ti.setDate(debt.getTime());
		ti.setState("0");
		ti.setIsDebt("1");
		ti.setIsShared("0");
		ti.setToUser(username);
		ti.setFromUser(username);
		
		result = updateDebt(ti.getUsername(), username);
		if (result) {
			result = writeTransaction (ti, 0);
		}
		
		return result;
	}
	
	public boolean writeSharedExpense(SharedExpense shared, String username) throws SQLException {
		
		TransactionInformation ti = new TransactionInformation();

		ti.setPrice(shared.getPerUserPrice());
		ti.setType(shared.getCategory());
		ti.setComment(shared.getComment());
		ti.setDate(shared.getTime());
		ti.setState("0");
		ti.setIsDebt("0");
		ti.setIsShared("1");
		ti.setFromUser(username);
		
		if (!updateSharedExpense(ti.getPrice(), username))
			return false;
		
	
		for (int i=0; i<shared.getSharedUsers().size(); i++) {
			ti.setUsername(shared.getSharedUsers().get(i).getName());
			if (!writeTransaction(ti, 0))
				return false;
		}
	
		return true;
	}
	
	
	private boolean writeTransaction(TransactionInformation ti, int idSpan) throws SQLException {
		
		boolean expression = true;
		int affectedRow;
		int index = -1;
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e1) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlInsertTransaction());
				
		try {

			index = getLastTransactionIndex() + idSpan;
			
			ti.setIdTr(Integer.toString(index));
			ti.setDateStr(dbDate(ti.getDate()));

			affectedRow = Query.insertTransaction(pstmt, ti);

			if (affectedRow != 1) {
				clean(index);
				expression = false;
			}
		
		} catch(SQLException e) {
			clean(index);
			throw new SQLException();
		} finally {	
			pstmt.close();
		}
		
		return expression;
	}
	
	private int getLastTransactionIndex() throws SQLException {
		
		int value;
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlGetLastTransactionIndex());
		ResultSet rs = Query.getLastTransactionIndex(pstmt);
		
		if (!rs.first()) 
			value = 0;
		else 
			value = rs.getInt("idTr");
		
		pstmt.close();
		rs.close();
		
		return value;
	}
	
	private boolean updateDebt(String debtUser, String user) throws SQLException {
		
		boolean expression = true;
		int index = getLastTransactionIndex();
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlUpdateDebt());
		int affectedRow = Query.updateDebt(pstmt, debtUser, user, Integer.toString(index));

		if (affectedRow != 1) {
			expression = false;
			clean(index);
		}
		
		return expression;
	}
	
	private boolean updateSharedExpense(String price, String user) throws SQLException {
		
		boolean expression = true;
		int index = getLastTransactionIndex();
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlUpdateShared());
		int affectedRows = Query.updateShared(pstmt, price, user, Integer.toString(index));

		if (affectedRows != 1) {
			expression = false;
			clean(index);
		}
		
		return expression;	
	}
	
	private void clean(int index) throws SQLException {
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlDeleteTransaction());

		Query.deleteTransaction(pstmt, Integer.toString(index));
		
		pstmt.close();
	}
	
	private String dbDate(LocalDateTime date) {
		String newDate;
		DecimalFormat form = new DecimalFormat("00");
		
		newDate = Integer.toString(date.getYear()) + "-" + form.format(date.getMonthValue()) + "-" + form.format(date.getDayOfMonth()) + " "
				+ form.format(date.getHour()) + ":" + form.format(date.getMinute()) + ":00";
		return newDate;
	}
}

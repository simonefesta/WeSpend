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

import logic.ab.dao.SingletonConnection;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;

public class RegistrationDao {

	
	public boolean register(String user, String pass, double startDeposit) throws SQLException {
		
		boolean expression;
		int affectedRow;
		PreparedStatement pstmt = null;
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlCreateUser());
		affectedRow = Query.createUser(pstmt, user, pass, Double.toString(startDeposit));

		if (affectedRow == 0)
			expression = false;
		else
			expression = true;
		
		pstmt.close();
		
		return expression;
	}
	
	public boolean userExists(String user) throws SQLException {
		
		boolean expression;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlCheckUserExists());
		rs = Query.checkUserExists(pstmt, user);
		
		if (!rs.first())
			expression = false;
		else
			expression = true;

		pstmt.close();	
		rs.close();

		return expression;
	}
}

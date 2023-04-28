/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.dao;

import logic.ab.dao.SingletonConnection;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	public boolean credentialIsValid(String username, String password) throws SQLException 
	{
		
		boolean expression = false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlCheckCredential());
		rs = Query.checkCredential(pstmt, username, password);
		
		if (!rs.first())
			expression = false;
		else
			expression = true;

		pstmt.close();
		rs.close();

		return expression;
	}
	
	public LoginInformation retrieveUserInformation(String user) throws SQLException {
		
		LoginInformation loginInformation = new LoginInformation();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlGetUserInformation());
		rs = Query.getUserInformation(pstmt, user);
		
		if (rs.first()) {
			
			String username = rs.getString("username");
			int idUser = rs.getInt("idUtente");
			float deposit  = rs.getFloat("deposit");
			
			loginInformation.setUsername(username);
			loginInformation.setStartDeposit(deposit);
			loginInformation.setUserId(idUser);	
		}
		else
			loginInformation = null;
		
		pstmt.close();
		rs.close();

		return loginInformation;
	}
}

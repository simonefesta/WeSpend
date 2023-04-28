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

public class FacebookLoginDao {

	public boolean facebookRegister(String user, double startDeposit, String userId) throws SQLException {
		
		boolean expression;
		int affectedRow;
		PreparedStatement pstmt = null;
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlCreateFacebookUser());
		affectedRow = Query.createFacebookUser(pstmt, user, Double.toString(startDeposit), userId);

		if (affectedRow == 0)
			expression = false;
		else
			expression = true;
		
		pstmt.close();
		
		return expression;
	}
	
	public boolean userExistsByFbId(String userId) throws SQLException {
		
		boolean expression;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlCheckFacebookUserExists());
		rs = Query.checkFacebookUserExists(pstmt, userId);
		
		if (!rs.first())
			expression = false;
		else
			expression = true;

		pstmt.close();	
		rs.close();

		return expression;
	}
	

	public LoginInformation retrieveUserInformationById(String userId) throws SQLException {
		
		LoginInformation loginInformation = new LoginInformation();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		pstmt = conn.prepareStatement(Query.sqlGetFacebookUserInformation());
		rs = Query.getFacebookUserInformation(pstmt, userId);
		
		if (rs.first()) {
			
			String user = rs.getString("username");
			float startDeposit  = rs.getFloat("deposit");
			int id = rs.getInt("idUtente");
			
			loginInformation.setUsername(user);
			loginInformation.setStartDeposit(startDeposit);
			loginInformation.setUserId(id);	
		}
		else
			loginInformation = null;
		
		pstmt.close();
		rs.close();

		return loginInformation;
	}
}

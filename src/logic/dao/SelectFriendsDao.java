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
import java.util.ArrayList;
import java.util.List;

import logic.ab.dao.SingletonConnection;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;
import logic.entity.User;

public class SelectFriendsDao {

	public List<User> getFriends(String user) {
		
		List <User> usersList;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			usersList = new ArrayList<>();
			Connection conn = SingletonConnection.getConnectionInstance();
	    	pstmt = conn.prepareStatement(Query.sqlGetFriendUsers());
			rs = Query.getFriendUsers(pstmt, user);

			if (rs.first()) {
			
				do {
					String username = rs.getString("username");
					usersList.add(new User(username));
				}
				while (rs.next());
			}		
		} catch (SQLException | ConnectionClosedException e) {
			usersList = new ArrayList<>();
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) {
					usersList = new ArrayList<>();
				}
			}
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) {
					usersList = new ArrayList<>();
				}
			}
		}

		return usersList;
	}
}

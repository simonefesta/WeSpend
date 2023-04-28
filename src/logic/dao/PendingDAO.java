/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.dao;

import logic.ab.dao.SingletonConnection;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;
import logic.controller.PendingTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PendingDAO {
	
List<PendingTable> data;
	
	public PendingDAO() {
		data = new ArrayList<>();
	}
	
	public static List<PendingTable> getPendingList(String username) throws SQLException {

		List<PendingTable> data = new ArrayList<>();
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlDebt());
		ResultSet rs = Query.debt(pstmt,username);
		if (!rs.first())
			return data;
				
		do {    int id =rs.getInt("idTr");
				String date = rs.getString("date");
				String type  = rs.getString("type");
				float price =  rs.getFloat("price");
				String user = rs.getString("fromUser");
				String comment = rs.getString("comment");
	
				data.add(new PendingTable(id,date,type,price,user,comment));
				
				
			}
		while (rs.next());

		pstmt.close();         
		return data;
	}
	
	
	public static void acceptDebt(int id,String username) throws SQLException{

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlAccept());
		Query.accept(pstmt,id,username);
		PreparedStatement pstmt1 = conn.prepareStatement(Query.sqlUpdateStatus());
		Query.updateStatus(pstmt1,id);
	}
	
	public static void declineDebt(int id) throws SQLException{

		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new SQLException();
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlDecline());
		Query.decline(pstmt,id);
		
	}

	
}

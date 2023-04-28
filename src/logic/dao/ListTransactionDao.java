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
import logic.controller.Table;
import logic.exceptions.RetrieveDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ListTransactionDao {
	
	List<Table> data;
	
	public ListTransactionDao() {
		data = new ArrayList<>();
	}
	
	public static List<Table> getListTr(String username) throws RetrieveDataException, SQLException {

		List<Table> data = new ArrayList<>();
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new RetrieveDataException("Unable to load the transaction");
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlOrderDate());
		ResultSet rs = Query.orderDate(pstmt,username);
		if (!rs.first())
			return data;
			
		do {
				float price =  rs.getFloat("price");
				String type  = rs.getString("type");
				String comment = rs.getString("comment");
				String date = rs.getString("date");
				data.add(new Table(date,type,price,comment));
				
				
			}
		while (rs.next());

		pstmt.close();         
		return data;
	}
}

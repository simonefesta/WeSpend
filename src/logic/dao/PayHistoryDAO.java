

/*
 * Copyright 2020 Simone Festa
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
import logic.controller.PayHistory;
import logic.exceptions.RetrieveDataException;


public class PayHistoryDAO {
	
	List<PayHistory> data;
	public PayHistoryDAO() {
		data = new ArrayList<>();
	}
	

	public static List<PayHistory> getPay(String username) throws RetrieveDataException, SQLException {

		List<PayHistory> data = new ArrayList<>();
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new RetrieveDataException("Unable to show the budget");
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlPayment());
		ResultSet rs = Query.payment(pstmt,username);
		if (!rs.first())
			return data;
			
		do {
				float price =  rs.getFloat("SUM(price)");
				data.add(new PayHistory(price));
				
				
			}
		while (rs.next());

		pstmt.close();         
		return data;
	}
	

}

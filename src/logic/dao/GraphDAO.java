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
import logic.exceptions.RetrieveDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.PieChart.Data;

public class GraphDAO {

	List<Data> dataList;

	public GraphDAO() {
		dataList = new ArrayList<>();
	}
	
	public static List<Data> getExpenseByType(String username) throws SQLException, RetrieveDataException {
		
		List<Data> dataList = new ArrayList<>();
		
		Connection conn;
		try {
			conn = SingletonConnection.getConnectionInstance();
		} catch (ConnectionClosedException e) {
			throw new RetrieveDataException("Unable to show the graph");
		}
		PreparedStatement pstmt = conn.prepareStatement(Query.sqlGraphVista());
		ResultSet rs = Query.graphVista(pstmt,username);
		if (!rs.first())
			return dataList;
			
		do {
			if (rs.getFloat("SUM(price)") <0) {
				float price = - rs.getFloat("SUM(price)");
				String type  = rs.getString("type");
				dataList.add(new Data(type, price));
				
			}
			
			
			
		}
		while (rs.next());

		pstmt.close();         
		return dataList;
	}
}

/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.ab.entity.Goal;
import logic.ab.entity.History;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;

public class HistoryDAO 
{
	private static PreparedStatement pstmt = null;
	
	private HistoryDAO(){
	    throw new IllegalStateException("Utility class");
	  }
	
	public static List<History> transactionHistory(Goal goal) throws SQLException, ConnectionClosedException 
    {
		Connection conn =  SingletonConnection.getConnectionInstance();
		ArrayList<History> steps= new ArrayList<>();
        
        ResultSet rs =null;
        try
        {
        	pstmt = conn.prepareStatement(Query.sqlSelectHistoryOfGoal());
        	rs = Query.selectHistoryOfGoal(pstmt, goal.getId());
        }
        catch(SQLException e)
        {
        	testConnection(e);
		}
        
        
        if (rs == null || !rs.first())
            	return new ArrayList<>();
        rs.first();
        do
        {
        	History step = new History();
        	step.setMoney(rs.getInt("Soldi"));
        	step.setDate(rs.getTimestamp("Date").toLocalDateTime());
        	steps.add(step);
        }while(rs.next());
        
        rs.close();

		return steps;
    	
    }
	 public static void addNewHistory(History history) throws SQLException, ConnectionClosedException
	 {
		 Connection conn =  SingletonConnection.getConnectionInstance();
		 pstmt = conn.prepareStatement(Query.sqlAddHistory()); 	 
		 try
		 {
			 Query.addNewHistory(pstmt, history);
		 }
	     catch(SQLException e)
		 {
	    	 testConnection(e);
		 }
	 }
	 
	 private static void testConnection(SQLException e) throws SQLException, ConnectionClosedException
	 {
	    	if(SingletonConnection.testConnection()==1)
	    	{
	    		SingletonConnection.closeConnectionIstance();
	    		throw e;
	    	}
	    	else
	    		throw e;
	 }
}

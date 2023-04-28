/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import logic.ab.exception.ConnectionClosedException;
import logic.ab.query.Query;



public class SingletonConnection 
{
	private static final String USER = "root";
    private static final String PASS = "Abcd1234";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/wespend";
    private static Connection conn = null;
	
    private SingletonConnection(){
	    throw new IllegalStateException("Utility class");
	  }
	public static void closeConnectionIstance() throws SQLException
	{
		if (SingletonConnection.conn != null)
		{
			conn.close();
			conn=null;
		}
	}
	public static boolean connectionExist(){
		return (conn != null);
	}
	public static Connection getConnectionInstance() throws ConnectionClosedException 
	{
		if (SingletonConnection.conn == null)
		{
			 try 
		     {
				 conn = DriverManager.getConnection(DB_URL, USER, PASS);
		     }
			 catch (SQLException e) 
		     {
				 throw new ConnectionClosedException("Connection is lost. Reconnection tentative.");
		     }
		
		}
		return conn;
	}
	public static int testConnection()
	{
		
		try 
    	{
			Statement stmt = (Statement) conn.createStatement();
			Query.testConnection(stmt);
			return 0;
		} 
    	catch (SQLException e) 
    	{
			return 1;
		}

	}
}
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import logic.ab.bean.GoalBean;
import logic.ab.entity.Goal;
import logic.ab.entity.GoalOffline;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.exception.InsertException;
import logic.ab.exception.TypeException;
import logic.ab.factory.Factory;
import logic.ab.query.Query;

public class GoalDAO 
{
		private static PreparedStatement pstmt = null;
		
		private GoalDAO(){
		    throw new IllegalStateException("Utility class");
		  }
		
	    public static List<Goal> retreiveNewGoals() throws SQLException, InsertException, TypeException, ConnectionClosedException 
	    {
	    	List<Goal> listOfGoals = new ArrayList<>();
	    	ResultSet rs = null;
	    	Connection conn =  SingletonConnection.getConnectionInstance();
	    	
	    	try
		    {
	    		pstmt = conn.prepareStatement(Query.sqlSelectNewGoals());
	    		rs = Query.selectNewGoals(pstmt);
	    	}
	    	catch(SQLException e)
	    	{
	    		testConnection(e);
	    	}
	        if (rs == null || !rs.first())
	        {
	            	return new ArrayList<>();
	        }
	        
	        // riposizionamento del cursore
	        rs.first();
	        do
	        {
	        	// lettura delle colonne
	        	String nome = rs.getString("nome");
	        	String prezzo = Float.toString(rs.getFloat("Budget"));
	        	int idGoal = rs.getInt("idGoal");
	        	int tipo = rs.getInt("tipo");
	        	LocalDateTime date = rs.getTimestamp("data").toLocalDateTime();
	        	String link = rs.getString("link");
	        	String descrizione = rs.getString("descrizione");
	        	

	        	
	        	GoalBean b = new GoalBean();
	        	b.setId(idGoal);
	        	b.setNome(nome);
	        	b.setPrezzo(prezzo);
	        	b.setTipo(tipo);
	        	b.setData(date);
	        	b.setLink(link);
	        	b.setDescrizione(descrizione);
	        	
	        	

	        	Goal goal=null;
				try
				{
					goal = Factory.createGoal(b);
				} 
				catch (TypeException e) 
				{	
					b.setTipo(0);
					GoalDAO.updateTypeAfterError(b.getId());
					throw e;
				}
				if(goal==null)
					goal = new GoalOffline();
	        	
	        	goal.setId(b.getId());
	        	goal.setNome(b.getNome());
	        	goal.setPrezzo(b.getPrezzo());
	        	goal.setTipo(b.getTipo());
	        	goal.setDate(b.getDate());
	        	goal.setLink(b.getLink());
	        	goal.setDescrizione(b.getDescrizione());
	        	listOfGoals.add(goal);
	        	
	        	

	         }while(rs.next());
	            
	         rs.close();
	         
	         
	         return listOfGoals;
	     } 

	        
	    
	    public static void updatePrice(Goal goal) throws SQLException, ConnectionClosedException
	    {
	    	Connection conn =  SingletonConnection.getConnectionInstance();
	        
	        try
		    {
	        	pstmt = conn.prepareStatement(Query.sqlUpdatePrice()); 
	        	Query.updatePrice(pstmt, goal);
	    	}
	        catch(SQLException e)
	    	{
	    		testConnection(e);
	    	}

	    }
	    
	    public static void addNewGoal(Goal goal) throws SQLException, ConnectionClosedException
	    {
	    	Connection conn =  SingletonConnection.getConnectionInstance();
	        
	        try
		    {
	        	pstmt = conn.prepareStatement(Query.sqlAddNewGoal());
	        	Query.addNewGoal(pstmt, goal);
	    	}
	    	catch(SQLException e)
	    	{
	    		testConnection(e);
	    	}
	        

	    }

	    public static void deleteGoal(int id) throws SQLException, ConnectionClosedException
	    {
	    	Connection conn =  SingletonConnection.getConnectionInstance();
	        pstmt = conn.prepareStatement(Query.sqlDeleteGoal());

	        try
		    {
	        	Query.deleteGoal(pstmt, id);
	    	}
	        catch(SQLException e)
	    	{
	    		testConnection(e);
	    	}
	    }
	    
	    public static void updateTypeAfterError(int id) throws SQLException, ConnectionClosedException
	    {
	    	Connection conn =  SingletonConnection.getConnectionInstance();
	        pstmt = conn.prepareStatement(Query.sqlUpdateTypeAfterError());
	        try
		    {
	        	Query.updateTypeAfterError(pstmt, id);
	    	}
	        catch(SQLException e)
	    	{
	    		testConnection(e);
	    	}
	    }
	    
	    private static void testConnection(SQLException e) throws SQLException
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

/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import logic.ab.entity.Goal;
import logic.ab.entity.History;
import logic.ab.exception.ConnectionClosedException;
import logic.ab.exception.InsertException;
import logic.ab.exception.TypeException;

public class WebControllerGoal 
{
	private static final String BDERRORMESSAGEMAIN = "Error from DB operation";
	private WebControllerGoal() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<Goal> getGoalList(HttpSession session)
	{
		ControllerGoal controller = new ControllerGoal();
		try 
		{
			return getGoals(controller);
		} 
		catch (SQLException | InsertException | TypeException | ConnectionClosedException e) 
		{
			session.setAttribute("err", BDERRORMESSAGEMAIN);
		}
		return Collections.emptyList();
	}
	
	public static Goal getGoalById(int id, HttpSession session)
	{
		List<Goal> listOfGoals = getGoalList(session);
		for(int i=0; i<listOfGoals.size(); i++)
		{
			if(listOfGoals.get(i).getId()==id)
				return listOfGoals.get(i);
		}
		return null;
	}
	
	private static List<Goal> getGoals(ControllerGoal controller) throws SQLException, InsertException, TypeException, ConnectionClosedException
    {
    	List<Goal> goals = null;
		ArrayList<History> steps = null;
		ControllerHistory controllerHistory = new ControllerHistory();
		goals = controller.retreiveGoals();
		if(!goals.isEmpty())
		{
			for(int i=0; i<goals.size(); i++)
			{
				steps= (ArrayList<History>)controllerHistory.transactionHistory(goals.get(i));
				if(!steps.isEmpty())
					goals.get(i).addNewHistoryVector(steps);
				else
					goals.get(i).addNewHistoryVector(null);
			}
		}
		else
		{
			goals=null;
				return goals;
		}

		return goals;
    }
}

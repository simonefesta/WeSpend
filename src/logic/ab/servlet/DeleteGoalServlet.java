/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.ab.controller.ControllerGoal;

public class DeleteGoalServlet extends HttpServlet 
{

	private static final long serialVersionUID = 386194088565578314L;
	private static final String GOALPAGE = "GoalPage.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String idGoal = request.getParameter("goalIdToDelete");
		if(Integer.parseInt(idGoal)==-1)
		{
			response.sendRedirect(GOALPAGE);
			return;
		}
		HttpSession session = request.getSession();
		
		ControllerGoal controller = new ControllerGoal();
		try 
		{
			controller.deleteGoal(Integer.parseInt(idGoal));
		} 
		catch (NumberFormatException | SQLException e) 
		{
			
			session.setAttribute("err", 3);
			response.sendRedirect(GOALPAGE);
		}
		catch(Exception e)
		{
			session.setAttribute("err", 4);
    		response.sendRedirect(GOALPAGE);
    		return;
		}
		
    	response.sendRedirect(GOALPAGE);
    }
}

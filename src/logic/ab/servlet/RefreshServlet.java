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
import logic.ab.controller.WebControllerGoal;
import logic.ab.entity.Goal;
import logic.ab.exception.RefreshException;
import logic.ab.exception.UrlException;

public class RefreshServlet extends HttpServlet
{

	private static final long serialVersionUID = -2592596789964785324L;
	private static final String GOALID = "goalIdToRefresh";
	private static final String GOALPAGE = "GoalPage.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ControllerGoal controller = new ControllerGoal();
		Goal goal = null;
		HttpSession session = request.getSession();
		if( request.getParameter(GOALID)!=null)
		{
			String idGoal = request.getParameter(GOALID);
			if(Integer.parseInt(idGoal)==-1)
			{
				response.sendRedirect(GOALPAGE);
				return;
			}
			int id=Integer.parseInt(idGoal);
			if(Integer.parseInt(idGoal)==-1)
				response.sendRedirect(GOALPAGE);
			goal=WebControllerGoal.getGoalById(id,session);
		}
		else
		{
			response.sendRedirect(GOALPAGE);
			return;
		}
		

		try 
		{
			controller.refreshGoal(goal);
		} 
		catch (UrlException | SQLException | RefreshException e) 
		{
			session.setAttribute("err", 1);
	    	response.sendRedirect(GOALPAGE);
	    	return;
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

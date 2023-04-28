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


import logic.Session;
import logic.ab.controller.ControllerGoal;
import logic.ab.exception.InsertException;
import logic.ab.exception.TypeException;

public class GoalServlet extends HttpServlet 
{
	private static final long serialVersionUID = -9168256095337373732L;
	private static final String DESCRIPTION = "description";
	private static final String PRICE = "price";
	private static final String NAME = "name";
	private static final String LINK = "link";
	private static final String GOALPAGE = "GoalPage.jsp";
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			HttpSession session = request.getSession();
			Session.setUserId(((int)session.getAttribute("userId")));
	    	if (request.getParameter(NAME) == null || request.getParameter(PRICE) == null || request.getParameter(DESCRIPTION) == null)
	    		throw new IOException("Error on Insert ");
	    	ControllerGoal controller= new ControllerGoal();
	    	try 
	    	{
	    		if(request.getParameter(LINK) == null)
	    			controller.addGoal(request.getParameter(NAME), request.getParameter(PRICE), request.getParameter(DESCRIPTION));
	    		else
	    		{
	    			controller.addGoal(request.getParameter(NAME),request.getParameter(PRICE), request.getParameter(DESCRIPTION),request.getParameter(LINK),2);
	    		}
	    	}
	    	catch (NumberFormatException| InsertException | SQLException | TypeException e) 
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
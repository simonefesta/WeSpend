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
import logic.ab.exception.InsertException;

public class AddMoneyServlet extends HttpServlet 
{

	private static final long serialVersionUID = -8397783390350120146L;
	private static final String GOALPAGE = "GoalPage.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// reading the user input
		String idGoal = request.getParameter("idGoal");
		if(Integer.parseInt(idGoal)==-1)
		{
			response.sendRedirect(GOALPAGE);
			return;
		}
		String money = request.getParameter("price");
		HttpSession session = request.getSession();
		ControllerGoal controller = new ControllerGoal();
		try 
		{
			controller.addMoneyInHistory(money, Integer.parseInt(idGoal));
		} 
		catch (NumberFormatException | InsertException | SQLException e) {
			
			session.setAttribute("err", 2);
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

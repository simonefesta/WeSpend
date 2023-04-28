

/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Session;
import logic.controller.ListController;
import logic.controller.Table;

public class ListTransactionServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger( ListTransactionServlet.class.getName() );
	private static final long serialVersionUID = -8020375829938145692L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		HttpSession session =request.getSession();
	 
	    ListController listController = new ListController();
		List<Table> list =  new ArrayList<>();
		
		
		try {
			list = listController.getListTransaction(Session.getUsername());
		} catch (SQLException e) {
			LOGGER.info("Unable to load List Transaction");
		}
		
	    session.setAttribute("listtest",list);
	    response.sendRedirect("ViewTransactionForm.jsp");
	    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 doPost(request,response);
	
	}


}

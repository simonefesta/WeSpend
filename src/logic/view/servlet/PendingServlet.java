

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
import logic.controller.PendingController;
import logic.controller.PendingTable;

public class PendingServlet extends HttpServlet{
	
	private static final Logger LOGGER = Logger.getLogger( PendingServlet.class.getName() );
	private static final long serialVersionUID = 4317685881684044905L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session =request.getSession();
	
	PendingController pendingController = new PendingController();
	List<PendingTable> pend =  new ArrayList<>();
	
	try {
		pend = pendingController.getPending(Session.getUsername());
	    } catch (SQLException e) {
	    			LOGGER.info("Unable to load Pending Transaction");
	    					}
	
	session.setAttribute("pending",pend);
    response.sendRedirect("ViewPending.jsp");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 doPost(request,response);
	
	}
	
	
	
	
	}
	
	



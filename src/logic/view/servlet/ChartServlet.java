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

import javafx.scene.chart.PieChart.Data;
import logic.Session;
import logic.controller.GraphController;

public class ChartServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger( ChartServlet.class.getName() );

	private static final long serialVersionUID = 3776233339427374778L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session =request.getSession();
	   
		GraphController graphController = new GraphController();
		List<Data> list = new ArrayList<>();
		
		
		try {
			list = graphController.getPieChartData(Session.getUsername());
		} catch (SQLException e) {
			LOGGER.info("Unable to load PieChart");
	
			
		}
		
		
		
	    session.setAttribute("datatest",list);
	    response.sendRedirect("GoogleCharts.jsp");
	    }
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 doPost(request,response);
	
	}

}

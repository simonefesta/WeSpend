/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.WebCon;
import logic.entity.TransactionManager;
import logic.entity.TransactionManagerWeb;
import logic.exceptions.DataInsertionException;
import logic.view.bean.AddTransactionBean;

public class AddTransactionFormServlet extends HttpServlet {

	private static final long serialVersionUID = -1803727517420596638L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/AddTransactionForm.jsp");		

		String value = request.getParameter("value");
		String comment = request.getParameter("comment");
		String type = request.getParameter("type");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String hour = request.getParameter("hour");
		String minute = request.getParameter("minute");
		
		HttpSession session = request.getSession();
    	TransactionManager manager = TransactionManagerWeb.getSessionInstance(session);
		AddTransactionBean addTransactionBean = new AddTransactionBean();

		if (request.getParameter("submit") != null || 
			request.getParameter("debt") != null ||
			request.getParameter("share") != null) {
			
			try {
				
		    	addTransactionBean.setValue(value);
		    	addTransactionBean.setComment(comment);
		    	addTransactionBean.setType(type);
		    	addTransactionBean.setDate(year, month, day, hour, minute);
		    	
        		addTransactionBean.setupTransaction(manager);
        		
			} catch (DataInsertionException e) {
				session.setAttribute("err", e.getMessage());
				requestDispatcher.forward(request, response);
				return;
			}
			
			if (request.getParameter("submit") != null) {
				String stat;
				
    	  		if (!addTransactionBean.commitTransaction(manager, (String)session.getAttribute(WebCon.USERNAME))) 
    	  			stat="fail";
    	  		else 
    	  			stat="success";
    	  		
    	  		response.sendRedirect(WebCon.MAINPAGE+"?stat="+stat);
    	  		return;
			}
			if (request.getParameter("debt") != null) {
				response.sendRedirect("AddDebtForm.jsp");
			}
			if (request.getParameter("share") != null) {
				response.sendRedirect("SharedExpenseForm.jsp");
			}
		}
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

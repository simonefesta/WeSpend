/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.WebCon;
import logic.controller.SharedExpenseController;
import logic.entity.TransactionManagerWeb;
import logic.entity.User;
import logic.entity.decorator.TransactionComponent;

public class SharedExpenseFormServlet extends HttpServlet {

	private static final long serialVersionUID = 8169863162563148848L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		TransactionManagerWeb manager = TransactionManagerWeb.getSessionInstance(session);
		TransactionComponent transaction = manager.getTransaction();
		
		if (request.getParameter("select") != null && !transaction.hasSharedExpense()) {
			
			selectFriend(request, manager);
	  		response.sendRedirect("SharedExpenseForm.jsp");
		}

		if (request.getParameter("debt") != null) {
			response.sendRedirect("AddDebtForm.jsp");
		}

		if (request.getParameter("submit") != null) {			
			submitTransaction(request, response, manager);
		}
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	private void selectFriend(HttpServletRequest request, TransactionManagerWeb manager) {

		String errMsg = null;
		List<User> selectedUser = CreateTransactionHelper.readFriendList(request);
			
		try {		 		
		  		SharedExpenseController sharedExpenseController = new SharedExpenseController();
	        	if (!sharedExpenseController.shareExpense(manager, selectedUser))
	        		errMsg = "You have already selected one of these friends";
	
	 	} catch (Exception e){
			errMsg = "Please select a friend";
	 	}   
		
		HttpSession session = request.getSession();
		session.setAttribute("err", errMsg);
	}
	
	private void submitTransaction(HttpServletRequest request, HttpServletResponse response, TransactionManagerWeb manager) throws IOException {
		
		SharedExpenseController sharedExpenseController = new SharedExpenseController();
		HttpSession session = request.getSession();

		String stat;
		if (!sharedExpenseController.commit(manager, (String)session.getAttribute(WebCon.USERNAME))) 
			stat="fail";
		else 
			stat="success";
		
		response.sendRedirect(WebCon.MAINPAGE+"?stat="+stat);
	}
}
 

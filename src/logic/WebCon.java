/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic;

import javax.servlet.http.HttpSession;

import logic.entity.TransactionManagerWeb;

public class WebCon {

	public static final String MAINPAGE = "ListTransactionServlet";
	public static final String LOGINPAGE = "LoginForm.jsp"; 
	
	public static final String USERNAME = "username";
	public static final String USERID = "userId"; 
	public static final String STARTDEPOSIT = "startDeposit"; 

	
	private WebCon() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean checkTransaction(HttpSession session) {
		boolean expression;
		
		if (TransactionManagerWeb.getSessionInstance(session).getTransaction() == null)
			expression = false;
		else
			expression = true;
		
		return expression;
	}
	
	public static boolean checkLogin(HttpSession session) {
		boolean expression;
		
		if (session.getAttribute(USERNAME) == null)
			expression = false;
		else
			expression = true;
		
		return expression;
	}
}

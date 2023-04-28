/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.WebCon;
import logic.exceptions.DataInsertionException;
import logic.view.bean.RegistrationBean;

public class RegistrationFormServlet extends HttpServlet {

	private static final long serialVersionUID = -2611003447107321234L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  	String param = "";	  	
	  	String expectedRequest = "submit";
	  	String registrationPage = "RegistrationForm.jsp";
	  	
		if (request.getParameter(expectedRequest) == null) 
			response.sendRedirect(registrationPage);
		
		
		if (request.getParameter(expectedRequest).equals("Create account")) {

			RegistrationBean registrationBean = new RegistrationBean();
			param = "?response=";
			String username = request.getParameter("user");
			String passwordOne = request.getParameter("passOne");
			String passwordTwo = request.getParameter("passTwo");
			String startDeposit = request.getParameter("deposit");
			
	    	try {
	    		registrationBean.setUsername(username);
	    		registrationBean.setPassword(passwordOne, passwordTwo);
	    		registrationBean.setStartDeposit(startDeposit);
	 	
			} catch (DataInsertionException e) {
				param = param.concat(e.getMessage());
				response.sendRedirect(registrationPage+param);
				return;
			}
	 
    		try {
        		if (!registrationBean.register()) {
        			param = param.concat("Account already exist");
            	}
        		else {
        	  		response.sendRedirect(WebCon.LOGINPAGE+"?response=Registration complete!");
        	  		return;
        		}    			
    		} catch (SQLException e) {
    			param = param.concat("Unable to conncet");
    		}
		}	
		if (request.getParameter(expectedRequest).equals("Login")) {
			response.sendRedirect(WebCon.LOGINPAGE);
			return;
		}
		
		response.sendRedirect(registrationPage+param);
	}
}

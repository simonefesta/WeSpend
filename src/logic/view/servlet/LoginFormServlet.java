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
import javax.servlet.http.HttpSession;

import logic.Session;
import logic.WebCon;
import logic.dao.LoginInformation;
import logic.exceptions.DataInsertionException;
import logic.view.bean.LoginBean;

public class LoginFormServlet extends HttpServlet {

	private static final long serialVersionUID = -1803727517420596638L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  	String param = "";	  	
	  	String expectedRequest = "submit";
	  	
		if (request.getParameter(expectedRequest) == null) {
			response.sendRedirect(WebCon.LOGINPAGE);
		}
		
		if (request.getParameter(expectedRequest).equals("Login")) {
			
			LoginBean loginBean = new LoginBean();
			param = "?response=";
			
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			
	    	try {
	    		loginBean.setUsername(username);
	    		loginBean.setPassword(password);
	    	
			} catch (DataInsertionException e) {
				param = param.concat(e.getMessage());
				response.sendRedirect(WebCon.LOGINPAGE+param);
				return;
			}

    		try {
        		LoginInformation loginInformation = loginBean.login();
    			if (loginInformation == null) {
    				param = param.concat("Username and password not valid");
        		}
    			else {
    				doLogin(request, response,loginInformation);
    	  			return;
    			}
    		} catch (SQLException e) {
				param = param.concat("Unable to connect");
    		}
    		
		} 
		else if (request.getParameter(expectedRequest).equals("Create account")) {
	  		response.sendRedirect("RegistrationForm.jsp");
	  		return;
		}
		
		response.sendRedirect(WebCon.LOGINPAGE+param);
	}	
	
	private void doLogin(HttpServletRequest request, HttpServletResponse respons, LoginInformation loginInformation) throws IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute(WebCon.USERNAME, loginInformation.getUsername());
		session.setAttribute(WebCon.USERID, loginInformation.getUserId());
		session.setAttribute(WebCon.STARTDEPOSIT, loginInformation.getStartDeposit());
		
		// ## 
		Session.setUsername(loginInformation.getUsername());
		Session.setUserId(loginInformation.getUserId());
		Session.setStartDeposit(loginInformation.getStartDeposit());
		// ##
		
		respons.sendRedirect(WebCon.MAINPAGE);
	}
}

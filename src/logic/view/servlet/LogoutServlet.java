/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.WebCon;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -1127600038613190854L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(WebCon.LOGINPAGE);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

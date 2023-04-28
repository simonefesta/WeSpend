/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import java.sql.SQLException;

import logic.dao.RegistrationDao;

public class RegistrationController {
	
	private RegistrationDao registrationDao;
	
	
	public RegistrationController() {
		registrationDao = new RegistrationDao();
	}
	
	
	public boolean register(String user, String pass, double startDeposit) throws SQLException {

		boolean expression;
		if (!registrationDao.userExists(user))
			expression = registrationDao.register(user, pass, startDeposit);
		else 
			expression = false;
			
		return expression;
	}
}

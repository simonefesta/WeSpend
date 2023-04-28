/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;

import java.sql.SQLException;

import logic.dao.LoginDao;
import logic.dao.LoginInformation;

public class LoginController {

	private LoginDao loginDao;
	
	
	public LoginController() {
		loginDao = new LoginDao();
	}
		
	public LoginInformation login(String user, String pass) throws SQLException {
		
		LoginInformation loginInformation = null;
		
		if (loginDao.credentialIsValid(user, pass)) {
			loginInformation = loginDao.retrieveUserInformation(user);
		}
		return loginInformation;
	}
	
}

/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.bean;

import java.sql.SQLException;

import logic.controller.LoginController;
import logic.dao.LoginInformation;
import logic.exceptions.DataInsertionException;
import logic.exceptions.DataInsertionTrigger;

public class LoginBean {
	
	private String username;
	private String password;
	
	private DataInsertionTrigger trigger;
	private int minLen;
	private int maxLen;
	
	public LoginBean() {
		trigger = new DataInsertionTrigger();
		minLen = 4;
		maxLen = 18;
	}
	
	private void validUsername(String username) throws DataInsertionException {
		if (username.isEmpty())
			trigger.throwDataInsertionException("Please insert your username");

		if (username.length() < minLen || username.length() > maxLen)
			trigger.throwDataInsertionException("Username must be in range (" + minLen + ", " + maxLen + ")");
		
		for (int i=0; i<UtilityBean.getNotValid().length; i++) {
			if (username.contains(UtilityBean.getNotValid()[i])) {
				trigger.throwDataInsertionException("Please do not use \"" + UtilityBean.getNotValid()[i] + "\" in your username");
			}
		}
	}
	
	private void validPassword(String password) throws DataInsertionException {
		if (password.isEmpty())
			trigger.throwDataInsertionException("Please insert your password");

		if (password.length() < minLen || password.length() > maxLen)
			trigger.throwDataInsertionException("Password must be in range (" + minLen + ", " + maxLen + ")");
		
		for (int i=0; i<UtilityBean.getNotValid().length; i++) {
			if (password.contains(UtilityBean.getNotValid()[i])) {
				trigger.throwDataInsertionException("Please do not use \"" + UtilityBean.getNotValid()[i] + "\" in your password");
			}
		}
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) throws DataInsertionException {
		validUsername(username);
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws DataInsertionException {
		validPassword(password);
		this.password = password;
	}
	
	public LoginInformation login() throws SQLException {
		LoginController loginController = new LoginController();
		return loginController.login(username, password);
	}
}

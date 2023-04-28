/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.bean;

import java.sql.SQLException;

import logic.controller.RegistrationController;
import logic.exceptions.DataInsertionException;
import logic.exceptions.DataInsertionTrigger;

public class RegistrationBean {
	
	private String username;
	private String password;
	private double startDeposit;
	
	private DataInsertionTrigger trigger;
	private double startDepositTemp;
	
	private double maxDeposit;
	
	public RegistrationBean() {
		trigger = new DataInsertionTrigger();
		maxDeposit = 100000000;
	}
 
	private void validPasswords(String psw1, String psw2) throws DataInsertionException {
		if (!psw1.equals(psw2))
			trigger.throwDataInsertionException("Passwords must be equal");
	}
	
	public void validDeposit(String startDeposit) throws DataInsertionException {
		if (startDeposit.isEmpty())
			trigger.throwDataInsertionException("Please fill 'Deposit' field");
			
		try {
			startDepositTemp = Double.parseDouble(startDeposit);
		}
		catch (NumberFormatException e) {
			trigger.throwDataInsertionException("'Deposit' is not a number"); 
		}
		
		if (startDepositTemp < 0)
			trigger.throwDataInsertionException("Start with at least 0€"); 

		if (startDepositTemp > maxDeposit)
			trigger.throwDataInsertionException("You have too much money, try to start with a little bit less!"); 

	}
	
	public double getStartDeposit() {
		return startDeposit;
	}

	public void setStartDeposit(String startDeposit) throws DataInsertionException {
		validDeposit(startDeposit);
		this.startDeposit = startDepositTemp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwordOne, String passwordTwo) throws DataInsertionException {
		LoginBean loginBean = new LoginBean();
		loginBean.setPassword(passwordOne);
		loginBean.setPassword(passwordTwo);
		validPasswords(passwordOne, passwordTwo);
		this.password = passwordOne;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws DataInsertionException {
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		this.username = username;
	}
	
	public boolean register() throws SQLException {
		RegistrationController registrationController = new RegistrationController();
		return registrationController.register(username, password, startDeposit);
	}
}

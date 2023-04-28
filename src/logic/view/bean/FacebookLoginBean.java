/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.bean;

import java.sql.SQLException;

import logic.controller.FacebookLoginController;
import logic.exceptions.DataInsertionException;

public class FacebookLoginBean {

	private String username;
	private double startDeposit;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) throws DataInsertionException {
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		this.username = username;
	}

	public double getStartDeposit() {
		return this.startDeposit;
	}
	
	public void setStartDeposit(String startDeposit) throws DataInsertionException {
		RegistrationBean registrationBean = new RegistrationBean();
		registrationBean.validDeposit(startDeposit);
		this.startDeposit = Double.parseDouble(startDeposit);
	}

	public boolean createAccount(String userId) throws SQLException {
		FacebookLoginController fbLoginController = new FacebookLoginController();
		return fbLoginController.createAccount(username, startDeposit, userId);
	}


}

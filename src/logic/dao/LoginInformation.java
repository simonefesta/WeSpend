/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.dao;

public class LoginInformation {

	private String username;
	private int userId;
	private double startDeposit;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public double getStartDeposit() {
		return startDeposit;
	}
	
	public void setStartDeposit(double startDeposit) {
		this.startDeposit = startDeposit;
	}
}

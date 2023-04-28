/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity;

public class User {
	
	private String name;
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}

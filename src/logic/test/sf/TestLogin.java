/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.sf;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.exceptions.DataInsertionException;
import logic.view.bean.LoginBean;

public class TestLogin {
	
	@Test
	public void testPasswordLenghtMIN() {
		
		boolean actual =true;
		String password ="1b!";
		LoginBean bean = new LoginBean();
		try {
			bean.setPassword(password);
		}
		catch (DataInsertionException e) {
			actual = false;
		}
		
		assertEquals(false, actual);
		
		
		}
	
	@Test
	public void testUsernameLenghtMAX() {
		
		boolean actual =true;
		String username ="nickname12345username";
		LoginBean bean = new LoginBean();
		try {
			bean.setUsername(username);
		}
		catch (DataInsertionException e) {
			actual = false;
		}
		
		assertEquals(false, actual);
		
		
		}
	


}

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
import logic.view.bean.RegistrationBean;

public class TestRegistration {
	
	@Test
	public void validDeposit() {
		
		boolean actual =true;
		String deposit ="-120";
		RegistrationBean bean = new RegistrationBean();
		try {
			bean.validDeposit(deposit);
		}
		catch (DataInsertionException e) {
			actual = false;
		}
		
		assertEquals(false, actual);
		
		
		}
	
	@Test
	public void testValidPassword() {
		
		boolean actual =true;
		String pass1 ="password1";
		String pass2 ="password2";
		RegistrationBean bean = new RegistrationBean();
		try {
			bean.setPassword(pass1,pass2);
		}
		catch (DataInsertionException e) {
			actual = false;
		}
		
		assertEquals(false, actual);
		
		
		}
	

}
/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.af;

import org.junit.Test;
import static org.junit.Assert.*;

import logic.exceptions.DataInsertionException;
import logic.view.bean.AddTransactionBean;

public class TestAddTransactionBean {

	@Test
    public void testSetValuePositive() {
    	
    	boolean check = true;
    	 
    	AddTransactionBean bean = new AddTransactionBean();
    	try {
			bean.setValue("100.45");
		} catch (DataInsertionException e) {
			check = false;
		}

    	assertEquals(true, check);
    }
}

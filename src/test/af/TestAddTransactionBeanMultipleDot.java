/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.af;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.exceptions.DataInsertionException;
import logic.view.bean.AddTransactionBean;

public class TestAddTransactionBeanMultipleDot {
	
	@Test
    public void testSetValueMultipleDot() {
    	
    	boolean check = false;
    	
    	AddTransactionBean bean = new AddTransactionBean();
    	try {
			bean.setValue("100.45.56");
		} catch (DataInsertionException e) {
			check = true;
		}

    	assertEquals(true, check);
    }
}

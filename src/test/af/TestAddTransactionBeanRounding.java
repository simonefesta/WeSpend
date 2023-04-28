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

public class TestAddTransactionBeanRounding {
	
    @Test
    public void testSetValueRounding() {
    	
    	AddTransactionBean bean = new AddTransactionBean();
    	String actual;
    	
    	try {
			bean.setValue("25.566");
			actual = bean.getValue();
		} catch (DataInsertionException e) {
			actual = "0.00"; 
		}

    	assertEquals("25.57", actual);
    }
}

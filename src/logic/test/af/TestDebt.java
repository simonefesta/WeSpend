/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.af;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.entity.Transaction;
import logic.entity.decorator.decorations.Debt;
import logic.entity.decorator.decorations.SharedExpense;

public class TestDebt {

    @Test
    public void testHasSharedExpense() {
    	
    	boolean actual;
    	
    	Transaction transaction = new Transaction();
    	SharedExpense sharedExpense = new SharedExpense(transaction);
    	Debt debt = new Debt(sharedExpense);
    	
    	actual = debt.hasSharedExpense();
    	
    	assertEquals(true, actual);
    }
}

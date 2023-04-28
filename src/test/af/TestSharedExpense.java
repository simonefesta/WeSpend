/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package test.af;

import org.junit.Test;

import logic.entity.Transaction;
import logic.entity.User;
import logic.entity.decorator.decorations.Debt;
import logic.entity.decorator.decorations.SharedExpense;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TestSharedExpense {

    @Test
    public void testSetUserCorrect() {
    	
    	boolean actual;
    	String user1 = "Test";
    	String user2 = "Test1";
    	
    	Transaction transaction = new Transaction();
    	Debt debt = new Debt(transaction);
    	debt.setDebtUser(new User(user1));
    	
    	SharedExpense sharedExpense = new SharedExpense(debt);
    	 
    	List<User> sharedUsers = new ArrayList<>();
    	sharedUsers.add(new User(user2));
    	actual = sharedExpense.setSharedUsers(sharedUsers);
    	
    	assertEquals(true, actual);
    }
}

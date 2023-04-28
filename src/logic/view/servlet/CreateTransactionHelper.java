/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.entity.User;

public class CreateTransactionHelper {

	private CreateTransactionHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<User> readFriendList(HttpServletRequest request) {
		
		List<User> selectedUser = new ArrayList<>();
		String[] listItems = request.getParameterValues("friends");
		
		if (listItems.length > 0) {
			for (int i=0; i<listItems.length; i++) 
    			selectedUser.add(new User(listItems[i]));
    	}
		return selectedUser;
	}
}

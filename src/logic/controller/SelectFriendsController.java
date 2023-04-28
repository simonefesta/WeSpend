/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.dao.SelectFriendsDao;
import logic.entity.User;

public class SelectFriendsController {
	
	SelectFriendsDao selectFriendsDao;
	
	public SelectFriendsController() {
		selectFriendsDao = new SelectFriendsDao();
	}
	 
	public ObservableList<String> getFriends(String username) {

		ObservableList<String> friends = FXCollections.observableArrayList();
		List<User> userList = selectFriendsDao.getFriends(username);
		for (int i=0; i<userList.size(); i++) {
			friends.add(userList.get(i).getName());
   		}
	
		return friends;
	}
}

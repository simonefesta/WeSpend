/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;
import java.sql.SQLException;
import java.util.List;

import logic.dao.ListTransactionDao;
import logic.exceptions.RetrieveDataException;

public class ListController {
	
	ListTransactionDao listdao;
	
	public ListController() {
		
		listdao = new ListTransactionDao();
		}
	public List<Table> getListTransaction(String username) throws RetrieveDataException, SQLException {
		return ListTransactionDao.getListTr(username);
}

}

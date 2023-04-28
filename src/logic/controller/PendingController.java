

/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;
import java.sql.SQLException;
import java.util.List;

import logic.dao.PendingDAO;

public class PendingController {
	PendingDAO pendingdao;
	
	public PendingController() {
		pendingdao = new PendingDAO();
		
	}
public List<PendingTable> getPending(String username) throws SQLException{
	return PendingDAO.getPendingList(username);
}


public static void acceptDebt(int id, String username) throws SQLException{
		PendingDAO.acceptDebt(id,username);
	
}

public static void declineDebt(int id) throws SQLException{
		PendingDAO.declineDebt(id);

}




}

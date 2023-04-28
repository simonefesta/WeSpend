/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.entity;

import javax.servlet.http.HttpSession;

public class TransactionManagerWeb extends TransactionManager {

	private static final String SESSIONID = "TransactionManager";
	
	public static TransactionManagerWeb getSessionInstance(HttpSession session) {
		TransactionManagerWeb trm;
		
		trm = (TransactionManagerWeb) session.getAttribute(SESSIONID);
		if (trm == null) {
			trm = new TransactionManagerWeb();
			session.setAttribute(SESSIONID, trm);
		}		
		
		return trm;
	}
}

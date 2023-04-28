/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.bean;

public class UtilityBean {
	
	private static final String[] NOTVALID = new String[]{" ", "|", "\"", "|", "="};
	private static final String[] NOTVALIDTR = new String[]{"|", "\"", "|", "="};
	
	private UtilityBean() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String[] getNotValid() {
		return NOTVALID;
	}
	
	public static String[] getNotValidTr() {
		return NOTVALIDTR;
	}
}

/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.exceptions;

public class DataInsertionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataInsertionException (String message) {
		super("Wrong data formatting : " + message);
	}
	
}

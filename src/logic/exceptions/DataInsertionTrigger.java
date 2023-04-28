/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.exceptions;

public class DataInsertionTrigger {

	public void throwDataInsertionException (String message) throws DataInsertionException {
		throw new DataInsertionException (message);
	}
}

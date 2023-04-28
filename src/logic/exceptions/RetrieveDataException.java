/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.exceptions;

import java.sql.SQLException;

public class RetrieveDataException extends SQLException {

	private static final long serialVersionUID = 1L;

	public RetrieveDataException (String message) {
		super(message);
	}
}

/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.exception;

public class ConnectionClosedException  extends Exception 
{
	private static final long serialVersionUID = 1L;

	public ConnectionClosedException (String message)
	{
		super("Error: " + message);
	}

}

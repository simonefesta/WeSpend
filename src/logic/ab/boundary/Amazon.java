/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.boundary;

import logic.ab.exception.RefreshException;

public class Amazon 
{
	private Amazon(){
	    throw new IllegalStateException("Utility class");
	  }
	
	public static String getHtml(String url) throws RefreshException
	{
		throw new RefreshException("Amazon refresh in development");
	}
	
}

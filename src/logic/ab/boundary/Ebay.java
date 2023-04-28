/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.boundary;

import java.io.IOException;

import logic.ab.controller.RequestHTTP;

public class Ebay 
{
	private Ebay(){
	    throw new IllegalStateException("Utility class");
	  }
	
	public static String getHtml(String url) throws IOException
	{
		return RequestHTTP.getHTML(url);
	}
}

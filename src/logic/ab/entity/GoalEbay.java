/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.entity;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import logic.ab.boundary.Ebay;
import logic.ab.exception.UrlException;

public class GoalEbay extends GoalStandard implements Goal 
{
	
	public void refresh() throws UrlException
	{
		String html = null;
		try 
		{
			html=Ebay.getHtml(this.getLink());
		} 
		catch (Exception e) 
		{
			throw new UrlException("Url about that goal is invalid");
		}
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		Element tds = doc.getElementById("prcIsum");
		String content = tds.attr("content");
		this.setPrezzo(content);
	}

    
}

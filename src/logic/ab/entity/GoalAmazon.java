/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.entity;

import logic.ab.boundary.Amazon;
import logic.ab.exception.RefreshException;

public class GoalAmazon extends GoalStandard implements Goal 
{
		
	public void refresh() throws RefreshException
	{
		Amazon.getHtml(this.getLink());
	}


}

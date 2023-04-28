/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.entity;

import logic.ab.exception.RefreshException;

public class GoalOffline extends GoalStandard implements Goal 
{
		@Override
		public void refresh() throws RefreshException 
		{
			throw new RefreshException("This goal is offline");
		}
}

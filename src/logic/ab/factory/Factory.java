/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.factory;

import logic.ab.bean.GoalBean;
import logic.ab.entity.Goal;
import logic.ab.entity.GoalAmazon;
import logic.ab.entity.GoalEbay;
import logic.ab.entity.GoalOffline;
import logic.ab.exception.TypeException;

public class Factory 
{
	private Factory(){
	    throw new IllegalStateException("Utility class");
	  }
	
	public static Goal createGoal(GoalBean goal) throws TypeException
	{
		int type = goal.getTipo();
		if(type==0)
		{
			return new GoalOffline();
		}
		else if(type==1)
		{
			return new GoalAmazon();
		}
		else if(type==2)
		{
			return new GoalEbay();
		}
		else
		{
			throw new TypeException("Goal type error.", goal);
		}
		
	}
	
}

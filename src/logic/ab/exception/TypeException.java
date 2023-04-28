/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.exception;

import logic.ab.bean.GoalBean;

public class TypeException extends Exception 
{

	private static final long serialVersionUID = 1L;
	private final GoalBean goal;
	public TypeException (String message, GoalBean goal)
	{
		super("Error: " + message);
		this.goal=goal;
	}
	public GoalBean getGoal()
	{
		return this.goal;
	}
}

/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */
package logic.ab.entity;

import java.time.LocalDateTime;

public class History 
{
	private int money;
	private LocalDateTime date;
	private int idGoal;
	
	
	public History() {}
	
	public History(String money,LocalDateTime date, String idGoal)
	{

		this.money=Integer.parseInt(money);
		
		this.date=date;
		
		this.idGoal=Integer.parseInt(idGoal);
		
	}
	
	public int getMoney()
	{
		return this.money;
	}
	public LocalDateTime getDate()
	{
		return this.date;
	}
	public int getIdGoal()
	{
		return this.idGoal;
	}
	
	public int setMoney(int money)
	{
		this.money=money;
		return 0;
	}
	
	public int setDate(LocalDateTime date)
	{
		this.date=date;
		return 0;
	}
	public int setIdGoal(int idGoal)
	{
		this.idGoal=idGoal;
		return 0;
	}
	
}

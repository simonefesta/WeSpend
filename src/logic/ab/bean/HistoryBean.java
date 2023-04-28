/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */
package logic.ab.bean;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.ab.exception.InsertException;

public class HistoryBean 
{
		private String money;
		private String idGoal;
		private LocalDateTime date;
		
		
		public String getMoney()
		{
			return this.money;
		}
		
		public String getIdGoal()
		{
			return this.idGoal;
		}

		public LocalDateTime getDate()
		{
			return this.date;
		}
		
		
		public void setMoney(String money) throws InsertException
		{
			checkMoney(money);
			this.money=money;
		}
		public void setId(String idGoal)
		{
			this.idGoal=idGoal;
		}
		public void setDate(LocalDateTime date)
		{
			this.date=date;
		}
		
		public void setMoney(int money)
		{
			this.money=Integer.toString(money);
		}
		public void setId(int idGoal)
		{
			this.idGoal=Integer.toString(idGoal);
		}
		
		
		private void checkMoney(String money) throws InsertException
		{
			if(money.length()==0)
				throw new InsertException("Money field is empty. Fill it.");
		
			
			Pattern pattern = Pattern.compile("[0-9]+");
		    Matcher matcher = pattern.matcher(money);
			if(!matcher.find())
				throw new InsertException("Insert only numbers in price field.");
			

			if(money.charAt(0)=='0')
				throw new InsertException("Need more or less of 0 money");
		}




		
}

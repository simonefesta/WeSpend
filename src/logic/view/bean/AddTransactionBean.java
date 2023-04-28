/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.view.bean;

import logic.controller.AddTransactionController;
import logic.entity.TransactionManager;
import logic.enumerations.Category;
import logic.enumerations.Month;
import logic.exceptions.DataInsertionException;
import logic.exceptions.DataInsertionTrigger;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;

public class AddTransactionBean {

	private String value;
	private String comment;
	private String type;
	private LocalDateTime date;
	
	private DataInsertionTrigger trigger;
	private AddTransactionController addTransaction;
	
	private double tempValue;
	private LocalDateTime tempDate;
	
	private int commentLen;
	private int minYear;
	private int maxYear;

	 
	public AddTransactionBean () {
		trigger = new DataInsertionTrigger();
		commentLen = 255;
		minYear = 1000;
		maxYear = 9999;
	}
	
	private void verifyValue (String value) throws DataInsertionException {
		if (value.isEmpty())
			trigger.throwDataInsertionException("Please fill 'Value' field");
			
		if (value.compareTo("0") == 0)
			trigger.throwDataInsertionException("'Value' cannot be 0");
		
		try {
			tempValue = Double.parseDouble(value);
		}
		catch (NumberFormatException e) {
			trigger.throwDataInsertionException("'Value' is not a number"); 
		}
	}
	
	private void verifyComment (String comment) throws DataInsertionException {
		if (comment.isEmpty())
			trigger.throwDataInsertionException("Please fill 'Comment' field");
		if (comment.length() > commentLen)
			trigger.throwDataInsertionException("'Comment' cannot exceed " + commentLen + "characters");
		
		for (int i=0; i<UtilityBean.getNotValidTr().length; i++) {
			if (comment.contains(UtilityBean.getNotValidTr()[i])) {
				trigger.throwDataInsertionException("Please do not use \"" + UtilityBean.getNotValidTr()[i] + "\" in your comment");
			}
		}
	}
	
	private void verifyType (String type) throws DataInsertionException {
		if (type.isEmpty())
			trigger.throwDataInsertionException("Please fill 'Category' field");

		try {
			Category.fromName(type);
		} catch (IllegalArgumentException e) {
			trigger.throwDataInsertionException("Please choose a correct Catogory");
		}
	}
	
	private void verifyDate (String year, String month, String day, String hour, String minute) throws DataInsertionException {
		if (year.isEmpty() || month.isEmpty() || day.isEmpty() || hour.isEmpty() || minute.isEmpty())
			trigger.throwDataInsertionException("Please fill 'Date' fields");
		
		Month tempMonth = Month.JANUARY;
		int tempYear = 0;
		int tempDay = 0;
		int tempHour = 0;
		int tempMinute = 0;
		
		try {
			tempYear = Integer.parseInt(year);
			tempDay = Integer.parseInt(day);
			tempHour = Integer.parseInt(hour);
			tempMinute = Integer.parseInt(minute);
		} catch (NumberFormatException e) {
			trigger.throwDataInsertionException("Please insert numbers in 'Date' fields");
		}
		
		if (tempYear <= minYear || tempYear >= maxYear) {
			trigger.throwDataInsertionException("'Year' must be in range "
										+ "(" + minYear + ", " + maxYear + ")");
		}
		
		try {
			tempMonth = Month.fromName(month);
		} catch (Exception e) {
			trigger.throwDataInsertionException("Please choose an existing Month");
		}
		
		int dayOfMonth = 0;
		int monthId = 0;
		try {
			dayOfMonth = tempMonth.getDayOfMonth();
			monthId = tempMonth.getId();
		} catch(NullPointerException e) {
			trigger.throwDataInsertionException("Please fill 'Date' fields");
		}
		
		if (tempDay <= 0 || tempDay > dayOfMonth) {
			trigger.throwDataInsertionException("Please choose an existing Day");
		}
		if (tempHour < 0 || tempHour > 23 || tempMinute < 0 || tempMinute > 59) {
			trigger.throwDataInsertionException("Please set a valid hours");
		}
		
		tempDate = LocalDateTime.of(tempYear, monthId, 
				tempDay, tempHour, tempMinute);
	}

	public String getValue () {
		return value;
	}
	
	public void setValue (String value) throws DataInsertionException {
		verifyValue(value);
		DecimalFormatSymbols symb = new DecimalFormatSymbols();
		symb.setDecimalSeparator('.');
		DecimalFormat form = new DecimalFormat("0.00", symb);
		this.value = form.format(tempValue);
	}

	public String getComment () {
		return comment;
	}
	
	public void setComment (String comment) throws DataInsertionException {
		verifyComment(comment);
		this.comment = comment;
	} 

	public String getType () {
		return type;
	}
	
	public void setType (String type) throws DataInsertionException {
		verifyType(type);
		this.type = type;
	}

	public String getDate() {
		return date.toString();
	}

	public void setDate(String year, String month, String day, String hour, String minute) throws DataInsertionException {
		verifyDate(year, month, day, hour, minute);
		this.date = tempDate;
	}
	
	public void setupTransaction (TransactionManager manager) {
		addTransaction = new AddTransactionController(); 
		addTransaction.setTransaction(manager, value, comment, type, date);
	}
	
	public boolean commitTransaction (TransactionManager manager, String username) {
		return addTransaction.commit(manager, username);
	}
		
}

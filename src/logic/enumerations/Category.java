/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.enumerations;

public enum Category {
	
	OTHER ("Other"),
	TAXES ("Taxes"),
	ENTERTAINMENT ("Entertainment"),
	FOOD ("Food"),
	HOUSE ("House"),
	CAR ("Car");
	
	private String name;
	
	Category (String name) {
		this.name = name;
	}
	
	public String getName () {
		return this.name;
	}
	
	public static Category fromName(String name) {
        for (Category cat : Category.values()) {
            if (cat.getName().equals(name)) {
                return cat;
            }
        }
        throw new IllegalArgumentException();
    }
}

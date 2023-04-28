/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.enumerations;

public enum Month {

	JANUARY ("January", 1, 31),
	FEBRUARY ("February", 2, 29),
	MARCH ("March", 3, 31),
	APRIL ("April", 4, 30),
	MAY ("May", 5, 31),
	JUNE ("June", 6, 30),
	JULY ("July", 7, 31),
	AUGUST ("August", 8, 31),
	SEPTEMBER ("September", 9, 30),
	OCTOBER ("October", 10, 31),
	NOVEMBER ("November", 11, 30),
	DECEMBER ("December", 12, 31);
	
	private String name;
	private int id;
	private int dayOfMonth;
	
	Month (String name, int id, int dayOfMonth) {
		this.name = name;
		this.id = id;
		this.dayOfMonth = dayOfMonth;
	}
	
	public String getName () {
		return this.name;
	}

	public int getId () {
		return this.id;
	}
	
	public int getDayOfMonth () {
		return this.dayOfMonth;
	}
	
	public static Month fromName(String name) {
        for (Month mon : Month.values()) {
            if (mon.getName().equals(name)) {
                return mon;
            }
        }
        throw new IllegalArgumentException();
    }
	
	public static Month fromId(int id) {
        for (Month mon : Month.values()) {
            if (mon.getId() == id) {
                return mon;
            }
        }
        throw new IllegalArgumentException();
    }
}

/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;
import javafx.beans.property.SimpleFloatProperty;

public class PayHistory {
	private final SimpleFloatProperty rPrice;
	
	public PayHistory (float sPrice) {
		this.rPrice = new SimpleFloatProperty(sPrice);

}
	
	
	public Float getRPrice() {
		return rPrice.get();
		
	}
	
	public void setRPrice(Integer v) {
		rPrice.set(v);
		
	}
}

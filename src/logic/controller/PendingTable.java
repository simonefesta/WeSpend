

/*
 * Copyright 2020 Simone Festa
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;


	public class PendingTable {
		private final SimpleIntegerProperty rID;
		private final SimpleStringProperty rDate;
		private final SimpleStringProperty rType;
		private final SimpleFloatProperty rPrice;
		private final SimpleStringProperty rUser;
		private final SimpleStringProperty rComment;
		public PendingTable (int sID,String sDate,String sType , float sPrice, String sUser, String sComment) {
			this.rID = new SimpleIntegerProperty(sID);
			this.rDate = new SimpleStringProperty(sDate);
			this.rType = new SimpleStringProperty(sType);
			this.rPrice = new SimpleFloatProperty(sPrice);
			this.rUser = new SimpleStringProperty(sUser);
			this.rComment = new SimpleStringProperty(sComment);
		}
		
		//get
		
		public Integer getRID() {
			return rID.get();
			
		}
		public String getRDate() {
			return rDate.get();
			
		}
		
		
		public String getRType() {
			return rType.get();
			
		}
		
		public Float getRPrice() {
			return rPrice.get();
			
		}
		
		public String getRUser() {
			return rUser.get();
			
		}
		
		public String getRComment() {
			return rComment.get();
			
		}
		
		//set
		public void setRID(Integer v) {
			rID.set(v);
			
		}
		
		public void setRDate(String v) {
			rDate.set(v);
			
		}
		
		public void setRType(String v) {
			rType.set(v);
			
		}
			
		
		public void setRPrice(Integer v) {
			rPrice.set(v);
			
		}
			
		public void setRUser(String v) {
			rUser.set(v);
			
		}
		
		public void setRComment(String v) {
			rComment.set(v);
			
		}
		
	}



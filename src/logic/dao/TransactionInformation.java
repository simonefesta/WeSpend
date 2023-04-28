/*
 * Copyright 2020 Alessandro Fato
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.dao;

import java.time.LocalDateTime;

public class TransactionInformation {
		
	private String idTr; 
	private String username;
	private String price; 
	private String state; 
	private String isDebt;
	private String isShared;
	private String toUser; 
	private String fromUser; 
	private String type; 
	private String comment;	 
	private LocalDateTime date; 
	private String dateStr; 
	
	public String getIdTr() {
		return idTr;
	}
	public void setIdTr(String idTr) {
		this.idTr = idTr;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsDebt() {
		return isDebt;
	}
	public void setIsDebt(String isDebt) {
		this.isDebt = isDebt;
	}
	public String getIsShared() {
		return isShared;
	}
	public void setIsShared(String isShared) {
		this.isShared = isShared;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getDate() {
		return date; 
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}

/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.entity;

import java.time.LocalDateTime;
import java.util.List;

import logic.ab.exception.RefreshException;
import logic.ab.exception.UrlException;


public interface Goal 
{
	
	public void setId(int id);
	public void setNome(String nome);
	public void setPrezzo(float prezzo);
	public void setTipo(int tipo);
	public void setDate(LocalDateTime data);
	public void setId(String id);
	public void setPrezzo(String prezzo);
	public void setTipo(String tipo);
	public void setDescrizione(String descrizione);
	public void setLink(String link);
	public List<History> getHistory();
	public int getId();
	public String getNome();
	public String getDescrizione();
	public String getLink();
	public float getPrezzo();
	public int getTipo();
	public LocalDateTime getData();
	public void refresh() throws UrlException, RefreshException;
	public void addNewHistoryVector(List <History> step);
}
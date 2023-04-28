/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */

package logic.ab.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GoalStandard
{
	protected String nome;
    protected int id;
    protected float prezzo;
    protected int tipo;
    protected LocalDateTime data;
    protected String descrizione;
    protected String link;
    
    protected List<History> step;

	public void setId(int id) 
	{
		this.id=id;
	}
	public void setNome(String nome) 
	{
		this.nome=nome;
	}
	public void setPrezzo(float prezzo) 
	{
		this.prezzo=prezzo;
	}
	public void setTipo(int tipo) 
	{
		this.tipo=tipo;
	}
	public void setDate(LocalDateTime data) 
	{
		this.data=data;
	}
	public void setId(String id) 
	{
		this.id= Integer.parseInt(id);
	}

	public void setPrezzo(String prezzo) 
	{
		this.prezzo= Float.parseFloat(prezzo);
	}
	public void setTipo(String tipo) 
	{
		this.tipo= Integer.parseInt(tipo);
	}
	public void setDescrizione(String descrizione) 
	{
		this.descrizione=descrizione;
	}
	public void setLink(String link) 
	{
		this.link=link;
	}
	public List<History> getHistory()
	{
		return (ArrayList<History>) this.step;
	}
	public int getId() 
	{
		return this.id;
	}
	public String getNome() 
	{
		return this.nome;
	}
	public String getDescrizione() 
	{
		return this.descrizione;
	}
	public String getLink() 
	{
		return this.link;
	}
	public float getPrezzo() 
	{
		return this.prezzo;
	}
	public int getTipo() 
	{
		return this.tipo;
	}
	public LocalDateTime getData() 
	{
		return this.data;
	}
	public void addNewHistoryVector(List<History> step) 
	{
		this.step=step;
	}
}

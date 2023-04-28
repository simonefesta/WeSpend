/*
 * Copyright 2020 Adriano Brugnoni
 *
 * This file is a part of weSpend project developed for the course
 * ISPW (A.Y. 2019-2020) at Università di Tor Vergata in Rome
 */
package logic.ab.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.ab.entity.History;
import logic.ab.exception.InsertException;

public class GoalBean 
{
	
	private String nome;
    private int id;
    private float prezzo;
    private int tipo;
    private LocalDateTime data;
    private String descrizione;
    private String link;
	
    private ArrayList<History> step;
    
    public void setId(String id) 
	{
    	this.id=Integer.parseInt(id);
	}
	public void setNome(String nome) throws InsertException 
	{
		checkNome(nome);
		this.nome=nome;
	}
	public void setPrezzo(String prezzo) throws InsertException
	{
		checkPrezzo(prezzo);
		this.prezzo=Float.parseFloat(prezzo);
	}
	public void setTipo(String tipo) 
	{
		this.tipo=Integer.parseInt(tipo);
	}
	public void setData(LocalDateTime data) 
	{
		this.data=data;
	}
	public void addNewHistory(History step) 
	{
		this.step.add(step);
	}
	public void addNewHistoryVector(List<History> step) 
	{
		this.step=(ArrayList<History>) step;
	}
	
	
	
	
	
	public void setId(int id) 
	{
    	this.id=id;
	}
	public void setPrezzo(int prezzo) 
	{
		this.prezzo=prezzo;
	}
	public void setTipo(int tipo) 
	{
		this.tipo=tipo;
	}
	public void setDescrizione(String descrizione) throws InsertException 
	{
		checkDescrizione(descrizione);
		this.descrizione=descrizione;
	}
	public void setLink(String link) throws InsertException 
	{
		checkLink(link);
		this.link=link;
	}
	
	
	public int getId() 
	{
		return this.id;
	}
	public String getNome() 
	{
		return this.nome;
	}
	public float getPrezzo() 
	{
		return this.prezzo;
	}
	public int getTipo() 
	{
		return this.tipo;
	}

	public String getDescrizione()
	{
		return this.descrizione;
	}
	public String getLink()
	{
		return this.link;
	}
	public List<History> getHistory() 
	{
		return this.step;
	}
	public LocalDateTime getDate()
	{
		return this.data;
	}
	
	
	private void checkNome(String nome) throws InsertException
	{
		if(nome.length()==0)
			throw new InsertException("Name min length is 1");
		if(nome.length()>6)
			throw new InsertException("Name max length is 6");
		
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
	    Matcher matcher = pattern.matcher(nome);
		if(matcher.find())
			throw new InsertException("Special character are not valid in name field.");
		
			
			
	}
	
	private void checkPrezzo(String prezzo) throws InsertException
	{
		if(prezzo.length()==0)
			throw new InsertException("Price field is empty. Fill it.");
	
		
		Pattern pattern = Pattern.compile("[0-9]+");
	    Matcher matcher = pattern.matcher(prezzo);
		if(!matcher.find())
			throw new InsertException("Insert only numbers in price field.");
		
		
		if(prezzo.charAt(0)=='-')
			throw new InsertException("A goal need a positive price!");
		
		if(prezzo.charAt(0)=='0')
			throw new InsertException("A goal can't have 0 as target!");
		
			
	}
	
	private void checkLink(String link) throws InsertException
	{
		if((this.tipo!=0 && link==null) )
			throw new InsertException("Link min length is 1");
		if(link!=null && link.length()>5000)
			throw new InsertException("Link max length is 5000");

		
		
	}
	
	private void checkDescrizione(String descrizione) throws InsertException
	{
		
		if(descrizione.length()>150)
			throw new InsertException("Description max length is 150");
		
		
	}

}